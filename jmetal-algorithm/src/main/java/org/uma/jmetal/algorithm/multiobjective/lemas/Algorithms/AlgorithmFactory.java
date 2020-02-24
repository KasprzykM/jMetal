package org.uma.jmetal.algorithm.multiobjective.lemas.Algorithms;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.lemas.Utils.Constants;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.jmetal5version.NSGAII;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.jmetal5version.NSGAIIBuilder;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;

import java.util.ArrayList;
import java.util.List;

import static org.uma.jmetal.algorithm.multiobjective.lemas.Utils.Constants.*;

/**
 * Basic Algorithm Factory that uses its builder. Basically wrapper functions for set configurations of builder.
 *
 * @author Michal Kasprzyk <michal0kasprzyk@gmail.com>
 * @since 29/10/2019
 */
@Data
public class AlgorithmFactory<S extends Solution<?>> {

    @Setter(AccessLevel.NONE)
    private final EMASBuilder<S> EMAS_BUILDER = new EMASBuilder<>();

    private List<Algorithm<List<S>>> algorithms;

    public AlgorithmFactory() {
        algorithms = new ArrayList<>();
    }

    public Algorithm<List<S>> getAlgorithm(int index) { return algorithms.get(index); }

    public AlgorithmFactory<S> addEMAS(String name) {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(BASE_AGENT)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(EMAS_DOMINANCE_COMPARATOR)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addAreaEMAS(String name) {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(BASE_AGENT)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(AREA_UNDER_CONTROL_COMPARATOR)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addNotWorseEMAS(String name) {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(BASE_AGENT)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(NOT_WORSE_COMPARATOR)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addReproductiveEMAS(String name) {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .reproCondition(PROGRESSIVE_REPRODUCTION_LEVEL)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(EMAS_DOMINANCE_COMPARATOR)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addReproductiveAreaEMAS(String name) {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .reproCondition(PROGRESSIVE_REPRODUCTION_LEVEL)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(AREA_UNDER_CONTROL_COMPARATOR)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addProgressiveEMAS(String name, int whenToAddOffspring)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(PROGRESSIVE_AGENT)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(EMAS_DOMINANCE_COMPARATOR)
                        .parentToChildComparator(EMAS_DOMINANCE_COMPARATOR)
                        .whenAddOffspringToPopulation(whenToAddOffspring)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addProgressiveAreaEMAS(String name, int whenToAddOffspring)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(PROGRESSIVE_AGENT)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(AREA_UNDER_CONTROL_COMPARATOR)
                        .parentToChildComparator(EMAS_DOMINANCE_COMPARATOR)
                        .whenAddOffspringToPopulation(whenToAddOffspring)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addProgressiveAreaNotWorseEMAS(String name, int whenToAddOffspring)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(PROGRESSIVE_AGENT)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(AREA_UNDER_CONTROL_COMPARATOR)
                        .parentToChildComparator(NOT_WORSE_COMPARATOR)
                        .whenAddOffspringToPopulation(whenToAddOffspring)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addReproductiveProgressiveAreaEMAS(String name, int whenToAddOffspring)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(PROGRESSIVE_AGENT)
                        .reproCondition(PROGRESSIVE_REPRODUCTION_LEVEL)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(AREA_UNDER_CONTROL_COMPARATOR)
                        .parentToChildComparator(EMAS_DOMINANCE_COMPARATOR)
                        .whenAddOffspringToPopulation(whenToAddOffspring)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addAreaCountingEMAS(String name)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(BASE_AGENT)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(AREA_UNDER_CONTROL_COUNTER_COMPARATOR)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addAreaCountingRadiusEMAS(String name)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(RADIUS_AGENT)
                        .radiusToCheckMetAgentsIn(RADIUS_TO_CHECK_MET_AGENTS_IN)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(AREA_UNDER_CONTROL_COUNTER_COMPARATOR)
                        .build());
        return this;
    }


    public AlgorithmFactory<S> addReproductiveProgressiveBaseEMAS(String name, int whenToAddOffspring)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(PROGRESSIVE_AGENT)
                        .reproCondition(PROGRESSIVE_REPRODUCTION_LEVEL)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(EMAS_DOMINANCE_COMPARATOR)
                        .parentToChildComparator(EMAS_DOMINANCE_COMPARATOR)
                        .whenAddOffspringToPopulation(whenToAddOffspring)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addRadiusBaseEMAS(String name)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(RADIUS_AGENT)
                        .radiusToCheckMetAgentsIn(RADIUS_TO_CHECK_MET_AGENTS_IN)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(EMAS_DOMINANCE_COMPARATOR)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addRadiusAreaEMAS(String name)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(RADIUS_AGENT)
                        .radiusToCheckMetAgentsIn(RADIUS_TO_CHECK_MET_AGENTS_IN)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(AREA_UNDER_CONTROL_COMPARATOR)
                        .build());
        return this;
    }

    public AlgorithmFactory<S> addRadiusNotWorseEMAS(String name)
    {
        algorithms.add(
                EMAS_BUILDER.emasType(BASE_EMAS)
                        .agentType(RADIUS_AGENT)
                        .radiusToCheckMetAgentsIn(RADIUS_TO_CHECK_MET_AGENTS_IN)
                        .algorithmName(name)
                        .allowKnowledgeExchange(false)
                        .comparator(NOT_WORSE_COMPARATOR)
                        .build());
        return this;
    }

    public NSGAII<DoubleSolution> createBaseNSGAII(int initialPopulationSize, int maxEvaluations)
    {
        return new NSGAIIBuilder<>(Constants.PROBLEM,
                Constants.XOP, Constants.MOP, initialPopulationSize)
                .setSelectionOperator(
                        new BinaryTournamentSelection<>(
                                new RankingAndCrowdingDistanceComparator<>()))
                .setMaxEvaluations(maxEvaluations)
                .setVariant(NSGAIIBuilder.NSGAIIVariant.Measures)
                .build();
    }

}