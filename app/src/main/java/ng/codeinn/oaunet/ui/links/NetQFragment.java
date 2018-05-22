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
import android.widget.Toast;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.utilities.MyWebViewClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetQFragment extends Fragment {


    private WebView mNetQWebView;
    private String mNetQUrl;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MyWebViewClient mMyWebViewClient;


    public NetQFragment() {
        // Required empty public constructor
    }

    public static NetQFragment newInstance(){
        return new NetQFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_netq, container, false);
        mNetQUrl = "http://netque.oauife.edu.ng/";
        mNetQWebView = rootView.findViewById(R.id.netq_webview);
        mSwipeRefreshLayout = rootView.findViewById(R.id.netq_swipe_layout);
        mMyWebViewClient = new MyWebViewClient(EportalFragment.newInstance(), mNetQUrl, mSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mNetQWebView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = mNetQWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);

        mNetQWebView.setWebViewClient(mMyWebViewClient);
        mNetQWebView.loadUrl(mNetQUrl);
        mNetQWebView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode != 4 || !mNetQWebView.canGoBack()) {
                return false;
            }
            mNetQWebView.goBack();
            return true;
        });

        mNetQWebView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (mNetQWebView.getScrollY() == 0) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            mNetQWebView.reload();
        }, 3000));
        return rootView;
    }


}
