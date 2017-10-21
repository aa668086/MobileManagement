package com.example.administrator.myapplication.Love;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 这是一个有关于Http的类
 * @author lundun
 * @version 1
 */


/**

 new Thread(){
@Override
public void run() {
Http http = new Http();

try {
Log.e("http.Get的内容是：",http.Get("39.108.113.219/QQyinliu/p.php"));
} catch (IOException e) {
e.printStackTrace();
}

try {
Log.e("http.Post的内容是：",http.Post("39.108.113.219/post.php","TXT=","canshu12345"));
} catch (IOException e) {
e.printStackTrace();
}


http.Download("39.108.113.219/4457_0.mp3","","5.mp3");

}
}.start();


 *
 *
 *
 */





public class Http {

    /**
     * 这是一个Get访问的方法
     * @param U 参数：一个网络的地址
     * @return Get访问后服务器发出的结果
     * @throws IOException 这是一个异常
     *
     * 例子：
     * Http http = new Http;
     * Log("Get的返回值",http.Get("39.108.113.219/get.php"));
     *
     */
    public String Get(String U) throws IOException {
        HttpURLConnection connection=null;
        String B = "http://"+U+"/";
        URL url=new URL(B);
        connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(8000);
        connection.setReadTimeout(8000);
        InputStream in=connection.getInputStream();
        //下面对获取到的输入流进行读取
        BufferedReader bufr=new BufferedReader(new InputStreamReader(in));
        StringBuilder response=new StringBuilder();
        String line=null;
        while((line=bufr.readLine())!=null){
            response.append(line);
        }
        String FFD = response.toString()+"";
        if(connection!=null){
            connection.disconnect();
        }
        return FFD;
    }

    /**
     * 这是一个post访问的方法<br>
     * @param U 参数：一个网络的地址
     * @param number 参数：post数组的下标
     * @param passwd 参数：post传递的内容
     * @return post访问后服务器发出的结果
     * @throws IOException 这是一个异常
     *
     * 例子：
     * Http http = new Http();
     * Log.e("http.Post的内容是：",http.Post("39.108.113.219/post.php","TXT=","canshu12345"));
     *
     */
    public String Post(String U, String number, String passwd) throws IOException {
        HttpURLConnection connection=null;
        String B = "http://"+U+"/";
        URL url=new URL(B);
        connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(8000);
        connection.setReadTimeout(8000);
        //设置运行输入,输出:
        connection.setDoOutput(true);
        connection.setDoInput(true);
        //Post方式不能缓存,需手动设置为false
        connection.setUseCaches(false);
        /**
         * 账号密码的例子
         String data = "passwd="+ URLEncoder.encode(passwd, "UTF-8")+
         "&number="+ URLEncoder.encode(number, "UTF-8");
         */
        String data = number + URLEncoder.encode(passwd, "UTF-8");
        //获取输出流
        OutputStream out = connection.getOutputStream();
        out.write(data.getBytes());
        out.flush();

        InputStream in=connection.getInputStream();
        //下面对获取到的输入流进行读取
        BufferedReader bufr=new BufferedReader(new InputStreamReader(in));
        StringBuilder response=new StringBuilder();
        String line=null;
        while((line=bufr.readLine())!=null){
            response.append(line);
        }

        String FFD = response.toString()+"";
        if(connection!=null){
            connection.disconnect();
        }
        return FFD;
    }


    /**
     * 这是一个下载文件的方法
     * @param urlStr 参数：一个下载的网络地址
     * @param path 参数：一个路径
     * @param fileName 参数：下载后保存的文件名
     * 注意：
     * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
     * <uses-permission android:name="android.permission.INTERNET" />
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
     * 文件当中本身有这个文件，那样一定会报错的
     *
     *  例子：
     *  // 取得SDCard的路径： Environment.getExternalStorageDirectory()
     *  Http http = new Http;
     *  http.Download("39.108.113.219/4457_0.mp3","","5.mp3");
     *
     */
    public void Download(String urlStr,String path,String fileName)  {
        OutputStream output=null;
        try {
            URL url=new URL("http://"+urlStr);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            //取得inputStream，并将流中的信息写入SDCard
                /*
                 * 取得写入SDCard的权限
                 * 2.取得SDCard的路径： Environment.getExternalStorageDirectory()
                 * 3.检查要保存的文件上是否已经存在
                 * 4.不存在，新建文件夹，新建文件
                 * 5.将input流中的信息写入SDCard
                 * 6.关闭流
                 */
            String SDCard= Environment.getExternalStorageDirectory()+"";
            String pathName=SDCard+"/"+path+"/"+fileName;//文件存储路径

            File file=new File(pathName);
            InputStream input=conn.getInputStream();

            if (file.exists()){
                file.delete();
            }

            String dir=SDCard+"/"+path;
            new File(dir).mkdir();//新建文件夹
            file.createNewFile();//新建文件
            output=new FileOutputStream(file);
            //读取大文件
            byte[] buffer=new byte[4*1024];
            while(input.read(buffer)!=-1){
                output.write(buffer);
            }
            output.flush();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
                System.out.println("成功");
            } catch (IOException e) {
                System.out.println("失败");
                e.printStackTrace();
            }
        }
    }


}
