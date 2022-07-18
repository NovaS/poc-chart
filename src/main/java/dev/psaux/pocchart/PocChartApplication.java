package dev.psaux.pocchart;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Hour;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocChartApplication {
	private static final Logger logger = LoggerFactory.getLogger(PocChartApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PocChartApplication.class, args);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		
/**
		// SAMPLE XCHART
		double[] xData = new double[] { 0.0, 1.0, 2.0, 1.0, 5.0, 3.0 };
	    double[] yData = new double[] { 2.0, 1.0, 0.0, 1.0, 6.0, 4.0 };
	    ArrayList<Date> xList = new ArrayList<Date>();
	    try {
			xList.add(sdf.parse("01-01-2022"));
			xList.add(sdf.parse("02-01-2022"));
			xList.add(sdf.parse("03-01-2022"));
			xList.add(sdf.parse("04-01-2022"));
			xList.add(sdf.parse("05-01-2022"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    List<Number> yList = new ArrayList<Number>();
	    yList.add(Double.valueOf(1));
	    yList.add(Double.valueOf(2));
	    yList.add(Double.valueOf(3));
	    yList.add(Double.valueOf(4));
	    yList.add(Double.valueOf(5));
	    
	    XYChart chart = new XYChartBuilder().xAxisTitle("Time").yAxisTitle("Index").width(600).height(400).build();
	    chart.setTitle("Index "+sdf.format(new Date()));
	    chart.addSeries("Index", xList, yList);
	    
	    chart.getStyler().setYAxisMin(Double.parseDouble("-10"));
	    chart.getStyler().setYAxisMax(Double.parseDouble("10"));
	    chart.getStyler().setLegendVisible(false);

	    try {
			BitmapEncoder.saveBitmap(chart, "sample-index-xchart", BitmapFormat.JPG);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOException: {}", e.getMessage());
		}
*/    
///**
	    // SAMPLE JFREECHART
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		TimeSeriesCollection coll = new TimeSeriesCollection();
		try {
			TimeSeries seri = new TimeSeries("Index");
			seri.add(new Hour(sdfTime.parse("09:00")), 6304.15);
			coll.addSeries(seri);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JFreeChart chartObject = ChartFactory.createTimeSeriesChart(
				"INDEX "+sdf.format(new Date()),
				"Time",
				"Index",
				coll,
//				PlotOrientation.VERTICAL,
				false,true,false);
		XYPlot plot = (XYPlot) chartObject.getPlot();
		
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(true);
        yAxis.setFixedAutoRange(10);
        yAxis.setTickLabelsVisible(true);
        yAxis.setAutoTickUnitSelection(true);
        
        DateAxis xAxis = (DateAxis) plot.getDomainAxis();
        xAxis.setStandardTickUnits(DateAxis.createStandardDateTickUnits());
        xAxis.setFixedAutoRange(60*1000*60);
        try {
			xAxis.setRange(sdfTime.parse("08:00"), sdfTime.parse("16:00"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		File lineChart = new File( "sample-index-jfree.jpeg" ); 
		try {
			ChartUtils.saveChartAsJPEG(lineChart ,chartObject, 640 ,480);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOException: {}", e.getMessage());
		}
// */
		
/**
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	    OHLCDataItem dataItem[] = null;
	    try {
	      OHLCDataItem data1 = new OHLCDataItem(format.parse("09:00"), 6288.00, 6288.50, 6282.70, 6285.80, 15148100);
	      OHLCDataItem data2 = new OHLCDataItem(format.parse("09:10"), 6288.00, 6292.70, 6285.30, 6288.10, 11663000);
	      OHLCDataItem data3 = new OHLCDataItem(format.parse("09:20"), 6283.55, 6292.25, 6283.55, 6290.30, 20474400);
	      OHLCDataItem data4 = new OHLCDataItem(format.parse("09:30"), 6284.95, 6284.95, 6278.80, 6281.55, 18235300);
	      OHLCDataItem data5 = new OHLCDataItem(format.parse("09:40"), 6282.00, 6287.50, 6280.55, 6285.80, 26282600);
	      OHLCDataItem data6 = new OHLCDataItem(format.parse("09:50"), 6265.80, 6283.50, 6262.80, 6281.80, 0);
	      OHLCDataItem data7 = new OHLCDataItem(format.parse("10:00"), 6265.80, 6283.50, 6262.80, 6281.80, 0);
	      OHLCDataItem data8 = new OHLCDataItem(format.parse("10:10"), 6265.80, 6283.50, 6262.80, 6281.80, 0);
	      OHLCDataItem data9 = new OHLCDataItem(format.parse("10:20"), 6265.80, 6283.50, 6262.80, 6281.80, 0);
	      OHLCDataItem data10 = new OHLCDataItem(format.parse("10:30"),6265.80, 6283.50, 6262.80, 6281.80, 0);
	      OHLCDataItem data11 = new OHLCDataItem(format.parse("10:40"), 6265.85, 6283.50, 6262.80, 6282.10, 40588400);
	      OHLCDataItem data12 = new OHLCDataItem(format.parse("10:50"), 6268.80, 6274.00, 6266.45, 6268.95, 18668300);
	      OHLCDataItem data13 = new OHLCDataItem(format.parse("11:00"), 6273.00, 6275.60, 6269.70, 6270.80, 15488600);
	      OHLCDataItem data14 = new OHLCDataItem(format.parse("11:10"), 6263.05, 6274.00, 6260.70, 6272.00, 81303300);
	      OHLCDataItem data15 = new OHLCDataItem(format.parse("11:20"), 6263.95, 6277.95, 6269.25, 6275.55, 41303300);

	      dataItem = new OHLCDataItem[] {
			  data1, data2, data3, data4, data5, 
	          data6, data7, data8, data9, data10,
	          data11, data12, data13, data14, data15
	      };
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    OHLCDataset dataset = new DefaultOHLCDataset("INDEX", dataItem);
		JFreeChart chart = ChartFactory.createHighLowChart("OHLC Stock chart", "Date", "Index", dataset, false);
		XYPlot plot = (XYPlot) chart.getPlot();

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRangeIncludesZero(false);
		
		File fileChart = new File( "sample-index-jfree.jpeg" ); 
		try {
			ChartUtils.saveChartAsJPEG(fileChart ,chart, 640 ,480);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOException: {}", e.getMessage());
		}
*/
	}
	
}
