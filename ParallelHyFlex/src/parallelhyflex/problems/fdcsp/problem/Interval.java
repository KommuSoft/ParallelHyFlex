package parallelhyflex.problems.fdcsp.problem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import parallelhyflex.algebra.InductiveBiasException;
import parallelhyflex.algebra.WithSetOperators;

/**
 *
 * @author kommusoft
 */
public class Interval implements WithSetOperators<Interval, Interval>, Iterable<SingleInterval> {

    private final TreeSet<SingleInterval> singleIntervals = new TreeSet<>();

    public Interval() {
    }

    public Interval(int low, int high) {
        this.singleIntervals.add(new SingleInterval(low, high));
    }
    
    public Interval(Iterable<? extends SingleInterval> sis) {
        for(SingleInterval si : sis) {
            singleIntervals.add(new SingleInterval(si.getLow(),si.getHigh()));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        boolean union = false;
        for (SingleInterval si : singleIntervals) {
            if (union) {
                sb.append(String.format(" u %s", si));
            } else {
                sb.append(si);
                union = true;
            }
        }
        sb.append(" )");
        return sb.toString();
    }

    public void add(int low, int high) {
        this.add(new SingleInterval(low, high));
    }

    public void add(SingleInterval si) {
        if (si.notEmpty()) {
            SingleInterval sic = si.clone();
            Stack<SingleInterval> toRemove = new Stack<>();
            for (SingleInterval si2 : singleIntervals) {
                if (sic.canUnite(si2)) {
                    try {
                        sic.unionWith(si2);
                    } catch (InductiveBiasException ex) {
                        Logger.getLogger(Interval.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    toRemove.add(si2);
                }
            }
            for (SingleInterval si2 : toRemove) {
                singleIntervals.remove(si2);
            }
            singleIntervals.add(sic);
        }
    }
    
    @Override
    public Interval clone () {
        return new Interval(this.singleIntervals);
    }

    @Override
    public Interval union(Interval other) {
        Interval res = this.clone();
        res.unionWith(other);
        return res;
    }

    @Override
    public void unionWith(Interval other) {
        for(SingleInterval si : other) {
            this.add(new SingleInterval(si.getLow(),si.getHigh()));
        }
    }

    @Override
    public Interval intersection(Interval other) {
        Interval res = this.clone();
        res.intersectWith(other);
        return res;
    }

    @Override
    public void intersectWith(Interval other) {
        ArrayList<SingleInterval> q = new ArrayList<>();
        for(SingleInterval si : this) {
            for(SingleInterval si2 : other) {
                SingleInterval siq = si.intersection(si2);
                if(siq.notEmpty()) {
                    q.add(siq);
                }
            }
        }
        this.singleIntervals.clear();
        this.singleIntervals.addAll(q);
    }

    @Override
    public Interval minus(Interval other) throws InductiveBiasException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void minusWith(Interval other) throws InductiveBiasException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<SingleInterval> iterator() {
        return this.singleIntervals.iterator();
    }
}
