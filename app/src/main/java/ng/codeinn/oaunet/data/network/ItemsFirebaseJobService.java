package ng.codeinn.oaunet.data.network;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import ng.codeinn.oaunet.utilities.InjectorUtils;

public class ItemsFirebaseJobService extends JobService {

    private static final String LOG_TAG = ItemsFirebaseJobService.class.getSimpleName();

    private AsyncTask <Void, Void, Void> mBackgroundTask;

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d(LOG_TAG, "Job service started");

        mBackgroundTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                ItemsNetworkDataSource networkDataSource = InjectorUtils.provideNetworkDataSource(getApplicationContext());
                networkDataSource.fetchItems();
                jobFinished(job, false);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                jobFinished(job, false);
            }
        };

        mBackgroundTask.execute();


        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {

        if (mBackgroundTask != null){
            mBackgroundTask.cancel(true);
        }
        return true;
    }
}
