package ng.codeinn.oaunet.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.data.database.Item;
import ng.codeinn.oaunet.utilities.InjectorUtils;

public class DetailActivity extends AppCompatActivity {

    private DetailActivityViewModel mViewModel;

    public static final String ITEM_LINK_EXTRA = "item_link_extra";

    private TextView detailDateView,
            detailTitleView,
            detailIntroTextView,
            detailFullText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDateView = findViewById(R.id.detail_date_id);
        detailTitleView = findViewById(R.id.detail_item_title_id);
        detailIntroTextView = findViewById(R.id.detail_intro_text_id);
        detailFullText = findViewById(R.id.full_text_view);

        String link = getIntent().getStringExtra(ITEM_LINK_EXTRA);
        DetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(this, link);
        mViewModel = ViewModelProviders.of(this, factory).get(DetailActivityViewModel.class);
        mViewModel.getItem().observe(this, item -> {
            if (item != null) setItemDetails(item);
        });

    }

    private void setItemDetails(Item itemDetails){
        detailDateView.setText(String.format(this.getString(R.string.date_format), itemDetails.getItemDateCreated()));
        detailTitleView.setText(itemDetails.getItemTitle());
        String introText = itemDetails.getItemHeader();
        introText = introText.replace("<p>", "");
        introText = introText.replace("</p>", "\n");
        Spannable span = new SpannableString(introText);
        span.setSpan(new RelativeSizeSpan(2f), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        detailIntroTextView.setText(span);
        String fullText = itemDetails.getItemFulltext();
        fullText = fullText.replace("<p>", "");
        fullText = fullText.replace("</p>", "\n");
        detailFullText.setText(fullText);
    }

}
