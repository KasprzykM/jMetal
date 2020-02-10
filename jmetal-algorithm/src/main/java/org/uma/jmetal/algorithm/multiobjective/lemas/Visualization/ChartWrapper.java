package org.uma.jmetal.algorithm.multiobjective.lemas.Visualization;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.lemas.Algorithms.JMetal5BaseEMAS;
import org.uma.jmetal.algorithm.multiobjective.lemas.Utils.Constants;
import org.uma.jmetal.algorithm.multiobjective.lemas.Utils.config.MeetingType;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.front.Front;
import org.uma.jmetal.util.front.imp.ArrayFront;
import org.uma.jmetal.util.front.util.FrontNormalizer;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChartWrapper<S extends Solution<?>> {
    protected final SwingWrapper<XYChart> wrapper;
    protected List<BaseChart<S>> charts;

    protected Front referenceFront;
    protected final String FRONT_FILE_NAME = Constants.REF_FRONT_DIR + Constants.PROBLEM.getName() + ".pf";

    public ChartWrapper() {
        charts = new ArrayList<>();
        charts.add(new PopulationChart<>());
        charts.add(new HVRChart<>());
        wrapper = new SwingWrapper<>(charts.stream().map(BaseChart::getChart).collect(Collectors.toList()));
        wrapper.displayChartMatrix();
    }

    public ChartWrapper(List<Algorithm<S>> algorithmToShow, int numberOFDecisionVariablesToShow) {
        initializeReferenceFronts();
        charts = new ArrayList<>();
        charts.add(new PopulationChart<>(algorithmToShow));
        charts.add(new PopulationChart<>(algorithmToShow, true));

        /*TODO: Usunąć te nie używane. Zcommonować to.. */
        //charts.add(new NoReferencePopulationChart(algorithmToShow));
        charts.add(new HVChart<>(algorithmToShow, referenceFront));
        //charts.add(new HVRChart(algorithmToShow));
        charts.add(new IGDPlusChart<>(algorithmToShow, referenceFront));
        //charts.add(new EvaluationHVRChart(algorithmToShow));
        //charts.add(new EvaluationIGDPlusChart(algorithmToShow));
//        for (int i = 0; i < numberOFDecisionVariablesToShow; i++) {
//            charts.add(new SingleVariableChart(algorithmToShow, i));
//        }
        //charts.add(new LegendChart(algorithmToShow));

        charts.add(new MeetingsChart<>(algorithmToShow, MeetingType.I_AM_BETTER));
        charts.add(new MeetingsChart<>(algorithmToShow, MeetingType.NEITHER_IS_BETTER));
        charts.add(new PopulationSizeChart<>(algorithmToShow));

        charts.forEach(chart -> chart.getChart().getStyler().setToolTipsEnabled(true));

        wrapper = new SwingWrapper<>(charts.stream().map(BaseChart::getChart).collect(Collectors.toList()), 3, 3);
        wrapper.displayChartMatrix();
    }

    private void initializeReferenceFronts()
    {
        try{
            referenceFront = new ArrayFront(FRONT_FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateChart(List<S> data) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < charts.size(); i++) {
                    charts.get(i).update(data);
                    wrapper.repaintChart(i);
            }
        });
    }

    public void updateChart(List<S> data, String seriesName, JMetal5BaseEMAS emas, int seriesNumber) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < charts.size(); i++) {
                charts.get(i).update(data, seriesName, emas, seriesNumber);
                wrapper.repaintChart(i);
            }
        });
    }


    public void updateChart(List<S> data, String seriesName, Algorithm emas, int seriesNumber) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < charts.size(); i++) {
                charts.get(i).update(data, seriesName, emas, seriesNumber);
                wrapper.repaintChart(i);
            }
        });

    }


    public void updateChart(List<S> data, String seriesName) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < charts.size(); i++) {
                charts.get(i).update(data, seriesName);
                wrapper.repaintChart(i);
            }
        });
    }
}


