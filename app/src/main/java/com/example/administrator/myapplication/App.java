package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;

import com.xdandroid.hellodaemon.DaemonEnv;

public class App extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        //需要在 Application 的 onCreate() 中调用一次 DaemonEnv.initialize()
        DaemonEnv.initialize(this, TraceServiceImpl.class, DaemonEnv.DEFAULT_WAKE_UP_INTERVAL);
        TraceServiceImpl.sShouldStopService = false;
        DaemonEnv.startServiceMayBind(TraceServiceImpl.class);
        context = getApplicationContext();
    }

    public static Context getContextObject(){
        return context;
    }

}
