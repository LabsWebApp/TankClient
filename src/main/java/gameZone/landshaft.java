package gameZone;

import proxy.proxyTank;

import java.awt.*;
import java.util.*;
import java.util.List;

public class landshaft {
    public final int w = 800, h = 600;
    final Rectangle field = new Rectangle(w, h);
    private UUID owner;

    Map<UUID, tank> tanks;

    public boolean hasTank(UUID id){ return tanks.containsKey(id);}

    public landshaft(){
        tanks = new HashMap<>();
    }

    public java.util.List<proxyTank> tanks(UUID id){
        List<proxyTank> res = new ArrayList<>();
        tanks.forEach((k, v) ->{
            if(k != id) res.add(new proxyTank(k, v,false));
        });
        return res;
    }

    public void addTank(proxyTank t){ tanks.putIfAbsent(t.getId(), new tank(t));}

    public void move(UUID id, int x, int y){
        Rectangle proxy = tanks.get(id).getSizePlace();
        int _x = proxy.x, _y = proxy.y;
        if(x>=0) _x = x;
        if(y>=0) _y = y;
        tanks.get(id).move(new Point(_x, _y));
    }
}
