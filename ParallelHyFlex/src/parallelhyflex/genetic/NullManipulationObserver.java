package parallelhyflex.genetic;

/**
 *
 * @author kommusoft
 */
public class NullManipulationObserver implements ManipulationObserver {

    private static final NullManipulationObserver instance = new NullManipulationObserver();

    /**
     *
     * @return
     */
    public static NullManipulationObserver getInstance() {
        return instance;
    }

    private NullManipulationObserver() {
    }

    /**
     *
     * @param index
     * @param value
     */
    @Override
    public void modify(int index, int value) {
    }
}
