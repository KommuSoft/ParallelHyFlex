package parallelhyflex.interference;

public class NoInterferenceStructure<TItem> implements InterferenceStructure<TItem> {

    private static final NoInterferenceStructure instance = new NoInterferenceStructure();

    public static NoInterferenceStructure getInstance() {
        return instance;
    }

    private NoInterferenceStructure() {
    }

    @Override
    public boolean interferes(TItem item1, TItem item2) {
        return false;
    }
}
