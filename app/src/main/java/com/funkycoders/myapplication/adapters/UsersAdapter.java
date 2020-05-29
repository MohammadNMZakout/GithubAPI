package com.funkycoders.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.funkycoders.myapplication.R;
import com.funkycoders.myapplication.api.models.UsersResponse;
import com.funkycoders.myapplication.views.UsersViewHolder;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    private List<UsersResponse> mList = null;

    public UsersAdapter(List<UsersResponse> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_user, parent, false);
        return new UsersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    public void updateList(List<UsersResponse> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
