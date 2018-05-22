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
public class EportalFragment extends Fragment {


    private WebView mEportalWebView;
    private String mEportalUrl;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MyWebViewClient mMyWebViewClient;


    public EportalFragment() {
        // Required empty public constructor
    }

    public static EportalFragment newInstance(){
        return new EportalFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_eportal, container, false);
        mEportalUrl = "http://eportal.oauife.edu.ng/home.php";
        mEportalWebView = rootView.findViewById(R.id.eportal_webview);
        mSwipeRefreshLayout = rootView.findViewById(R.id.eportal_swipe_layout);
        mMyWebViewClient = new MyWebViewClient(EportalFragment.newInstance(), mEportalUrl, mSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mEportalWebView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = mEportalWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);

        mEportalWebView.setWebViewClient(mMyWebViewClient);
        mEportalWebView.loadUrl(mEportalUrl);
        mEportalWebView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode != 4 || !mEportalWebView.canGoBack()) {
                return false;
            }
            mEportalWebView.goBack();
            return true;
        });

        mEportalWebView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (mEportalWebView.getScrollY() == 0) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            mEportalWebView.reload();
        }, 3000));
        return rootView;
    }


}
