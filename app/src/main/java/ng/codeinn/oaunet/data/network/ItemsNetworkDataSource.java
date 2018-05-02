package ng.codeinn.oaunet.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ng.codeinn.oaunet.AppExecutors;
import ng.codeinn.oaunet.data.database.Item;
import ng.codeinn.oaunet.data.network.model.ItemsModel;
import ng.codeinn.oaunet.data.network.model.ListWrapper;
import ng.codeinn.oaunet.utilities.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class ItemsNetworkDataSource {

    private static final String LOG_TAG = ItemsNetworkDataSource.class.getSimpleName();

    private static final int SYNC_INTERVAL_HOURS = 3;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 3;

    private static final String ITEMS_SYNC_TAG = "items-sync";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static ItemsNetworkDataSource sInstance;
    private final Context mContext;

    private final AppExecutors mExecutors;

    private APIInterface mApiInterface;

    private final MutableLiveData<Item[]> mDownloadedItems;



    private ItemsNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        mDownloadedItems = new MutableLiveData<>();

    }

    public static ItemsNetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ItemsNetworkDataSource(context.getApplicationContext(), executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    public LiveData<Item[]> getItemsUpdate(){
        return mDownloadedItems;
    }






    public void startFetchItemsService() {
        Intent intentToFetch = new Intent(mContext, ItemsSyncIntentService.class);
        mContext.startService(intentToFetch);
        Log.d(LOG_TAG, "Service created");
    }

    public void scheduleRecurringFetchItemsSync() {
        Driver driver = new GooglePlayDriver(mContext);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job syncSunshineJob = dispatcher.newJobBuilder()
                .setService(ItemsFirebaseJobService.class)
                .setTag(ITEMS_SYNC_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        SYNC_INTERVAL_SECONDS,
                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();
        dispatcher.schedule(syncSunshineJob);
        Log.d(LOG_TAG, "Job scheduled");
    }

    void fetchItems(){
        Log.d(LOG_TAG, "Fetch items started");

        createItemsAPI();
        mApiInterface.getNews().enqueue(new Callback<ListWrapper>() {
            @Override
            public void onResponse(Call<ListWrapper> call, Response<ListWrapper> response) {
                if (response.isSuccessful()){
                    List<ItemsModel> newsResult = response.body().getItems();
                    Item[] itemArray = new Item[newsResult.size()];
                    for (int i = 0; i < newsResult.size(); i++){
                        ItemsModel item = newsResult.get(i);
                        Item news = new Item(item.getTitle(),
                                item.getIntrotext(),
                                item.getLink(),
                                item.getFulltext(),
                                item.getCreated(),
                                item.getHits(),
                                Constants.NEWS_ITEM);

                        itemArray[i] = news;

                        Log.i(TAG, "onResponse: news: " + news.getItemTitle());
                    }
                    mDownloadedItems.postValue(itemArray);
                }
            }

            @Override
            public void onFailure(Call<ListWrapper> call, Throwable t) {
                t.printStackTrace();

            }
        });

        mApiInterface.getEvents().enqueue(new Callback<ListWrapper>() {
            @Override
            public void onResponse(Call<ListWrapper> call, Response<ListWrapper> response) {
               if (response.isSuccessful()) {
                   List<ItemsModel> itemsResult = response.body().getItems();
                   Item[] eventsArray = new Item[itemsResult.size()];
                   for (int i = 0; i < itemsResult.size(); i++){
                       ItemsModel item = itemsResult.get(i);
                       Item event = new Item(item.getTitle(),
                               item.getIntrotext(),
                               item.getLink(),
                               item.getFulltext(),
                               item.getCreated(),
                               item.getHits(),
                               Constants.EVENT_ITEM);

                       eventsArray[i] = event;


                   }
                   mDownloadedItems.postValue(eventsArray);
               }
            }

            @Override
            public void onFailure(Call<ListWrapper> call, Throwable t) {
                t.printStackTrace();
                Log.i(TAG, "onFailure: failed" + t.getMessage());
            }
        });

        mApiInterface.getResearches().enqueue(new Callback<ListWrapper>() {
            @Override
            public void onResponse(Call<ListWrapper> call, Response<ListWrapper> response) {
                if(response.isSuccessful()) {
                    List<ItemsModel> researchResult = response.body().getItems();
                    Item[] researchArray = new Item[researchResult.size()];
                    for (int i = 0; i < researchResult.size(); i++){
                        ItemsModel item = researchResult.get(i);
                        Item research = new Item(item.getTitle(),
                                item.getIntrotext(),
                                item.getLink(),
                                item.getFulltext(),
                                item.getCreated(),
                                item.getHits(),
                                Constants.RESEARCH_ITEM);

                        researchArray[i] = research;
                    }
                    mDownloadedItems.postValue(researchArray);
                }
            }

            @Override
            public void onFailure(Call<ListWrapper> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void createItemsAPI(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

//        OkHttpClient client = new OkHttpClient.Builder()
//                .retryOnConnectionFailure(true)
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mApiInterface = retrofit.create(APIInterface.class);
    }


}
