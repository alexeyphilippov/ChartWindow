package projectspace;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;


public class ScatterGraf extends StackPane {



        final NumberAxis xAxis = new NumberAxis(0, 20, 1);
        final NumberAxis yAxis = new NumberAxis(27, 29, 0.1);
        final ScatterChart<Number,Number> sc = new
                ScatterChart<Number,Number>(xAxis,yAxis);
    ObservableList<XYChart.Data> datas;
        public ScatterGraf(List<String> strings){
            xAxis.setLabel("Age (years)");
            yAxis.setLabel("Returns to date");
            sc.setTitle("Investment Overview");

            XYChart.Series series1 = new XYChart.Series();
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
            sc.getData().addAll(series1);
        }

    public Parent getScatterPane (){
            return new Pane(sc);
    }
}