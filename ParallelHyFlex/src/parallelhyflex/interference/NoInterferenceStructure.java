package parallelhyflex.interference;

/**
 *
 * @author kommusoft
 * @param <TItem>
 */
public class NoInterferenceStructure<TItem> implements InterferenceStructure<TItem> {
    
    private static final NoInterferenceStructure instance = new NoInterferenceStructure();
    
    /**
     *
     * @return
     */
    public static NoInterferenceStructure getInstance() {
        return instance;
    }
    
    private NoInterferenceStructure() {
    }
    
    /**
     *
     * @param item1
     * @param item2
     * @return
     */
    @Override
    public boolean interferes(TItem item1, TItem item2) {
        return item1.equals(item2);
    }
}
