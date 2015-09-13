package com.apps.nikhil.expodaddyv20;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.apps.nikhil.expodaddyv20.Fragments.Fragment_Companies;
import com.apps.nikhil.expodaddyv20.Fragments.Fragment_Exhibitions;
import com.apps.nikhil.expodaddyv20.Fragments.Fragment_WhitePapers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikhil on 20-07-2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private Map<Integer,String> mFragmentTags;
    private FragmentManager mFragmentManager;
    private Context mContext;

    public PagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        mFragmentTags = new HashMap<Integer,String>();
        mFragmentManager = fm;
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: return new Fragment_Exhibitions();

            case 1: return new Fragment_Companies();

            case 2: return  new Fragment_WhitePapers();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            //Record the fragment tag here
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position,tag);
        }

        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag==null)
            return null;
        return mFragmentManager.findFragmentByTag(tag);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
