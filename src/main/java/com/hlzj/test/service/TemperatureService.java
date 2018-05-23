package com.hlzj.test.service;

import java.util.Date;

public interface TemperatureService {
    /**
     *  写入接收到的一次温度数据
     * @param id 客户端id
     * @param temperature 单次温度数据
     * @param cdate 单次接收时刻
     */
    public void insert(int id, Double temperature, Date cdate);

    /**
     *
     * @return 返回最新的一次温度数据
     */
    public Double getLatest();

    /**
     * 插入相应表
     * @param id 用户id
     * @param symbol 接收数据的标识符
     * @param realData 接收数据的数值部分
     */
    public void dataInsert(int id, String symbol, String realData);
}
