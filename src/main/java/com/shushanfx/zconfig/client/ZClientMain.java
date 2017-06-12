package com.shushanfx.zconfig.client;

import java.util.Scanner;

/**
 * Created by dengjianxin on 2017/6/12.
 */
public class ZClientMain {
    public static void main(String[] args) {
        ZClient client = new ZClient();
        client.setServers("10.110.28.204:2181");
        client.setPath("/zkconfig/config/test");
        client.setMonitor(true);
        client.setMonitorPath("/zkconfig/connection");
        client.addListener((config, content) -> {
            System.out.println("Config is load:\n " + content);
            System.out.println("Read value from name: " + config.getString("name", "defaultValue"));
        });
        client.init();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to exit.");
        scanner.nextLine();

    }
}
