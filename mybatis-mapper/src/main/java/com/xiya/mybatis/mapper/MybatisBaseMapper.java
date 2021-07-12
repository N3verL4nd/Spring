package com.xiya.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

public interface MybatisBaseMapper<T, E, PK extends Serializable> {
    <E> long countByExample(E e);

    <E> int deleteByExample(E e);

    <PK> int deleteByPrimaryKey(PK pk);

    <T> int insert(T t);

    <T> int insertSelective(T t);

    <T, E> List<T> selectByExample(E e);

    <T, E> List<T> selectByExampleWithRowbounds(E e, RowBounds rowBounds);

    <T, PK> T selectByPrimaryKey(PK pk);

    <T, E> int updateByExample(@Param("record") T t, @Param("example") E e);

    <T, E> int updateByExampleSelective(@Param("record") T t, @Param("example") E e);

    <T> int updateByPrimaryKey(T t);

    <T> int updateByPrimaryKeySelective(T t);
}
