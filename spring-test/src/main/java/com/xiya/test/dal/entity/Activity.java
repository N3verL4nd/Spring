package com.xiya.test.dal.entity;

import java.util.Date;
import lombok.*;

/**
 *
 *   表名: activity
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Activity {
    /**
     *   字段: id
     */
    private Long id;

    /**
     *   字段: token
     *   说明: 字符串类型id，用于防猜
     */
    private String token;

    /**
     *   字段: title
     *   说明: 活动标题
     */
    private String title;

    /**
     *   字段: start_time
     *   说明: 活动开始时间
     */
    private Date startTime;

    /**
     *   字段: end_time
     *   说明: 活动结束时间
     */
    private Date endTime;

    /**
     *   字段: type
     *   说明: 活动类型 0 累计次数发奖品
     */
    private Integer type;

    /**
     *   字段: action_type
     *   说明: 动作类型 0签到
     */
    private Integer actionType;

    /**
     *   字段: mis
     *   说明: 操作人的 mis 账号
     */
    private String mis;

    /**
     *   字段: rule_info
     *   说明: 规则基本信息，活动类型不同，对应的规则需要配置的基本字段也不一样，直接压缩成 json
     */
    private String ruleInfo;

    /**
     *   字段: state
     *   说明: 活动状态 0可编辑 1结束
     */
    private Byte state;

    /**
     *   字段: create_time
     *   说明: 创建时间
     */
    private Date createTime;

    /**
     *   字段: mod_time
     *   说明: 修改时间
     */
    private Date modTime;

    /**
     *   字段: data_source
     *   说明: 数据源类型
     */
    private Integer dataSource;

    /**
     *   字段: supplement_signinfo
     */
    private String supplementSigninfo;

    /**
     *   字段: master_version
     *   说明: 主数据版本
     */
    private Long masterVersion;

    /**
     *   字段: slave_version
     *   说明: 副数据版本
     */
    private Long slaveVersion;

    /**
     *   字段: creator
     *   说明: 创建者的 mis 账号
     */
    private String creator;

    /**
     *   字段: city_ids
     *   说明: 城市id限制
     */
    private String cityIds;
}