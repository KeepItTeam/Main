package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StatActivity extends AppCompatActivity {
    String readFile() {
        String resultString = "";
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("data.txt")));
            String str = "";

            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.e("LOG_TAG", str);
                resultString+=str;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String dataFromFile=readFile();
        String[] a=dataFromFile.split(" ");
        int dataManagement= Integer.parseInt(a[0]);
        int communications=Integer.parseInt(a[1]);
        int safety=Integer.parseInt(a[2]);
        int contentMaking=Integer.parseInt(a[3]);
        int problemSolving=Integer.parseInt(a[4]);
        problemSolving+=4;//todo не забыть, что это только для наглядности
        dataManagement+=3;
        setContentView(R.layout.activity_stat);
        ArrayList<BarEntry> entries = new ArrayList<>();
        final SharedPreferences sPref = getPreferences(MODE_PRIVATE);

        entries.add(new BarEntry(dataManagement, 0));
        entries.add(new BarEntry(communications, 1));
        entries.add(new BarEntry(safety, 2));
        entries.add(new BarEntry(contentMaking, 3));
        entries.add(new BarEntry(problemSolving, 4));

            BarDataSet dataset = new BarDataSet(entries, "ВЫБЕРИТЕ НАВЫК ДЛЯ ПОЛУЧЕНИЯ БОЛЕЕ ПОДРОБНОЙ ИНФОРМАЦИИ");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Управление данными");
        labels.add("Коммуникации");
        labels.add("Безопасность");
        labels.add("Создание контента");
        labels.add("Решение проблем");

        BarChart chart = new BarChart(this);
        chart.setFitsSystemWindows(true);
            chart.setDescription("ВАШИ НАВЫКИ");
        setContentView(chart);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                entry.getData();
                switch (entry.getXIndex()){
                    case 0:
                        AlertDialog.Builder builder = new AlertDialog.Builder(StatActivity.this);
                        builder.setTitle("Управление информацией и данными")
                                .setMessage("Просмотр, поиск и фильтрация данных, информации и цифрового контента: "+
                                        sPref.getInt("dataViewing",0)+"\n"
                                        +"Оценка данных, информации и цифрового контента: "+
                                        sPref.getInt("dataRating",0)+"\n"+
                                        "Управление данными, информацией и цифровым контентом: "+
                                        sPref.getInt("informationManagement",0)
                                )
                                .setPositiveButton("ОК",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        break;
                    case 1:
                        //todo аналогичным способом для остальных
                        break;
                    case 2:
                            break;
                    case 3:
                        break;
                    case 4:
                        break;

                }
                Log.d("VAL SELECTED",
                        "Value: " + entry.getVal() + ", xIndex: " + entry.getXIndex()
                                + ", DataSet index: " + highlight.getDataSetIndex());
            }

            @Override
            public void onNothingSelected() {
                Log.d("BAR_CHART_SAMPLE", "nothing selected X is ");
            }
        });
        BarData data = new BarData(labels, dataset);
        chart.setData(data);
    }
}
