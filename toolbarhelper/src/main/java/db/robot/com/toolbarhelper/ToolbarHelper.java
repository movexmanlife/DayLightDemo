package db.robot.com.toolbarhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;

/**
 */
public class ToolbarHelper {
    private Context mContext;
    private LayoutInflater mInflater;

    private Toolbar mToolBar;
    private FrameLayout mContentView;
    private View mCustomView;

    public ToolbarHelper(Context context, int layoutId) {
        this.mContext = context.getApplicationContext();
        this.mInflater = LayoutInflater.from(mContext);
        initContentView();
        initToolbar();
        initCustomView(layoutId);
    }

    /**
     * 初始化ContentView
     */
    private void initContentView() {
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(lp);
    }

    private void initCustomView(int layoutId) {
        mCustomView = mInflater.inflate(layoutId, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        mToolBar.measure(w, h);
//        int height = mToolBar.getMeasuredHeight();
//        params.topMargin = height;
        mContentView.addView(mCustomView, params);
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }

    private void initToolbar() {
        View view = mInflater.inflate(R.layout.toolbar_layout, mContentView);
        mToolBar = (Toolbar)view.findViewById(R.id.toolbar);
    }

    public FrameLayout getContentView() {
        return mContentView;
    }

}
