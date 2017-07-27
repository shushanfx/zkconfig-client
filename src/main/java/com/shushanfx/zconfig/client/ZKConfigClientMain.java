package com.shushanfx.zconfig.client;

import com.shushanfx.zconfig.client.listener.ZKConfigDataListener;

import java.util.Scanner;

/**
 * Created by shushanfx on 2017/6/12.
 */
public class ZKConfigClientMain {
    public static void main(String[] args) {
        ZKConfigClient client = new ZKConfigClient();
        client.setServers("10.110.28.204:2181");
        client.setPath("/zkconfig/config/beijing");
        client.setMonitor(true);
        client.setMonitorPath("/zkconfig/connection");
        client.addListener(new ZKConfigDataListener() {
            public void handle(ZKConfig config) {
                System.out.println("Read value from name: " + config.getString("name", "defaultValue"));
            }
            public void error(ZKConfigError error, Throwable e){
                System.out.println(error);
            }
        });
        client.connect();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to exit.");
        scanner.nextLine();

    }
}
