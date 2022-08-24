package vip.zhguo.xier.util;

import java.io.*;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;

public class NetUtil {
    public static String doGet(String url) throws Exception {
        URL u = new URL(url);
        InputStream in = u.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[] = out.toByteArray();
        System.out.println(new String(b, "utf-8"));
        String res = new String(b, "utf-8");
        return res;
    }

    public static String doPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String doGet(String url, String param) {
        String result = "";
        String location = "";
        BufferedReader in = null;
        try {
            String url3 = url;

            String urlNameString = url3 + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }


        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String senMsgGet(String url, String title, String desp) throws UnsupportedEncodingException {
        String param1 = URLEncoder.encode(title, "UTF-8");
        String param2 = URLEncoder.encode(desp, "UTF-8");
//        拼接参数
        String param ="?title=" + param1 + "&desp=" + param2;
        return doGet(url, param);
    }

}
