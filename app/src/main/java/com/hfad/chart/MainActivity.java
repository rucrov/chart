package com.hfad.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    BarChart chart;
    BarDataSet lineDataSet;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rnd = new Random();
        chart= findViewById(R.id.chart);

       ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();          // записываем значения
              for (int i=0;i<10;i++) {
                  dataVals.add(new BarEntry(i, rnd.nextInt(6)));
              }
        lineDataSet = new BarDataSet(dataVals,"data set 1");       //запихиваем значения и запихиваем их в lineDataSet для отрисовки единственной линии

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();       //запихнутые значения записываем в особенный формат
        style();
        dataSets.add(lineDataSet);


        BarData data = new BarData(dataSets);

        chart.setData(data);                //вставить значения в график
        XAxis xAxis = chart.getXAxis();
        chart.setTouchEnabled(false);
        xAxis.setGranularityEnabled(true);

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setGranularityEnabled(true);

        YAxis yAxisRight = chart.getAxisRight();

        chart.getXAxis().setLabelCount(3,true); // решение
        chart.invalidate();                 // отрисовать
    }
    void style(){
        //lineDataSet.enableDashedLine(6,10,5);   //настройка определенной лини чтобы она была пунктирной

        lineDataSet.setColor(Color.RED);
        chart.setBorderWidth(3f);       //настройка ширины обводки
        chart.setDrawBorders(false);    // включение обводки, она более толстая чем без включения этой настройки
        chart.setBorderColor(Color.RED);    // настройка  цвета обводки


    }
}