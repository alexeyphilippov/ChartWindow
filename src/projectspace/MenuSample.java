package projectspace;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.RE;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuSample extends Application {

    File file;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        final FileChooser fileChooser = new FileChooser();
        stage.setTitle("Menu Sample");
        Scene scene = new Scene(new VBox(), 500, 500);
        scene.setFill(Color.OLDLACE);



        MenuBar menuBar = new MenuBar();

        final VBox vbox = new VBox();


        Menu menuFile = new Menu("File");
        MenuItem add = new MenuItem("Choose file");
        add.setOnAction(t -> {
            file = fileChooser.showOpenDialog(stage);
            vbox.getChildren().addAll(getGraf(file));
        });


        menuFile.getItems().addAll(add);

        menuBar.getMenus().addAll(menuFile);

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar,vbox);
        stage.setScene(scene);
        stage.show();
    }

//    public Pane getGrafPane(File file) {
//        List<String> strings = FileUtils.readAll(file.getAbsolutePath());
//        NumberAxis x = new NumberAxis();
//        NumberAxis y = new NumberAxis(27, 29, 0.1);
//
//        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x, y);
//        numberLineChart.setTitle("Series");
//        XYChart.Series series1 = new XYChart.Series();
//        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
//
//        double[] arr = new double[20];
//        for (int i = 0; i < 20; i++) {
//            arr[i] = Double.parseDouble(strings.get(i).substring(14, 21));
//        }
//        for (int i = 0; i < 20; i++) {
//            datas.add(new XYChart.Data(i, arr[i]));
//        }
//
//        series1.setData(datas);
//
//        numberLineChart.getData().add(series1);
//        numberLineChart.setLayoutY(30);
//
//        return new Pane(numberLineChart,getRdioButtons());
//    }

    public Parent getGraf (File file){
        List<String> strings = FileUtils.readAll(file.getAbsolutePath());
        XYgraf xYgraf = new XYgraf(strings);
        return new Pane(xYgraf.getGrafPane());
    }

    public Pane getRdioButtons() {
        Label label = new Label("Your Gender: ");
        Label labelInfo = new Label();
        labelInfo.setTextFill(Color.BLUE);

        // Group
        ToggleGroup group = new ToggleGroup();

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                // Has selection.
                if (group.getSelectedToggle() != null) {
                    RadioButton button = (RadioButton) group.getSelectedToggle();
                    System.out.println("Button: " + button.getText());
                    labelInfo.setText("You are " + button.getText());
                }
            }
        });

        // Radio 1: Male
        RadioButton button1 = new RadioButton("Male");
        button1.setToggleGroup(group);


        // Radio 2: Female.
        RadioButton button2 = new RadioButton("Female");
        button2.setToggleGroup(group);

        RadioButton button3 = new RadioButton("not stated");
        button3.setSelected(true);
        button3.setToggleGroup(group);

        HBox root = new HBox();
        root.setPadding(new Insets(50));
        root.getChildren().addAll(label, button1, button2,button3, labelInfo);
        root.setLayoutY(-40);
        return root;
    }
}