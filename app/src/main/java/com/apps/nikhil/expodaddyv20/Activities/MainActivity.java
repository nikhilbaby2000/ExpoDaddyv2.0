package com.apps.nikhil.expodaddyv20.Activities;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.apps.nikhil.expodaddyv20.PagerAdapter;
import com.apps.nikhil.expodaddyv20.R;
import com.viewpagerindicator.CirclePageIndicator;



public class MainActivity extends ActionBarActivity implements View.OnTouchListener, ViewTreeObserver.OnScrollChangedListener {

    ViewPager viewPager;

    ActionBar actionBar;
    public GestureDetector gestureDetector1;
    public float x,y;
    public static final String TAG = "MyTag";
    RequestQueue queue;
    private ActionBar mActionBar;
    private ListView exhibition_listview, company_listview, whitepaper_listview;
    public float mActionBarHeight;
    public float yValue;
    int exitvalue=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        setContentView(R.layout.activity_main);

        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(
                new int[] { R.attr.actionBarSize });
        mActionBarHeight = styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        mActionBar = getSupportActionBar();

        exhibition_listview = (ListView) findViewById(R.id.listView_exhibitions);
        company_listview = (ListView) findViewById(R.id.listView_companies);
        whitepaper_listview = (ListView) findViewById(R.id.listView_whitepapers);
/*
        exhibition_listview.getViewTreeObserver().addOnScrollChangedListener(this);
        company_listview.getViewTreeObserver().addOnScrollChangedListener(this);
        whitepaper_listview.getViewTreeObserver().addOnScrollChangedListener(this);
*/

        //Adding the View pager for swipe views
        viewPager = (ViewPager) findViewById(R.id.pager_mainActivity);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(pagerAdapter);

        //Setting Page Indicator
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(viewPager);
        circlePageIndicator.setStrokeColor(Color.parseColor("#FF040405")); //Outer circle color >> Dark Black
        circlePageIndicator.setFillColor(Color.parseColor("#9529aada"));  //Current Page color
        circlePageIndicator.setPageColor(Color.TRANSPARENT); //Un-selected page fill color
        circlePageIndicator.setCentered(true);
        circlePageIndicator.setRadius(5);




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //exhibition_listview.getViewTreeObserver().addOnScrollChangedListener(this); //Not still in view. still in blind.

/*
        textView_exhibitions = (TextView) findViewById(R.id.textView);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://httpbin.org/get?param1=hello";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                resp = response.toString();
                textView_exhibitions.setText("Response => "+ response.toString());

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
*/



        // Instantiate the RequestQueue.
        //       queue = Volley.newRequestQueue(this);


        /*

        String url ="http://www.hdwallpapersinn.com/wp-content/uploads/2014/07/baby-girl-blue-eyes-beautiful-images-desktop-hd-wallappers.jpg";

        final ImageView im = (ImageView)findViewById(R.id.imageView);
        ImageRequest imageRequest = new ImageRequest(url,new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                im.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(),"req serviced 0",Toast.LENGTH_SHORT).show();
            }
        },0,0,null,null);

*/

 /*

        //Using Network Image View of volley

        ImageLoader imageLoader = new ImageLoader(queue,new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap> mCache = new LruCache<String,Bitmap>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url,bitmap);

            }
        });

        final NetworkImageView im1 = (NetworkImageView)findViewById(R.id.imageView2);
        im1.setImageUrl("https://raw.githubusercontent.com/lucasr/twoway-view/master/images/sample.png",imageLoader);

*/

 /*
        ImageRequest imageRequest1 = new ImageRequest("http://www.wpclipart.com/cartoon/signs/more_signs/What_word_bubble.png",new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                im1.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(),"req serviced 1",Toast.LENGTH_SHORT).show();
            }
        },0,0,null,null);

*/
        //String Request
/*
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView0002.setText("Response is: "+ response.length());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                 textView0002.setText("That didn't work! " + error.toString());
            }
        });

        //JSON obj Request
        String url2 = "https://www.filepicker.io/api/file/DCL5K46FS3OIxb5iuKby/metadata";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url2,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    textView0002.setText("Response: " + jsonObject.names().toString() +" | " +jsonObject.getString("filename"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    textView0002.setText(e.toString());
                    Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView0002.setText("That didnt work json");
            }
        });


        stringRequest.setTag(TAG);
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.add(imageRequest);
        //queue.add(imageRequest1);
        queue.add(jsonObjectRequest);

        //onStop();
*/

    }

    @Override
    protected void onStop() {
        super.onStop();
        if ( queue != null) {
            queue.cancelAll(TAG);
            Toast.makeText(getApplicationContext(),"Cancelled ",Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onScrollChanged() {



    }

    public void ShowHide() {
        float y = yValue;
        if (y >= mActionBarHeight && mActionBar.isShowing()) {
            mActionBar.hide();
        } else if ( y==0 && !mActionBar.isShowing()) {
            mActionBar.show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && exitvalue==0) {
            exitvalue=1;
            Toast.makeText(getApplicationContext(),"Press again to exit",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            exitvalue=0;
            Toast.makeText(getApplicationContext(),"Exiting",Toast.LENGTH_SHORT).show();
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            return true;
        }

    }

    public void showMainPopup(View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main_options, popup.getMenu());
        popup.show();
    }

    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.txt_user_name)
            showMainPopup(v);

    }
}
