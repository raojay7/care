package com.hlzj.test.service;

import com.hlzj.util.ServerSocket;
import com.hlzj.util.imp.SingleThreadServer;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HBTestService {
    public int getData(){
        Random random = new Random();
        return random.nextInt(10) + 60;
    }
}
