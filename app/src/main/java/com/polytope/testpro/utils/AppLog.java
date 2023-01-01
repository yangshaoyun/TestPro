package com.polytope.testpro.utils;

import android.util.Log;

/**
 * @author: 雪豹高科
 * @time: 2020-12-9
 */

public class AppLog {
    public static final String LOG_DEBUG = "debug";
    public static final String LOG_ERROR = "error";
    public static final String LOG_INFO = "info";
    public static final String LOG_VERBOSE = "verbose";
    public static final String LOG_WARN = "warn";

    private static final boolean DEBUG = true;
    private static final String TAG = "AppLog";
    private static int LOG_LEVEL = Log.VERBOSE;
    private static int LEVEL_3 = 3;
    private static int LEVEL_4 = 4;

    public static void e(String message) {

        if (DEBUG && LOG_LEVEL <= Log.ERROR) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements.length < LEVEL_4) {
                Log.e(TAG, "Stack to shallow");
            } else {
                String fullClassName = elements[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = elements[3].getMethodName();
                int lineNumber = elements[3].getLineNumber();

                Log.e(TAG, "[" + className + ":" + lineNumber + " " + methodName + "()] - " + message);
            }
        }
    }

    public static void d(String message) {

        if (DEBUG && LOG_LEVEL <= Log.DEBUG) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements.length < LEVEL_3) {
                Log.e(TAG, "Stack to shallow");
            } else {
                String fullClassName = elements[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = elements[3].getMethodName();
                int lineNumber = elements[3].getLineNumber();

                Log.d(TAG, "[" + className + ":" + lineNumber + " " + methodName + "()] - " + message);
            }
        }
    }

    public static void i(String message) {

        if (DEBUG && LOG_LEVEL <= Log.INFO) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements.length < LEVEL_3) {
                Log.e(TAG, "Stack to shallow");
            } else {
                String fullClassName = elements[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = elements[3].getMethodName();
                int lineNumber = elements[3].getLineNumber();

                Log.i(TAG, "[" + className + ":" + lineNumber + " " + methodName + "()] - " + message);
            }
        }
    }

    public static void w(String message) {

        if (DEBUG && LOG_LEVEL <= Log.WARN) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements.length < LEVEL_3) {
                Log.e(TAG, "Stack to shallow");
            } else {
                String fullClassName = elements[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = elements[3].getMethodName();
                int lineNumber = elements[3].getLineNumber();

                Log.w(TAG, "[" + className + ":" + lineNumber + " " + methodName + "()] - " + message);
            }
        }
    }

    public static void v(String message) {

        if (DEBUG && LOG_LEVEL <= Log.VERBOSE) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements.length < LEVEL_3) {
                Log.e(TAG, "Stack to shallow");
            } else {
                String fullClassName = elements[3].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = elements[3].getMethodName();
                int lineNumber = elements[3].getLineNumber();

                Log.v(TAG, "[" + className + ":" + lineNumber + " " + methodName + "()] - " + message);
            }
        }
    }
}
