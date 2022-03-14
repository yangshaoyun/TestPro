package com.polytope.testpro;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.OrientationEventListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author yangshaoyun
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    private Button oriBtn;

    private MyOrientoinListener myOrientoinListener;
    private TextView tv_count;
    private long count = 0;

    Handler handler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppLog.d("onCreate");
        oriBtn = (Button) findViewById(R.id.orientation);
        tv_count = findViewById(R.id.tv_count);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                count++;
                tv_count.setText(count + "");
                AppLog.d("count = " + count);
                handler.postDelayed(this, 1000);
            }
        }, 1000);
//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                count++;
//                tv_count.setText(count + "");
//                Log.d(TAG, "count = " + count);
//            }
//        }, 1000, 1000, TimeUnit.MILLISECONDS);

        myOrientoinListener = new MyOrientoinListener(this);
        boolean autoRotateOn = (Settings.System.getInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0) == 1);
        //检查系统是否开启自动旋转
        if (autoRotateOn) {
            myOrientoinListener.enable();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, MyService.class));
        } else {
            startService(new Intent(this, MyService.class));
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时取消监听
        AppLog.d("onDestroy");
        myOrientoinListener.disable();
        if (handler != null){
            handler.removeCallbacksAndMessages(null);
        }
    }

    class MyOrientoinListener extends OrientationEventListener {

        public MyOrientoinListener(Context context) {
            super(context);
        }

        public MyOrientoinListener(Context context, int rate) {
            super(context, rate);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            int screenOrientation = getResources().getConfiguration().orientation;
            if (((orientation >= 0) && (orientation < 45)) || (orientation > 315)) {//设置竖屏
                AppLog.d("设置竖屏");
//                if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT && orientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    oriBtn.setText("竖屏");
//                }
            } else if (orientation > 225 && orientation < 315) { //设置横屏
                AppLog.d("设置横屏");
                if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    oriBtn.setText("横屏");
                }
            } else if (orientation > 45 && orientation < 135) {// 设置反向横屏
                AppLog.d("反向横屏");
                if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                    oriBtn.setText("反向横屏");
                }
            } else if (orientation > 135 && orientation < 225) {
                AppLog.d("反向竖屏");
//                if (screenOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
//                    oriBtn.setText("反向竖屏");
//                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppLog.d("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppLog.d("onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppLog.d("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppLog.d("onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppLog.d("onRestart");
    }
}