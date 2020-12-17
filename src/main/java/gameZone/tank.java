package gameZone;

import proxy.proxyTank;

import java.awt.*;

public class tank {
    public final Rectangle stnd = new Rectangle(-8, -4, 8,4 );

    private Rectangle sizePlace = new Rectangle(stnd);

    private byte health = 100;
    public byte getHealth(){
        return health;
    }
    //private byte power = 1;
    private byte ammo = 100;
    public byte getAmmo(){
        return ammo;
    }

    public Rectangle getSizePlace() {
        return new Rectangle(sizePlace.width, sizePlace.width, sizePlace.x, sizePlace.y);
    }

    public tank(proxyTank t){
        health = t.getHealth();
        ammo = t.getAmmo();
        sizePlace = new Rectangle(t.sizePlace());
    }
}
