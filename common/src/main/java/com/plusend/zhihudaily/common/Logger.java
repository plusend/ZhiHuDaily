package com.plusend.zhihudaily.common;

import android.util.Log;


/**
 * -------------------------------------------------------------------------
 * Author: imilk
 * Create: 15/10/14 14:10
 * -------------------------------------------------------------------------
 * Describe:Logging helper class. Logger is a wrapper of {@link Log}
 * But more pretty, simple and powerful.
 * <p/>
 *
 * {@code <android-sdk>/platform-tools/adb shell setprop log.tag.Yulore VERBOSE}
 * -------------------------------------------------------------------------
 * Changes:
 * -------------------------------------------------------------------------
 * 15/10/14 14 : Create by imilk
 * -------------------------------------------------------------------------
 */
public final class Logger {
    private static String TAG = "plusend";

    private static boolean DEBUG = true;
//    private static boolean DEBUG = Log.isLoggable(TAG, Log.VERBOSE);

    private static LoggerPrinter printer = new LoggerPrinter();
    private static final int METHOD_COUNT = 5;


    /**
     * Customize the log tag for your application, so that other apps
     * using Volley don't mix their logs with yours.
     * <br />
     * Enable the log property for your tag before starting your app:
     * <br />
     * {@code adb shell setprop log.tag.&lt;tag&gt;}
     */
    static {
        Log.d(TAG, "LOGGER DEBUG = " + DEBUG);
        printer.tag(TAG, METHOD_COUNT);
    }

    //no instance
    private Logger() {
    }


    public static void v(String tag, String msg){
        if (DEBUG)
            Log.v(tag,msg);
    }

    public static void d(String tag, String msg){
        if (DEBUG)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg){
        if (DEBUG)
            Log.i(tag, msg);
    }

    public static void w(String tag, String msg){
        if (DEBUG)
            Log.w(tag, msg);
    }

    public static void e(String tag, String msg){
        if (DEBUG)
            Log.e(tag, msg);
    }


    public static void e(String tag, String msg,Throwable tr){
        if (DEBUG)
            Log.e(tag, msg,tr);
    }


    public static void superV(String message, Object... args) {
        if (DEBUG)
            printer.superV(message, args);
    }

    public static void superD(String message, Object... args) {
        if (DEBUG)
            printer.superD(message, args);
    }

    public static void superI(String message, Object... args) {
        if (DEBUG)
            printer.superI(message, args);
    }

    public static void superW(String message, Object... args) {
        if (DEBUG)
            printer.superW(message, args);
    }

    public static void superE(String message, Object... args) {
        if (DEBUG)
            printer.superE(null, message, args);
    }

    public static void superE(Throwable throwable, String message, Object... args) {
        if (DEBUG)
            printer.superE(throwable, message, args);
    }

    public static void superWTF(String message, Object... args) {
        if (DEBUG)
            printer.superWTF(message, args);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        if (DEBUG)
            printer.json(json);
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        if (DEBUG)
            printer.xml(xml);
    }

}
