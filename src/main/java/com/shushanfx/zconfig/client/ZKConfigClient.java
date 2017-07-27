package com.shushanfx.zconfig.client;

import com.shushanfx.zconfig.client.config.ZKPropertiesConfig;
import com.shushanfx.zconfig.client.listener.ZKConfigListener;
import com.shushanfx.zconfig.client.util.Assert;
import com.shushanfx.zconfig.client.util.IPUtils;
import com.shushanfx.zconfig.client.util.JSONUtils;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * The client to communicate with the zookeeper server.
 * Created by shushanfx on 2017/6/12.
 */
public class ZKConfigClient implements IZkStateListener, IZkDataListener {
    private static final Logger logger = LoggerFactory.getLogger(ZKConfigClient.class);

    private ZkClient client = null;
    private String path = null;
    private String servers = null;
    private int connectTimeout = 30000;
    private boolean monitor = true;
    private String monitorPath = null;
    private ZKConfigConnection vNode = null;

    private String scheme = null;
    private String username = null;
    private String password = null;

    private ZKConfigParser parser = null;
    private List<ZKConfigListener> listeners = null;


    public ZKConfigClient() {
        listeners = new ArrayList<>();
    }

    /**
     * auth with username and password.
     */
    public void auth() {
        if (username != null) {
            try {
                client.addAuthInfo(this.scheme, (username + ":" + password).getBytes("UTF8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Connect to the server and read the data. then register the watcher for update.
     */
    public void connect() {
        Assert.notNull(path, "path can not be null.");
        Assert.notNull(servers, "servers can not be null");
        Assert.assertTrue(connectTimeout > 0, "connect timeout must greater than 0.");
        if (isMonitor()) {
            Assert.notNull(monitorPath, "Monitor is set, and the monitor path can not be null");
        }
        client = new ZkClient(servers, connectTimeout);
        client.setZkSerializer(new ZKConfigSerializer());
        this.auth();
        if (isMonitor()) {
            try {
                handleNewSession();
            } catch (Exception e) {
                logger.error("Register monitor fail.", e);
            }
        }
        client.watchForData(this.path);
        client.subscribeStateChanges(this);
        client.subscribeDataChanges(this.path, this);
        Object value = client.readData(this.path, true);
        if (value == null) {
            logger.error("The config is null.");
        }
        try {
            handleDataChange(this.path, value);
        } catch (Exception e) {
            logger.error("Read config fail.", e);
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getMonitorPath() {
        return monitorPath;
    }

    public void setMonitorPath(String monitorPath) {
        this.monitorPath = monitorPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZKConfigParser getParser() {
        return parser;
    }

    public void setParser(ZKConfigParser parser) {
        this.parser = parser;
    }

    public ZkClient getClient() {
        return client;
    }

    public boolean isMonitor() {
        return monitor;
    }

    public void setMonitor(boolean monitor) {
        this.monitor = monitor;
    }

    @Override
    public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {

    }

    public void addListener(ZKConfigListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ZKConfigListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void handleNewSession() throws Exception {
        if (monitor) {
            vNode = new ZKConfigConnection();
            vNode.setId(UUID.randomUUID().toString());
            vNode.setPath(path);
            vNode.setConnectedTime(System.currentTimeMillis());
            StringBuilder ip = new StringBuilder();
            List<String> list = IPUtils.getAddresses();
            int iCount = 0;
            for (String item : list) {
                if (iCount > 0) {
                    ip.append(",");
                }
                ip.append(item);
                iCount++;
            }
            vNode.setIp(ip.toString());
            client.createEphemeral(monitorPath + "/" + vNode.getId(), JSONUtils.toString(vNode));
        }
        if (listeners != null) {
            for (ZKConfigListener listener : listeners) {
                try {
                    listener.connected();
                } catch (Exception e) {
                    listener.error(ZKConfigError.ERROR_CONNECT, e);
                }
            }
        }
    }

    @Override
    public void handleSessionEstablishmentError(Throwable error) throws Exception {
        if (listeners != null) {
            for (ZKConfigListener listener : listeners) {
                listener.error(ZKConfigError.ERROR_CONNECT, error);
            }
        }
    }

    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {
        String value = null;
        if (data != null) {
            value = data.toString();
        }
        if (parser == null) {
            logger.info("No parse is config, use default properties config.");
            parser = new ZKPropertiesConfig();
        }
        try {
            ZKConfig config = parser.parse(value);
            config.setContent(value);
            if (listeners != null && listeners.size() > 0) {
                for (ZKConfigListener listener : listeners) {
                    try {
                        listener.handle(config);
                    } catch (Exception e) {
                        listener.error(ZKConfigError.ERROR_HANDLE, e);
                    }
                }
            }
        } catch (Exception e) {
            if (listeners != null) {
                for (ZKConfigListener listener : listeners) {
                    listener.error(ZKConfigError.ERROR_HANDLE, e);
                }
            }
        }

    }
    @Override
    public void handleDataDeleted(String dataPath) throws Exception {
        handleDataChange(dataPath, null);
    }
}
