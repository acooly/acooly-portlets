/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-03-02 07:51
 */
package com.acooly.portlets.fqa.core.entity;

import com.acooly.core.common.domain.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author zhangpu
 * @date 2020-03-02 07:51
 */
@Entity
@Table(name = "p_fqa_body")
@Getter
@Setter
public class FqaBody extends AbstractEntity {

    @NotBlank
    private String body;

    public FqaBody() {
    }

    public FqaBody(Long id, @NotBlank String body) {
        setId(id);
        this.body = body;
    }
}
