# ZKConfig client
Read config from zookeeper system, supporting rich api to handle the config. See [zkconfig-server](https://github.com/shushanfx/zkconfig-server) for more detail.

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
> If you can not down load this, use the jar package. see release.

You can use it like this:
```java
package com.shushanfx.zconfig.client;

import com.shushanfx.zconfig.client.config.ZNodeConfig;
import com.shushanfx.zconfig.client.listener.ZNodeDataListener;

import java.util.Scanner;

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
```

## Refer
You can view the server config in [zkconfig-server](https://github.com/shushanfx/zkconfig-server)