/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelhyflex;

import parallelhyflex.problemdependent.heuristics.HeuristicType;
import parallelhyflex.problemdependent.solution.Solution;

/**
 *
 * @author kommusoft
 */
public interface ProblemInterface<TSolution extends Solution<TSolution>> {

    double getDepthOfSearch();

    double getIntensityOfMutation();

    int getNumberOfDistanceFunctions();

    int getNumberOfHeuristics();

    int getNumberOfHeuristicsOfType(HeuristicType type);

    int getNumberOfLocalSearchHeuristics();

    int getNumberOfMutationHeuristics();

    int getNumberOfCrossoverHeuristics();

    int getNumberOfRuinRecreateHeuristics();

    HeuristicType getHeuristicType(int heuristic);

    int getNumberOfObjectiveFunctions();

    void setDepthOfSearch(double dos);

    void setIntensityOfMutation(double iom);
}
