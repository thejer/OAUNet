package ng.codeinn.oaunet.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.data.database.Event;
import ng.codeinn.oaunet.data.database.News;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventsAdapterViewHolder>{

    private List<Event> mEvents;

    private ListItemClickListener mListItemClickListener;

    public EventsListAdapter(List<Event> events, ListItemClickListener listItemClickListener) {
       mEvents = events;
       mListItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public EventsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        view.setFocusable(true);

        return new EventsListAdapter.EventsAdapterViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull EventsAdapterViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class EventsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleView,
                dateView,
                introTextView;
        public EventsAdapterViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
