package com.pratap.sectionrecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.pratap.sectionrecyclerview.adapter.DiscoverySearchAdapter;
import com.pratap.sectionrecyclerview.models.DiscoveryInstantSearchModel;
import com.pratap.sectionrecyclerview.models.TripModel;
import com.pratap.sectionrecyclerview.models.UserModel;

import java.util.ArrayList;
import java.util.List;

import static com.pratap.sectionrecyclerview.adapter.DiscoverySearchAdapter.ITEM_TYPE_DIVIDER;
import static com.pratap.sectionrecyclerview.adapter.DiscoverySearchAdapter.ITEM_TYPE_TRIPHEADER;
import static com.pratap.sectionrecyclerview.adapter.DiscoverySearchAdapter.ITEM_TYPE_TRIPITEM;
import static com.pratap.sectionrecyclerview.adapter.DiscoverySearchAdapter.ITEM_TYPE_USERHEADER;
import static com.pratap.sectionrecyclerview.adapter.DiscoverySearchAdapter.ITEM_TYPE_USERITEM;
import static com.pratap.sectionrecyclerview.adapter.DiscoverySearchAdapter.ITEM_TYPE_USERSEEMORE;

public class MainActivity extends AppCompatActivity implements DiscoverySearchAdapter.ContactsAdapterListener {

    private Toolbar toolbar;
    List<DiscoveryInstantSearchModel> list_discoveryInstantSearchModel = new ArrayList<>();
    List<UserModel> list_user;
    List<TripModel> list_trip_model;
    RecyclerView my_recycler_view;
    LinearLayoutManager linearLayoutManager;
    DiscoverySearchAdapter discoverySearchAdapter;
    Context context;
    EditText etSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle("Section RecyclerView");
        }


        initview();
        fillArraylist();
        inflateview();

    }

    private void initview() {
        list_user = new ArrayList<>();
        list_trip_model = new ArrayList<>();
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);
        etSearch = findViewById(R.id.etSearch);
        etSearch.requestFocus();
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0)
                    discoverySearchAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void inflateview() {
        // my_recycler_view.addItemDecoration(new DividerItemDecoration(this, null));
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        my_recycler_view.setLayoutManager(linearLayoutManager);
        discoverySearchAdapter = new DiscoverySearchAdapter(context, list_discoveryInstantSearchModel);
        my_recycler_view.setAdapter(discoverySearchAdapter);
    }

    //String userId, String userName, String userFirstName, String userLastName, String userProfilePic, String tripId, String tripName, String tripCoverPic, int itemType

    private void fillArraylist() {
        list_discoveryInstantSearchModel.clear();
        list_user.clear();
        list_trip_model.clear();

        ///fill userlist
        ///String userId, String userName, String userFirstName, String userLastName, String userProfilePic
        list_user.add(new UserModel("1", "@roxy", "Roxy", "Sinny", ""));
        list_user.add(new UserModel("2", "@roxy", "Roxroy1", "Sinny", ""));
        list_user.add(new UserModel("3", "@roxy", "Roxy2", "Sinny", ""));
        list_user.add(new UserModel("4", "@roxy", "Roxy3", "Sinny", ""));
        list_user.add(new UserModel("5", "@roxy", "Roxy4", "Sinny", ""));
        list_user.add(new UserModel("6", "@roxy", "Roxy5", "Sinny", ""));
        list_user.add(new UserModel("7", "@roxy", "Roxy6", "Sinny", ""));
        list_user.add(new UserModel("8", "@roxy", "Roxy7", "Sinny", ""));
        list_user.add(new UserModel("9", "@roxy", "Roxy8", "Sinny", ""));
        list_user.add(new UserModel("10", "@roxy", "Roxy9", "Sinny", ""));
        list_user.add(new UserModel("11", "@roxy", "Roxy10", "Sinny", ""));
        ////fill triplist
        ///String tripId, String tripName, String tripCoverPic
        list_trip_model.add(new TripModel("1", "German Trip", ""));
        list_trip_model.add(new TripModel("2", "American Trip", ""));
        list_trip_model.add(new TripModel("3", "England Trip", ""));
        list_trip_model.add(new TripModel("4", "Usa Trip", ""));
        list_trip_model.add(new TripModel("5", "German Trip1", ""));
        list_trip_model.add(new TripModel("6", "German Trip2", ""));
        list_trip_model.add(new TripModel("7", "German Trip3", ""));
        list_trip_model.add(new TripModel("8", "German Trip4", ""));
        list_trip_model.add(new TripModel("9", "German Trip5", ""));


        mergeFewData();
    }

    private void mergeFewData() {
        if (list_user.size() > 0) {
            list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel("", "", "", "", "", "", "", "", ITEM_TYPE_USERHEADER));
            if (list_user.size() > 3) {
                for (int i = 0; i < 3; i++) {
                    list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel(list_user.get(i).getUserId(),
                            list_user.get(i).getUserName(), list_user.get(i).getUserFirstName(), list_user.get(i).getUserLastName(), "", "", "", "", ITEM_TYPE_USERITEM));

                }
                list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel("", "", "", "", "", "", "", "", ITEM_TYPE_USERSEEMORE));
            } else {
                for (int i = 0; i < list_user.size(); i++) {
                    list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel(list_user.get(i).getUserId(),
                            list_user.get(i).getUserName(), list_user.get(i).getUserFirstName(), list_user.get(i).getUserLastName(), "", "", "", "", ITEM_TYPE_USERITEM));

                }
            }
            list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel("", "", "", "", "", "", "", "", ITEM_TYPE_DIVIDER));
        }

        if (list_trip_model.size() > 0) {
            list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel("", "", "", "", "", "", "", "", ITEM_TYPE_TRIPHEADER));
            for (int i = 0; i < list_trip_model.size(); i++) {
                list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel("",
                        "", "", "", "", list_trip_model.get(i).getTripId(), list_trip_model.get(i).getTripName(), "", ITEM_TYPE_TRIPITEM));

            }
        }

      /*  for (int i = 0; i < list_discoveryInstantSearchModel.size(); i++) {
            Log.d("TripFlags", "" + list_discoveryInstantSearchModel.get(i).getItemType());
        }*/


    }

    public void mergeAllData() {
        list_discoveryInstantSearchModel.clear();
        if (list_user.size() > 0) {
            list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel("", "", "", "", "", "", "", "", ITEM_TYPE_USERHEADER));
            for (int i = 0; i < list_user.size(); i++) {
                list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel(list_user.get(i).getUserId(),
                        list_user.get(i).getUserName(), list_user.get(i).getUserFirstName(), list_user.get(i).getUserLastName(), "", "", "", "", ITEM_TYPE_USERITEM));
            }
            list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel("", "", "", "", "", "", "", "", ITEM_TYPE_DIVIDER));
        }

        if (list_trip_model.size() > 0) {
            list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel("", "", "", "", "", "", "", "", ITEM_TYPE_TRIPHEADER));
            for (int i = 0; i < list_trip_model.size(); i++) {
                list_discoveryInstantSearchModel.add(new DiscoveryInstantSearchModel("",
                        "", "", "", "", list_trip_model.get(i).getTripId(), list_trip_model.get(i).getTripName(), "", ITEM_TYPE_TRIPITEM));

            }
        }

        inflateview();
    }


    @Override
    public void onContactSelected(DiscoveryInstantSearchModel contact) {

    }
}
