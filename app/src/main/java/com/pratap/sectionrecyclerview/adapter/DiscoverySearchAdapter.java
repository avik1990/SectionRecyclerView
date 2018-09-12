package com.pratap.sectionrecyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.pratap.sectionrecyclerview.MainActivity;
import com.pratap.sectionrecyclerview.R;
import com.pratap.sectionrecyclerview.models.DiscoveryInstantSearchModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DiscoverySearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    Context context;
    public static final int ITEM_TYPE_USERHEADER = 0;
    public static final int ITEM_TYPE_USERITEM = 1;
    public static final int ITEM_TYPE_USERSEEMORE = 2;
    public static final int ITEM_TYPE_TRIPHEADER = 3;
    public static final int ITEM_TYPE_TRIPITEM = 4;
    public static final int ITEM_TYPE_DIVIDER = 5;
    List<DiscoveryInstantSearchModel> list_discoveryInstantSearchModel;
    String header = "";
    private List<DiscoveryInstantSearchModel> searchfiltered;

    public DiscoverySearchAdapter(Context context, List<DiscoveryInstantSearchModel> list_discoveryInstantSearchModel) {
        this.context = context;
        this.list_discoveryInstantSearchModel = list_discoveryInstantSearchModel;
        this.searchfiltered = list_discoveryInstantSearchModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder holder = null;

        switch (viewType) {
            case ITEM_TYPE_USERHEADER:
                header = "Users";
                view = inflater.inflate(R.layout.row_header, parent, false);
                holder = new HeaderViewHolder(view);
                break;

            case ITEM_TYPE_USERITEM:
                view = inflater.inflate(R.layout.row_user, parent, false);
                holder = new UserViewHolder(view);
                break;

            case ITEM_TYPE_USERSEEMORE:
                view = inflater.inflate(R.layout.row_seemore, parent, false);
                holder = new SeemoreViewHolder(view);
                break;

            case ITEM_TYPE_TRIPHEADER:
                header = "Trips";
                //view = inflater.inflate(R.layout.item_recommended_recyclerview, parent, false);
                view = inflater.inflate(R.layout.row_header, parent, false);
                holder = new HeaderViewHolder(view);
                break;

            case ITEM_TYPE_TRIPITEM:
                //view = inflater.inflate(R.layout.item_recommended_recyclerview, parent, false);
                view = inflater.inflate(R.layout.row_trip, parent, false);
                holder = new TripViewHolder(view);
                break;

            case ITEM_TYPE_DIVIDER:
                //view = inflater.inflate(R.layout.item_recommended_recyclerview, parent, false);
                view = inflater.inflate(R.layout.row_divider, parent, false);
                holder = new DividerViewHolder(view);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder.getItemViewType() == ITEM_TYPE_USERITEM) {
            UserViewHolder userviewViewHolder = (UserViewHolder) holder;
            UserDataSetter(userviewViewHolder, i);
        } else if (holder.getItemViewType() == ITEM_TYPE_TRIPITEM) {
            TripViewHolder tripViewHolder = (TripViewHolder) holder;
            TripDataSetter(tripViewHolder, i);
        } else if (holder.getItemViewType() == ITEM_TYPE_USERSEEMORE) {
            SeemoreViewHolder tripViewHolder = (SeemoreViewHolder) holder;
            SeemoreSetter(tripViewHolder, i);
        }
    }

    private void SeemoreSetter(SeemoreViewHolder tripViewHolder, int i) {
        tripViewHolder.tvSeemore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((MainActivity) context).mergeAllData();
            }
        });
    }

    private void TripDataSetter(TripViewHolder tripViewHolder, int i) {
        tripViewHolder.tvName.setText(list_discoveryInstantSearchModel.get(i).getTripName());
        String userTriptName = list_discoveryInstantSearchModel.get(i).getTripName().toLowerCase(Locale.getDefault());

        String searchString = "ge";
        if (userTriptName.contains(searchString)) {
            Log.e("test", userTriptName + " contains: " + searchString);
            int startPos = userTriptName.indexOf(searchString);
            int endPos = startPos + searchString.length();

            Spannable spanText = Spannable.Factory.getInstance().newSpannable(tripViewHolder.tvName.getText());
            spanText.setSpan(new ForegroundColorSpan(Color.RED), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            tripViewHolder.tvName.setText(spanText, TextView.BufferType.SPANNABLE);
        }
    }

    private void UserDataSetter(UserViewHolder userviewViewHolder, int i) {
        userviewViewHolder.tvName.setText(list_discoveryInstantSearchModel.get(i).getUserFirstName() + " " + list_discoveryInstantSearchModel.get(i).getUserLastName());
        userviewViewHolder.tvUsername.setText(list_discoveryInstantSearchModel.get(i).getUserName());
        String userFirstName = list_discoveryInstantSearchModel.get(i).getUserFirstName().toLowerCase(Locale.getDefault());
        String searchString = "ro";
        if (userFirstName.contains(searchString)) {
            Log.e("test", userFirstName + " contains: " + searchString);
            int startPos = userFirstName.indexOf(searchString);
            int endPos = startPos + searchString.length();

            Spannable spanText = Spannable.Factory.getInstance().newSpannable(userviewViewHolder.tvName.getText()); // <- EDITED: Use the original string, as `country` has been converted to lowercase.
            spanText.setSpan(new ForegroundColorSpan(Color.RED), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            userviewViewHolder.tvName.setText(spanText, TextView.BufferType.SPANNABLE);
        }

    }


    @Override
    public int getItemCount() {
        return searchfiltered.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (list_discoveryInstantSearchModel.get(position).getItemType() == ITEM_TYPE_USERHEADER) {
            return ITEM_TYPE_USERHEADER;
        } else if (list_discoveryInstantSearchModel.get(position).getItemType() == ITEM_TYPE_USERITEM) {
            return ITEM_TYPE_USERITEM;
        } else if (list_discoveryInstantSearchModel.get(position).getItemType() == ITEM_TYPE_USERSEEMORE) {
            return ITEM_TYPE_USERSEEMORE;
        } else if (list_discoveryInstantSearchModel.get(position).getItemType() == ITEM_TYPE_TRIPHEADER) {
            return ITEM_TYPE_TRIPHEADER;
        } else if (list_discoveryInstantSearchModel.get(position).getItemType() == ITEM_TYPE_TRIPITEM) {
            return ITEM_TYPE_TRIPITEM;
        } else if (list_discoveryInstantSearchModel.get(position).getItemType() == ITEM_TYPE_DIVIDER) {
            return ITEM_TYPE_DIVIDER;
        } else {
            return ITEM_TYPE_TRIPITEM;
        }
    }

    public void setQuery(String s) {
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvName;

        HeaderViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvName.setText(header);
        }
    }


    private class UserViewHolder extends RecyclerView.ViewHolder {

        CircleImageView avatar;
        AppCompatTextView tvName, tvUsername;


        public UserViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvUsername = itemView.findViewById(R.id.tvUsername);


        }
    }

    private class SeemoreViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvSeemore;

        public SeemoreViewHolder(View itemView) {
            super(itemView);
            tvSeemore = itemView.findViewById(R.id.tvSeemore);

        }
    }

    private class TripViewHolder extends RecyclerView.ViewHolder {

        ImageView ivTripphoto;
        AppCompatTextView tvName, tvUsername;

        public TripViewHolder(View itemView) {
            super(itemView);
            ivTripphoto = itemView.findViewById(R.id.ivTripPhoto);
            tvName = itemView.findViewById(R.id.tvName);
            tvUsername = itemView.findViewById(R.id.tvUsername);
        }
    }

    private class DividerViewHolder extends RecyclerView.ViewHolder {
        public DividerViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    searchfiltered = list_discoveryInstantSearchModel;
                } else {
                    List<DiscoveryInstantSearchModel> filteredList = new ArrayList<>();
                    for (DiscoveryInstantSearchModel row : list_discoveryInstantSearchModel) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getUserFirstName().toLowerCase().trim().contains(charString.toLowerCase().trim()) || row.getTripName().toLowerCase().trim().contains(charString.toLowerCase().trim())) {
                            Log.d("UserName1", row.getTripName().trim());
                            filteredList.add(row);
                        }
                    }

                    searchfiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = searchfiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Log.d("Results", "" + "CALLED");
                searchfiltered = (ArrayList<DiscoveryInstantSearchModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(DiscoveryInstantSearchModel contact);
    }

}
