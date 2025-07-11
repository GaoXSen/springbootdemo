package com.example.springbootdemo.service.game.Impl;

import com.example.springbootdemo.dao.mapper.game.GameRecordMapper;
import com.example.springbootdemo.pojo.game.GameRecord;
import com.example.springbootdemo.service.game.GameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author gaosen
 * @Date 2023/3/28 14:39
 * @Version 1.0
 */
@Service
public class GameRecordServiceImpl implements GameRecordService {

    @Autowired
    private GameRecordMapper gameRecordMapper;

    @Override
    public void saveRecord(String playerName, int score) {
        GameRecord record = new GameRecord();
        record.setPlayerName(playerName);
        record.setScore(score);
        gameRecordMapper.insert(record);
    }

}
