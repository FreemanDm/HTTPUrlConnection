package com.freeman.httpurlconnection.httpurlconnection;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

/**
 * Created by Freeman on 10.01.2017.
 */

public class GetDataTask extends AsyncTask<Void, Void, Response> {

    private Context context;
    private TextView userIdTxt, idTxt, titleTxt, bodyTxt;
    private Button getDataBtn;
    private ProgressBar myProgress;

    public GetDataTask(Context context, TextView userIdTxt, TextView idTxt, TextView titleTxt, TextView bodyTxt, Button getDataBtn, ProgressBar myProgress) {
        this.userIdTxt = userIdTxt;
        this.idTxt = idTxt;
        this.titleTxt = titleTxt;
        this.bodyTxt = bodyTxt;
        this.getDataBtn = getDataBtn;
        this.myProgress = myProgress;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        getDataBtn.setEnabled(false);
        myProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected Response doInBackground(Void... params) {
        Response response = null;

        try {
            String result = HttpProvider.getProvider().get("/posts/1");
            Gson gson = new Gson();
            response = gson.fromJson(result, Response.class);
        } catch (IOException | JsonSyntaxException e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
        myProgress.setVisibility(View.INVISIBLE);
        getDataBtn.setEnabled(true);
        if (response == null){
            Toast.makeText(context, "Fatal exception!!! Server was down!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            userIdTxt.setText(String.valueOf(response.getUserId()));
            idTxt.setText(String.valueOf(response.getId()));
            titleTxt.setText(response.getTitle());
            bodyTxt.setText(response.getBody());
        }
    }
}
