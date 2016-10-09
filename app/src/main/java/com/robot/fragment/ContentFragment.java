package com.robot.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robot.app.R;
import com.robot.adapter.RecyclerViewAdapter;
import com.robot.utils.TabBeanUtils;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContentFragment extends Fragment {
    private static final String KEY_PARAM_TAB_BEAN ="tabBean";
    private boolean isVisible;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private List<String> mDataList = new ArrayList<>();
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private String mTitle;
    private Handler handler;
    private Runnable runnable;
    private int i = 0;

    public static ContentFragment newInstance(TabBeanUtils.TabBean tabBean) {
        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PARAM_TAB_BEAN, tabBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabBeanUtils.TabBean tabBean = getArguments().getParcelable(KEY_PARAM_TAB_BEAN);
        if (tabBean != null) {
            mTitle = tabBean.getTitle();
        }
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity(), mDataList);
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mDataList.clear();
                setList();

            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //懒加载
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }

    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isVisible) {
            return;
        }
        setList();
    }

    protected void onInvisible() {
    }

    protected void loadMore() {
        runnable = new Runnable() {
            @Override
            public void run() {
                for (int j = i; j < i + 20; j++) {
                    mDataList.add(mTitle + "\n" + getActivity().getString(R.string.test_data) + j);
                }
                i = i + 20;
                mRecyclerViewAdapter.notifyDataSetChanged();
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 500);

    }


    private void setList() {
        runnable = new Runnable() {
            @Override
            public void run() {
                for (i = 0; i < 20; i++) {
                    mDataList.add(mTitle + "\n" + getActivity().getString(R.string.test_data) + i);
                }
                mRecyclerViewAdapter.notifyDataSetChanged();
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 500);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("wxl", "onDestroy");
        if (handler != null)
            handler.removeCallbacks(runnable);
    }
}
