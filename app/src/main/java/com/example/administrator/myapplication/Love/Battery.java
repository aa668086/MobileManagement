package com.example.administrator.myapplication.Love;

import android.os.BatteryManager;

public class Battery {
    //单例模式
    private static Battery mInstance =  new Battery();
    private Battery() {}
    public static Battery getInstance() { return(mInstance); }
    //获取状态描述
    public String getStatusDesc(int status) {
        switch(status) {
            case BatteryManager.BATTERY_STATUS_CHARGING: { return ("充电中"); }
            case BatteryManager.BATTERY_STATUS_DISCHARGING: { return ("放电中"); }
            case BatteryManager.BATTERY_STATUS_FULL: { return ("充满"); }
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING: { return ("没充电"); }
            case BatteryManager.BATTERY_STATUS_UNKNOWN: { return ("未知状态"); }
        }
        return ("未知状态");
    }
    //获取健康状态描述
    public String getHealthDesc(int health) {
        switch(health) {
            case BatteryManager.BATTERY_HEALTH_DEAD: { return ("需更换"); }
            case BatteryManager.BATTERY_HEALTH_GOOD: { return ("良好"); }
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE: { return ("电压过高"); }
            case BatteryManager.BATTERY_HEALTH_OVERHEAT: { return ("过热"); }
            case BatteryManager.BATTERY_HEALTH_UNKNOWN: { return ("未知状态"); }
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE: { return ("未定义失败"); }
            //case BatteryManager.BATTERY_HEALTH_COLD: { return ("低温"); }
        }
        return ("未知状态");
    }
    //获取电源类型描述
    public String getPluggedDesc(int plugged) {
        switch(plugged) {
            case 0: { return ("电池供电"); }
            case BatteryManager.BATTERY_PLUGGED_AC: { return ("交流电源"); }
            case BatteryManager.BATTERY_PLUGGED_USB: { return ("USB电源"); }
        }
        return ("未知状态");
    }






}