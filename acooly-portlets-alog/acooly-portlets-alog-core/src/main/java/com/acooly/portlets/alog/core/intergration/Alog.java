/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-21 00:22
 */
package com.acooly.portlets.alog.core.intergration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangpu
 * @date 2019-03-21 00:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Alog {


    String value() default "";


    /**
     * 名称
     */
    String actionKey() default "";

    /**
     * 分组
     *
     * @return
     */
    String actionGroup() default "";

    /**
     * 中文名
     *
     * @return
     */
    String actionName() default "";
}
