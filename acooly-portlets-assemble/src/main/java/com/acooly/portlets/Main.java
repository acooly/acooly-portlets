package com.acooly.portlets;

import com.acooly.core.common.boot.Apps;
import org.springframework.boot.SpringApplication;
import com.acooly.core.common.BootApp;


/**
 * @author qiubo
 */
@BootApp(sysName = "acooly-component-portlets", httpPort = 8080)
public class Main {
    public static void main(String[] args) {
        Apps.setProfileIfNotExists("sdev");
        new SpringApplication(Main.class).run(args);
    }
}