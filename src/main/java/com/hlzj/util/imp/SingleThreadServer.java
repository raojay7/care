package com.hlzj.util.imp;

import com.hlzj.util.ServerSocket;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SingleThreadServer implements ServerSocket{
    private java.net.ServerSocket server = null;
    private Socket socket = null;

    @Override
    public void shutDown() {
        if(socket != null && server != null){
            try {
                socket.close();
                server.close();
                System.out.println("服务端终止");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void startListening(final int port) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server = new java.net.ServerSocket(port);
                    System.out.println("服务端启动...");
                    socket = server.accept();
                    System.out.println("用户连接");
                    invoke(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void invoke(final Socket client) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                //BufferedReader in = null;
                InputStream cin = null;
                InputStreamReader in = null;
                //OutputStreamWriter out = null;
                BufferedOutputStream out = null;
                try {
                    cin = client.getInputStream();
                    in = new InputStreamReader(cin);
                    char[] text;
                    out = new BufferedOutputStream(client.getOutputStream());

                    while (true) {
                        text = new char[1024];
                        in.read(text);
                        System.out.println(String.valueOf(text));
                        //out.println("Server received " + String.valueOf(text));
                        out.write(("server received : " + String.valueOf(text)).getBytes("UTF-8"));
                        //out.write("0\n".getBytes());一定要有换行
                        out.flush();
                        if (String.valueOf(text).equals("bye")) {
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

}
