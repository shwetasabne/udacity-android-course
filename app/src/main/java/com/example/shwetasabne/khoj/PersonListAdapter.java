package com.example.shwetasabne.khoj;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonListAdapterViewHolder> {

    private static final String TAG = "PersonListAdapter";

    private String[] mPersonListData;

    private final PersonListAdapterClickHandler mClickHandler;

    public interface PersonListAdapterClickHandler {
        void onClick(String currentPerson);
    }

    public PersonListAdapter(PersonListAdapterClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public PersonListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.i(TAG, "onCreateViewHolder called..");
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.single_person_item_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new PersonListAdapterViewHolder(view);
    }


    public void onBindViewHolder(@NonNull PersonListAdapterViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder called..");
        String personDataForThisRow = mPersonListData[position];
        holder.mSinglePersonDataTextView.setText(personDataForThisRow);
        holder.mSinglePersonDataInfoView.setText("Mumbai, MH");
    }


    public int getItemCount() {
        Log.i(TAG, "getItemCount called..");
        if(null == mPersonListData) return 0;

        return mPersonListData.length;
    }

    public void setPersonListData(String[] personListData) {
        Log.i(TAG, "setPersonListData called..");
        mPersonListData = personListData;
        notifyDataSetChanged();
    }


    public class PersonListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mSinglePersonDataTextView;
        public final TextView mSinglePersonDataInfoView;

        public PersonListAdapterViewHolder(View itemView) {
            super(itemView);
            Log.i(TAG, "PersonListAdapterViewHolder... called..");
            mSinglePersonDataTextView = (TextView) itemView.findViewById(R.id.tv_person_data);
            mSinglePersonDataInfoView = (TextView) itemView.findViewById(R.id.tv_person_info);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String currentPersonData = mPersonListData[adapterPosition];
            mClickHandler.onClick(currentPersonData);
        }
    }
}
