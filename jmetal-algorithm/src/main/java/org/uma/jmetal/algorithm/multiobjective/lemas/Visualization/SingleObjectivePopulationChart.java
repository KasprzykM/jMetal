package org.uma.jmetal.algorithm.multiobjective.lemas.Visualization;

import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.lemas.Utils.Constants;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.front.Front;
import org.uma.jmetal.util.front.impl.ArrayFront;
import org.uma.jmetal.util.front.util.FrontUtils;
import org.uma.jmetal.util.point.PointSolution;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class SingleObjectivePopulationChart<S extends Solution<?>> extends BaseChart<S> {

//    public SingleObjectivePopulationChart() {
//        super();
//        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
//        chart.addSeries("population", new double[]{0}, new double[]{0});
//
////        this.drawReferenceFront();
//    }

    public SingleObjectivePopulationChart(List<Algorithm<S>> algorithmToShow) {
        super();
        this.chart.getStyler().setLegendVisible(false);
        this.getChart().setTitle("Populacja");
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setXAxisMax(Constants.PROBLEM.createSolution().getUpperBound(0));
        chart.getStyler().setXAxisMin(Constants.PROBLEM.createSolution().getLowerBound(0));
        chart.getStyler().setYAxisMax(25.0);
        chart.getStyler().setYAxisMin(0.0);

        for (Algorithm alg : algorithmToShow) {

            String name = alg.getName();
//            if(((JMetal5BaseEMAS)alg).getWhenAddOffspringToPopulation()==1){
//                name = name + "Elite";
//            }
            chart.addSeries(name, new double[]{0}, new double[]{0});
        }
//        this.drawReferenceFront();
    }

    private void drawReferenceFront() {
        Front front = null;
        try {
            String fileName = "../jMetal/jmetal-problem/src/test/resources/pareto_fronts/" + Constants.PROBLEM.getName() + ".pf";
//            front = new ArrayFront("../jMetal/jmetal-problem/src/test/resources/pareto_fronts/ZDT1.pf");
            front = new ArrayFront(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<PointSolution> normalizedFront = FrontUtils.convertFrontToSolutionList(front);
        chart.addSeries("reference front", normalizedFront.stream().
                        mapToDouble(n -> n.getObjective(0)).toArray()
                , normalizedFront.stream().mapToDouble(n -> n.getObjective(1)).toArray());
    }


    @Override
    public void update(List<S> population) {
        chart.updateXYSeries(
                "population",
                population.stream().map(solution -> solution.getObjective(0)).collect(Collectors.toList()),
                population.stream().map(solution -> solution.getObjective(1)).collect(Collectors.toList()),
                null);
    }

    @Override
    public void update(List<S> population, String seriesName) {
        chart.updateXYSeries(
                seriesName,
                population.stream().map(solution -> solution.getVariable(0)).collect(Collectors.toList()),
                population.stream().map(solution -> solution.getObjective(0)).collect(Collectors.toList()),
                null);
    }

}

