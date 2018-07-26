package com.example.greendaodemo.main.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
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
        void onClick(UserEntity userEntity, int position);

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("Log25", "Called");
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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {


        if (data.size() != Constants.NO_DATA) {
            final UserEntity current = data.get(position);

            Log.d("Log25", "" + current);
            RecyclerViewAdapter.WithDataViewHolder withDataViewHolder = (WithDataViewHolder) holder;
            ColorGenerator generator = ColorGenerator.MATERIAL;
            withDataViewHolder.binding.tvUserName.setText(String.valueOf(current.getUserName()));
            withDataViewHolder.binding.tvStateName.setText(String.valueOf(current.getUserState()));
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(String.valueOf(current.getUserName().trim().charAt(0)), generator.getRandomColor());
            withDataViewHolder.binding.imageView.setImageDrawable(drawable);
        }

    }


    @Override
    public int getItemCount() {
        if (data.size() == 0) {
            return 1;
        } else {
            return data.size();
        }
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

            binding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(data.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (data.size() == 0) {
            return Constants.NO_DATA;
        } else {
            return Constants.WITH_DATA;
        }
    }


    public void setData(List<UserEntity> userEntities) {
        this.data.addAll(userEntities);
        notifyItemInserted(data.size() );
    }

    public void insertItem(UserEntity userEntity) {
        this.data.add(userEntity);
        notifyItemInserted(data.size() );

    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }


}
