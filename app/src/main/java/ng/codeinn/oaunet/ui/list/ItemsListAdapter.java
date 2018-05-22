package ng.codeinn.oaunet.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.data.database.Item;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ItemViewHolder>{

    private List<Item> mItems;

    private ItemAdapterOnItemClickHandler mClickHandler;

    private final Context mContext;


    ItemsListAdapter(Context context, ItemAdapterOnItemClickHandler clickHandler) {
        mClickHandler = clickHandler;
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
        String introText = currentItem.getItemHeader();
        introText = introText.replace("<p>", "");
        StringBuffer buffer = new StringBuffer(introText);
        buffer.replace(introText.indexOf("<"), introText.indexOf(">")+1, "");
        holder.introTextView.setText(buffer);
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

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleView,
                dateView,
                introTextView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.item_title_id);
            dateView = itemView.findViewById(R.id.date_id);
            introTextView = itemView.findViewById(R.id.intro_text_id);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String link = mItems.get(adapterPosition).getItemLink();
            mClickHandler.onItemClick(link);
        }
    }

    public interface ItemAdapterOnItemClickHandler {
        void onItemClick(String link);
    }
}
