package parallelhyflex.genetic;

public class NullManipulationObserver implements ManipulationObserver {

    private static final NullManipulationObserver instance = new NullManipulationObserver();

    public static NullManipulationObserver getInstance() {
        return instance;
    }

    private NullManipulationObserver() {
    }

    @Override
    public void modify(int index, int value) {
    }
}
