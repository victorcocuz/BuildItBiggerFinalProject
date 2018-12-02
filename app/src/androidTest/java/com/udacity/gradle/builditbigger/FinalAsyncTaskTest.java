package com.udacity.gradle.builditbigger;
import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/******
 * Created by Victor on 12/2/2018.
 ******/
public class FinalAsyncTaskTest extends ApplicationTestCase<Application> {

    private CountDownLatch signal;

    public FinalAsyncTaskTest() {
        super(Application.class);
    }

    public void testJokes() {
        EndpointsAsyncTask testJokes = new EndpointsAsyncTask(new EndpointsAsyncTask.Callback() {
            @Override
            public void onFinished(String result) {

            }
        });
        testJokes.execute();
        String joke = null;
        try {
            signal.await(10, TimeUnit.SECONDS);
            joke = testJokes.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(joke);
    }
}
