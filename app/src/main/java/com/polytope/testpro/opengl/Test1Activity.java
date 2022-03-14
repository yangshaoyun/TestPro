package com.polytope.testpro.opengl;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.polytope.testpro.R;

/**
 * @author: 雪豹高科
 * @time: 2021-12-21
 */
public class Test1Activity extends AppCompatActivity {
    GlRender render = new GlRender();
    private float mPreviousX;
    private float mPreviousY;
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private final float TRACKBALL_SCALE_FACTOR = 36.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLImage.load(this.getResources());
        GLSurfaceView glView = new GLSurfaceView(this);

        glView.setRenderer(render);
        setContentView(glView);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        render.onKeyUp(keyCode, event);
        return false;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent e) {
        render.xrot += e.getX() * TRACKBALL_SCALE_FACTOR;
        render.yrot += e.getY() * TRACKBALL_SCALE_FACTOR;
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;
                render.xrot += dx * TOUCH_SCALE_FACTOR;
                render.yrot += dy * TOUCH_SCALE_FACTOR;
        }
        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
}

class GLImage {
    public static Bitmap mBitmap1;
    public static Bitmap mBitmap2;
    public static Bitmap mBitmap3;
    public static Bitmap mBitmap4;
    public static Bitmap mBitmap5;
    public static Bitmap mBitmap6;

    public static void load(Resources resources) {
        mBitmap1 = BitmapFactory.decodeResource(resources, R.drawable.hhh);
        mBitmap2 = BitmapFactory.decodeResource(resources, R.drawable.hhh);
        mBitmap3 = BitmapFactory.decodeResource(resources, R.drawable.hhh);
        mBitmap4 = BitmapFactory.decodeResource(resources, R.drawable.hhh);
        mBitmap5 = BitmapFactory.decodeResource(resources, R.drawable.hhh);
        mBitmap6 = BitmapFactory.decodeResource(resources, R.drawable.hhh);

    }

}
