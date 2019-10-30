package com.example.studentinfo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.studentinfo.R;
import com.example.studentinfo.databinding.ActivitySignUpBinding;
import com.example.studentinfo.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private String name, email, password;
    private List<User> users;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();


        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
            finish();
        }

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.nameET.getText().toString().trim();
                email = binding.emailET.getText().toString().trim();
                password = binding.passwordET.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Email is empty", Toast.LENGTH_SHORT).show();
                }else if(password.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
                }else{
                    SignUpUser(email, password);
                }
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }


    private void SignUpUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "User Created Successful", Toast.LENGTH_SHORT).show();
                            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                            String id = currentUser.getUid();
                            if(id!= null){
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }else{
                            Toast.makeText(SignUpActivity.this, "User not created", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
