package projectspace;

import java.io.File;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuSample extends Application {
    Parent currentGraf;
    File file;
    boolean XYgrafOn = false;
    boolean ScatterGrafOn = false;

    final VBox vbox = new VBox();
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



        ToggleGroup group = new ToggleGroup();
        Menu menuFile = new Menu("File");
        MenuItem add = new MenuItem("Choose file");
        MenuItem clear = new Menu("Clear all");

        Menu menuView  = new Menu("View");
        RadioMenuItem chart1 = new RadioMenuItem("chart1");
        RadioMenuItem chart2 = new RadioMenuItem("chart2");
        chart1.setToggleGroup(group);
        chart1.setSelected(true);
        chart2.setToggleGroup(group);
        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            // Has selection.
            if (group.getSelectedToggle() != null) {
                RadioMenuItem button = (RadioMenuItem) group.getSelectedToggle();
                checkState(button.getText());
                System.out.println(XYgrafOn + " " + ScatterGrafOn);
            }
        });

        add.setOnAction(t -> {
            file = fileChooser.showOpenDialog(stage);
            currentGraf = getXYGraf(file);
            vbox.getChildren().addAll(currentGraf);
        });

        clear.setOnAction(e ->{
            vbox.getChildren().removeAll(currentGraf);
        });


        menuFile.getItems().addAll(add,clear);
        menuView.getItems().addAll(chart1,chart2);
        menuBar.getMenus().addAll(menuFile,menuView);

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar,vbox);
        stage.setScene(scene);
        stage.show();
    }
    void checkState (String string){
        if (XYgrafOn && string.equals("chart1")){
            return;
        } else if(!XYgrafOn && ScatterGrafOn && string.equals("chart1")){
            vbox.getChildren().removeAll(currentGraf);
           currentGraf = getXYGraf(file);
            vbox.getChildren().addAll(currentGraf);
            XYgrafOn = true;
            ScatterGrafOn = false;
        } else if (XYgrafOn && !ScatterGrafOn && string.equals("chart2")) {
            vbox.getChildren().removeAll(currentGraf);
            currentGraf = getScatterGraf(file);
            vbox.getChildren().addAll(currentGraf);
            XYgrafOn = false;
            ScatterGrafOn = true;

        }}


    public Parent getXYGraf (File file){
        List<String> strings = FileUtils.readAll(file.getAbsolutePath());
        XYgraf xYgraf = new XYgraf(strings);
        XYgrafOn = true;
        return new Pane(xYgraf.getGrafPane());
    }

    public Parent getScatterGraf (File file){
        List<String> strings = FileUtils.readAll(file.getAbsolutePath());
        ScatterGraf scatterGraf = new ScatterGraf(strings);
        ScatterGrafOn = true;
        return new Pane(scatterGraf.getScatterPane());
    }

}