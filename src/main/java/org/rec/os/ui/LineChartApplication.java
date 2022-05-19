package org.rec.os.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class LineChartApplication extends Application {
    static int[] coresArray;
    static long[] sjfTimes;
    static long[] fcfsTimes;

    public static void setData(int[] coresArray, long[] fcfsTimes, long[] sjfTimes){
        LineChartApplication.coresArray = coresArray;
        LineChartApplication.fcfsTimes = fcfsTimes;
        LineChartApplication.sjfTimes = sjfTimes;
    }

    @Override
    public  void start(Stage stage){
        stage.setTitle("Non preemptive Job Scheduler");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Scheduling Strategy comparison");
        xAxis.setLabel("Number od cores");
        yAxis.setLabel("Time taken (millis)");

        XYChart.Series sjfSeries= new XYChart.Series();
        sjfSeries.setName("Shortest Job first");

        XYChart.Series fcfsSeries = new XYChart.Series();
        fcfsSeries.setName("First come first serve");

        for(int i = 0; i < coresArray.length; i++){
            sjfSeries.getData().add(new XYChart.Data(("#cores " + coresArray[i]), sjfTimes[i]));
            fcfsSeries.getData().add(new XYChart.Data(("#cores " + coresArray[i]), fcfsTimes[i]));
        }

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().addAll(sjfSeries, fcfsSeries);

        addToolTips(lineChart);
        stage.setScene(scene);
        stage.show();
    }

    static void addToolTips(LineChart<String, Number> lineChart){
        for(XYChart.Series<String, Number> series : lineChart.getData()){
            for(XYChart.Data<String, Number> data : series.getData()){
                Tooltip.install(data.getNode(),  new Tooltip(data.getXValue() + "\n" + "Time(ms): " + data.getYValue()));
                data.getNode().setOnMouseEntered(event -> data.getNode().getStyleClass().add("omHover"));
                data.getNode().setOnMouseExited(event -> data.getNode().getStyleClass().remove("onHover"));
            }
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
