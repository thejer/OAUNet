package ng.codeinn.oaunet.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ng.codeinn.oaunet.data.ItemsRepository;
import ng.codeinn.oaunet.data.database.Item;

public class MainActivityViewModel extends ViewModel {

    private LiveData<List<Item>> mItems;

    public MainActivityViewModel (ItemsRepository repository, int itemType){
        mItems = repository.getItemsByType(itemType);

    }

    public LiveData<List<Item>> getItems() {
        return mItems;
    }
}
