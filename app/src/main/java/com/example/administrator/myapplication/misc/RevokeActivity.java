package com.example.administrator.myapplication.misc;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * uses-permission android:name="android.permission.UPDATE_APP_OPS_STATS"
 * android:theme="@android:style/Theme.NoDisplay"
 */
public class RevokeActivity extends Activity {

    static final List<String> WHITE_LIST_APPS = Arrays.asList(
            "com.github.shadowsocks",
            "com.tencent.mm",
            "com.xdandroid.kill",
            "me.piebridge.brevent"
    );

    static final List<String> WHITE_LIST_PERMISSIONS = Arrays.asList(
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    );

    static final String[] BLACK_LIST_OPS = {
            "WIFI_SCAN",
            "WAKE_LOCK",
            "RUN_IN_BACKGROUND",
            "WRITE_SETTINGS",
            "SYSTEM_ALERT_WINDOW"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return;
        new Thread(() -> {
            try {
                PackageManager pm = getPackageManager();
                AppOpsManager aom = getSystemService(AppOpsManager.class);
                Method setUidModeMethod = AppOpsManager.class.getMethod("setUidMode", String.class, int.class, int.class);
                Method setModeMethod = AppOpsManager.class.getMethod("setMode", int.class, int.class, String.class, int.class);
                pm.getInstalledPackages(PackageManager.GET_PERMISSIONS)
                  .stream()
                  .filter(i -> (i.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                  .filter(i -> (i.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 0)
                  .forEach(i -> {
                      int uid = i.applicationInfo.uid;
                      String n = i.applicationInfo.packageName;
                      try { setModeMethod.invoke(aom, 10, uid, n, AppOpsManager.MODE_IGNORED); } catch (Exception e) { e.printStackTrace(); }
                      try { setModeMethod.invoke(aom, 40, uid, n, AppOpsManager.MODE_IGNORED); } catch (Exception e) { e.printStackTrace(); }
                      try { setModeMethod.invoke(aom, 63, uid, n, WHITE_LIST_APPS.contains(n) ? AppOpsManager.MODE_ALLOWED : AppOpsManager.MODE_IGNORED); } catch (Exception e) { e.printStackTrace(); }
                      if (i.applicationInfo.targetSdkVersion < Build.VERSION_CODES.M && i.requestedPermissions != null) {
                          Arrays.stream(i.requestedPermissions)
                                .map(p -> {
                                    try { return pm.getPermissionInfo(p, 0); } catch (Exception e) { return null; }
                                })
                                .filter(Objects::nonNull)
                                .filter(pi -> (pi.protectionLevel & PermissionInfo.PROTECTION_MASK_BASE) == PermissionInfo.PROTECTION_DANGEROUS)
                                .map(pi -> pi.name)
                                .filter(pn -> pn.startsWith("android"))
                                .filter(pn -> !WHITE_LIST_PERMISSIONS.contains(pn))
                                .map(AppOpsManager::permissionToOp)
                                .filter(op -> !TextUtils.isEmpty(op))
                                .forEach(op -> {
                                    try { setUidModeMethod.invoke(aom, op, uid, AppOpsManager.MODE_IGNORED); } catch (Exception e) { e.printStackTrace(); }
                                });
                          try { setModeMethod.invoke(aom, 23, uid, n, AppOpsManager.MODE_IGNORED); } catch (Exception e) { e.printStackTrace(); }
                          try { setModeMethod.invoke(aom, 24, uid, n, AppOpsManager.MODE_IGNORED); } catch (Exception e) { e.printStackTrace(); }
                      }
                  });
            } catch (Exception e) { e.printStackTrace(); }
        }).start();
        finish();
    }
}
