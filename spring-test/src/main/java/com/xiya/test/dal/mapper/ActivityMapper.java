package com.xiya.test.dal.mapper;

import com.xiya.mybatis.mapper.MybatisBaseMapper;
import com.xiya.test.dal.entity.Activity;
import com.xiya.test.dal.example.ActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityMapper extends MybatisBaseMapper<Activity, ActivityExample, Long> {
    int batchInsert(@Param("list") List<Activity> list);
}