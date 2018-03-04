package com.innovatorlabs.androidarchitecturecomponents.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innovatorlabs.androidarchitecturecomponents.R;
import com.innovatorlabs.androidarchitecturecomponents.databinding.NamesFragmentBinding;
import com.innovatorlabs.androidarchitecturecomponents.db.entity.NameEntity;
import com.innovatorlabs.androidarchitecturecomponents.ui.adapters.NameListAdapter;
import com.innovatorlabs.androidarchitecturecomponents.ui.listeners.NameClickListener;
import com.innovatorlabs.androidarchitecturecomponents.viewmodel.NameViewModel;

import java.util.List;

public class NameListFragment extends Fragment{

    private static final String TAG = NameListFragment.class.getSimpleName();

    private NamesFragmentBinding mBinding;

    private NameListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.getBinding(inflater.inflate(R.layout.names_fragment, container, false));

        mAdapter = new NameListAdapter(nameClickListener);

        mBinding.namesList.setAdapter(mAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NameViewModel nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);

        subscribeUI(nameViewModel);
    }

    private void subscribeUI(NameViewModel nameViewModel){

        nameViewModel.getNames().observe(this, new Observer<List<NameEntity>>() {
            @Override
            public void onChanged(@Nullable List<NameEntity> nameEntities) {

                if(nameEntities != null){

                    mBinding.setIsLoading(false);

                    mAdapter.setNames(nameEntities);
                }else {
                    mBinding.setIsLoading(true);
                }

                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private NameClickListener nameClickListener = new NameClickListener() {
        @Override
        public void onNameClick(int id) {


        }
    };
}
