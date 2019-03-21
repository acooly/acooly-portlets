/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-10
 */
package com.acooly.portlets.alog.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.portlets.alog.core.enums.ActionMappingTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 访问映射 Entity
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-01-10 18:10:55
 */
@Entity
@Table(name = "p_action_mapping")
@Getter
@Setter
public class ActionMapping extends AbstractEntity {

    /**
     * 映射类型
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private ActionMappingTypeEnum mappingType;

    /**
     * 操作Key
     */
    @NotEmpty
    @Size(max = 255)
    private String actionKey;

    /**
     * 操作名称
     */
    @NotEmpty
    @Size(max = 32)
    private String actionName;

    /**
     * 分组
     */
    @Size(max = 64)
    private String actionGroup;

    /**
     * 需要收集的参数，多个逗号分隔
     */
    private String params;

    /**
     * 备注
     */
    @Size(max = 128)
    private String comments;

}
