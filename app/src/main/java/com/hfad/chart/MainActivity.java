package com.hfad.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    BarChart SessionChart,ShotChart;
    LineChart SelectedChart;
    BarDataSet barDataSetSession,barDataSetShot;
    LineDataSet lineDataSetSelected;
    TextView numberSession,numberShot,numberSelected;
    //int save;

    float save ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rnd = new Random();
        SessionChart = findViewById(R.id.Sessions);
        ShotChart = findViewById(R.id.Shot);
        SelectedChart = findViewById(R.id.Selected);
        numberSession=  findViewById(R.id.numberSession);
        numberShot=findViewById(R.id.numberShot);
        numberSelected = findViewById(R.id.numberSelected);



       ArrayList<BarEntry> dataValuesSession = new ArrayList<BarEntry>();          //создаём arrayList типа BarEntry для session
        ArrayList<BarEntry> dataValueShot = new ArrayList<BarEntry>();          // создаём arrayList типа BarEntry для shot
        ArrayList<Entry> dataValueSelected = new ArrayList<Entry>();

              for (int i=0;i<30;i++) {
                  dataValuesSession.add(new BarEntry(i, rnd.nextInt(15)));       //записывем x,y в session
                  dataValueShot.add(new BarEntry(i, rnd.nextInt(15)+2));          //записывем x,y в shot
                  dataValueSelected.add(new Entry(i, rnd.nextInt(15)));          //записывем x,y в selected
              }
        barDataSetSession = new BarDataSet(dataValuesSession,"Session value");       //вставляем значения в блочный график для session
        barDataSetShot = new BarDataSet(dataValueShot,"Shot value");
        lineDataSetSelected = new LineDataSet(dataValueSelected,"Selected value");


        ArrayList<IBarDataSet> Session = new ArrayList<>();       //запихнутые значения записываем в особенный формат
        ArrayList<IBarDataSet> Shot = new ArrayList<>();
        ArrayList<ILineDataSet> Selected = new ArrayList<>();

        Session.add(barDataSetSession);         // добовлаем в еще формат
        Shot.add(barDataSetShot);
        Selected.add(lineDataSetSelected);


        BarData dataSession = new BarData(Session);
        BarData dataShot = new BarData(Shot);
        LineData dataSelected = new LineData(Selected);
        numberShot.setHighlightColor(Color.RED);
        SessionChart.setData(dataSession);  //вставляем значения в графики
        ShotChart.setData(dataShot);
        SelectedChart.setData(dataSelected);

        style();
        XAxis xAxis = ShotChart.getXAxis(); // получение осей с двух сторон
        xAxis.setLabelCount(6,true);        // настройка вертикальных полосок

        SessionChart.invalidate();          // обновить график
        ShotChart.invalidate();

        ShotChart.getData().notifyDataChanged();
        ShotChart.notifyDataSetChanged();
        ShotChart.invalidate();
        SelectedChart.invalidate();



                ShotChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {

                        if (e.getY()!=2.0) {
                            save = e.getX();
                            barDataSetShot.setHighLightColor(Color.BLUE);
                            ShotChart.highlightValue(h);
                            numberShot.setText(save+"");
                        }
                        else {
                            barDataSetShot.setHighLightColor(Color.BLUE);
                            ShotChart.highlightValue(save,0);
                        }
                    }

                    @Override
                    public void onNothingSelected() {
                        ShotChart.highlightValue(save,0);           // запрет снятия фокуса с bar
                    }
                });




    }
    private void style (){
        SessionChart.getAxisLeft().setDrawLabels(false);
        SessionChart.getAxisRight().setDrawLabels(false);
        SessionChart.getXAxis().setDrawLabels(false);
        SessionChart.getDescription().setEnabled(false);
        SessionChart.getLegend().setEnabled(false);


        ShotChart.getAxisLeft().setDrawLabels(false);
        ShotChart.getAxisRight().setDrawLabels(false);
        ShotChart.getXAxis().setDrawLabels(false);
        ShotChart.getDescription().setEnabled(false);
        ShotChart.getLegend().setEnabled(false);
        ShotChart.setScaleEnabled(false);
        barDataSetShot.setHighLightColor(Color.BLUE);
        ShotChart.highlightValue(0,0);

        SelectedChart.getAxisLeft().setDrawLabels(false);
        SelectedChart.getAxisRight().setDrawLabels(false);
        SelectedChart.getXAxis().setDrawLabels(false);
        SelectedChart.getDescription().setEnabled(false);
        SelectedChart.getLegend().setEnabled(false);
        SelectedChart.setTouchEnabled(false);

        // ShotChart.xAxis.centerAxisLabelsEnabled
        XAxis xAxis = ShotChart.getXAxis();
        YAxis Ly = ShotChart.getAxisLeft();
        YAxis Ry = ShotChart.getAxisRight();
        xAxis.enableGridDashedLine(17,8,8);
        ShotChart.getAxisLeft().setDrawGridLines(false);
         // xAxis.setDrawAxisLine(false);
             Ly.setDrawAxisLine(false);      // убирает левую полоску графика
        Ly.setDrawLabels(false);
        Ly.setDrawZeroLine(false);
        Ly.setDrawAxisLine(false);
        Ly.setDrawTopYLabelEntry(false);
        Ly.setDrawGridLinesBehindData(false);       //объеденив это
        Ly.setDrawGridLines(false);
        xAxis.setCenterAxisLabels(true);
        ShotChart.setDrawBorders(false);
     //   xAxis.setGranularity(3f);
      //  xAxis.setGranularityEnabled(true);
        //xAxis.setDrawLabels(false);
        ShotChart.getAxisRight().setDrawGridLines(false);
        // xAxis.setDrawAxisLine(false);
        Ry.setDrawAxisLine(false);      // убирает левую полоску графика
//       /* Ry.setDrawLabels(false);
//        Ry.setDrawZeroLine(false);
//        Ry.setDrawAxisLine(false);
//        Ry.setDrawTopYLabelEntry(false);
//        Ry.setDrawGridLinesBehindData(false);
//        Ry.setDrawGridLines(false);*/

        //xAxis.setDrawAxisLine(false);
     //   Ry.setEnabled(false);
       // xAxis.setDrawGridLines(false);
        xAxis.setDrawGridLinesBehindData(false); // и это выключяется первая вертекальная полоска
      //  xAxis.setDrawGridLines(false);
        //   Ry.setDrawGridLines(false);

        YAxis yAxisLeft = SelectedChart.getAxisLeft();
        YAxis yAxisRight = SelectedChart.getAxisRight();
      //  SelectedChart.getAxisLeft().setDrawGridLines(false);
        yAxisLeft.setSpaceBottom(0f);                   // убирание отступа от нижней части
        yAxisLeft.setSpaceTop(0f);
        yAxisLeft.setGranularity(0);
        yAxisRight.setSpaceBottom(0f);                   // убирание отступа от нижней части
        yAxisRight.setSpaceTop(0f);
        yAxisRight.setGranularity(0);
    }

}