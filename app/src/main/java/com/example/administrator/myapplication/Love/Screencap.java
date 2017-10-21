package com.example.administrator.myapplication.Love;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/9/24.
 */

public class Screencap {
    public Bitmap F;

    public Bitmap getScreenShotStream() {

        Process process = null;
        DataOutputStream os;

        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());

            os.write("/system/bin/screencap -p ".getBytes());
            os.writeBytes("\n");
            os.flush();

            os.writeBytes("exit\n");
            os.flush();

            F = BitmapFactory.decodeStream(process.getInputStream());

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (process != null) {
                    process.exitValue();
                }
            } catch (IllegalThreadStateException e) {
                process.destroy();
            }
        }
        return F;
    }

    public void rootbb(String COM) throws IOException{
        OutputStream os = null;
        Process process = null;
        process = Runtime.getRuntime().exec("su");
        os = process.getOutputStream();
        os.write(COM.getBytes());
        os.flush();
        os.close();
    }



}
