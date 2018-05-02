package ng.codeinn.oaunet.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ng.codeinn.oaunet.R;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    TextView titleView,
            dateView,
            introTextView;
    public ItemViewHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.item_title_id);
        dateView = itemView.findViewById(R.id.date_id);
        introTextView = itemView.findViewById(R.id.intro_text_id);

    }
}
