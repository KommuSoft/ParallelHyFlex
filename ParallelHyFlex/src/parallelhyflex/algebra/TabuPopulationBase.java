package parallelhyflex.algebra;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Stack;


public class TabuPopulationBase<TIndividual> extends PopulationBase<TIndividual> implements TabuPopulation<TIndividual> {
    
    private final PriorityQueue<TabuedIndividual<TIndividual>> tabuQueue = new PriorityQueue<>();
    private int time = 0;

    @Override
    public boolean tabu(TIndividual individual, int ticks) {
        if(this.remove(individual)) {
            int freetime = this.getTime()+ticks;
            tabuQueue.add(new TabuedIndividual(freetime,individual));
            return true;
        }
        return false;
    }
    
    @Override
    public boolean tabu(Predicate<TIndividual> predicate, int ticks) {
        Stack<TIndividual> tabulist = new Stack<TIndividual>();
        boolean mod = false;
        for(TIndividual ti : this) {
            if(predicate.generate(ti)) {
                tabulist.add(ti);
            }
        }
        for(TIndividual ti : tabulist) {
            mod |= this.tabu(ti, ticks);
        }
        return mod;
    }

    @Override
    public boolean isTabued(TIndividual individual) {
        return this.getRemainingTabuTicks(individual) >= 0;
    }

    @Override
    public Collection<TIndividual> tabuTick() {
        HashSet<TIndividual> untabued = new HashSet<>();
        TIndividual ind;
        this.time++;
        while(this.tabuQueue.size() > 0 && this.tabuQueue.peek().freetime <= this.getTime()) {
            ind = this.tabuQueue.poll().individual;
            this.add(ind);
            untabued.add(ind);
        }
        return untabued;
    }

    @Override
    public int getRemainingTabuTicks(TIndividual individual) {
        for(TabuedIndividual<TIndividual> ti : this.tabuQueue) {
            if(ti.getIndividual().equals(individual)) {
                return ti.getFreetime()-this.getTime();
            }
        }
        return -1;
    }

    @Override
    public boolean untabu(TIndividual individual) {
        for (Iterator<TabuedIndividual<TIndividual>> it = this.tabuQueue.iterator(); it.hasNext();) {
            TabuedIndividual<TIndividual> ti = it.next();
            if(ti.getIndividual().equals(individual)) {
                it.remove();
                this.add(individual);
                return true;
            }
        }
        return false;
    }  

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    @Override
    public int getNumberOfTabuedIndividuals() {
        return this.tabuQueue.size();
    }

    @Override
    public boolean tabu(Collection<TIndividual> individuals, int ticks) {
        boolean mod = false;
        for(TIndividual ti : individuals) {
            mod |= this.tabu(ti, ticks);
        }
        return mod;
    }

    @Override
    public Collection<TIndividual> getTabuedIndividuals() {
        HashSet<TIndividual> tabued = new HashSet<TIndividual>();
        for(TabuedIndividual<TIndividual> ti : this.tabuQueue) {
            tabued.add(ti.getIndividual());
        }
        return tabued;
    }

    private static class TabuedIndividual<T> implements Comparable<TabuedIndividual<?>> {
        
        private final int freetime;
        private final T individual;

        public TabuedIndividual(int freetime, T individual) {
            this.freetime = freetime;
            this.individual = individual;
        }

        @Override
        public int compareTo(TabuedIndividual<?> o) {
            return ((Integer) this.getFreetime()).compareTo(o.getFreetime());
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 89 * hash + Objects.hashCode(this.individual);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final TabuedIndividual<T> other = (TabuedIndividual<T>) obj;
            if (!Objects.equals(this.individual, other.individual)) {
                return false;
            }
            return true;
        }

        /**
         * @return the freetime
         */
        public int getFreetime() {
            return freetime;
        }

        /**
         * @return the individual
         */
        public T getIndividual() {
            return individual;
        }
    }
    
}
