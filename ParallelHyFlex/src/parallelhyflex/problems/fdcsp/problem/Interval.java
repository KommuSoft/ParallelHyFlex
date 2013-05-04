package parallelhyflex.problems.fdcsp.problem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import parallelhyflex.algebra.InductiveBiasException;
import parallelhyflex.algebra.WithSetOperators;
import parallelhyflex.communication.ReadWriteable;
import parallelhyflex.communication.ReadableGenerator;

/**
 *
 * @author kommusoft
 */
public class Interval implements WithSetOperators<Interval, Interval>, Iterable<SingleInterval>, ReadWriteable, ReadableGenerator<Interval> {

    private final TreeSet<SingleInterval> singleIntervals;

    public Interval() {
        singleIntervals = new TreeSet<>();
    }

    private Interval(TreeSet<SingleInterval> singleIntervals) {
        this.singleIntervals = singleIntervals;
    }

    public Interval(int low, int high) {
        this();
        this.singleIntervals.add(new SingleInterval(low, high));
    }

    public Interval(Iterable<? extends SingleInterval> sis) {
        this();
        for (SingleInterval si : sis) {
            singleIntervals.add(new SingleInterval(si.getLow(), si.getHigh()));
        }
    }

    public int size() {
        int siz = 0;
        for (SingleInterval si : this.singleIntervals) {
            siz += si.size();
        }
        return siz;
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
    public Interval clone() {
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
        for (SingleInterval si : other) {
            this.add(new SingleInterval(si.getLow(), si.getHigh()));
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
        for (SingleInterval si : this) {
            for (SingleInterval si2 : other) {
                SingleInterval siq = si.intersection(si2);
                if (siq.notEmpty()) {
                    q.add(siq);
                }
            }
        }
        this.singleIntervals.clear();
        this.singleIntervals.addAll(q);
    }

    @Override
    public Interval minus(Interval other) {
        Interval res = this.clone();
        res.intersectWith(other);
        return res;
    }

    @Override
    public void minusWith(Interval other) {
        ArrayList<SingleInterval> sis = new ArrayList<>(2 * singleIntervals.size());
        for (SingleInterval si : this) {
            sis.add(si);
        }
        ArrayList<SingleInterval> toAdd = new ArrayList<>();
        for (SingleInterval tr : other) {
            for (Iterator<SingleInterval> it = sis.iterator(); it.hasNext();) {
                SingleInterval si = it.next();
                if (si.canMinus(tr)) {
                    try {
                        si.minusWith(tr);
                    } catch (InductiveBiasException ex) {
                        Logger.getLogger(Interval.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (si.empty()) {
                        System.out.println("EMPTY");
                        it.remove();
                    }
                } else {
                    it.remove();
                    toAdd.add(new SingleInterval(si.getLow(), tr.getLow() - 1));
                    toAdd.add(new SingleInterval(tr.getHigh() + 1, si.getHigh()));
                }
            }
            sis.addAll(toAdd);
            toAdd.clear();
        }
        this.singleIntervals.clear();
        this.singleIntervals.addAll(sis);
    }

    @Override
    public Iterator<SingleInterval> iterator() {
        return this.singleIntervals.iterator();
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        int n = dis.readInt();
        this.singleIntervals.clear();
        for (int i = 0; i < n; i++) {
            this.singleIntervals.add(new SingleInterval(dis.readInt(), dis.readInt()));
        }
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.singleIntervals.size());
        for (SingleInterval si : this.singleIntervals) {
            dos.writeInt(si.getLow());
            dos.writeInt(si.getHigh());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (SingleInterval si : this.singleIntervals) {
            hash = 79 * hash + si.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Interval other = (Interval) obj;
        if (this.singleIntervals.size() != other.singleIntervals.size()) {
            return false;
        }
        for (Iterator<SingleInterval> it1 = this.singleIntervals.iterator(), it2 = other.iterator(); it1.hasNext() && it2.hasNext();) {
            if (!it1.next().equals(it2.next())) {
                return false;
            }

        }
        return true;
    }

    @Override
    public Interval readAndGenerate(DataInputStream dis) throws IOException {
        int n = dis.readInt();
        TreeSet<SingleInterval> sis = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            sis.add(new SingleInterval(dis.readInt(), dis.readInt()));
        }
        return new Interval(sis);
    }
}
