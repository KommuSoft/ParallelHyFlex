package parallelhyflex.communication;

/**
 *
 * @author kommusoft
 */
public enum CommunicationMode {

    Unreliable(0x00),
    Bounded(0x01),
    Eventually(0x02),
    UnreliableQueued(0x10),
    EventuallyQueued(0x11),
    BoundedQueued(0x12);
            
    private final int mode;
    
    public int getMode () {
        return this.mode;
    }
    
    private CommunicationMode (int mode) {
        this.mode = mode;
    }
    
    private boolean isQueued () {
        return ((mode&0xf0) != 0x00);
    }
    public int getType () {
        return this.mode&0x0f;
    }
    
}
