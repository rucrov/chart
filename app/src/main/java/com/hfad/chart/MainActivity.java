package com.hfad.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    LineChart chart;
    LineDataSet lineDataSet;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rnd = new Random();
        chart= findViewById(R.id.chart);

       ArrayList<Entry> dataVals = new ArrayList<Entry>();          // записываем значения
       for (int i=0;i<20;i++){
           dataVals.add(new Entry(i, rnd.nextInt(5)));
       }
        lineDataSet = new LineDataSet(dataVals,"data set 1");       //запихиваем значения и запихиваем их в lineDataSet для отрисовки единственной линии

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();       //запихнутые значения записываем в особенный формат
        style();
        dataSets.add(lineDataSet);


        LineData data = new LineData(dataSets);

        chart.setData(data);                //вставить значения в график
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