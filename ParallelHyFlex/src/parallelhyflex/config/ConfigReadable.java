package parallelhyflex.config;

/**
 *
 * @author kommusoft
 */
public interface ConfigReadable {

    public boolean canHandle(int id);

    public boolean handle(int id, String value);
}
