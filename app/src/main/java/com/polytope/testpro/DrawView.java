package com.polytope.testpro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: 雪豹高科
 * @time: 2021-12-14
 */
public class DrawView extends View {
    //屏幕的宽度
    private int view_width = 0;
    //屏幕的高度
    private int view_height = 0;
    //起始点的X坐标值
    private float preX;
    //起始点的y坐标值
    private float preY;
    //路径
    private Path path;
    //画笔
    public Paint paint = null;
    //定义一个内存中的图片，该图片将作为缓冲区
    Bitmap cacheBitmap = null;
    //定义cacheBitmap上的Canvas对象
    Canvas cacheCanvas = null;

    public DrawView(Context context, AttributeSet set) { //构造方法
        super(context, set);
        view_width = context.getResources().getDisplayMetrics().widthPixels; //获取屏幕的宽度
        view_height = context.getResources().getDisplayMetrics().heightPixels; //获取屏幕的高度
        System.out.println(view_width + "*" + view_height); //屏幕宽和高
        //创建一个与该View相同大小的缓存区
        cacheBitmap = Bitmap.createBitmap(view_width, view_height, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas(); //创建一个新的画布
        path = new Path(); //实例化路径
        cacheCanvas.setBitmap(cacheBitmap); //在cacheCanvas上绘制cacheBitmap
        paint = new Paint(); //实例化画笔
        paint.setColor(Color.BLACK); //设置默认的画笔颜色
        //设置画笔风格
        paint.setStyle(Paint.Style.STROKE); //设置填充方式为描边
        paint.setStrokeWidth(15); //设置默认笔触的宽度为1像素
        paint.setAntiAlias(true); //使用抗锯齿功能
    }

    @Override
    protected void onDraw(Canvas canvas) { //重写onDraw()方法
        super.onDraw(canvas);
        canvas.drawBitmap(cacheBitmap, 0, 0, null);//绘制cacheBitmap
        canvas.drawPath(path, paint); //绘制路径
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//获取触摸事件的发生位置
        float x = event.getX(); //获取x坐标
        float y = event.getY(); //获取y坐标
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //当手指按下时
                path.moveTo(x, y); //将绘图的起始点移到（x,y）坐标点的位置
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE: //根据触摸过程与位置绘制线条
                float dx = Math.abs(x - preX); //计算x值的移动距离
                float dy = Math.abs(y - preY); //计算y值的移动距离
                if (dx >= 5 || dy >= 5) { //判断是否在允许的范围内
                    path.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2); //贝塞尔曲线
                    preX = x;
                    preY = y;
                }
                break;
            case MotionEvent.ACTION_UP: //当手指抬起时
                cacheCanvas.drawPath(path, paint); //绘制路径
                path.reset(); //重置路径
                break;
        }
        invalidate();
        return true;
    }

    public void clear() {
        if (cacheCanvas != null) { //如果绘制路径不为空
            path.reset(); //重置路径
            cacheCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            invalidate(); //刷新
        }
    }
}
