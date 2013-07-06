package parallelhyflex.interference;

import parallelhyflex.utils.CompactBitArray;

/**
 *
 * @author kommusoft
 */
public class CompactBitArrayInterferenceStructure implements InterferenceStructure<Integer> {

    private final CompactBitArray cba;
    private final int n;

    public CompactBitArrayInterferenceStructure(int n) {
        this.cba = new CompactBitArray((n * (n - 1) / 2));
        this.n = n;
    }

    public void setInterference(int i, int j, boolean value) {
        if (i != j && i < n && j < n) {
            this.cba.set(this.calculateIndex(i, j), value);
        }
    }

    public int calculateIndex(int i, int j) {
        if (j >= i) {
            return i * (i - 1) / 2 + j - i - 1;
        } else {
            return j * (j - 1) / 2 + i - j - 1;
        }
    }

    @Override
    public boolean interferes(Integer i, Integer j) {
        if (i != j && i < n && j < n) {
            return this.cba.get(this.calculateIndex(i, j));
        } else {
            return false;
        }
    }
}
