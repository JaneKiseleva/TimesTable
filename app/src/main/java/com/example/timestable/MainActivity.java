package com.example.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private ListView listViewNumbers;
    private ArrayList<Integer> numbers;
    private int max = 20;
    private int min = 1;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(max);
        listViewNumbers = findViewById(R.id.listViewNumbers);
        numbers = new ArrayList<>();
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, numbers);
        listViewNumbers.setAdapter(arrayAdapter);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numbers.clear(); //очищаем список и теперь цифры добавляются в зависимости от прогресса не вниз по экрану, а заменяют старые
                //проверка про ноль
                if(progress <min) {
                    seekBar.setProgress(min);
                    numbers.clear();
                }
                for (int i = min; i <= count; i++) {
                    //убираем нули в списке, поэтому заменяем переменую progress на seekBar.getProgress() и в самом сигбаре на эмуляторе ы упираемся в единицу
                    numbers.add(seekBar.getProgress() * i);
                }
                //Адаптер на этом моменте еoе не знает что у нас какие-то цифры в массиве есть, надо ему сообщить. Есть для этого метод notifyDataSetChanged
                //Говорим адаптеру, обрати внимание, что данные изменились.
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        //Теперь хотим, чтобы числа добавлялись при запуске нашей программы, еще до того, как пользователь начнет двигать кружок на SeekBar (и начинает работать метод onProgressChanged
        seekBar.setProgress(10);
    }
}