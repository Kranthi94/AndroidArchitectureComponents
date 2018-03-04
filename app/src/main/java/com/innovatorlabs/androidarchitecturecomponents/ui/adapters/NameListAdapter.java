package com.innovatorlabs.androidarchitecturecomponents.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.innovatorlabs.androidarchitecturecomponents.db.entity.NameEntity;
import com.innovatorlabs.androidarchitecturecomponents.ui.listeners.NameClickListener;

import java.util.List;

public class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.ViewHolder>{

    private NameClickListener nameClickListener;

    private List<NameEntity> nameEntities;

    public NameListAdapter(NameClickListener nameClickListener){
        this.nameClickListener = nameClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return nameEntities != null ? nameEntities.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);


        }
    }

    public void setNames(List<NameEntity> nameEntities){
        this.nameEntities = nameEntities;
    }
}
