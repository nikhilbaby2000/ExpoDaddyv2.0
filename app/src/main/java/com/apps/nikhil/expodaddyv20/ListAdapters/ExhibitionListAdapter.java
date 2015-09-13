package com.apps.nikhil.expodaddyv20.ListAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.apps.nikhil.expodaddyv20.R;

/**
 * Created by Nikhil on 04-08-2015.
 */
public class ExhibitionListAdapter extends BaseAdapter {

    String[] ImageUrlArray;
    Context context;
    private  static LayoutInflater layoutInflater;
    RequestQueue queue;
    private ImageLoader imageLoader;
    int like_count =0;

    public ExhibitionListAdapter(String[] ImageList, Context recContext) {
        ImageUrlArray = ImageList;
        context = recContext;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        queue= Volley.newRequestQueue(context);

    }


    @Override
    public int getCount() {
        return ImageUrlArray.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {

        Holder() {  }

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView= null;
        Holder holder = new Holder();

        imageLoader = new ImageLoader(queue,new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap> mCache = new LruCache<String,Bitmap>(getCount() * 5);
            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url,bitmap);

            }
        });


        rowView = layoutInflater.inflate(R.layout.list_item_exhibitions_layout,parent,false);

        final Button E_like_bttn = (Button) rowView.findViewById(R.id.exhibition_like_button);
        final TextView E_like_count_text =(TextView) rowView.findViewById(R.id.exhibition_like_count);
        NetworkImageView img1=(NetworkImageView) rowView.findViewById(R.id.img11);
        NetworkImageView img2=(NetworkImageView) rowView.findViewById(R.id.img22);
        NetworkImageView img3=(NetworkImageView) rowView.findViewById(R.id.exhibition_logo);
        final ImageButton like_btn = (ImageButton) rowView.findViewById(R.id.exhibition_like_ImageButton);

        like_btn.setTag("0");

        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like_btn.getTag() == "0") {
                    like_btn.setImageResource(R.drawable.liked);
                    like_btn.setTag("1");
                    like_count+=1;
                    E_like_bttn.setText("Unlike");
                }
                else {
                    like_btn.setImageResource(R.drawable.like_img);
                    like_btn.setTag("0");
                    like_count-=1;
                    E_like_bttn.setText("Like");
                }

                E_like_count_text.setText(like_count+" Likes");
            }
        });

        E_like_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like_btn.getTag() == "0") {
                    like_btn.setImageResource(R.drawable.liked);
                    like_btn.setTag("1");
                    like_count+=1;
                    E_like_bttn.setText("Unlike");
                }
                else {
                    like_btn.setImageResource(R.drawable.like_img);
                    like_btn.setTag("0");
                    like_count-=1;
                    E_like_bttn.setText("Like");
                }

                E_like_count_text.setText(like_count+" Likes");

            }
        });

        img1.setAdjustViewBounds(true);
        img2.setAdjustViewBounds(true);
        //img3.setAdjustViewBounds(true);


        img1.setImageUrl(ImageUrlArray[0],imageLoader);
        img2.setImageUrl(ImageUrlArray[1],imageLoader);
        img3.setImageUrl(ImageUrlArray[2],imageLoader);

        img1.setDefaultImageResId(R.drawable.exhibition_logo);
        img2.setDefaultImageResId(R.drawable.whitepaper_logo);
        img3.setDefaultImageResId(R.drawable.company_logo);




        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You Clicked on item " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }
}
