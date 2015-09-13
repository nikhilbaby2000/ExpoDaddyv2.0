package com.apps.nikhil.expodaddyv20.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.Toast;

import com.apps.nikhil.expodaddyv20.ListAdapters.ExhibitionListAdapter;
import com.apps.nikhil.expodaddyv20.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class Fragment_Exhibitions extends Fragment implements View.OnTouchListener, ViewTreeObserver.OnScrollChangedListener{

    ExhibitionListAdapter exhibitionListAdapter;

    private ListView exhibition_listview;
    private ActionBar mActionBar;
    public float mActionBarHeight;

    public Fragment_Exhibitions() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__exhibitions_layout, container, false);
        try {

            exhibitionListAdapter = new ExhibitionListAdapter(new String[]{"http://www.umbc.edu/cadvc/about/images/MSAC_logo_RGB-2.jpg",
                    "http://www.underconsideration.com/brandnew/archives/swedish_travel_ex_logo.gif",
                    "http://www.nathanael.com/wordpress/wp-content/uploads/logo_AAE-670x446.jpg"}, getActivity().getApplicationContext());
            exhibition_listview = (ListView) view.findViewById(R.id.listView_exhibitions);
            exhibition_listview.setAdapter(exhibitionListAdapter);

            exhibition_listview.setScrollingCacheEnabled(true);

            exhibition_listview.getViewTreeObserver().addOnScrollChangedListener(this);



        }
        catch (NullPointerException ex) {Toast.makeText(getActivity().getApplicationContext(),"1 "+ex.toString(),Toast.LENGTH_SHORT).show();   }

        // Inflate the layout for this fragment
        return view;
    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Toast.makeText(v.getContext(),"Clicked",Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onScrollChanged() {
/*
      float y = exhibition_listview.getScrollY();
      MainActivity m = new MainActivity();
      m.yValue = y;
      m.ShowHide();

*/
    }
}
