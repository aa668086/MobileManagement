package com.example.administrator.myapplication.Love;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017/9/5.
 */

public class tool  {

    private static Toast myToast;

    /**
     * 这是一个吐司的方法
     * @param context 参数：上下文环境
     * @param text 参数：显示的内容
     *   例子：
     *     toast(MyApplication.getContextObject(),"还有"+X+"开始运行");
     */
    public static void toast(final Context context, final String text) {
        if (myToast != null) {
            myToast.cancel();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    myToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    myToast.show();
                }
            });
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    myToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    myToast.show();
                }
            });
        }

    }

    /**
     * 这是一个延时的参数
     * @param l 参数：延时的时间
     *   例子：
     *     Dalay(1000);
     */
    public static void Dalay(int l) {
        try {
            sleep(l);
        } catch (Exception e) {
            System.out.println("在这里有一个异常，现在运行reture");
            return;
        }
    }






}
