package parallelhyflex.hyperheuristics.records;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author kommusoft
 */
public class QualityIndexer<TRecord extends HeuristicRecord> {
    
    private final Comparator<TRecord> comparator;
    
    public QualityIndexer (Comparator<TRecord> comparator) {
        this.comparator = comparator;
    }
    
    private HashMap<TRecord,Integer> toQualityIndex (Collection<TRecord> records) {
        ArrayList<TRecord> temp = new ArrayList<>();
        HashMap<TRecord,Integer> result = new HashMap<>();
        for(TRecord rec : records) {
            temp.add(rec);
        }
        Collections.sort(temp,this.getComparator());
        int i = temp.size();
        for(TRecord rec : records) {
            result.put(rec, i--);
        }
        return result;
    }

    /**
     * @return the comparator
     */
    public Comparator<TRecord> getComparator() {
        return comparator;
    }
    
}
