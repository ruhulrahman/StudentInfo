package com.example.studentinfo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.studentinfo.R;
import com.example.studentinfo.model.User;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private List<User> users;
    private DatabaseReference databaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
}
