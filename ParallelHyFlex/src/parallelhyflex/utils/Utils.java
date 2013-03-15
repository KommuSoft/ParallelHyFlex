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
    public static<T> void randomPermutate (T[] vals) {
        T tmp;
        int ind;
        for(int i = 0, j = vals.length; j > 1; i++, j--) {
            ind = i+Utils.StaticRandom.nextInt(j);
            tmp = vals[i];
            vals[i] = vals[ind];
            vals[ind] = tmp;
        }
    }
    
}
