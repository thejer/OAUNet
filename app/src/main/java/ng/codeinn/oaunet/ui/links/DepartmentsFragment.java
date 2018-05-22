package ng.codeinn.oaunet.ui.links;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import ng.codeinn.oaunet.R;
import ng.codeinn.oaunet.utilities.MyWebViewClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentsFragment extends Fragment {

    private WebView mDepartmentWebView;
    private String mDepartmentsUrl;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MyWebViewClient mMyWebViewClient;


    public DepartmentsFragment() {
        // Required empty public constructor
    }

    public static DepartmentsFragment newInstance(){
        return new DepartmentsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_departments, container, false);
        mDepartmentsUrl = "https://www.oauife.edu.ng/academics/departments";
        mDepartmentWebView = rootView.findViewById(R.id.departments_webview);
        mSwipeRefreshLayout = rootView.findViewById(R.id.departments_swipe_layout);
        mMyWebViewClient = new MyWebViewClient(DepartmentsFragment.newInstance(), mDepartmentsUrl, mSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mDepartmentWebView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = mDepartmentWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);
        mDepartmentWebView.setWebViewClient(mMyWebViewClient);
        mDepartmentWebView.loadUrl(mDepartmentsUrl);
        mDepartmentWebView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode != 4 || !mDepartmentWebView.canGoBack()) {
                return false;
            }
            mDepartmentWebView.goBack();
            return true;
        });

        mDepartmentWebView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (mDepartmentWebView.getScrollY() == 0) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            mSwipeRefreshLayout.setRefreshing(false);
            mDepartmentWebView.reload();
        }, 3000));
        return rootView;
    }



}
