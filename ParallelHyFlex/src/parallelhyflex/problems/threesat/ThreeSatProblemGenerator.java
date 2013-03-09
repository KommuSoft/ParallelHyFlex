package parallelhyflex.problems.threesat;

import parallelhyflex.utils.CompactBitArray;
import parallelhyflex.utils.Utils;

/**
 *
 * @author kommusoft
 */
public class ThreeSatProblemGenerator {
    
    private int n, k;
    
    public ThreeSatProblemGenerator (int n, int k) {
        this.n = n;
        this.k = k;
    }
    
    public ThreeSatProblem generateProblem () {
        long[] constraints = new long[k];
        CompactBitArray cba = CompactBitArray.randomInstance(this.n);
        //System.out.println("A solution will be: "+cba);
        for(int i = 0; i < k; i++) {
            long fill = (((long) Utils.StaticRandom.nextInt(8))<<60)|(((long) Utils.StaticRandom.nextInt(n))<<40)|(((long) Utils.StaticRandom.nextInt(n))<<20)|((long) Utils.StaticRandom.nextInt(n));
            int ci = Utils.StaticRandom.nextInt(3);
            long index = ((fill>>(20*ci))&0x0FFFFF);
            //System.out.println("Old Clause: "+Utils.clauseToString(fill));
            //System.out.println(String.format("Indices are %s>%s %s>%s %s>%s",fill&0x0FFFFF,cba.getBit(fill&0x0FFFFF),(fill>>20)&0x0FFFFF,cba.getBit((fill>>20)&0x0FFFFF),(fill>>40)&0x0FFFFF,cba.getBit((fill>>40)&0x0FFFFF)));
            fill &= ~(0x1L<<(60+ci));
            //System.out.println("After masking:"+Utils.clauseToString(fill)+" with mask "+);
            fill |= cba.getBit(index)<<(60+ci);
            
            //System.out.println(String.format("Vals with %s: %s",ci,String.format("%3s",Long.toBinaryString(fill>>60)).replace(" ","0")));
            //System.out.println("New Clause: "+Utils.clauseToString(fill)+" after modifying "+index);
            
            /*if(!cba.satisfiesClause(fill)) {
                System.out.println(String.format("KERNEL PANIC with %s",ci));
            }*/
            if(Utils.isValidClause(fill)) {
                constraints[i] = fill;
            }
            else {
                i--;
                //System.out.println("REDO");
            }
        }
        return new ThreeSatProblem(this.n,constraints);
    }

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * @return the k
     */
    public int getK() {
        return k;
    }

    /**
     * @param k the k to set
     */
    public void setK(int k) {
        this.k = k;
    }
    
}
