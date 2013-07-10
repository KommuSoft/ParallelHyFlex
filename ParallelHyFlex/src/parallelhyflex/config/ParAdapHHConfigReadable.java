package parallelhyflex.config;

import java.util.logging.Logger;
import parallelhyflex.hyperheuristics.paradaphh.ParAdapHH;

/**
 *
 * @author kommusoft
 */
public class ParAdapHHConfigReadable extends ConfigReadableBase {

    private static final Logger LOG = Logger.getLogger(ParAdapHHConfigReadable.class.getName());

    public ParAdapHHConfigReadable() {
        super(1000);
    }

    @Override
    public boolean handle(int id, String value) {
        switch (id) {
            case 1000://LEARNING_AUTOMATON_LOCAL_INFLUENCE
                ParAdapHH.LEARNING_AUTOMATON_LOCAL_INFLUENCE = Double.parseDouble(value);
                break;
            case 1001://AILLA_SIZE
                ParAdapHH.AILLA_LENGTH = Integer.parseInt(value);
                break;
            case 1002://AILLA_LOCAL_SIZE
                ParAdapHH.AILLA_LOCAL_LENGTH = Integer.parseInt(value);
                break;
            case 1003://HEURISTIC_RECORD_INFLUENCE
                ParAdapHH.setHeuristicRecordInfluence(Double.parseDouble(value));
                break;
            default:
                return false;
        }
        return true;
    }
}