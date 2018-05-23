package com.hlzj.test.service;

import java.util.Date;

public interface OxygenService
{
    /**
     *  写入接收到的血氧
     * @param id 客户端id
     * @param oxygendata
     * @param cdate 单次接收时刻
     */
    public void insert(int id, Double oxygendata, Date cdate);

    /**
     *
     * @return 返回最新的一次数据
     */
    public Double getLatest();
}
