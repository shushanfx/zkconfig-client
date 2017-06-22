package com.shushanfx.zconfig.client;

import com.shushanfx.zconfig.client.config.ZNodeConfig;
import com.shushanfx.zconfig.client.listener.ZNodeDataListener;

import java.util.Scanner;

/**
 * Created by shushanfx on 2017/6/12.
 */
public class ZClientMain {
    public static void main(String[] args) {
        ZClient client = new ZClient();
        client.setServers("10.110.28.204:2181");
        client.setPath("/zkconfig/config/test");
        client.setMonitor(true);
        client.setMonitorPath("/zkconfig/connection");
        client.addListener(new ZNodeDataListener() {
            public void handle(ZNodeConfig config) {
                System.out.println("Read value from name: " + config.getString("name", "defaultValue"));
            }
        });
        client.connect();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to exit.");
        scanner.nextLine();

    }
}
