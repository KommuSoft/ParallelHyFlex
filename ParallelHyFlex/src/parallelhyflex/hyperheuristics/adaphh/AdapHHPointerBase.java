package parallelhyflex.hyperheuristics.adaphh;

/**
 *
 * @author kommusoft
 */
public class AdapHHPointerBase implements AdapHHPointer {
    
    private final AdapHH adapHH;
    
    public AdapHHPointerBase (AdapHH adapHH) {
        this.adapHH = adapHH;
    }

    @Override
    public AdapHH getAdapHH() {
        return this.adapHH;
    }
    
}
