package com.example.greendaodemo.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.greendaodemo.R;
import com.example.greendaodemo.database.user.entity.UserEntity;
import com.example.greendaodemo.database.user.operations.UserOperations;
import com.example.greendaodemo.databinding.ActivityMainBinding;
import com.example.greendaodemo.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;


        binding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = binding.etUserName.getText().toString().trim();
                String stateCity = binding.etStateCity.getText().toString().trim();

                if (userName.equals(Constants.EMPTY)) {
                    binding.etUserName.setError(getString(R.string.user_name_error));
                } else if (stateCity.equals(Constants.EMPTY)) {
                    binding.etStateCity.setError(getString(R.string.state_city_error));
                } else {
                    insertDataToInternal(userName, stateCity);
                }

            }
        });
    }


    private void insertDataToInternal(String userName, String stateCity) {
        UserOperations userOperations = UserOperations.getInstance(context);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setStateCity(stateCity);
        userOperations.insertUser(userEntity);
    }


}
