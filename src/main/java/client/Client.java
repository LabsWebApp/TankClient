package client;

import gameZone.landshaft;

import java.util.UUID;

public class Client {

    public static String ipAddr = "localhost";
    public static int port = 6666;
    static UUID[] ids = new UUID[]{
        UUID.fromString("6c598d54-c8d6-4ed2-b95b-cfcc4f1e2f24"), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()};

    public static landshaft land = new landshaft();

    public static void main(String[] args) throws InterruptedException {
        outRun c1 = new outRun(ipAddr, port, ids[0]);
        Thread.sleep(1000);
        System.out.println("1 * 10");
        c1.move((short) 1,(short) 10);
    }
}
