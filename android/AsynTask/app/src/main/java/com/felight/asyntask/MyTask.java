package com.felight.asyntask;

import android.app.Activity;
import android.os.AsyncTask;

import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by Tejashree on 17-Feb-17.
 */

public class MyTask extends AsyncTask<Integer,Integer,String> {
    private static int progress=1;
    private Activity activity;
    private RatingBar rbProgress;


    public MyTask(Activity activity){

        this.activity=activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        rbProgress=(RatingBar)activity.findViewById(R.id.rbProgress);
    }

    @Override
    protected String doInBackground(Integer...integers) {
        for(Integer integer:integers){try{
            Thread.sleep(integer);
            publishProgress(++progress);
        }
        catch (InterruptedException e){}}
        return "Task Completed";
    }

    @Override
    protected void onProgressUpdate(Integer...values) {
        rbProgress.setRating(progress);
        super.onProgressUpdate(values);
    }



    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(activity,s,Toast.LENGTH_LONG).show();
        super.onPostExecute(s);
    }
}
