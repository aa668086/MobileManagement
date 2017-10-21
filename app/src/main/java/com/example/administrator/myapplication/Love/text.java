package com.example.administrator.myapplication.Love;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/9/14.
 */

public class text {
    /**
     * 这是一个写内容到文件的方法
     * @param parent 参数：文件的路径
     * @param content 参数：要写入的内容
     * 注意：
     *     当文件没有的时候会创建
     *     当文件有的时候会覆盖
     *     这里面的换行符需要自己写
     * 例子：
     *    new File().Write(this.getFilesDir().toString()+"/"+"IN.txt","我们是好的");
     */
    public static void Write(String parent, String content){
        try {
            java.io.File file = new java.io.File(parent);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个是追加内容到文件的方法
     * @param parent
     * @param content
     * 注意：
     *     当文件没有的时候会创建
     *     这里面的换行符需要自己写
     * 例子：
     *     new File().Writeappend(this.getFilesDir().toString()+"/"+"IN.txt","我们是好的")
     */
    public void Writeappend(String parent, String content){
        try {
            java.io.File file = new java.io.File(parent);
            //第二个参数意义是说是否以append方式添加内容
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(content);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * 这是一个读取文件内容的方法
     * @param parent 参数：文本的路径
     * @return 读取出来的内容
     * 说明：
     *    这是一行一行的读取
     * 例子：
     *    String Y = new File().Read(this.getFilesDir().toString()+"/"+"IN.txt");
     *
     */
    public String Read(String parent) {
        java.io.File filee = new java.io.File(parent);
        // 如果文件存在并且有内容就读取出来
        if (filee.exists() && filee.length() > 0) {
            try {
                FileInputStream fiss = new FileInputStream(filee);
                BufferedReader brr = new BufferedReader(new InputStreamReader(fiss));
                String Areadline = "";
                StringBuffer sb = new StringBuffer();
                while ((Areadline = brr.readLine()) != null) {
                    sb.append(Areadline);
                }
                brr.close();
                return sb.toString();
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }


    /**
     * 这是一个读取文本行数的方法
     * @param parent 参数：文本的路径
     * @return 一个int类型的变量
     * 例子：
     *    int Y = new File().Readline(this.getFilesDir().toString()+"/"+"IN.txt");
     */
    public int Readline(String parent){
        java.io.File file = new java.io.File(parent);
        // 如果文件存在并且有内容就读取出来
        if (file.exists() && file.length() > 0) {
            try{
                FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String Bre = "";
                int lineIndex=0;//记录行数
                while ((Bre = br.readLine()) != null) {
                    lineIndex++;
                }
                br.close();
                return lineIndex;
            }catch (Exception e){
                return 0;
            }
        }else {
            return 0;
        }
    }












    /**
     * 这是一个读取文件的指定行数内容的方法
     * @param parent 参数：文件的路径
     * @param line 指定的行数
     * @return 读取出来的内容
     * 注意：
     *     如果没有就返回一个null
     * 例子：
     *     String Y = new File().Readlinecontent(this.getFilesDir().toString()+"/"+"IN.txt",3);
     */
    public String Readlinecontent(String parent, int line){
        java.io.File file = new java.io.File(parent);
        // 如果文件存在并且有内容就读取出来
        if (file.exists() && file.length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String Breadline = "";
                int lineIndex=0;//判断第几行,readLine方法整行读取
                while ((Breadline = br.readLine()) != null) {
                    lineIndex++;
                    if(lineIndex==line){
                        break;
                    }
                }
                br.close();
                return Breadline;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }


    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     * @return 一个布尔值
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    /**
     * 获取SD卡根目录路径
     * @return 一个String类型
     */
    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath();
            return sdpath;
        } else {
            sdpath = "不适用";
            return null;
        }
    }
}
