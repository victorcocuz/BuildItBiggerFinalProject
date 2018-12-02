package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import android.os.AsyncTask;
import android.util.Pair;

import com.example.jokeandroidlibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import com.example.jokeandroidlibrary.JokeActivity;
import com.example.jokejavalibrary.JokesClass;

import java.io.IOException;

/******
 * Created by Victor on 12/2/2018.
 ******/
class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private static final String INTENT_JOKE_KEY = "jokeKey";
//    private Callback mCallback;

    JokesClass jokesClass = new JokesClass();

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
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

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(jokesClass.getJoke()).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
//        if(result != null){
//            mCallback.onFinished(result);
//        }

        Intent myIntent = new Intent(context, JokeActivity.class);
        myIntent.putExtra(INTENT_JOKE_KEY, result);
        context.startActivity(myIntent);
    }

//    public interface Callback{
//        void onFinished(String result);
//    }
//
//    public EndpointsAsyncTask(Callback callback){
//        mCallback = callback;
//    }
}