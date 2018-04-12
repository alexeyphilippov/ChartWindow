package projectspace;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ChartStuffing {
    private final XYChart.Series series1;
    private final NumberAxis x;
    private final NumberAxis y;

    private ObservableList<XYChart.Data> createChartData (List<String> strings){
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        double[] arr = new double[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = Double.parseDouble(strings.get(i).substring(14, 21));
        }
        for (int i = 0; i < 20; i++) {
            datas.add(new XYChart.Data(i, arr[i]));
        }
        return datas;
    }
    public  ChartStuffing (List<String> strings){
        x = new NumberAxis(0,20,1);
        y = new NumberAxis(27, 29, 0.1);
        x.setLabel("Age (years)");
        y.setLabel("Returns to date");
        series1 = new XYChart.Series();
        series1.setName("Equities");
        series1.setData(createChartData(strings));
    }

    public XYChart.Series getSeries1() {
        return series1;
    }

    public NumberAxis getX() {
        return x;
    }

    public NumberAxis getY() {
        return y;
    }
}
