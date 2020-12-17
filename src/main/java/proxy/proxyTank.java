package proxy;

import gameZone.tank;

import java.awt.*;
import java.io.Serializable;
import java.util.UUID;

public class proxyTank extends base {
    private UUID id;
    private byte health;
    private transient byte ammo;
    private short x, y, w, h;

    public UUID getId() { return  id;}
    public byte getHealth() {
        return health;
    }
    public byte getAmmo(){
        return ammo;
    }

    public Rectangle sizePlace(){
        return new Rectangle(x, y, w, h);
    }

    public proxyTank(UUID id, tank t, boolean owner){
        super(base.TANK);
        this.id = id;
        Rectangle sp = t.getSizePlace();
        x = (short) sp.x;
        y = (short) sp.y;
        w = (short) sp.width;
        h = (short) sp.height;
        health = t.getHealth();
        if(owner) ammo = t.getAmmo();
    }

    public proxyTank(proxyTank t){
        super(base.TANK);
        id = t.id;
        x = t.x;
        y = t.y;
        w = t.w;
        h = t.h;
        health = t.health;
    }
}
