package com.example.administrator.myapplication.Love;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.PowerManager;
import android.util.Log;

import com.example.administrator.myapplication.App;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/24.
 */

public class lundun extends Thread {
    @Override
    public void run() {

        while (true) {


            try {
                new Screencap().rootbb("input keyevent 26");
            } catch (IOException e) {
                e.printStackTrace();
            }


        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            wakeUpAndUnlock(App.getContextObject());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            new Screencap().rootbb("input swipe 250 1050 250 250 200 ");
        } catch (IOException e) {
            e.printStackTrace();
        }




            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                new Screencap().rootbb("am start -n com.mhmhjmjmjojgjojm.lundun/com.cyjh.elfin.activity.ElfinFreeActivity");
            } catch (IOException e) {
                e.printStackTrace();
            }


            for (int a = 0; a < 30; a++) {
                tool.toast(App.getContextObject(), "还有" + (30 - a) + "开始运行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String SDCarePath;
            String filePath;
            Bitmap Bitmap1;
            int color;
            String d;
            long st = System.currentTimeMillis();
            Log.e("时间", st + "");


            Bitmap1 = new Screencap().getScreenShotStream();

            color = Bitmap1.getPixel(128, 1205);
            d = toHexEncoding(color);
            long ed = System.currentTimeMillis();
            System.out.print(d);

            if (d.equals("0xc57a3c")) {
                try {
                    new Screencap().rootbb("input tap 128 1205");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else if ((ed - st) > 50000) {
                tool.toast(App.getContextObject(), "找不到启动功能");
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            new Screencap().rootbb("input tap 707 402");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            new Screencap().rootbb("input tap 370 407");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static String toHexEncoding(int color) {
        String R, G, B;
        StringBuffer sb = new StringBuffer();

        R = Integer.toHexString(Color.red(color));
        G = Integer.toHexString(Color.green(color));
        B = Integer.toHexString(Color.blue(color));

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        sb.append("0x");
        sb.append(B);
        sb.append(G);
        sb.append(R);

        return sb.toString();
    }


    public static void wakeUpAndUnlock(Context context) throws InterruptedException {

        while (true) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            boolean screen = pm.isScreenOn();
            if (!screen) {
                PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
                wl.acquire();
                tool.toast(App.getContextObject(), "亮屏");
                sleep(3000);
            } else {
                break;
            }
        }

//        for (int i = 0; i <3 ; i++) {
//            KeyguardManager km= (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//            KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
//            //解锁
//            kl.disableKeyguard();
//            //获取电源管理器对象
//            PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
//            //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
//            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");
//            //点亮屏幕
//            wl.acquire();
//            //释放
//            wl.release();
//        }



    }


}
