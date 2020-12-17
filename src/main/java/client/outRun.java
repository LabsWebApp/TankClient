package client;

import proxy.base;
import proxy.clientIn;
import proxy.move;
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
    private UUID id;

    private Serializable tmp = null;

    String addr;
    int port;

    public outRun(String addr, int port, UUID id) {
        this.addr = addr;
        this.port = port;
        this.id = id;

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


     void send(Serializable obj){
        tmp = obj;
        new WriteObj().run();
    }

    public void move (short x, short y){
        move m = new move(null, x, y);
        System.out.println(m.Y());
        send(m);
    }

    class WriteObj extends Thread {
        @Override
        public void run() {
                try {
                    out.writeObject(tmp);
                    //System.out.println(tmp.getClass().getName());
                    out.flush();
                } catch (IOException e) {
                    downService();
                }
                finally {
                    tmp = null;
                }
            }
        }

    private class ReadObj extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Object obj = in.readObject();
                  /*  switch (obj.getClass().getSimpleName()){
                        case "proxyTank":
                            Client.land.addTank((proxyTank)obj);
                            break;
                    }*/
                    base msg = (base) obj;
                    switch (msg.type){
                        case base.TANK:
                            synchronized (Client.land){
                                Client.land.addTank((proxyTank)obj);

                            }

                            break;
                        case base.MOVE:
                            move t = (move)msg;
                            System.out.println(t.Y());
                            System.out.println(t.id);
                            synchronized (Client.land){
                                Client.land.move(t.id, t.X(), t.Y());
                            }
                            break;
                        default:
                            throw new ClassNotFoundException("Класс для посыла не найден");
                    }


                }
            } catch (IOException | ClassNotFoundException e) {
                downService();
                System.err.println(e.getMessage());
            }
        }
    }
}
