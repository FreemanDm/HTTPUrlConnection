package com.freeman.httpurlconnection.httpurlconnection;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userIdTxt, idTxt, titleTxt, bodyTxt;
    private Button getDataBtn;
    private ProgressBar myProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userIdTxt = (TextView) findViewById(R.id.user_id_txt);
        idTxt = (TextView) findViewById(R.id.id_txt);
        titleTxt = (TextView) findViewById(R.id.title_txt);
        bodyTxt = (TextView) findViewById(R.id.body_txt);
        getDataBtn = (Button) findViewById(R.id.get_data_btn);
        myProgress = (ProgressBar) findViewById(R.id.my_progress);
        getDataBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.get_data_btn){
            new GetDataTask(this, userIdTxt, idTxt, titleTxt, bodyTxt, getDataBtn, myProgress).execute();
            new AuthTask().execute();
        }
    }
}
