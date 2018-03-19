package com.hlzj.DataServer.service;

import javax.servlet.http.HttpSession;

public interface DataServerService {
    /**
     * 开启服务端
     * @param port 监听端口号
     * @param session 浏览器session
     */
    public void startListening(final int port, HttpSession session);

    /**
     * 关闭服务端
     * @param session 浏览器session
     */
    public void shutDown(HttpSession session);

    /**
     * 服务端发送指令给客户端
     * @param session 浏览器session
     * @param order 要发送的指令
     */
    public void send(HttpSession session, String order);
}
