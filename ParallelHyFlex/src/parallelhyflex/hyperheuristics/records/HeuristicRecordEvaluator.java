package parallelhyflex.hyperheuristics.records;

import java.util.Set;
import parallelhyflex.algebra.Generator;
import parallelhyflex.algebra.Tuple2;

/**
 *
 * @author kommusoft
 */
public interface HeuristicRecordEvaluator<THeuristicRecord extends HeuristicRecord> extends Generator<Tuple2<THeuristicRecord,Set<THeuristicRecord>>,Double> {
    
}
