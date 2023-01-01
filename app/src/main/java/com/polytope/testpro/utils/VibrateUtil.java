package com.polytope.testpro.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;


/**
 * @author: 雪豹高科
 * @time: 2020-12-9
 */

public class VibrateUtil {
    /**
     * final Activity activity ：调用该方法的Activity实例
     * long milliseconds ：震动的时长，单位是毫秒
     * long[] pattern ：自定义震动模式 。数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
     * boolean isRepeat ： 是否反复震动，如果是true，反复震动，如果是false，只震动一次
     * TipHelper.Vibrate(getActivity(), new long[]{800, 1000, 800, 1000, 800, 1000}, true）; 
     */
    private static Vibrator vib;
    private static VibrationEffect vibrationEffect;

    /**
     * 一次性震动
     *
     * @param activity
     * @param milliseconds 震动时长（ms）
     *
     * VibrationEffect.createOneShot(long milliseconds, int amplitude);
     * amplitude 振动强度。这必须是1到255之间的值，或者DEFAULT_AMPLITUDE
     */
    public static void Vibrate(Activity activity, long milliseconds) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            vibrationEffect = VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE);
            vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
            vib.vibrate(vibrationEffect);
            AppLog.i(vib.hasVibrator()+"，振幅："+vib.hasAmplitudeControl());
        }else {
            vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
            vib.vibrate(milliseconds);
        }

    }

    public static void VibrateOld(final Activity activity, long[] pattern, boolean isRepeat) {
        vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(pattern, isRepeat ? 1 : -1);
    }

    /**
     * 波形振动
     *
     * @param activity
     * @param timings  交替开关定时的模式，从关闭开始。0的定时值将导致定时/振幅对被忽略
     * @param isRepeat
     *
     *
     * VibrationEffect.createWaveform(long[] timings, int repeat);
     *                 repeat 振动重复的模式，如果您不想重复，则将索引放入计时数组中重复，或者-1。
     *                 -1 为不重复
     *                 0 为一直重复振动
     *                 1 则是指从数组中下标为1的地方开始重复振动，重复振动之后结束
     *                 2 从数组中下标为2的地方开始重复振动，重复振动之后结束
     */
    @SuppressLint("NewApi")
    public static void Vibrate(Activity activity, long[] timings, boolean isRepeat) {
        vibrationEffect = VibrationEffect.createWaveform(timings, isRepeat ? 0 : VibrationEffect.DEFAULT_AMPLITUDE);
        vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(vibrationEffect);
    }

    /**
     * 停止震动
     */
    public static void stop() {
        if (vib != null){
            vib.cancel();
        }
    }

    /**
     * 取消震动
     *
     * @param activity
     */
    public static void virateCancle(Activity activity) {
        vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);
        vib.cancel();
    }
}
