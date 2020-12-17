package proxy;

import java.io.Serializable;

public class base implements Serializable{
    public static final byte TANK = 0, MOVE = 1, SHUT = 2;
    public byte type;
    public base(byte type){this.type = type;}
}
