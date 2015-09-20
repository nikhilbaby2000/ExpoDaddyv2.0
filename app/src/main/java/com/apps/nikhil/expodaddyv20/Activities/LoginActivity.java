package com.apps.nikhil.expodaddyv20.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.apps.nikhil.expodaddyv20.FileReaderWriterClass;
import com.apps.nikhil.expodaddyv20.MoveToNewActivity;
import com.apps.nikhil.expodaddyv20.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Arrays;


public class LoginActivity extends ActionBarActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    RequestQueue queue;
    private CallbackManager callbackManager;
    String TAG = "Main Activity";
    ProfilePictureView profilePictureView;
    ProgressDialog pDialog;
    String Name,DOB,Email,Gender,Id;
    String gPersonURL,gPersonPicURl,fPersonURL="www.facebook.com/",fPersonPicURL="https://graph.facebook.com/";

    public int clicked_button_no=0;
    private FileReaderWriterClass fileWriter;

    TextView login_status;

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;
    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;
    private ImageLoader imageLoader;
    private NetworkImageView profile_pic_imageView;

    MoveToNewActivity moveToNewActivity = new MoveToNewActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext()); // Have to put code right here. No other fucking place for that will work. Hell it is - otherwise.
        setContentView(R.layout.activity_login);

        File file = new File("/storage/sdcard0/ExpoDaddy");
        if ( ! file.exists() )
            file.mkdir();

        queue = Volley.newRequestQueue(this);
        imageLoader = new ImageLoader(queue,new ImageLoader.ImageCache() {
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

        profile_pic_imageView = (NetworkImageView)findViewById(R.id.profilePic_ImgView);

        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();


        final LoginButton fb_login = (LoginButton) findViewById(R.id.facebook_login_button);
        final SignInButton google_login = (SignInButton) findViewById(R.id.google_login_button);
        google_login.setOnClickListener(this);

        fb_login.setReadPermissions(Arrays.asList("public_profile, email, user_birthday,user_friends"));
        login_status = (TextView) findViewById(R.id.textView);


        callbackManager = CallbackManager.Factory.create();
        Log.i(TAG,"here...");




        fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                login_status.setText("UserID: " + loginResult.getAccessToken().getUserId() /*+ "\n AuthToken: "+ loginResult.getAccessToken().getToken() + "\n keyHash= "*/);

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {

                                //Toast.makeText(getApplicationContext(), response.toString(),Toast.LENGTH_SHORT).show();
                                try {
                                    Name = object.getString("name");
                                    Id = object.getString("id");
                                    Email = object.getString("email");
                                    DOB = object.getString("birthday");
                                    Gender = object.getString("gender");
                                    login_status.setText(" Id:" + Id + "\n Name:" + Name + "\n DOB: " + DOB
                                            + "\n Gender:" + Gender + "\n Email:" + Email + "\n picURL=" + fPersonPicURL + Id + "/picture?type=large");

                                    profile_pic_imageView.setImageUrl(fPersonPicURL+Id+"/picture",imageLoader);
                                    //login_status.setText("Id:"+object.getString("id")+"\n Name:" + object.getString("name")+ "\n DOB: "+object.getString("birthday")
                                    //     +"\n Gender:"+object.getString("gender")+"\n Email:"+object.getString("email"));

                                    //Write User Data to file
                                    FileReaderWriterClass dataWriter = new FileReaderWriterClass();
                                    dataWriter.writeToFile(LoginActivity.this, object+"");

                                } catch (JSONException j) {

                                }
                                Log.v("LoginActivity", response.toString());
                            }
                        });
                clicked_button_no=3;
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

                //Toast.makeText(getApplicationContext(),"U: "+username+" E: "+emailid,Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onCancel() {

                login_status.setText("User Canceled Login attempt! :(");
            }

            @Override
            public void onError(FacebookException e) {
                login_status.setText("Error: "+ e.toString());
            }
        });

//----------------------------------------------------------------------------------------------------

        ImageButton g,fb;
        ImageView login_main;
        TextView new_user = (TextView) findViewById(R.id.new_user);

        g=(ImageButton) findViewById(R.id.g_btn);
        fb=(ImageButton) findViewById(R.id.fb_btn);
        login_main=(ImageView) findViewById(R.id.login_button_main);
        g.setAdjustViewBounds(true);
        fb.setAdjustViewBounds(true);
        login_main.setAdjustViewBounds(true);

        final EditText username, password;
        username =(EditText) findViewById(R.id.username_value);
        password =(EditText) findViewById(R.id.passwordLogin_value);

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_button_no=1;
                onSignInClicked();
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fb_login.performClick();
            }
        });

        login_main.setOnClickListener(new View.OnClickListener() {
            int v1=0,v2=0;
            @Override
            public void onClick(View v) {
                if ((v1=username.getText().toString().length())==0) username.setError("Username is required");
                if ((v2=password.getText().toString().length())==0) password.setError("Password is required");
                if (v1!=0 && v2!=0)
                moveToNext();
            }
        });

        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivityForResult(intent,1);

            }
        });
    }


    public void onClick(View v) {
        if (v.getId() == R.id.google_login_button) {
            clicked_button_no=1;
            onSignInClicked();
        }


        if (v.getId() == R.id.facebook_login_button)
            clicked_button_no=3;

    }

    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();

        // Show a message to the user that we are signing in.
        login_status.setText("You are about to Sign in with your Google Account");
    }

    private void onSignOutClicked() {
        // Clear the default account so that GoogleApiClient will not automatically
        // connect in the future.
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }

        login_status.setText("You have signed out from your Google Account");
        //showSignedOutUI();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                Toast.makeText(getApplicationContext(),connectionResult.toString(),Toast.LENGTH_SHORT).show();
                //showErrorDialog(connectionResult);
            }
        } else {
            // Show the signed-out UI
            Toast.makeText(getApplicationContext(),"You just signed out of your Google Account",Toast.LENGTH_SHORT).show();
            //showSignedOutUI();
        }
    }



    @Override
    public void onConnected(Bundle bundle) {
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        Log.d(TAG, "onConnected:" + bundle);
        mShouldResolve = false;

        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            Name = currentPerson.getDisplayName();
            String personPhoto = currentPerson.getImage().getUrl();
            String personGooglePlusProfile = currentPerson.getUrl();
            Email = Plus.AccountApi.getAccountName(mGoogleApiClient);
            login_status.setText("Name: "+Name+"\nEmail: "+Email+"\nPicURL: "+personPhoto);

            profile_pic_imageView.setImageUrl(personPhoto,imageLoader);

            //Write User Data to file
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name" , Name);
                jsonObject.put("email", Email);
                jsonObject.put("photo_url", personPhoto);
            }
            catch (JSONException e){
                Toast.makeText(this, "Oops.. Met with some error guy." ,Toast.LENGTH_SHORT).show();
            }
            FileReaderWriterClass dataWriter = new FileReaderWriterClass();
            dataWriter.writeToFile(LoginActivity.this, jsonObject + "");

        }

        // Show the signed-in UI
        //>>login_status.setText("Welcome, You are signed in with your Google Account! ");
        //showSignedInUI();
        moveToNext();
    }



    @Override
    public void onConnectionSuspended(int i) {

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (clicked_button_no == 3)
        callbackManager.onActivityResult(requestCode, resultCode, data);  //For Facebook SIgn in

        if (clicked_button_no==1) {
            //For Google Sign in
            Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);
            if (requestCode == RC_SIGN_IN) {
                // If the error resolution was not successful we should not resolve further.
                if (resultCode != RESULT_OK) {
                    mShouldResolve = false;
                }

                mIsResolving = false;
                mGoogleApiClient.connect();
            }
        }
        //Move to next Main Activity page
        moveToNext();
    }


    @Override
    public void finish() {
        Intent i = new Intent();
        i.putExtra("key1","message");
        setResult(2,i);

        super.finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void moveToNext() {
         moveToNewActivity.moveToMainActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
             moveToNewActivity.moveToHomeScreen(this);
        }
        return true;
    }
}
