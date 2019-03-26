/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-23 23:36
 */
package com.acooly.portlets.test.alog;

import com.acooly.core.common.boot.Apps;
import com.acooly.module.test.AppWebTestBase;
import com.acooly.portlets.alog.analysis.persist.service.ActionAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu
 * @date 2019-03-23 23:36
 */
@Slf4j
public class ActionAnalysisServiceTest extends AppWebTestBase {

    static {
        Apps.setProfileIfNotExists("dev");
    }

    @Autowired
    private ActionAnalysisService actionAnalysisService;

    @Test
    public void testAnalysisVisitsByDay() {
        actionAnalysisService.analysisVisitsByDay();
    }

}
