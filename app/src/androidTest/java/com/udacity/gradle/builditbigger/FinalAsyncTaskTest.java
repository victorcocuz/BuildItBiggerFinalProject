package com.udacity.gradle.builditbigger;
import android.app.Application;
import android.test.ApplicationTestCase;


/******
 * Created by Victor on 12/2/2018.
 ******/
public class FinalAsyncTaskTest extends ApplicationTestCase<Application> {

    public FinalAsyncTaskTest() {
        super(Application.class);
    }

    public void testJokes() {
        EndpointsAsyncTask testJokes = new EndpointsAsyncTask();
        testJokes.execute();
        String joke = null;
        try {
            joke = testJokes.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(joke);
    }
}
