package com.polytope.testpro;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.polytope.testpro.utils.AppLog;
import com.polytope.testpro.utils.MobileInfoUtil;

/**
 * @author: 雪豹
 * @time: 2022-12-8
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileInfoUtil.setStatusBarStyle(this, Color.TRANSPARENT, true);
        boolean isPad = MobileInfoUtil.isPad(this);
        AppLog.w("是否是平板：" + isPad);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}
