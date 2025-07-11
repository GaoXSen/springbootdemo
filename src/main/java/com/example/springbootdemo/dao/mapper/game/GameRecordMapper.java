package com.example.springbootdemo.dao.mapper.game;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.pojo.game.GameRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameRecordMapper  extends BaseMapper<GameRecord> {

}

