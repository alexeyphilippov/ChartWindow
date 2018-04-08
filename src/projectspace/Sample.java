package projectspace;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.List;

public class Sample extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        FileUtils fileUtils = new FileUtils();
        List<String> strings = fileUtils.readAll("/Users/aleksejfilippov/Desktop/fold2/HistoricalQuotes.csv");

        primaryStage.setTitle("JavaFX Chart (Series)");

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis(27, 29,0.1);

        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x,y);
        numberLineChart.setTitle("Series");
        XYChart.Series series1 = new XYChart.Series();
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();

        double[] arr = new double[20];
        for(int i=0; i<20; i++) {
            arr[i] = Double.parseDouble(strings.get(i).substring(14,21));
        }
        for(int i=0; i<20; i++){
            datas.add(new XYChart.Data(i, arr[i]));
        }

        series1.setData(datas);


        Scene scene = new Scene(numberLineChart, 600,600);
        numberLineChart.getData().add(series1);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}