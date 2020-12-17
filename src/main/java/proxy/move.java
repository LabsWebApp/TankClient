package proxy;

import java.util.UUID;

public class move extends base {
    transient UUID id;
    transient short x, y;

    public move(byte type, short x, short y) {
        super(type);
        this.x = x;
        this.y = y;
    }

    public int X() {
        return x;
    }
    public int Y() {
        return y;
    }
    public UUID getId() {return id;}
}
