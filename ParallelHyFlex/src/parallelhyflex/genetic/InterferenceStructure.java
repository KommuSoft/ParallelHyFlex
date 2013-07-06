package parallelhyflex.genetic;

/**
 *
 * @param <TItem>
 * @author kommusoft
 */
public interface InterferenceStructure<TItem> {

    public boolean interferes(TItem item1, TItem item2);
}
