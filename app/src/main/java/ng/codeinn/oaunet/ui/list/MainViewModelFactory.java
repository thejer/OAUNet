package ng.codeinn.oaunet.ui.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import ng.codeinn.oaunet.data.ItemsRepository;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private final ItemsRepository mRepository;

    private final int mItemType;

    public MainViewModelFactory (ItemsRepository repository, int itemType){
        this.mRepository = repository;
        this.mItemType = itemType;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository, mItemType);
    }
}
