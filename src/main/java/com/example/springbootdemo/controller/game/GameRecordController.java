package com.example.springbootdemo.controller.game;


import com.example.springbootdemo.service.game.GameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameRecordController {

    @Autowired
    private GameRecordService gameRecordService;

    @PostMapping(value = "/saveRecord")
    public String saveRecord(@RequestParam String playerName, @RequestParam int score) {
        gameRecordService.saveRecord(playerName, score);
        return "记录成功";
    }

}
