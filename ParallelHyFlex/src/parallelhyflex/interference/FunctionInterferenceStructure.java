package parallelhyflex.interference;

import parallelhyflex.algebra.Generator;

/**
 *
 * @param <TItem> 
 * @param <TFunction> 
 * @author kommusoft
 */
public class FunctionInterferenceStructure<TItem, TFunction> implements InterferenceStructure<TItem> {

    private Generator<TItem, TFunction> generator;

    /**
     *
     * @param generator
     */
    public FunctionInterferenceStructure(Generator<TItem, TFunction> generator) {
        this.generator = generator;
    }

    /**
     *
     * @param item1
     * @param item2
     * @return
     */
    @Override
    public boolean interferes(TItem item1, TItem item2) {
        return this.getGenerator().generate(item1).equals(this.getGenerator().generate(item2));
    }

    /**
     * @return the generator
     */
    public Generator<TItem, TFunction> getGenerator() {
        return generator;
    }

    /**
     * @param generator the generator to set
     */
    public void setGenerator(Generator<TItem, TFunction> generator) {
        this.generator = generator;
    }
}