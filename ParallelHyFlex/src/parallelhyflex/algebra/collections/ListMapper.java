package parallelhyflex.algebra.collections;

import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author kommusoft
 */
public interface ListMapper<TKey,TItem> extends Map<TKey,TItem>, ArgumentIterable<TKey,TItem>, Iterable<Entry<TKey,TItem>> {
    
}
