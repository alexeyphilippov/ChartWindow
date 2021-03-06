package projectspace;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuSample extends Application  implements Constants{
    private Parent currentGraf;
    private static Stage stage;
    private File file;
    private ChartStuffing chartStuffing;
    private boolean XYgrafOn = false;
    private boolean ScatterGrafOn = false;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Chart app");
        stage.setResizable(false);
        Scene scene = new Scene(createContent(), 490, 450);
        scene.setFill(Color.OLDLACE);
        stage.setScene(scene);
        stage.show();
    }

    private Parent createContent() {
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
                showAlertWithoutHeaderText();
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
        vvBox.getChildren().addAll(menuBar, vbox);
        return vvBox;
    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("File must be chosen");

        alert.setHeaderText(null);
        alert.setContentText("Choose file first!");
        if (currentGraf == null && !XYgrafOn && !ScatterGrafOn) {
            alert.showAndWait();
        }
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
            currentGraf = getScatterGraf();
            vbox.getChildren().addAll(currentGraf);
            XYgrafOn = false;
            ScatterGrafOn = true;
        }
    }

    private Parent getXYGraf() {
        XYgraf xYgraf = new XYgraf(chartStuffing);
        XYgrafOn = true;
        return new Pane(xYgraf.getGrafPane());
    }

    private Parent getScatterGraf() {
        ScatterGraf scatterGraf = new ScatterGraf(chartStuffing);
        ScatterGrafOn = true;
        return new Pane(scatterGraf.getScatterPane());
    }

}