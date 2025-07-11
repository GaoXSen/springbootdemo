package com.example.springbootdemo.service.game;

/**
 * @Author gaosen
 * @Date 2023/3/29 10:33
 * @Version 1.0
 */
public interface GameRecordService {

    void saveRecord(String playerName, int score);
}
