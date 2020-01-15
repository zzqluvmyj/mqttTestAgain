package com.shine;

public class ReceiveThread extends PeerThread{
    private ReceivePeer receivePeer;

    public ReceiveThread(String broker, String clientId, int expectedMessageNum, String[] topics) {
        super(broker, clientId, expectedMessageNum, topics);
    }
    @Override
    public void run() {
        int[] qoss=new int[topics.length];
        for(int i=0;i<qoss.length;i++){
            qoss[i]=1;
        }
        receivePeer = new ReceivePeer(broker,clientId,expectedMessageNum);
        receivePeer.subscribe(topics,qoss);
    }
}
