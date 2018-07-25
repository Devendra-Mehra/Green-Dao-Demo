package com.example.greendaodemo.main.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.greendaodemo.R;
import com.example.greendaodemo.database.user.entity.UserEntity;
import com.example.greendaodemo.databinding.NoDataRowBinding;
import com.example.greendaodemo.databinding.WithDataRowBinding;
import com.example.greendaodemo.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<UserEntity> data;
    private ItemListener listener;


    public RecyclerViewAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();

    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    public interface ItemListener {
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constants.NO_DATA) {
            NoDataRowBinding noDataRowBinding = DataBindingUtil
                    .inflate(LayoutInflater.from(context),
                            R.layout.no_data_row, parent, false);
            return new NoDataViewHolder(noDataRowBinding);
        } else {
            WithDataRowBinding withDataRowBinding = DataBindingUtil
                    .inflate(LayoutInflater.from(context),
                            R.layout.with_data_row, parent, false);
            return new WithDataViewHolder(withDataRowBinding);
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (position != Constants.NO_DATA) {

            final UserEntity current = data.get(position - 1);

            RecyclerViewAdapter.WithDataViewHolder withDataViewHolder = (WithDataViewHolder) holder;


        }

    }


    @Override
    public int getItemCount() {
        return data.size() + 1;
    }


    class NoDataViewHolder extends RecyclerView.ViewHolder {
        NoDataRowBinding binding;

        NoDataViewHolder(NoDataRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class WithDataViewHolder extends RecyclerView.ViewHolder {

        WithDataRowBinding binding;

        WithDataViewHolder(WithDataRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (data.size() <= 0) {
            return Constants.NO_DATA;
        } else {
            return Constants.WITH_DATA;
        }
    }

}
