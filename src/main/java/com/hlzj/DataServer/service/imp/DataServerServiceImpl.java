package com.hlzj.DataServer.service.imp;

import com.hlzj.DataServer.service.DataServerService;
import com.hlzj.test.dao.TemperatureMapper;
import com.hlzj.test.entity.Temperature;
import com.hlzj.util.ServerSocket;
import com.hlzj.util.TrimUtil;
import com.hlzj.util.imp.MultipleThreadServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class DataServerServiceImpl implements DataServerService {
    @Autowired
    private TemperatureMapper temperatureMapper;

    private java.net.ServerSocket server = null;
    private Socket socket = null;
    private ServerSocket server2 = null;

    @Override
    public void send(HttpSession session, String order) {
        List<Socket> socketList = (List<Socket>) session.getAttribute("socketList");
        BufferedOutputStream bos;
        for(Socket client : socketList){
            try {
                bos = new BufferedOutputStream(client.getOutputStream());
                bos.write(order.getBytes("UTF-8"));
                bos.flush();
            }catch(SocketException e){
                continue;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void startListening(final int port, HttpSession session) {
        try {
            List<Socket> socketList = new LinkedList<>();
            server = new java.net.ServerSocket(port);
            System.out.println("开始监听...");
            while (true) {
                try {
                    socket = server.accept();
                    socketList.add(socket);
                    session.setAttribute("socketList", socketList);
                } catch (SocketException e) {
                    break;
                }
                System.out.println("用户连接...");
                invoke(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutDown(HttpSession session) {
        session.invalidate();
        if(server != null) {
            try {
                server.close();
                if(socket != null)socket.close();
                if(server2 != null)server2.shutDown();
                System.out.println("服务端关闭");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void invoke(final Socket client) throws IOException {
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
                            Temperature record = new Temperature();
                            record.setCdate(new Date());
                            record.setId(1);
                            switch (trim[0]){
                                case "T01":
                                    record.setTemperature(Double.valueOf(trim[1]));
                                    temperatureMapper.insert(record);
                                    break;
                            }
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
}
