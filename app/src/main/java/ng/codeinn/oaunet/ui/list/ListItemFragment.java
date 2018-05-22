package ng.codeinn.oaunet.ui.list;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.utilities.Constants;
import ng.codeinn.oaunet.utilities.InjectorUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListItemFragment extends Fragment implements ItemsListAdapter.ItemAdapterOnItemClickHandler {

    private RecyclerView mRecyclerView;

    private MainActivityViewModel mViewModel;
    private int mPosition = RecyclerView.NO_POSITION;

    ItemsListAdapter mAdapter;

    OnItemClickListener mCallBack;

    int mItemType = Constants.NEWS_ITEM;
    public ListItemFragment() {
        // Required empty public constructor
    }

    public static ListItemFragment newInstance(int itemType){
        ListItemFragment fragment = new ListItemFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(Constants.ITEM_TYPE_KEY, itemType);
        fragment.setArguments(arguments);
        return fragment;
    }

    public interface OnItemClickListener{
        void onItemSelected(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallBack = (OnItemClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement OnItemClickListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter =  new ItemsListAdapter(getContext(), this);
        if (getArguments() != null && getArguments().containsKey(Constants.ITEM_TYPE_KEY)){
            mItemType = getArguments().getInt(Constants.ITEM_TYPE_KEY);
        }
        setTitle(mItemType);


    }


    private void setTitle(int itemType){
        switch (itemType){
            case Constants.NEWS_ITEM:
                ((MainListActivity)getActivity()).setActionBarTitle("News");
                break;
                case Constants.EVENT_ITEM:
                    ((MainListActivity)getActivity()).setActionBarTitle("Events");
                    break;
                    case Constants.RESEARCH_ITEM:
                        ((MainListActivity)getActivity()).setActionBarTitle("Research");
                        break;
                        default:
                            ((MainListActivity)getActivity()).setActionBarTitle("News");
                            break;
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_list, container, false);


        mRecyclerView = rootView.findViewById(R.id.items_recycler_view);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(getActivity(), mItemType);
        mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        mViewModel.getItems().observe(this, items -> {
            mAdapter.swapItems(items);
            if (mPosition == RecyclerView.NO_POSITION)mPosition = 0;
            mRecyclerView.smoothScrollToPosition(mPosition);

        });




        return rootView;
    }



    @Override
    public void onItemClick(String link) {
        mCallBack.onItemSelected(link);
    }
}
