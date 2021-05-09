/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by zhangpu
 * date:2021-05-08
 */
package com.acooly.portlets.successtory.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.core.common.domain.Sortable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * p_success_story Entity
 *
 * @author zhangpu
 * @date 2021-05-08 18:12:01
 */
@Entity
@Table(name = "p_success_story")
@Getter
@Setter
public class SuccessStory extends AbstractEntity implements Sortable {

    /**
     * 案例名称
     */
    @NotBlank
    @Size(max = 45)
    private String title;

    /**
     * 案例简介
     */
    @NotBlank
    @Size(max = 255)
    private String discn;

    /**
     * 案例类型
     */
    private String type;

    /**
     * 行业
     */
    @Size(max = 16)
    private String industry;

    /**
     * 上线日期
     */
    private Date onlineDate;

    /**
     * 图标
     */
    @Size(max = 128)
    private String logo;

    /**
     * 网址
     */
    @Size(max = 128)
    private String website;

    /**
     * 排序值
     */
    private Long sortTime;

    /**
     * 备注
     */
    @Size(max = 128)
    private String comments;

}
