package parallelhyflex.config;

/**
 *
 * @author kommusoft
 */
public abstract class ConfigReadableBase implements ConfigReadable {

    private final int idbase;

    /**
     *
     * @param idbase
     */
    public ConfigReadableBase(int idbase) {
        this.idbase = idbase;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean canHandle(int id) {
        return (id / 1_000) * 1_000 == this.idbase;
    }

    /**
     *
     * @param id
     * @param value
     */
    @Override
    public abstract boolean handle(int id, String value);
}
