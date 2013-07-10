package parallelhyflex.config;

import java.util.logging.Logger;
import parallelhyflex.HyperHeuristic;

/**
 *
 * @author kommusoft
 */
public class ParHyFlexConfigReadable extends ConfigReadableBase {

    private static final Logger LOG = Logger.getLogger(ParAdapHHConfigReadable.class.getName());

    public ParHyFlexConfigReadable() {
        super(0);
    }

    @Override
    public boolean handle(int id, String value) {
        switch (id) {
            case 000000://SEARCHSPACE_SIZE
                HyperHeuristic.SEARCH_SPACE_SIZE = Integer.parseInt(value);
                break;
            case 000001://SEARCHSPACE_GENERATION
                HyperHeuristic.SEARCH_SPACE_GENERATION = Integer.parseInt(value);
                break;
            default:
                return false;
        }
        return true;
    }
}