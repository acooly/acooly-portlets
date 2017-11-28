
package com.acooly.test;

import org.junit.Test;


/**
 * @author qiubo@yiji.com
 */
public class DemoTest extends TestBase {
    @Test
    public void testController() throws Exception {
        assertGetBodyIs("hello", "hello world");
    }

    @Test
    public void testStaticResouce() throws Exception {
        assertGetBodyIs("demo.html", "demo");
    }

    @Test
    public void testNotFound() throws Exception {
        assertGetBodyContains("xxxx", "status=404");
    }
}
