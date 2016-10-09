package com.robot.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.robot.activity.MainActivity;
import com.robot.app.R;
import com.robot.utils.TabBeanUtils;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private Toolbar mToolbar;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("首页");

        /**
         * 需要加上这句话才可以，调用Fragment中的onCreateOptionsMenu()方法。
         */
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);


        /**
         * （1）不调用setHasOptionsMenu(true);
         * 这句话不写的话，则会调用MainActivity中的onCreateOptionsMenu()方法，
         * 而不会调用MainFragment中的onCreateOptionsMenu()方法。
         *
         * 注意：onCreateOptionsMenu()在Activity中和Fragment中是不一样的。
         *
         * （2）调用setHasOptionsMenu(true)；
         * MainActivity和MainFragment中的onCreateOptionsMenu()都会被调用。
         * 但是要注意重写MainFragment中的onCreateOptionsMenu()方法适合，记得要
         * 加上menu.clear()，这样子就不会出现叠加的现象。
         */
        setHasOptionsMenu(true);
        ((MainActivity) getActivity()).initDrawer(mToolbar);
        initTabLayout(view);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void initTabLayout(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);
        // 设置ViewPager的数据等
        tabLayout.setupWithViewPager(viewPager);
        //适合很多tab
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tab均分,适合少的tab
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //tab均分,适合少的tab,TabLayout.GRAVITY_CENTER
        //tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_bottomsheetdialog:
                showBottomSheetDialog();
                break;
            case R.id.action_about:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/robotlife"));
                getActivity().startActivity(intent);
                break;
            default:
                //对没有处理的事件，交给父类来处理
                return super.onOptionsItemSelected(item);

        }

        return true;
    }

    private BottomSheetDialog mBottomSheetDialog;

    private void showBottomSheetDialog() {
        View sheetDialogView = LayoutInflater.from(getContext()).inflate(R.layout.sheet_dialog, null);
        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        mBottomSheetDialog.setContentView(sheetDialogView);
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
        mBottomSheetDialog.show();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        Fragment newfragment = ContentFragment.newInstance(TabBeanUtils.getTabBean(0));
        adapter.addFrag(newfragment, TabBeanUtils.getTabBeanTitle(0));

        newfragment = ContentFragment.newInstance(TabBeanUtils.getTabBean(1));
        adapter.addFrag(newfragment, TabBeanUtils.getTabBeanTitle(1));

        newfragment = ContentFragment.newInstance(TabBeanUtils.getTabBean(2));
        adapter.addFrag(newfragment, TabBeanUtils.getTabBeanTitle(2));

        viewPager.setAdapter(adapter);
    }

}
