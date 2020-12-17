package proxy;
import java.io.Serializable;
import java.util.UUID;

public class clientIn implements Serializable {
    private UUID id;
    public UUID getId(){ return id; }
    public clientIn(UUID id){ this.id = id; }
}
