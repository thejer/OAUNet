package ng.codeinn.oaunet.ui.links;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.utilities.MyWebViewClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranscriptFragment extends Fragment {


    private WebView mTranscriptWebView;
    private String mTranscriptUrl;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MyWebViewClient mMyWebViewClient;


    public TranscriptFragment() {
        // Required empty public constructor
    }


    public static TranscriptFragment newInstance(){
        return new TranscriptFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_transcript, container, false);
        mTranscriptUrl = "https://www.etx-ng.com/oau";
        mTranscriptWebView = rootView.findViewById(R.id.transcript_webview);
        mSwipeRefreshLayout = rootView.findViewById(R.id.transcript_swipe_layout);
        mMyWebViewClient = new MyWebViewClient(EportalFragment.newInstance(), mTranscriptUrl, mSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mTranscriptWebView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = mTranscriptWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);
        mTranscriptWebView.setWebViewClient(mMyWebViewClient);
        mTranscriptWebView.loadUrl(mTranscriptUrl);
        mTranscriptWebView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode != 4 || !mTranscriptWebView.canGoBack()) {
                return false;
            }
            mTranscriptWebView.goBack();
            return true;
        });

        mTranscriptWebView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (mTranscriptWebView.getScrollY() == 0) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            mTranscriptWebView.reload();
        }, 3000));
        return rootView;
    }

}
