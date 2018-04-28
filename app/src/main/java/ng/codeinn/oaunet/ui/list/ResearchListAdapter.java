package ng.codeinn.oaunet.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.data.database.Research;

public class ResearchListAdapter extends RecyclerView.Adapter<ResearchListAdapter.ResearchAdapterViewHolder>{

    private List<Research> mResearches;

    private ListItemClickListener mListItemClickListener;

    public ResearchListAdapter(List<Research> researches, ListItemClickListener listItemClickListener) {
       mResearches = researches;
       mListItemClickListener = listItemClickListener;
    }


    @NonNull
    @Override
    public ResearchAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        view.setFocusable(true);

        return new ResearchListAdapter.ResearchAdapterViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ResearchAdapterViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ResearchAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleView,
                dateView,
                introTextView;
        public ResearchAdapterViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
