package parallelhyflex.genetic;

import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class UniformCrossover implements CrossoverImplementation {

    private static final UniformCrossover instance = new UniformCrossover();

    public static UniformCrossover getInstance() {
        return instance;
    }

    private UniformCrossover() {
    }

    @Override
    public int[] crossover(int[]... parents) {
        int m = parents.length;
        if (m <= 0x00) {
            return null;
        }
        int n = parents[0].length;
        for (int i = 1; i < m; i++) {
            n = Math.min(n, parents[i].length);
        }
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = parents[Utils.nextInt(m)][i];
        }
        return values;
    }

    @Override
    public int[] crossover(int[] genes, int[]... parents) {
        int m = parents.length;
        if (m <= 0x00) {
            return null;
        }
        int n = parents[0].length;
        for (int i = 0x01; i < m; i++) {
            n = Math.min(n, parents[i].length);
        }
        int k = genes.length;
        int[] values = new int[n];
        int j = 0x00;
        for (int i = 0x00; i < k; i++) {
            int l = Utils.nextInt(m);
            int J = j + genes[i];
            for (; j < J; j++) {
                values[j] = parents[l][j];
            }
        }
        return values;
    }

    @Override
    public void crossoverLocal(int[] genes, int[]... parents) {
        int m = parents.length;
        if (m > 0x00) {
            int n = parents[0].length;
            for (int i = 0x01; i < m; i++) {
                n = Math.min(n, parents[i].length);
            }
            int k = genes.length;
            int[] values = parents[0x00];
            int j = 0x00;
            for (int i = 0x00; i < k; i++) {
                int l = Utils.nextInt(m);
                int J = j + genes[i];
                if (l > 0x00) {
                    for (; j < J; j++) {
                        values[j] = parents[l][j];
                    }
                }
                else {
                    j = J;
                }
            }
        }
    }

    @Override
    public void crossoverLocal(int[]... parents) {
        int m = parents.length;
        if (m > 0x00) {
            int n = parents[0].length;
            for (int i = 1; i < m; i++) {
                n = Math.min(n, parents[i].length);
            }
            int[] values = parents[0];
            for (int i = 0; i < n; i++) {
                int k = Utils.nextInt(m);
                if (k > 0x00) {
                    values[i] = parents[k][i];
                }
            }
        }
    }
}
