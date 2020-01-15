package com.shine;

import org.eclipse.paho.client.mqttv3.MqttException;

public class ReceivePeer extends Peer{
    public ReceivePeer(String broker, String clientId, int expectReceiveMessageNum) {
        super(broker, clientId, expectReceiveMessageNum);
    }
    public void subscribe(String [] topicIds,int[] qoss){
        if(topicIds.length!=qoss.length){
            System.out.println("订阅的topicNo和qOS数量不一致");
            return ;
        }
        try{
            client.subscribe(topicIds,qoss);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }
}
