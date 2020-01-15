package com.shine;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 同一个线程不能既是收的，又是发的
 */
public class PushCallBack  implements MqttCallback {
    private String clientId;
    private int expectMessageNum;//预计的发送信息数或接收信息数
    private int nowMessageNum;
    private boolean isAllSend;
    private boolean isAllReceive;
    long temp;
    long [] messageArrivedTime;
    long [] messagePublishSuccessTime;

    public PushCallBack(String clientId, int expectMessageNum) {
        this.clientId = clientId;
        this.expectMessageNum = expectMessageNum;
        this.nowMessageNum=0;
        this.isAllReceive=false;
        this.isAllSend=false;
        this.messageArrivedTime=new long[expectMessageNum];
        this.messagePublishSuccessTime=new long[expectMessageNum];
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println(clientId+" lost ...");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        temp=System.currentTimeMillis();
        mqttMessage.getId();//判断是哪个消息，然后填入时间
        nowMessageNum++;
        if(nowMessageNum==expectMessageNum){
            isAllReceive=true;
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        temp=System.currentTimeMillis();
        //判断是哪个消息，然后填入时间
        nowMessageNum++;
        iMqttDeliveryToken.getMessageId();
        if(nowMessageNum==expectMessageNum){
            isAllSend=true;
        }
    }

    public boolean isAllSend() {
        return isAllSend;
    }

    public boolean isAllReceive() {
        return isAllReceive;
    }
}
