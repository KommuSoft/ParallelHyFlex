/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.hyperheuristics.adaphh;

/**
 *
 * @author kommusoft
 */
public class AdaptiveIterationLimitedListbasedThresholdMoveAcceptor extends AdapHHPointerBase {
    
    public AdaptiveIterationLimitedListbasedThresholdMoveAcceptor (AdapHH adaphh) {
        super(adaphh);
    }
    
    public boolean acceptMove (int sa, int s) {
        return false;
    }
    
}
