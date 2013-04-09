package parallelhyflex.hyperheuristics.records;

import java.util.Comparator;
import parallelhyflex.algebra.Generator;

/**
 *
 * @author kommusoft
 */
public interface HeuristicRecordEvaluator<THeuristicRecord extends HeuristicRecord> extends Generator<THeuristicRecord,Double>, Comparator<THeuristicRecord> {
    
}
