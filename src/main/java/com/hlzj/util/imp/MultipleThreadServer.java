package com.hlzj.util.imp;

import com.hlzj.test.service.TemperatureService;
import com.hlzj.test.service.imp.TemperatureServiceImpl;
import com.hlzj.util.ServerSocket;
import com.hlzj.util.TrimUtil;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class MultipleThreadServer implements ServerSocket{

    private java.net.ServerSocket server = null;
    private Socket socket = null;
    private static TemperatureService temperatureService = new TemperatureServiceImpl();



    @Override
    public void startListening(final int port) {
                    try {
                        server = new java.net.ServerSocket(port);
                        System.out.println("开始监听...");


                        while (true) {
                            socket = server.accept();
                            System.out.println("用户连接...");
                            invoke(socket);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

    }

    @Override
    public void shutDown() {
        if(socket != null && server != null){
            try {
                socket.close();
                server.close();
                System.out.println("服务端终止...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void invoke(final Socket client) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                InputStream cin = null;
                InputStreamReader in = null;
                BufferedOutputStream out = null;
                try {
                    cin = client.getInputStream();
                    in = new InputStreamReader(cin);
                    char[] text;
                    out = new BufferedOutputStream(client.getOutputStream());

                    while (true) {
                        try {
                            if(client == null)break;
                            text = new char[1024];
                            in.read(text);
                            System.out.println("from client:" + String.valueOf(text));
                            String[] trim = TrimUtil.dataTrim(text);
                            temperatureService.dataInsert(1, trim[0], trim[1]);
                            //System.out.println("请输入要发送的指令：");
                            //invoke2(client);
                            /*BufferedOutputStream out2 = new BufferedOutputStream(client.getOutputStream());
                            out2.write("PF4".getBytes("UTF-8"));
                            out2.flush();*/
                            if (String.valueOf(text).equals("bye")) {
                                break;
                            }
                        }catch (SocketException e){
                            System.out.println("用户断开连接");
                            break;
                        }

                    }
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
                finally {
                    try {
                        in.close();
                    } catch (Exception e) {}
                    try {
                        out.close();
                    } catch (Exception e) {}
                    try {
                        client.close();
                    } catch (Exception e) {}
                }
            }
        }).start();

    }

    public static void invoke2(final Socket client){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String msg;
                try {
                    msg = br.readLine();//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
                    out.write(msg.getBytes("UTF-8"));
                    out.flush();
                    //PrintWriter pw = new PrintWriter(client.getOutputStream());
                    //pw.println(msg);
                    //pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
