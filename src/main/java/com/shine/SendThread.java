package com.shine;

public class SendThread extends PeerThread {
    private SendPeer sendPeer;
    private long[] messageStartTime;
    public SendThread(String broker, String clientId, int expectedMessageNum, String[] topics) {
        super(broker, clientId, expectedMessageNum, topics);
        messageStartTime=new long[expectedMessageNum];
    }

    @Override
    public void run() {
        super.run();
        sendPeer = new SendPeer(broker,clientId,expectedMessageNum);
        for (int i = 0; i < topics.length; i++) {
            messageStartTime[i]=System.currentTimeMillis();
            sendPeer.publish(topics[i],1,"sendPeer "+clientId+" to "+topics[i]);
        }
        sendPeer.disConnect();
    }
}
