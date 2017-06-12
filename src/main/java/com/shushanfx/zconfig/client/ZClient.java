package com.shushanfx.zconfig.client;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dengjianxin on 2017/6/12.
 */
public class ZClient implements IZkStateListener, IZkDataListener {
    private static final Logger logger = LoggerFactory.getLogger(ZClient.class);

    private ZkClient client = null;
    private String path = null;
    private String servers = null;
    private int connectTimeout = 30000;
    private boolean monitor = true;
    private String monitorPath = null;
    private VNode vNode = null;

    private String username = null;
    private String password = null;

    private ZNodeParser parser = null;
    private List<ZNodeListener> listeners = null;


    public ZClient(){
        listeners = new ArrayList<>();
    }

    public void init() {
        Assert.notNull(path, "path can not be null.");
        Assert.notNull(servers, "servers can not be null");
        Assert.assertTrue(connectTimeout > 0, "connect timeout must greater than 0.");
        if (isMonitor()) {
            Assert.notNull(monitorPath, "Monitor is set, and the monitor path can not be null");
        }
        client = new ZkClient(servers, connectTimeout);
        client.watchForData(this.path);
        client.subscribeStateChanges(this);
        client.subscribeDataChanges(this.path, this);
        Object value = client.readData(this.path, true);
        if(value == null){
            logger.warn("The config is null.");
        }
        try {
            handleDataChange(this.path, value);
        } catch (Exception e) {
            logger.error("Read config fail.", e);
        }

        if(isMonitor()){
            try{
                handleNewSession();
            } catch (Exception e){
                logger.error("Register monitor fail.", e);
            }
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

    public ZNodeParser getParser() {
        return parser;
    }

    public void setParser(ZNodeParser parser) {
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

    public void addListener(ZNodeListener listener){
        listeners.add(listener);
    }

    public void removeListener(ZNodeListener listener){
        listeners.remove(listener);
    }

    @Override
    public void handleNewSession() throws Exception {
        if (monitor) {
            vNode = new VNode();
            vNode.setId(UUID.randomUUID().toString());
            vNode.setPath(path);
            vNode.setConnectedTime(System.currentTimeMillis());
            client.createEphemeral(monitorPath + "/" + vNode.getId(), JSON.serialize(vNode));
        }
    }

    @Override
    public void handleSessionEstablishmentError(Throwable error) throws Exception {

    }

    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {
        String value = null;
        if (data != null) {
            value = data.toString();
        }
        if(parser == null){
            logger.info("No parse is config, use default properties config.");
            parser = new ZNodePropertiesConfig();
        }
        final String content = value;
        final ZNodeConfig config = parser.parse(value);
        if(listeners!=null && listeners.size() > 0){
            listeners.forEach(item -> {
                item.handle(config, content);
            });
        }
        else{
            logger.warn("No data listener is registered.");
        }
    }


    @Override
    public void handleDataDeleted(String dataPath) throws Exception {

    }
}
