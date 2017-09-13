package com.robert.beisaierdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by robert on 2017/8/19.
 */

public class BezierView extends View {
    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path mPath = new Path();

    Paint mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path mSrcPath = new Path();

    private void init() {
        Paint paint = mPaint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setColor(Color.BLUE);

        paint = mSrcPaint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setColor(Color.RED);

        mSrcPath.moveTo(0, 400);
        mSrcPath.cubicTo(300, 1000, 550, 200, 800, 600);

        new Thread() {
            @Override
            public void run() {
                initBezier();
            }
        }.start();


    }

    private void initBezier() {
        Path path = mPath;

        float[] xPoints = new float[]{0, 300, 550, 800,1050};
        float[] yPoints = new float[]{400, 1000, 200, 600,400};

        int fps = 10000;
        for (int i = 0; i <= fps; i++) {
            float progress = i / (float) fps;
            float x = calculateBezier(progress, xPoints);
            float y = calculateBezier(progress, yPoints);
            path.lineTo(x, y);
            postInvalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private float calculateBezier(float progress, float... points) {
        final int length = points.length;
        //i=5
        for (int i = length - 1; i > 0; i--) {
            //j=3
            for (int j = 0; j < i; j++) {
                points[j] = points[j] + (points[j + 1] - points[j]) * progress;
            }
        }
        return points[0];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mSrcPath, mSrcPaint);
        canvas.drawPath(mPath, mPaint);
    }
}
