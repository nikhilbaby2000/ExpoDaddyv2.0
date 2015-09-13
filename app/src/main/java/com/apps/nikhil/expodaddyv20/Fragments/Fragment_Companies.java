package com.apps.nikhil.expodaddyv20.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.toolbox.NetworkImageView;
import com.apps.nikhil.expodaddyv20.ListAdapters.CompanyListAdapter;
import com.apps.nikhil.expodaddyv20.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class Fragment_Companies extends Fragment {

    CompanyListAdapter companyListAdapter;

    public Fragment_Companies() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {

        //companyListAdapter.notifyDataSetChanged();

        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__companies_layout, container, false);
        try {


            companyListAdapter = new CompanyListAdapter(new String[]{"http://www.publika.uz/uploads/2012/10/asus-logo-company.jpg",
                    "http://www.jagatreview.com/wp-content/uploads/2013/11/asus-building.jpg",
                    "http://androidandme.com/wp-content/uploads/2013/12/ASUS-Logo.jpg"}, getActivity().getApplicationContext());
            ListView company_listview = (ListView) view.findViewById(R.id.listView_companies);
            company_listview.setAdapter(companyListAdapter);

            NetworkImageView company_logo = (NetworkImageView) view.findViewById(R.id.company_logo);
            company_logo.setDefaultImageResId(R.drawable.company_logo);

        }
        catch (Exception e) {}

        // Inflate the layout for this fragment
        return view;
    }


}
