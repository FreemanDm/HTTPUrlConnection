package com.freeman.httpurlconnection.httpurlconnection;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by Freeman on 14.02.2017.
 */

public class AuthTask extends AsyncTask<Void, Void, AuthResponse> {
    @Override
    protected AuthResponse doInBackground(Void... params) {
        AuthResponse response = null;
        Auth auth = new Auth("sde236678@gmail.com", "12345");
        Gson gson = new Gson();
        String requestData = gson.toJson(auth);
        try {
            String result = HttpProvider.getProvider().post("/registration", requestData);
            response = gson.fromJson(result, AuthResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(AuthResponse authResponse) {
        super.onPostExecute(authResponse);
        if (authResponse != null) {
            Log.d("MY_TAG", "token = " + authResponse.getToken());
        } else {
//            Toast.makeText(MainActivity, "Error!", Toast.LENGTH_SHORT).show();
            Log.d("MY_ERROR", "Error!");
        }
    }
}

    class Auth{
        String email;
        String password;

        public Auth() {
        }

        public Auth(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    class AuthResponse{
        String token;

        public AuthResponse() {
        }

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


