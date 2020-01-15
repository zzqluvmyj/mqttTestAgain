package com.shine;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Peer {
    protected String clientId;
    private String broker;
    protected MqttClient client;
    private MemoryPersistence persistence;
    protected PushCallBack callBack;
    protected int expectMessageNum;
    protected boolean isConnected;
    private MqttConnectOptions connOpts;
    public Peer(String broker,String clientId,int expectMessageNum){
        this.broker=broker;
        this.clientId=clientId;
        this.expectMessageNum=expectMessageNum;
        this.persistence=new MemoryPersistence();
        this.isConnected=false;
        this.connOpts=new MqttConnectOptions();
        callBack=new PushCallBack(clientId,expectMessageNum);
        try{
            client=new MqttClient(broker,clientId,persistence);
        }catch (MqttException e){
            e.printStackTrace();
        }
        connect();
        isConnected=true;
    }
    public void connect(){
        connOpts.setUserName("user");
        connOpts.setPassword("user".toCharArray());
        connOpts.setConnectionTimeout(200);
        connOpts.setKeepAliveInterval(50);
        try{
            client.setCallback(callBack);
            client.connect(connOpts);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void disConnect(){
        try{
            client.disconnect();
        }catch (MqttException e){
            e.printStackTrace();
        }
    }
}
