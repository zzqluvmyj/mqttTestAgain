package com.shine;

import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class SendPeer extends Peer {
    private MqttMessage message;
    private String topicId;
    private MqttTopic topic;
    MqttDeliveryToken token;
    public SendPeer(String broker, String clientId, int expectSendMessageNum) {
        super(broker, clientId, expectSendMessageNum);
    }

    public void publish(String topicId,int qos,String content){
        message=new MqttMessage();
        message.setQos(qos);
        message.setRetained(false);
        message.setPayload(content.getBytes());
        topic=client.getTopic(topicId);
        token=new MqttDeliveryToken();
        try{
            token=topic.publish(message);
            token.waitForCompletion();
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

}
