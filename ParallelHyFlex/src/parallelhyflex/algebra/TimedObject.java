package parallelhyflex.algebra;

/**
 *
 * @param <TObject> 
 * @author kommusoft
 */
public class TimedObject<TObject> implements Comparable<TimedObject<TObject>> {
    
    private final double time;
    private final TObject object;
    
    public TimedObject (double time, TObject object) {
        this.time = time;
        this.object = object;
    }

    /**
     * @return the time
     */
    public double getTime() {
        return time;
    }

    /**
     * @return the object
     */
    public TObject getObject() {
        return object;
    }

    @Override
    public int compareTo(TimedObject<TObject> t) {
        return (int) Math.signum(this.getTime()-t.getTime());
    }
    
}
