package org.rec.os.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * Reference: https://docs.oracle.com/javafx/2/charts/bar-chart.htm
 */
public class BarChart extends Application {
    static int[] coresArray;
    static long[] sjfTimes;
    static long[] fcfsTimes;

    public static void setData(int[] coresArray, long[] sjfTimes, long[] fcfsTimes) {
        BarChart.coresArray = coresArray;
        BarChart.sjfTimes = sjfTimes;
        BarChart.fcfsTimes = fcfsTimes;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Job Scheduler");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final javafx.scene.chart.BarChart<String,Number> barChart =
                new javafx.scene.chart.BarChart<>(xAxis,yAxis);
        barChart.setTitle("Scheduling Strategy Comparison");
        xAxis.setLabel("Number of Cores");
        yAxis.setLabel("Time taken in millis");

        XYChart.Series sjfSeries = new XYChart.Series();
        sjfSeries.setName("Shortest Job First");

        XYChart.Series fcfsSeries = new XYChart.Series();
        fcfsSeries.setName("First come first serve");

        for(int i=0;i<coresArray.length;i++){
            sjfSeries.getData().add(new XYChart.Data("#cores "+coresArray[i], sjfTimes[i]));
            fcfsSeries.getData().add(new XYChart.Data("#cores "+coresArray[i], fcfsTimes[i]));
        }

        Scene scene  = new Scene(barChart,800,600);
        barChart.getData().addAll(sjfSeries, fcfsSeries);

        //add tooltips
        for (XYChart.Series<String, Number> series : barChart.getData()) {
            for (XYChart.Data<String, Number> data : series.getData()) {
                Tooltip.install(data.getNode(), new Tooltip(
                        data.getXValue() + "\n" + "Time (ms): " + data.getYValue()));
                //Adding class on hover
                data.getNode().setOnMouseEntered(event -> data.getNode().getStyleClass().add("onHover"));
                //Removing class on exit
                data.getNode().setOnMouseExited(event -> data.getNode().getStyleClass().remove("onHover"));
            }
        }

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
