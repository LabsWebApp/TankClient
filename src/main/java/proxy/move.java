package proxy;

import java.util.UUID;

public class move extends base {
    public UUID id;

    short x, y;

    public int X() {
        return x;
    }
    public int Y() {
        return y;
    }

    public move(UUID id, short x, short y){
        super(base.MOVE);
        this.id = id;
        this.x = x;
        this.y = y;
    }
}
