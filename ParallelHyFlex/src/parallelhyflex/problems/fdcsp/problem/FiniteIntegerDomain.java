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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import parallelhyflex.algebra.InductiveBiasException;
import parallelhyflex.algebra.WithSetOperators;
import parallelhyflex.algebra.collections.IndirectIterator;
import parallelhyflex.algebra.collections.ItemIterable;
import parallelhyflex.communication.ReadWriteable;
import parallelhyflex.communication.ReadableGenerator;
import parallelhyflex.parsing.tokenizing.Token;
import parallelhyflex.parsing.tokenizing.TokenAnnotation;
import parallelhyflex.parsing.tokenizing.TokenGeneratorBase;

/**
 *
 * @author kommusoft
 */
@TokenAnnotation(token = "(\\[(-?[0-9]+),(-?[0-9]+)\\]|\\{(-?[0-9]+)\\})(u(\\[(-?[0-9]+),(-?[0-9]+)\\]|\\{(-?[0-9]+)\\}))*")
public class FiniteIntegerDomain extends TokenGeneratorBase<FiniteIntegerDomain> implements WithSetOperators<FiniteIntegerDomain, FiniteIntegerDomain>, Iterable<IntegerInterval>, ReadWriteable, ReadableGenerator<FiniteIntegerDomain>, Token {

    public static FiniteIntegerDomain all() {
        return new FiniteIntegerDomain(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private final TreeSet<IntegerInterval> singleIntervals;
    private Pattern subPattern = null;

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

    public int first() {
        return this.singleIntervals.first().getLow();
    }

    public int last() {
        return this.singleIntervals.last().getHigh();
    }

    public int size() {
        int siz = 0;
        for (IntegerInterval si : this.singleIntervals) {
            siz += si.size();
        }
        return siz;
    }

    public void clear() {
        this.singleIntervals.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean union = false;
        for (IntegerInterval si : singleIntervals) {
            if (union) {
                sb.append(String.format("u%s", si));
            } else {
                sb.append(si);
                union = true;
            }
        }
        return sb.toString();
    }

    public boolean contains(int value) {
        IntegerInterval ii = this.singleIntervals.floor(new IntegerInterval(value));
        return (ii != null && ii.contains(value));
    }

    public boolean add(int value) {
        return this.add(value, value);
    }

    public boolean add(int low, int high) {
        return this.add(new IntegerInterval(low, high));
    }

    public boolean add(IntegerInterval si) {
        boolean ch = false;
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
            ch = (toRemove.size() != 1 || toRemove.get(0).equals(sic));
            for (IntegerInterval si2 : toRemove) {
                singleIntervals.remove(si2);
            }
            singleIntervals.add(sic);
        }
        return ch;
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

    public boolean unionWith(Iterable<IntegerInterval> other) {
        boolean ch = false;
        for (IntegerInterval si : other) {
            if (si.notEmpty()) {
                ch |= this.add(new IntegerInterval(si.getLow(), si.getHigh()));
            }
        }
        return ch;
    }

    @Override
    public boolean unionWith(FiniteIntegerDomain other) {
        return unionWith((Iterable<IntegerInterval>) other);
    }

    public boolean unionWith(IntegerInterval interval) {
        return unionWith(new ItemIterable<>(interval));
    }

    public boolean unionWith(int low, int high) {
        return unionWith(new IntegerInterval(low, high));
    }

    public boolean unionWith(int value) {
        return unionWith(value, value);
    }

    @Override
    public FiniteIntegerDomain intersection(FiniteIntegerDomain other) {
        FiniteIntegerDomain res = this.clone();
        res.intersectWith(other);
        return res;
    }

    @Override
    public boolean intersectWith(FiniteIntegerDomain other) {
        return intersectWith((Iterable<IntegerInterval>) other);
    }

    public boolean intersectWith(IntegerInterval interval) {
        return intersectWith(new ItemIterable<>(interval));
    }

    public boolean intersectWith(int low, int high) {
        return intersectWith(new IntegerInterval(low, high));
    }

    public boolean intersectWith(int value) {
        return intersectWith(value, value);
    }

    @Override
    public FiniteIntegerDomain minus(FiniteIntegerDomain other) {
        FiniteIntegerDomain res = this.clone();
        res.minusWith(other);
        return res;
    }

    @Override
    public boolean minusWith(FiniteIntegerDomain fid) {
        return this.minusWith((Iterable<IntegerInterval>) fid);
    }

    public boolean minusWith(IntegerInterval interval) {
        return minusWith(new ItemIterable<>(interval));
    }

    public boolean minusWith(int low, int high) {
        return minusWith(new IntegerInterval(low, high));
    }

    public boolean minusWith(int value) {
        return minusWith(value, value);
    }

    public boolean minusWith(Iterable<IntegerInterval> other) {
        int oldSize = 0;
        ArrayList<IntegerInterval> sis = new ArrayList<>(2 * singleIntervals.size());
        for (IntegerInterval si : this) {
            sis.add(si);
            oldSize += si.size();
        }
        ArrayList<IntegerInterval> toAdd = new ArrayList<>();
        for (IntegerInterval tr : other) {
            if (tr.notEmpty()) {
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
        }
        this.singleIntervals.clear();
        this.singleIntervals.addAll(sis);
        return (this.size() != oldSize);
    }

    @Override
    public Iterator<IntegerInterval> iterator() {
        return this.singleIntervals.iterator();
    }

    public Iterator<Integer> integerIterator() {
        return new IndirectIterator(iterator());
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

    public boolean intersectWith(Iterable<IntegerInterval> other) {
        ArrayList<IntegerInterval> q = new ArrayList<>();
        int oldSize = 0;
        for (IntegerInterval si : this) {
            oldSize += si.size();
            for (IntegerInterval si2 : other) {
                if (si2.notEmpty()) {
                    IntegerInterval siq = si.intersection(si2);
                    if (siq.notEmpty()) {
                        q.add(siq);
                    }
                }
            }
        }
        this.singleIntervals.clear();
        this.singleIntervals.addAll(q);
        return (oldSize != this.size());
    }

    public void setToSingle(IntegerInterval interval) {
        this.singleIntervals.clear();
        if (interval.notEmpty()) {
            this.singleIntervals.add(interval);
        }
    }

    public void setToSingle(int low, int high) {
        this.setToSingle(new IntegerInterval(low, high));
    }

    public void setToSingle(int value) {
        this.setToSingle(value, value);
    }

    private Pattern getSubPattern() {
        if (subPattern == null) {
            this.subPattern = Pattern.compile("\\[(-?[0-9]+),(-?[0-9]+)\\]|\\{(-?[0-9]+)\\}");
        }
        return this.subPattern;
    }

    @Override
    public FiniteIntegerDomain generate(String text) {
        Matcher matcher = this.getPattern().matcher(text);
        if (matcher.matches()) {
            FiniteIntegerDomain fid = new FiniteIntegerDomain();
            matcher = this.getSubPattern().matcher(text);
            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    fid.add(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                } else {
                    fid.add(Integer.parseInt(matcher.group(3)));
                }
            }
            return fid;
        } else {
            return null;
        }
    }
}