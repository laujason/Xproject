package com.ormediagroup.xproject.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ormediagroup.xproject.R;
import com.ormediagroup.xproject.TabContentFragment;


import java.util.ArrayList;
import java.util.List;



/**
 * Created by YQ04 on 2018/4/13.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{

    private TabLayout tablayout ;
    private ViewPager tabViewpager;
    private View view;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;
    private String[] mTabTitles_top = new String []{"推荐","热点","体育","健康","娱乐","军事","科技","学习","社会"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if (view != null) {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null) {
                    parent.removeView(view);
                }
                return view;
            }

            view  = inflater.inflate(R.layout.fragment_home,container,false);
            tablayout = view.findViewById(R.id.tablayout);
            tabViewpager =  view.findViewById(R.id.tab_viewpager);
            initTab(view);
            initView(view);
            return view;
    }

    private void initTab(View view) {
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setTabTextColors(ContextCompat.getColor(getActivity(), R.color.gray), ContextCompat.getColor(getActivity(), R.color.white));
        tablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.white));
        ViewCompat.setElevation(tablayout, 10);
        tablayout.setupWithViewPager(tabViewpager);

    }

    private void initView(View view) {
        tabIndicators = new ArrayList<>();
        for (int i = 0; i < mTabTitles_top.length; i++) {
            tabIndicators.add(mTabTitles_top[i]);
        }
        tabFragments = new ArrayList<>();
        for (String s : tabIndicators) {
            tabFragments.add(TabContentFragment.newInstance(s));  //内容
        }
        contentAdapter = new ContentPagerAdapter(getActivity().getSupportFragmentManager());
        tabViewpager.setAdapter(contentAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    private class ContentPagerAdapter extends FragmentPagerAdapter{
        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }
}
