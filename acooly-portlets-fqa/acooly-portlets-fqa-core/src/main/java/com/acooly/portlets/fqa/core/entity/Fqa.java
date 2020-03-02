/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-26
 */
package com.acooly.portlets.fqa.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.core.utils.enums.AbleStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * p_fqa Entity
 *
 * @author zhangpu@acooly.cn
 * @date 2020-02-26 12:31:54
 */
@Entity
@Table(name = "p_fqa")
@Getter
@Setter
public class Fqa extends AbstractEntity {

    /**
     * 问题
     */
    @NotEmpty
    @Size(max = 128)
    private String ask;

    /**
     * 作者
     */
    @Size(max = 16)
    private String author;

    /**
     * 问题分类编码
     */
    @Size(max = 32)
    private String askTypeCode;
    /**
     * 问题分类名称(冗余)
     */
    @Size(max = 32)
    private String askTypeName;

    /**
     * 访问量
     */
    private Integer hits = 0;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private AbleStatus status;

    /**
     * 备注
     */
    @Size(max = 45)
    private String comments;

}
