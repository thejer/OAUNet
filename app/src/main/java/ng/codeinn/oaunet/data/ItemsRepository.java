package ng.codeinn.oaunet.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import ng.codeinn.oaunet.AppExecutors;
import ng.codeinn.oaunet.data.database.Item;
import ng.codeinn.oaunet.data.database.ItemDao;
import ng.codeinn.oaunet.data.network.ItemsNetworkDataSource;

public class ItemsRepository {
    private static final String LOG_TAG = ItemsRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static ItemsRepository sInstance;
    private final ItemDao mItemDao;
    private final ItemsNetworkDataSource mItemsNetworkDataSource;
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    public ItemsRepository(ItemDao itemDao,
                           ItemsNetworkDataSource itemsNetworkDataSource,
                           AppExecutors executors) {
        mItemDao = itemDao;
        mItemsNetworkDataSource = itemsNetworkDataSource;
        mExecutors = executors;

        LiveData<Item[]> itemData = mItemsNetworkDataSource.getItemsUpdate();

        itemData.observeForever(itemsUpdate -> mExecutors.diskIO().execute(() ->
                mItemDao.bulkInsertItems(itemsUpdate)));

    }


    public synchronized static ItemsRepository getInstance(ItemDao itemDao,
                                                           ItemsNetworkDataSource itemsNetworkDataSource,
                                                           AppExecutors executors){
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ItemsRepository(itemDao, itemsNetworkDataSource,
                        executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public synchronized void initializeData() {
        if (mInitialized) return;
        mInitialized = true;

        mItemsNetworkDataSource.scheduleRecurringFetchItemsSync();

        startFetchItemsService();
    }


    public LiveData<List<Item>> getItemsByType(int itemType){
        initializeData();
        return mItemDao.getItemsByType(itemType);
    }

    private LiveData<Item> getItemById(int id){
        initializeData();
        return mItemDao.getItemById(id);
    }

    private void deleteOldData() {

    }


    private void startFetchItemsService() {
        mItemsNetworkDataSource.startFetchItemsService();

    }

}
