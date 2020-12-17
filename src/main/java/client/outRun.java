package client;

import proxy.clientIn;
import proxy.proxyTank;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class outRun {
    Socket server;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Serializable tmp = null;

    String addr;
    int port;

    public outRun(String addr, int port, UUID id) {
        this.addr = addr;
        this.port = port;

        try {
            this.server = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Сервер не подключается");
        }
        try {
            in = new ObjectInputStream(server.getInputStream());
            out = new ObjectOutputStream(server.getOutputStream());

            out.writeObject(new clientIn(id));
            out.flush();

            new ReadObj().start();

            } catch (IOException e) {
            System.err.println("Сервер сказал нам пока!");
            downService();
        }
    }

    private void downService() {
        try {
            if (!server.isClosed()) {
                server.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }


    public void send(Serializable obj){
        tmp = obj;
        new WriteObj().run();
    }

    class WriteObj extends Thread {
        @Override
        public void run() {
                try {
                    out.writeObject(tmp);
                    out.flush();
                } catch (IOException e) {
                    downService();
                }
                finally {
                    tmp = null;
                }
            }
        }

    public void move (Point p){

    }

    private class ReadObj extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Object obj = in.readObject();
                    switch (obj.getClass().getSimpleName()){
                        case "proxyTank":
                            Client.land.addTank((proxyTank)obj);
                            break;
                    }

                    System.out.println("прочли " + obj.getClass().getSimpleName());
                }
            } catch (IOException | ClassNotFoundException e) {
                downService();
                System.err.println(e.getMessage());
            }
        }
    }
}
