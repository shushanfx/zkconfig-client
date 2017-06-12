# ZKConfig client
Read config from zookeeper system, supporting rich api to handle the config.

## How to use
```xml
<dependencies>
    <dependency>
        <groupId>com.shushanfx</groupId>
        <artifactId>zkconfig-client</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
> If you can not down load this, use the jar package.

You can use it like this:
```java
package com.shushanfx.zconfig.client;

import java.util.Scanner;

/**
 * Created by dengjianxin on 2017/6/12.
 */
public class ZClientMain {
    public static void main(String[] args) {
        ZClient client = new ZClient();
        // set the servers
        client.setServers("10.110.28.204:2181");
        // set the path
        client.setPath("/zkconfig/config/test");
        // if notify the config center
        client.setMonitor(true);
        client.setMonitorPath("/zkconfig/connection");
        // handle the config
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
```

## Refer
You can view the server config in [zkconfig-server](https://github.com/shushanfx/zkconfig-server)