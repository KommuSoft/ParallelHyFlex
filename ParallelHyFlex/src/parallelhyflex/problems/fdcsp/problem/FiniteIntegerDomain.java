package parallelhyflex.problems.fdcsp.problem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import parallelhyflex.algebra.InductiveBiasException;
import parallelhyflex.algebra.WithSetOperators;
import parallelhyflex.algebra.collections.ItemIterable;
import parallelhyflex.communication.ReadWriteable;
import parallelhyflex.communication.ReadableGenerator;

/**
 *
 * @author kommusoft
 */
public class FiniteIntegerDomain implements WithSetOperators<FiniteIntegerDomain, FiniteIntegerDomain>, Iterable<IntegerInterval>, ReadWriteable, ReadableGenerator<FiniteIntegerDomain> {

    private final TreeSet<IntegerInterval> singleIntervals;

    public FiniteIntegerDomain() {
        singleIntervals = new TreeSet<>();
    }

    private FiniteIntegerDomain(TreeSet<IntegerInterval> singleIntervals) {
        this.singleIntervals = singleIntervals;
    }

    public FiniteIntegerDomain(int value) {
        this(value, value);
    }

    public FiniteIntegerDomain(int low, int high) {
        this();
        this.singleIntervals.add(new IntegerInterval(low, high));
    }

    public FiniteIntegerDomain(Iterable<? extends IntegerInterval> sis) {
        this();
        for (IntegerInterval si : sis) {
            this.add(new IntegerInterval(si.getLow(), si.getHigh()));
        }
    }

    public int min() {
        return this.singleIntervals.first().getLow();
    }

    public int max() {
        return this.singleIntervals.last().getHigh();
    }

    public int size() {
        int siz = 0;
        for (IntegerInterval si : this.singleIntervals) {
            siz += si.size();
        }
        return siz;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("( ");
        boolean union = false;
        for (IntegerInterval si : singleIntervals) {
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
        this.add(new IntegerInterval(low, high));
    }

    public void add(IntegerInterval si) {
        if (si.notEmpty()) {
            IntegerInterval sic = si.clone();
            Stack<IntegerInterval> toRemove = new Stack<>();
            for (IntegerInterval si2 : singleIntervals) {
                if (sic.canUnion(si2)) {
                    try {
                        sic.unionWith(si2);
                    } catch (InductiveBiasException ex) {
                        Logger.getLogger(FiniteIntegerDomain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    toRemove.add(si2);
                }
            }
            for (IntegerInterval si2 : toRemove) {
                singleIntervals.remove(si2);
            }
            singleIntervals.add(sic);
        }
    }

    @Override
    public FiniteIntegerDomain clone() {
        return new FiniteIntegerDomain(this.singleIntervals);
    }

    @Override
    public FiniteIntegerDomain union(FiniteIntegerDomain other) {
        FiniteIntegerDomain res = this.clone();
        res.unionWith(other);
        return res;
    }

    public void unionWith(Iterable<IntegerInterval> other) {
        for (IntegerInterval si : other) {
            this.add(new IntegerInterval(si.getLow(), si.getHigh()));
        }
    }

    @Override
    public void unionWith(FiniteIntegerDomain other) {
        unionWith((Iterable<IntegerInterval>) other);
    }

    public void unionWith(IntegerInterval interval) {
        unionWith(new ItemIterable<>(interval));
    }

    public void unionWith(int low, int high) {
        unionWith(new IntegerInterval(low, high));
    }

    public void unionWith(int value) {
        unionWith(value, value);
    }

    @Override
    public FiniteIntegerDomain intersection(FiniteIntegerDomain other) {
        FiniteIntegerDomain res = this.clone();
        res.intersectWith(other);
        return res;
    }

    @Override
    public void intersectWith(FiniteIntegerDomain other) {
        intersectWith((Iterable<IntegerInterval>) other);
    }

    public void intersectWith(IntegerInterval interval) {
        intersectWith(new ItemIterable<>(interval));
    }

    public void intersectWith(int low, int high) {
        intersectWith(new IntegerInterval(low, high));
    }

    public void intersectWith(int value) {
        intersectWith(value, value);
    }

    @Override
    public FiniteIntegerDomain minus(FiniteIntegerDomain other) {
        FiniteIntegerDomain res = this.clone();
        res.intersectWith(other);
        return res;
    }

    @Override
    public void minusWith(FiniteIntegerDomain fid) {
        this.minusWith((Iterable<IntegerInterval>) fid);
    }

    public void minusWith(IntegerInterval interval) {
        minusWith(new ItemIterable<>(interval));
    }

    public void minusWith(int low, int high) {
        minusWith(new IntegerInterval(low, high));
    }

    public void minusWith(int value) {
        minusWith(value, value);
    }

    public void minusWith(Iterable<IntegerInterval> other) {
        ArrayList<IntegerInterval> sis = new ArrayList<>(2 * singleIntervals.size());
        for (IntegerInterval si : this) {
            sis.add(si);
        }
        ArrayList<IntegerInterval> toAdd = new ArrayList<>();
        for (IntegerInterval tr : other) {
            for (Iterator<IntegerInterval> it = sis.iterator(); it.hasNext();) {
                IntegerInterval si = it.next();
                if (si.canMinus(tr)) {
                    try {
                        si.minusWith(tr);
                    } catch (InductiveBiasException ex) {
                        Logger.getLogger(FiniteIntegerDomain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (si.empty()) {
                        it.remove();
                    }
                } else {
                    it.remove();
                    toAdd.add(new IntegerInterval(si.getLow(), tr.getLow() - 1));
                    toAdd.add(new IntegerInterval(tr.getHigh() + 1, si.getHigh()));
                }
            }
            sis.addAll(toAdd);
            toAdd.clear();
        }
        this.singleIntervals.clear();
        this.singleIntervals.addAll(sis);
    }

    @Override
    public Iterator<IntegerInterval> iterator() {
        return this.singleIntervals.iterator();
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        int n = dis.readInt();
        this.singleIntervals.clear();
        for (int i = 0; i < n; i++) {
            this.singleIntervals.add(new IntegerInterval(dis.readInt(), dis.readInt()));
        }
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(this.singleIntervals.size());
        for (IntegerInterval si : this.singleIntervals) {
            dos.writeInt(si.getLow());
            dos.writeInt(si.getHigh());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (IntegerInterval si : this.singleIntervals) {
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
        final FiniteIntegerDomain other = (FiniteIntegerDomain) obj;
        if (this.singleIntervals.size() != other.singleIntervals.size()) {
            return false;
        }
        for (Iterator<IntegerInterval> it1 = this.singleIntervals.iterator(), it2 = other.iterator(); it1.hasNext() && it2.hasNext();) {
            if (!it1.next().equals(it2.next())) {
                return false;
            }

        }
        return true;
    }

    @Override
    public FiniteIntegerDomain readAndGenerate(DataInputStream dis) throws IOException {
        int n = dis.readInt();
        TreeSet<IntegerInterval> sis = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            sis.add(new IntegerInterval(dis.readInt(), dis.readInt()));
        }
        return new FiniteIntegerDomain(sis);
    }

    @Override
    public boolean canIntersect(FiniteIntegerDomain tr) {
        return true;
    }

    @Override
    public boolean canMinus(FiniteIntegerDomain tr) {
        return true;
    }

    @Override
    public boolean canUnion(FiniteIntegerDomain si) {
        return true;
    }

    public void intersectWith(Iterable<IntegerInterval> other) {
        ArrayList<IntegerInterval> q = new ArrayList<>();
        for (IntegerInterval si : this) {
            for (IntegerInterval si2 : other) {
                IntegerInterval siq = si.intersection(si2);
                if (siq.notEmpty()) {
                    q.add(siq);
                }
            }
        }
        this.singleIntervals.clear();
        this.singleIntervals.addAll(q);
    }
}
