package com.shine;

public class PeerThread extends Thread {
    protected String broker;
    protected String clientId;
    protected int expectedMessageNum;
    protected String[] topics;//发送的主题或者接收的主题
    public PeerThread(String broker, String clientId, int expectedMessageNum,String[] topics){
        this.broker=broker;
        this.clientId=clientId;
        this.expectedMessageNum=expectedMessageNum;
        this.topics=topics;
    }
}
