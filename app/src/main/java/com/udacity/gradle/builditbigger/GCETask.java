package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.jokes.JokesActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import java.io.IOException;


public class GCETask extends AsyncTask<Context, Void, String> {

    private ProgressBar spinner;
    public GCETask(ProgressBar spinner){
        this.spinner=spinner;
    }
private static MyApi myApiService = null;
private Context context;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
        new AndroidJsonFactory(), null)
        // options for running against local devappserver
        // - 10.0.2.2 is localhost's IP address in Android emulator
        // - turn off compression when running against local devappserver
        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
@Override
public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
        abstractGoogleClientRequest.setDisableGZipContent(true);
        }
        });
        // end options for devappserver

        myApiService = builder.build();
        }

        context = params[0];


        try {
        return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
        return e.getMessage();
        }
        }

@Override
protected void onPostExecute(String result) {

        spinner.setVisibility(View.GONE);
        Intent intent=new Intent(context,JokesActivity.class);
        intent.putExtra("joke",result);
        context.startActivity(intent);
        }
        }