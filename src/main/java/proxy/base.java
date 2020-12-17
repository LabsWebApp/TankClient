package proxy;

import java.io.Serializable;

public class base implements Serializable{
    public final byte move = 1, shut = 2;
    public byte type;
    public base(byte type){this.type = type;}
}
