package projectspace;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuSample extends Application {
    FileUtils fileUtils = new FileUtils();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        final FileChooser fileChooser = new FileChooser();
        stage.setTitle("Menu Sample");
        Scene scene = new Scene(new VBox(), 400, 350);
        scene.setFill(Color.OLDLACE);


        MenuBar menuBar = new MenuBar();

        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));

        Menu menuFile = new Menu("File");
        MenuItem add = new MenuItem("Choose file");
        add.setOnAction(t -> {
            File file = fileChooser.showOpenDialog(stage);
            getGrafStage(new Stage(),file);
        });


        menuFile.getItems().addAll(add);


        menuBar.getMenus().addAll(menuFile);
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, vbox);
        stage.setScene(scene);
        stage.show();
    }

    public void getGrafStage(Stage stage, File file){
        List<String> strings = fileUtils.readAll(file.getAbsolutePath());
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
        stage.setScene(scene);

        stage.show();
    }
}