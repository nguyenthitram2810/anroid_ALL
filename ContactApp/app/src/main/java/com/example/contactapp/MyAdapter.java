package com.example.contactapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    private static ArrayList<Contact> mContacts;
    private ArrayList<Contact> searchContact;
    private static Context mContext;

    public MyAdapter(ArrayList<Contact> mContacts, Context mContext) {
        this.mContacts = mContacts;
        this.mContext = mContext;
        this.searchContact = new ArrayList<Contact>(mContacts);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.tvName.setText(mContacts.get(position).getName());
        holder.tvMobile.setText(mContacts.get(position).getMobile());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Contact> filteredList = new ArrayList<Contact>();
            if(charSequence.toString().isEmpty()) {
                filteredList.addAll(searchContact);
            }
            else {
                for(Contact contact : searchContact) {
                    if(contact.getName().toString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(contact);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mContacts.clear();
            mContacts.addAll((Collection<? extends Contact>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public TextView tvName;
        public TextView tvMobile;
        public LinearLayout detailOption;
        public TextView tvCall;
        public TextView tvMessage;
        public TextView tvDetail;

        public MyViewHolder(View view) {
            super(view);
            view = view;
            tvName = view.findViewById(R.id.tv_name);
            detailOption = view.findViewById(R.id.detail_option);
            tvMessage = view.findViewById(R.id.tv_message);
            tvMobile = view.findViewById(R.id.tv_mobile);
            tvCall = view.findViewById(R.id.tv_call);
            tvDetail = view.findViewById(R.id.tv_detail);

            tvName.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(detailOption.getVisibility() == View.GONE) {
                        detailOption.setVisibility(View.VISIBLE);
                    }
                    else {
                        detailOption.setVisibility(View.GONE);
                    }
                }
            });

            tvCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String phone = mContacts.get(position).getMobile();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    mContext.startActivity(intent);
                }
            });

            tvMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String phone = mContacts.get(position).getMobile();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phone, null));
                    mContext.startActivity(intent);
                }
            });

            tvDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Contact contact = mContacts.get(position);
                    Intent intent = new Intent(mContext, DetailContactActivity.class);
                    intent.putExtra("contact", contact);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
