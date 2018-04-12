package projectspace;

import javafx.scene.chart.LineChart;
import javafx.scene.layout.Pane;

public class XYgraf {

    private final LineChart<Number, Number> numberLineChart;

    public XYgraf(ChartStuffing chartStuffing){
        numberLineChart = new LineChart<Number, Number>(chartStuffing.getX(), chartStuffing.getY());
        numberLineChart.setTitle("Investment Overview");
        numberLineChart.getData().add(chartStuffing.getSeries1());
    }

public Pane getGrafPane(){
    return new Pane(numberLineChart);

}
}
