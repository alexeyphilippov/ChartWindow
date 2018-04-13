package projectspace;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

public class ChartStuffing implements Constants{
    private final XYChart.Series series1;
    private final NumberAxis x;
    private final NumberAxis y;

    private ObservableList<XYChart.Data> createChartData (List<String> strings){
        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        double[] arr = new double[NUMBER_OF_DOTS];
        for (int i = 0; i < NUMBER_OF_DOTS; i++) {
            arr[i] = Double.parseDouble(strings.get(i).substring(USEFULL_INFO_START_INDEX, USEFULL_INFO_END_INDEX));
        }
        for (int i = 0; i < NUMBER_OF_DOTS; i++) {
            datas.add(new XYChart.Data(i, arr[i]));
        }
        return datas;
    }
    public  ChartStuffing (List<String> strings){
        x = new NumberAxis(0,NUMBER_OF_DOTS,SPACE_BETWEEN_TWO_Xs);
        y = new NumberAxis(VALUE_LOWER_BOUND, VALUE_UPPER_BOUND, SPACE_BETWEEN_TWO_Ys);
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
