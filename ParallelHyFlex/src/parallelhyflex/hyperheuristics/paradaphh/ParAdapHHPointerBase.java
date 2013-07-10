package parallelhyflex.hyperheuristics.paradaphh;

import java.util.logging.Logger;

/**
 *
 * @author kommusoft
 */
public class ParAdapHHPointerBase implements ParAdapHHPointer {
    
    private final ParAdapHH adapHH;
    
    /**
     *
     * @param adapHH
     */
    public ParAdapHHPointerBase (ParAdapHH adapHH) {
        this.adapHH = adapHH;
    }

    /**
     *
     * @return
     */
    @Override
    public ParAdapHH getParAdapHH() {
        return this.adapHH;
    }
    private static final Logger LOG = Logger.getLogger(ParAdapHHPointerBase.class.getName());
    
}
