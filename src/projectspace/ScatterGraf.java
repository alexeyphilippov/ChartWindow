package projectspace;

import javafx.scene.Parent;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class ScatterGraf extends StackPane {

        private final ScatterChart<Number,Number> scatterChart;

        public ScatterGraf(ChartStuffing chartStuffing){
            scatterChart = new ScatterChart<Number,Number>(chartStuffing.getX(),chartStuffing.getY());
            scatterChart.setTitle("Investment Overview");
            scatterChart.getData().addAll(chartStuffing.getSeries1());
        }

    public Parent getScatterPane (){
            return new Pane(scatterChart);
    }
}