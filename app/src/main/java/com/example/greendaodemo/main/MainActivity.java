package com.example.greendaodemo.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.greendaodemo.R;
import com.example.greendaodemo.database.user.entity.UserEntity;
import com.example.greendaodemo.database.user.operations.UserOperations;
import com.example.greendaodemo.databinding.ActivityMainBinding;
import com.example.greendaodemo.utils.Constants;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityMainBinding binding;
    private Context context;
    private String selectedState;
    private int selectedStatePosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(context,
                R.array.state_name, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerState.setAdapter(spinnerAdapter);

        binding.spinnerState.setOnItemSelectedListener(this);

        binding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = binding.etUserName.getText().toString().trim();

                if (userName.equals(Constants.EMPTY)) {
                    binding.etUserName.setError(getString(R.string.user_name_error));
                } else if (selectedState != null && selectedStatePosition != 1) {
                    Toast.makeText(context, R.string.state_error, Toast.LENGTH_SHORT).show();
                } else {
                    insertDataToInternal(userName, selectedState);
                }
            }
        });
    }


    private void insertDataToInternal(String userName, String state) {
        UserOperations userOperations = UserOperations.getInstance(context);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setUserState(state);
        userOperations.insertUser(userEntity);
        setRecyclerView(userEntity);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedState = adapterView.getItemAtPosition(i).toString();
        selectedStatePosition = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setRecyclerView(UserEntity userEntity) {

    }
}
