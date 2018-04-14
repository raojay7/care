package com.hlzj.test.service;

import java.util.Date;

public interface BeatService
{
    /**
     *  写入接收到的一组心跳数据
     * @param id 客户端id
     * @param beatdata 单次多组心跳数据
     * @param cdate 单次接收时刻
     */
    public void insert(int id, String beatdata, Date cdate);

    /**
     *
     * @return 返回最新的一次心跳组数据
     */
    public String getLatest();


}
