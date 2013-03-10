package parallelhyflex.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author kommusoft
 */
public interface ICompactBitArray {

    void clearTail();

    boolean get(int index);

    long getBit(int index);

    long getBit(long index);

    int hashCode();

    void readSolution(DataInputStream is) throws IOException;

    void resetRange(int fromIndex, int toIndex);

    boolean satisfiesClause(long constraint);

    void set(int index, boolean value);

    void setRange(int fromIndex, int toIndex);

    void swap(int index);

    void swapRange(int fromIndex, int toIndex);

    void writeSolution(DataOutputStream os) throws IOException;
    
}
