/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-19 00:54
 */
package com.acooly.portlets.test.alog;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangpu
 * @date 2019-03-19 00:54
 */
@Slf4j
@Controller
@RequestMapping("/alog/test")
public class AlogTestController extends AbstractJQueryEntityController {

    {
        super.allowMapping = "";
    }


    @RequestMapping("test1")
    public String test1(HttpServletRequest request) {
        return "/alog/test1";
    }

    @RequestMapping("test2")
    public String test2(HttpServletRequest request) {
        return "/alog/test2";
    }

    @RequestMapping("test3")
    public String test3(HttpServletRequest request) {
        return "/alog/test3";
    }

    @RequestMapping("test4")
    public String test4(HttpServletRequest request) {
        return "/alog/test4";
    }

    @RequestMapping("footer")
    public String footer(HttpServletRequest request) {
        return "/common/footer";
    }
}
