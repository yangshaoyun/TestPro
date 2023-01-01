package com.polytope.testpro.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;

import com.polytope.testpro.R;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author: 雪豹高科
 * @time: 2021-12-20
 */
public class CubeRenderer implements GLSurfaceView.Renderer {
    //顶点坐标数据缓冲
    private FloatBuffer mVertexBuffer;
    private long startTime;
    //立方体顶点坐标
    private float box[] = {
            //前
            -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
            //后
            -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
            //左
            -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
            //右
            0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
            //上
            -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
            //下
            -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f, 0.5f, -0.5f, -0.5f
    };

    //立方体纹理坐标
    private float texCoords[] = {
            //前
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 1.0f, 1.0f,
            //后
            1.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 0.0f, 1.0f,
            //左
            1.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 0.0f, 1.0f,
            //右
            1.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 0.0f, 1.0f,
            //上
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 1.0f, 1.0f,
            //下
            1.0f, 0.0f, 1.0f, 1.0f,
            0.0f, 0.0f, 0.0f, 1.0f
    };

    private Context context; //定义上下文

    public CubeRenderer(Context context) {
        this.context = context;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //将顶点坐标转换为float类型的数据
        mVertexBuffer = Utility.createFloatBuffer(box);
//        mVertexBuffer = Utility.createFloatBuffer(texCoords);
        //设置窗体背景颜色
        gl.glClearColor(0.7f, 0.9f, 0.9f, 1.0f);
        //设置阴影平滑模式
        gl.glShadeModel(GL10.GL_SMOOTH);
        //设置深度缓存
        gl.glClearDepthf(1.0f);
        //启动深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //深度测试的类型
        gl.glDepthFunc(GL10.GL_LEQUAL);
        //告诉系统对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

        //开启纹理功能
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //加载纹理
        loadTexture(gl, context, R.drawable.hhh);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置场景大小
        gl.glViewport(0, 0, width, height);
        //设置投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //初始化单位矩阵
        gl.glLoadIdentity();
        //设置窗口比例和透视图
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 1f, 100.0f);
        //设置模型矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //重置模型矩阵
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓存和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //设置使用模型矩阵进行变换
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //重置模型矩阵
        gl.glLoadIdentity();
        //开启顶点坐标功能
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //设置顶点坐标
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        //当使用GL_MODELVIEW模式时，必须设置视点，也就是观察点
        GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        //旋转
        long elapsed = System.currentTimeMillis() - startTime; //计算逝去的时间
        gl.glRotatef(elapsed * (30f / 1000f), 0, 1, 0); //在y轴上旋转30度
        gl.glRotatef(elapsed * (15f / 1000f), 1, 0, 0); //在x轴上旋转15度
        //绘制FRONT和BACK两个面
        gl.glColor4f(1, 0, 0, 1);
        gl.glNormal3f(0, 0, 1);
        //绘制第一个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        gl.glColor4f(1, 0, 0.5f, 1);
        gl.glNormal3f(0, 0, -1);
        //绘制第二个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
        //绘制LEFT和RIGHT两个面
        gl.glColor4f(0, 1, 0, 1);
        gl.glNormal3f(-1, 0, 0);
        //绘制第三个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
        gl.glColor4f(0, 1, 0.5f, 1);
        gl.glNormal3f(1, 0, 0);
        //绘制第四个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
        //绘制TOP和BOTTOM两个面
        gl.glColor4f(0, 0, 1, 1);
        gl.glNormal3f(0, 1, 0);
        //绘制第五个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
        gl.glColor4f(0, 0, 0.5f, 1);
        gl.glNormal3f(0, -1, 0);
        //绘制第六个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
    }

    private void loadTexture(GL10 gl, Context context, int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
                resource); //加载位图
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0); //使用图片生成纹理
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        bmp.recycle(); //释放资源
    }
}
