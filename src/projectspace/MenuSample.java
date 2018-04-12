package projectspace;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuSample extends Application {
    private Parent currentGraf;
    private static Stage notificationWindow = new Stage();
    private static Stage stage;
    private File file;
    private ChartStuffing chartStuffing;
    private boolean XYgrafOn = false;
    private boolean ScatterGrafOn = false;
    private final VBox vbox = new VBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Menu Sample");
        stage.setOnCloseRequest(event -> {
            stage.close();
            notificationWindow.close();
        });
        stage.setResizable(false);
        Scene scene = new Scene(createContent(), 490, 450);
        scene.setFill(Color.OLDLACE);
        stage.setScene(scene);
        stage.show();
    }
    private Parent createContent(){
        final FileChooser fileChooser = new FileChooser();
        MenuBar menuBar = new MenuBar();
        ToggleGroup group = new ToggleGroup();
        Menu menuFile = new Menu("File");
        MenuItem add = new MenuItem("Choose file");
        MenuItem clear = new Menu("Clear all");

        Menu menuView = new Menu("View");
        RadioMenuItem linerChart = new RadioMenuItem("liner");
        RadioMenuItem scatterChart = new RadioMenuItem("scatter");
        linerChart.setToggleGroup(group);
        scatterChart.setToggleGroup(group);
        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (group.getSelectedToggle() != null) {
                RadioMenuItem button = (RadioMenuItem) group.getSelectedToggle();
                checkState(button.getText());
                if (currentGraf == null && !XYgrafOn && !ScatterGrafOn) {
                    scatterChart.setSelected(false);
                    linerChart.setSelected(false);
                }
                System.out.println(XYgrafOn + " " + ScatterGrafOn);
            }
        });

        add.setOnAction(t -> {
            file = fileChooser.showOpenDialog(stage);
            chartStuffing = new ChartStuffing(FileUtils.readAll(file.getAbsolutePath()));
//            strings = FileUtils.readAll(file.getAbsolutePath());
            currentGraf = getXYGraf();
            vbox.getChildren().addAll(currentGraf);
            linerChart.setSelected(true);
        });

        clear.setOnAction(e -> {
            vbox.getChildren().removeAll(currentGraf);
            XYgrafOn = false;
            ScatterGrafOn = false;
            currentGraf = null;
            scatterChart.setSelected(false);
            linerChart.setSelected(false);
        });


        menuFile.getItems().addAll(add, clear);
        menuView.getItems().addAll(linerChart, scatterChart);
        menuBar.getMenus().addAll(menuFile, menuView);
        VBox vvBox = new VBox();
        vvBox.getChildren().addAll(menuBar,vbox);
        return vvBox;
    }

    private void checkState(String string) {
        if (XYgrafOn && string.equals("liner")) {
            return;
        } else if (ScatterGrafOn && string.equals("scatter")) {
            return;
        } else if (!XYgrafOn && ScatterGrafOn && string.equals("liner")) {
            vbox.getChildren().removeAll(currentGraf);
            currentGraf = getXYGraf();
            vbox.getChildren().addAll(currentGraf);

            XYgrafOn = true;
            ScatterGrafOn = false;
        } else if (XYgrafOn && !ScatterGrafOn && string.equals("scatter")) {
            vbox.getChildren().removeAll(currentGraf);
            currentGraf = getScatterGraf(file);
            vbox.getChildren().addAll(currentGraf);
            XYgrafOn = false;
            ScatterGrafOn = true;

        } else if (currentGraf == null && !XYgrafOn && !ScatterGrafOn) {
            getNotifWindow();
        }
    }

    private void getNotifWindow() {
        Label label = new Label("Choose file first");
        Button okButton = new Button("Ok");
        okButton.setOnMouseClicked(e -> {
            notificationWindow.hide();
        });
        okButton.setLayoutY(30);
        okButton.setLayoutX(30);
        Pane pane = new Pane(label, okButton);
        notificationWindow.setScene(new Scene(pane, 100, 70));
        notificationWindow.show();
    }


    private Parent getXYGraf() {
        XYgraf xYgraf = new XYgraf(chartStuffing);
        XYgrafOn = true;
        return new Pane(xYgraf.getGrafPane());
    }

    private Parent getScatterGraf(File file) {
        ScatterGraf scatterGraf = new ScatterGraf(chartStuffing);
        ScatterGrafOn = true;
        return new Pane(scatterGraf.getScatterPane());
    }

}