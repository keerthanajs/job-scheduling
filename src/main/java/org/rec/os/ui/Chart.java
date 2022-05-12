package org.rec.os.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * Reference: https://docs.oracle.com/javafx/2/charts/bar-chart.htm
 */
public class Chart extends Application {
    static int[] coresArray;
    static long[] sjfTimes;
    static long[] fcfsTimes;

    public static void setData(int[] coresArray, long[] sjfTimes, long[] fcfsTimes) {
        Chart.coresArray = coresArray;
        Chart.sjfTimes = sjfTimes;
        Chart.fcfsTimes = fcfsTimes;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Non-preemptive Job Scheduler");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart =  new BarChart<>(xAxis,yAxis);
        barChart.setTitle("Scheduling Strategy Comparison");
        xAxis.setLabel("Number of Cores");
        yAxis.setLabel("Time taken in millis");

        XYChart.Series sjfSeries = new XYChart.Series();
        sjfSeries.setName("Shortest Job First");

        XYChart.Series fcfsSeries = new XYChart.Series();
        fcfsSeries.setName("First Come First Serve");

        for(int i=0;i<coresArray.length;i++){
            sjfSeries.getData().add(new XYChart.Data("#cores "+coresArray[i], sjfTimes[i]));
            fcfsSeries.getData().add(new XYChart.Data("#cores "+coresArray[i], fcfsTimes[i]));
        }

        Scene scene  = new Scene(barChart,800,600);
        barChart.getData().addAll(sjfSeries, fcfsSeries);

        //add tooltips
        addToolTips(barChart);
        stage.setScene(scene);
        stage.show();
    }

    static void addToolTips(BarChart<String, Number> chart){
        for (XYChart.Series<String, Number> series : chart.getData()) {
            for (XYChart.Data<String, Number> data : series.getData()) {
                Tooltip.install(data.getNode(), new Tooltip(
                        data.getXValue() + "\n" + "Time (ms): " + data.getYValue()));
                //Adding class on hover
                data.getNode().setOnMouseEntered(event -> data.getNode().getStyleClass().add("onHover"));
                //Removing class on exit
                data.getNode().setOnMouseExited(event -> data.getNode().getStyleClass().remove("onHover"));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
