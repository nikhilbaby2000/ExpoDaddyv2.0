package com.apps.nikhil.expodaddyv20.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.toolbox.NetworkImageView;
import com.apps.nikhil.expodaddyv20.ListAdapters.WhitepaperListAdapter;
import com.apps.nikhil.expodaddyv20.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class Fragment_WhitePapers extends Fragment {

    WhitepaperListAdapter whitepaperListAdapter;

    public Fragment_WhitePapers() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__white_papers_layout, container, false);

        try {
            whitepaperListAdapter = new WhitepaperListAdapter(new String[]{"http://www.cegeka.com/en/~/media/main/images/content%20blocks/resources/whitepaper%20agile/agile-whitepaper-view-4.ashx",
                    "http://www.elcomcms.com/Images/UserUploadedImages/1264/MobileWeb_ResponsiveAdaptive_whitepaper.jpg",
                    "https://www.timeshighereducation.co.uk/sites/default/files/Pictures/web/n/u/t/white_paper_logo_for_web.jpg"}, getActivity().getApplicationContext());
            ListView whitepaper_listview = (ListView) view.findViewById(R.id.listView_whitepapers);
            whitepaper_listview.setAdapter(whitepaperListAdapter);

            NetworkImageView whitepaper_logo = (NetworkImageView) view.findViewById(R.id.whitepaper_logo);
            whitepaper_logo.setDefaultImageResId(R.drawable.whitepaper_logo);
        }
        catch (Exception ex) {}

        // Inflate the layout for this fragment
        return view;
    }


}
