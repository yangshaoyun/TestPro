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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
//import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author yangshaoyun
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    private Button oriBtn, bt_encryption, bt_analysis;

    private MyOrientoinListener myOrientoinListener;
    private TextView tv_count;
    private long count = 0;
    private String t;

    Handler handler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppLog.d("onCreate");
        oriBtn = (Button) findViewById(R.id.orientation);
        bt_encryption = (Button) findViewById(R.id.bt_encryption);
        bt_analysis = (Button) findViewById(R.id.bt_analysis);
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
        List list = new ArrayList();


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

        bt_encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    AppLog.d("加密：" + md5(t));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                t = EncryptUtil.md5("123");
                AppLog.v("加密：" + t);
            }
        });
        bt_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tt = EncryptUtil.md5Decoded(t);
                AppLog.d("解密：" + tt);

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时取消监听
        AppLog.d("onDestroy");
        myOrientoinListener.disable();
        if (handler != null) {
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

    public static String md5(String dateString) throws Exception {
        MessageDigest md5 = null;
        byte[] digest = MessageDigest.getInstance("md5").digest(dateString.getBytes("utf-8"));
        String md5code = new BigInteger(1, digest).toString(16);
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    //ASCII 码使用指定的7 位或8 位二进制数组合来表示128 或256 种可能的字符。
    //（1）0～31及127(共33个)是控制字符或通信专用字符（其余为可显示字符），
    // 如控制符：LF（换行）、CR（回车）、FF（换页）、DEL（删除）、BS（退格)、BEL（响铃）等；通信专用字			   符：SOH（文头）、EOT（文尾）、ACK（确认）等；
    // ASCII值为8、9、10 和13 分别转换为退格、制表、换行和回车字符。它们并没有特定的图形显示，但会依不同的	应用程序，而对文本显示有不同的影响。
    // 2）32～126(共95个)是字符(32是空格），其中48～57为0到9十个阿拉伯数字。
    //（3）65～90为26个大写英文字母，97～122号为26个小写英文字母，其余为一些标点符号、运算符号等

//    public static String md5PlusSalt(String keyword) {
//        //md5加密
//        String md5 = DigestUtils.md5Hex(keyword);
//        //md5+盐
//        char[] cArray = md5.toCharArray();
//        for (int i = 0; i < cArray.length; i++) {
//            if (cArray[i] >= 48 && cArray[i] <= 57) {
//                cArray[i] = (char) (105 - cArray[i]);
//
//            }
//        }
//        //都可以使用
//        return String.valueOf(cArray);
//        //return  "".valueOf(cArray);
//
//    }

    //解密+盐
    public static String md5MinusSalt(String md5) {
        char[] cArray = md5.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            if (cArray[i] >= 48 && cArray[i] <= 57) {
                cArray[i] = (char) (105 - cArray[i]);
            }
        }
        return String.valueOf(cArray);
        //return  "".valueOf(cArray);
    }
}