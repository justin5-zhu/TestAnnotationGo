package Demo1;

import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.rmi.runtime.Log;

import java.io.*;

/**
 * @author justin-zhu
 * <p>
 * 2022年10月09日 21:26
 */

public class HttpRequestType {

    @Test
    private void getRequest() {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url("https://www.baidu.com").method("GET",null).build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.请求加入调度，重写回调方法
        call.enqueue(new Callback() {
            //请求失败执行的方法
            @Override
            public void onFailure(Call call, IOException e) {
            }
            //请求成功执行的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
//                Log.d("response",data);

                new Thread(() -> {
                    System.out.println(data);
                    Assert.assertTrue(data.contains("百度222一下"));
                    //更新UI
                });
            }
        });
    }

    @Test
    private void getSync() throws IOException {
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建Request对象，设置一个url地址（百度地址）,设置请求方式。
        Request request = new Request.Builder().url("http://www.baidu.com").method("GET",null).build();
        //3.创建一个call对象,参数就是Request请求对象
        Call call = okHttpClient.newCall(request);
        //4.同步调用会阻塞主线程,这边在子线程进行
        Response response = call.execute();
        System.out.println(response.body().string());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //同步调用,返回Response,会抛出IO异常
                    Response response = call.execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void postAsynHttp() {
        OkHttpClient client=new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url("http://api.1-blog.com/biz/bizserver/article/list.do")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
    }

    /**
     * 上传文件模板
     */
    @Test
    private void uploadFile(){
        // step 1: 创建 OkHttpClient 对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2:创建 RequestBody 以及所需的参数
        //2.1 获取文件
        File file = new File("Environment.getExternalStorageDirectory()" + "test.txt");
        //2.2 创建 MediaType 设置上传文件类型
        MediaType MEDIATYPE = MediaType.parse("text/plain; charset=utf-8");
        //2.3 获取请求体
        RequestBody requestBody = RequestBody.create(MEDIATYPE, file);

        //step 3：创建请求
        Request request = new Request.Builder().url("http://www.baidu.com")
                .post(requestBody)
                .build();

        //step 4 建立联系
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 请求成功
            }
        });
    }

    /**
     * 网上下载 同步/异步
     * @throws IOException
     */
    @Test
    private void downloadFile() throws IOException {

        OkHttpClient client = new OkHttpClient();
        // 文件下载路径
        String url = "https://img-my.csdn.net/uploads/201603/26/1458988468_5804.jpg";
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream("src/test/download/123.jpg");
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Log.d("123", "文件下载成功");
                System.out.println("文件下载成功");
//        异步执行
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//            @Override
//            public void onResponse(Call call, Response response) {
//                InputStream inputStream = response.body().byteStream();
//                FileOutputStream fileOutputStream = null;
//                try {
//                    fileOutputStream = new FileOutputStream(new File("src/test/download/123.jpg"));
//                    byte[] buffer = new byte[2048];
//                    int len = 0;
//                    while ((len = inputStream.read(buffer)) != -1) {
//                        fileOutputStream.write(buffer, 0, len);
//                    }
//                    fileOutputStream.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////                Log.d("123", "文件下载成功");
//                System.out.println("文件下载成功");
//            }
//        });
    }

    /**
     * 从网上下载到本地
     * @throws IOException
     */
    @Test
    private void download() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();
        Response response = client.newCall(request).execute();
        InputStream inputStream = response.body().byteStream();
        FileOutputStream outputStream ;
        outputStream = new FileOutputStream("src/test/download/helloworld.txt");
        byte[] buffer = new byte[4080];
        int len = inputStream.read(buffer);
        if (len  != -1){
            outputStream.write(buffer,0,len);
        }
        outputStream.flush();

    }

    /**
     * 从本地读取转存到另一地址
     * @throws IOException
     */
    @Test
    private void upload() throws IOException {
        InputStream is = new FileInputStream("D:\\Program Files\\JetBrains\\project\\javaCode\\spring-study\\javaDemo\\src\\main\\resources\\upload\\a.txt");
        FileOutputStream fileOutputStream;
        fileOutputStream = new FileOutputStream("src/test/download/a.txt");
        byte[] buffer = new byte[4080];
        int len = 0;
        while ((len=is.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
            fileOutputStream.flush();
        }


}
