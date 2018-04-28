package ng.codeinn.oaunet.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.data.database.News;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ListAdapterViewHolder>{

    private List<News> mNews;

    private ListItemClickListener mListItemClickListener;

    public NewsListAdapter(List<News> news, ListItemClickListener listItemClickListener) {
       mNews = news;
       mListItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public ListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        view.setFocusable(true);

        return new NewsListAdapter.ListAdapterViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ListAdapterViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleView,
                dateView,
                introTextView;
        public ListAdapterViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
