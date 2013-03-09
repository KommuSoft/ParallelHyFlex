/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex.utils;

import java.util.Random;

/**
 *
 * @author kommusoft
 */
public class Utils {
    
    private Utils () {}
    
    public static final Random StaticRandom = new Random();
    
    public static String stringReverse (String inp) {
        StringBuilder sb = new StringBuilder();
        for(int i = inp.length()-1; i >= 0; i--) {
            sb.append(inp.charAt(i));
        }
        return sb.toString();
    }
    
    public static boolean isValidClause (long clause) {
        long inda = clause&0x0FFFFF;
        long indb = (clause>>20)&0x0FFFFF;
        long indc = (clause>>40)&0x0FFFFF;
        long vala = (clause>>60)&1;
        long valb = (clause>>61)&1;
        long valc = (clause>>62)&1;
        return (inda != indb || vala == valb) && (inda != indc || vala == valc) && (indb != indc || valb == valc);
    }
    
    public static String clauseToString (long clause) {
        long inda = clause&0x0FFFFF;
        long indb = (clause>>20)&0x0FFFFF;
        long indc = (clause>>40)&0x0FFFFF;
        long vala = (clause>>60)&1;
        long valb = (clause>>61)&1;
        long valc = (clause>>62)&1;
        return String.format("[%s]=%s or [%s]=%s or [%s]=%s",inda,vala,indb,valb,indc,valc);
    }
    
}
