/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.hyperheuristics.adaphh.records;

import parallelhyflex.hyperheuristics.adaphh.AdapHH;


public class AdapHHHybridRelaxationHeuristicRecord extends AdapHHHeuristicRecord {

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex) {
        super(adaphh, heuristicIndex);
    }

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset) {
        super(adaphh, heuristicIndex, tabuDurationOffset);
    }

    public AdapHHHybridRelaxationHeuristicRecord(AdapHH adaphh, int heuristicIndex, int tabuDurationOffset, int tabuDurationLimit) {
        super(adaphh, heuristicIndex, tabuDurationOffset, tabuDurationLimit);
    }
    
}
