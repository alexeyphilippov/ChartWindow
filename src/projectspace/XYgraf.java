package projectspace;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.util.List;

public class XYgraf {

    LineChart<Number, Number> numberLineChart;
    XYChart.Series series1;
    NumberAxis x;
    NumberAxis y;
    ObservableList<XYChart.Data> datas;

    public XYgraf(List<String> strings){
        x = new NumberAxis(0,20,1);
        y = new NumberAxis(27, 29, 0.1);
        numberLineChart = new LineChart<Number, Number>(x, y);
        x.setLabel("Age (years)");
        y.setLabel("Returns to date");
        numberLineChart.setTitle("Investment Overview");
        series1 = new XYChart.Series();
        series1.setName("Equities");
        datas = FXCollections.observableArrayList();
        double[] arr = new double[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = Double.parseDouble(strings.get(i).substring(14, 21));
        }
        for (int i = 0; i < 20; i++) {
            datas.add(new XYChart.Data(i, arr[i]));
        }
        series1.setData(datas);
        numberLineChart.getData().add(series1);
//        numberLineChart.setLayoutY(10);
    }

public Pane getGrafPane(){
    return new Pane(numberLineChart);

}
}
