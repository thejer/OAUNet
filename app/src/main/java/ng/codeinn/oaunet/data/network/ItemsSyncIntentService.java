package ng.codeinn.oaunet.data.network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import ng.codeinn.oaunet.utilities.InjectorUtils;

public class ItemsSyncIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ItemsSyncIntentService() {
        super("ItemsSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        ItemsNetworkDataSource itemsNetworkDataSource =
                InjectorUtils.provideNetworkDataSource(this.getApplicationContext());
        itemsNetworkDataSource.fetchItems();

    }
}
