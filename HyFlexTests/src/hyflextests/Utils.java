package hyflextests;

/**
 *
 * @author kommusoft
 */
public class Utils {

    static int rouletteWheel(double[] pv) {
        double sum = 0.0d;
        double min = 0.0d;
        for(int i = 0; i < pv.length; i++) {
            sum += pv[i];
            min = Math.min(pv[i], min);
        }
        double prob = Math.random()*sum-min*pv.length;
        for(int i = 0; i < pv.length; i++) {
            prob -= pv[i]-min;
            if(prob <= 0) {
                return i;
            }
        }
        return pv.length-1;
    }
    
}
