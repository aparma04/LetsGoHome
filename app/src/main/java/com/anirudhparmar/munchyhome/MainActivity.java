package com.anirudhparmar.munchyhome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yelp.clientlib.connection.YelpAPI;
import com.yelp.clientlib.connection.YelpAPIFactory;
import com.yelp.clientlib.entities.Business;
import com.yelp.clientlib.entities.Category;
import com.yelp.clientlib.entities.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //    android.widget.SearchView mYelp_Search;
    //   ListView mYelp_List;
    YelpAPIFactory mYelpAPIFactory;
    YelpAPI mYelpAPI;
    Map<String, String> mParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starts");

        //yThis library uses a YelpAPI object to query against the API.
        // Instantiate a YelpAPI object by using YelpAPIFactory with your API keys.

        mYelpAPIFactory = new YelpAPIFactory(
                getString(R.string.consumerKey),
                getString(R.string.consumerSecret),
                getString(R.string.token),
                getString(R.string.tokenSecret));

        mYelpAPI = mYelpAPIFactory.createAPI();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //    mYelp_Search = (android.widget.SearchView) findViewById(R.id.yelp_search);
        //   mYelp_List = (ListView) findViewById(R.id.yelp_list);
        mParams = new HashMap<>();
        // general params
        mParams.put("term", "coffe");

        new FetchYelpSearch().execute();

        Log.d(TAG, "onCreate: Ends");
    }

    class FetchYelpSearch extends AsyncTask<String, String, String> {
        private static final String TAG = "FetchYelpSearch";
        

        //    List<Restaurant> restaurants;

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "doInBackground: Starts");

            // coordinates
//            CoordinateOptions coordinate = CoordinateOptions.builder()
//                    .latitude(37.7577)
//                    .longitude(-122.4376).build();
            Call<SearchResponse> call = mYelpAPI.search("Manhattan", mParams);
            try {
                SearchResponse searchResponse = call.execute().body();
                int totalNumberOfResult = searchResponse.total();

                ArrayList<Business> businesses = searchResponse.businesses();

                String buisnessImage = businesses.get(0).imageUrl();
                String businessName = businesses.get(0).name();  // "JapaCurry Truck"
                String ratingImageUrl = businesses.get(0).ratingImgUrlSmall();
                ArrayList<String> address = businesses.get(0).location().displayAddress();
                ArrayList<Category> categories = businesses.get(0).categories();
                String mobileUrl = businesses.get(0).mobileUrl();

                //display in card
                System.out.println("totalNumberOfResult :" +totalNumberOfResult);
                System.out.println("buisnessImage : " +buisnessImage);
                System.out.println("businessName : " +businessName);
                System.out.println("ratingImageUrl : "+ratingImageUrl);
                System.out.println("address : " +address);
                System.out.println("categories : " +categories);



                //on click should pop up this mobile url page
                System.out.println("mobile url : " +mobileUrl);


            } catch (IOException e) {
                e.printStackTrace();
            }
    return  null;

        }

    }
}