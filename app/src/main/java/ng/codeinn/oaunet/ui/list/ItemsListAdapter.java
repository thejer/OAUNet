package ng.codeinn.oaunet.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.data.database.Item;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemViewHolder>{

    private List<Item> mItems;

    private ListItemClickListener mListItemClickListener;

    private final Context mContext;


    public ItemsListAdapter(Context context, ListItemClickListener listItemClickListener) {
       mListItemClickListener = listItemClickListener;
       mContext = context;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        view.setFocusable(true);

        return new ItemViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Item currentItem = mItems.get(position);
        holder.dateView.setText(String.format(mContext.getString(R.string.date_format), currentItem.getItemDateCreated()));
        holder.introTextView.setText(currentItem.getItemHeader());
        holder.titleView.setText(currentItem.getItemTitle());
    }

    void swapItems(final List<Item> newItems){
//        if(mItems == null){
            mItems = newItems;
            notifyDataSetChanged();
//        }
    }

    @Override
    public int getItemCount() {
        if(null == mItems) return 0;
        return mItems.size();
    }

  
}
