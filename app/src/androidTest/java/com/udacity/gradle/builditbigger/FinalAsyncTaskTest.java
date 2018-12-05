package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/******
 * Created by Victor on 12/2/2018.
 ******/
public class FinalAsyncTaskTest extends ApplicationTestCase<Application> {

    private static final String LOG_TAG = FinalAsyncTaskTest.class.getSimpleName();
    private CountDownLatch signal;

    public FinalAsyncTaskTest() {
        super(Application.class);
    }

    public void testJokes() {
        EndpointsAsyncTask testJokes = new EndpointsAsyncTask(new EndpointsAsyncTask.Callback() {
            @Override
            public void onFinished(String result) {
                signal.countDown();
            }
        });
        testJokes.execute();
        String joke = null;
        try {
            signal.await(10, TimeUnit.SECONDS);
            joke = testJokes.get();
        } catch (Exception e) {
            joke = "";
            Log.e(LOG_TAG, "could not retrieve joke", e);
        }
        assertNotNull(joke);
    }
}
