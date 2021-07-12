package com.xiya.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

public interface MybatisBLOBsMapper<T, E, PK extends Serializable> extends MybatisBaseMapper<T, E, PK> {
    <T, E> List<T> selectByExampleWithBLOBs(E e);

    <T, E> List<T> selectByExampleWithBLOBsWithRowbounds(E e, RowBounds rowBounds);

    <T, E> int updateByExampleWithBLOBs(@Param("record") T t, @Param("example") E e);

    <T> int updateByPrimaryKeyWithBLOBs(T t);
}
