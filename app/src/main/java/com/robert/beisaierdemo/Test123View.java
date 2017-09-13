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

public class Test123View extends View {
    public Test123View(Context context) {
        super(context);
        init();
    }

    public Test123View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Test123View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path mPath = new Path();

    private void init() {

        Paint paint = mPaint;
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(12);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        Path path = mPath;
        path.moveTo(100, 100);
        path.lineTo(300, 300);

        //二阶贝塞尔曲线
//        path.quadTo(500, 50, 700, 300);
        //二阶贝塞尔曲线,相对于之前的位置
        path.rQuadTo(200, -250, 400, 0);

        path.moveTo(300, 800);
        //三阶贝塞尔曲线
//        path.cubicTo(500, 400, 700, 1200, 900, 800);
        //三阶贝塞尔曲线，相对于之前的位置
        path.rCubicTo(200, -400, 400, 400, 600, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(mPath, mPaint);
        //二阶贝塞尔曲线点
        canvas.drawPoint(500, 50, mPaint);

        //三阶贝塞尔曲线点
        canvas.drawPoint(500, 400, mPaint);
        canvas.drawPoint(700, 1200, mPaint);


    }
}
