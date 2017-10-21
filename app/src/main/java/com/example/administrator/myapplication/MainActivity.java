package com.example.administrator.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xdandroid.hellodaemon.DaemonEnv;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startlundun();

    }



    //点击事件
    public void click(View view) {
        switch (view.getId()) {
            case R.id.button:
                startlundun();
                break;
            default:
                break;
        }
    }



    //启动服务
    public void startlundun (){
        TraceServiceImpl.sShouldStopService = false;
        DaemonEnv.startServiceMayBind(TraceServiceImpl.class);
    }


}


