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
        DBManager dbManager = DBManager.getInstance(this);
        int c = dbManager.count();
        count.setText(c +" ");
    }
}

