package org.uma.jmetal.algorithm.multiobjective.lemas.Comparators;

import org.uma.jmetal.algorithm.multiobjective.lemas.Agents.JMetal5Agent;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.comparator.DominanceComparator;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Extension class from {@link DominanceComparator} to allow two {@link JMetal5Agent} as parameters.
 * @author dr inż. Siwik Leszek siwik@agh.edu.pl
 * @since 8/27/2018
 * */
public class EmasDominanceComparator<Agent extends JMetal5Agent<?>> implements Comparator<Agent>, Serializable {

    /**
     * Compares two Agents genotypes using {@link DominanceComparator#compare(Solution, Solution)}.
     * @param a1 agent to compare.
     * @param a2 agent to compare.
     * @return result of comparison.
     * */
    @SuppressWarnings("unchecked")
    @Override
    public int compare(Agent a1, Agent a2) {
        return new DominanceComparator().compare(a1.genotype, a2.genotype);
    }
}
