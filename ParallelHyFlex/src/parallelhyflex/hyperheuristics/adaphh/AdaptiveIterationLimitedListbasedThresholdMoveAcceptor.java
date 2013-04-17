/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.hyperheuristics.adaphh;

import parallelhyflex.algebra.collections.CircularList;
import parallelhyflex.algebra.collections.StridingList;

/**
 *
 * @author kommusoft
 */
public class AdaptiveIterationLimitedListbasedThresholdMoveAcceptor extends AdapHHPointerBase {

    private int l, k, adapt_iterations, w_iterations = 0, best_list_index = 0;
    private StridingList<Double> bestList;

    public AdaptiveIterationLimitedListbasedThresholdMoveAcceptor(AdapHH adaphh) {
        super(adaphh);
        this.bestList = new StridingList<>(l);
        double best = adaphh.getBestObjectiveSolution();
        for (int i = 0; i < l; i++) {
            this.bestList.add(best);
        }
    }

    public void acceptMove(int sa, int s, int sb) {
        double fs = this.getAdapHH().getObjectiveFunction(s);
        double fsa = this.getAdapHH().getObjectiveFunction(sa);
        double fsb = this.getAdapHH().getObjectiveFunction(sb);
        if(adapt_iterations >= AdapHH.AILLA_K && best_list_index < l-1) {
            best_list_index++;
        }
        if (fsa < fs) {
            this.getAdapHH().copySolution(sa, s);
            w_iterations = 0;
            if (fsa < fsb) {
                this.best_list_index = 1;
                this.getAdapHH().copySolution(sa, sb);
                this.adapt_iterations = 0;
                this.bestList.add(fsb);
            }
        } else if (fs == fsa) {
            this.getAdapHH().copySolution(sa, s);
        } else {
            this.w_iterations++;
            this.adapt_iterations++;
            if(w_iterations >= k && fsa <= this.bestList.get(this.bestList.size()-best_list_index)) {
                this.getAdapHH().copySolution(sa, s);
                this.w_iterations = 0;
            }
        }
    }
}
