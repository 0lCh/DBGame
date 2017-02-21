package ru.samsung.itschool.dbgame;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StrActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_str);
        TextView count = (TextView)findViewById(R.id.count);
        TextView sum = (TextView)findViewById(R.id.sum);
        TextView max = (TextView)findViewById(R.id.max);
        TextView dbln = (TextView)findViewById(R.id.dbln);
        TextView odd = (TextView)findViewById(R.id.odd);
        TextView even = (TextView)findViewById(R.id.even);
        DBManager dbManager = DBManager.getInstance(this);
        int c = dbManager.count();
        count.setText("Колличество игр: "+c);
        int s = dbManager.sum();
        sum.setText("Сумма очков: "+s);
        int m = dbManager.max();
        max.setText("Максимальное число: "+m);
        int d = dbManager.dbln();
        dbln.setText("Колличество игроков: "+d);
        int e = dbManager.even();
        even.setText("Количество четных: "+Math.round(e/(c*1.0)*100)+"%");
        int o = dbManager.odd();
        odd.setText("Количество нечетные: "+Math.round(o/(c*1.0)*100)+"%");



    }
}

