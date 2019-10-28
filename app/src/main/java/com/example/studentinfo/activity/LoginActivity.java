package com.example.studentinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.studentinfo.R;
import com.example.studentinfo.databinding.ActivityLoginBinding;
import com.example.studentinfo.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private List<User> users;
    private ActivityLoginBinding binding;
    private DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //databaseUser = FirebaseDatabase.getInstance().getReference().child("users");

        
    }
}
