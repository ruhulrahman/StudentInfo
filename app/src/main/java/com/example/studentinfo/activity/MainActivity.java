package com.example.studentinfo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.studentinfo.R;
import com.example.studentinfo.databinding.ActivityMainBinding;
import com.example.studentinfo.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String name, department;
    DatabaseReference databaseStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main);

        databaseStudent = FirebaseDatabase.getInstance().getReference("student");

        binding.AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudentInfo();
            }
        });
    }

    private void addStudentInfo() {
        name = binding.nameET.getText().toString().trim();
        department = binding.departmentSn.getSelectedItem().toString().trim();

        if(!TextUtils.isEmpty(name)){
            String id = databaseStudent.push().getKey();
            Student student = new Student(id, name, department);
            databaseStudent.child(id).setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Data Save Successfully", Toast.LENGTH_SHORT).show();
                        binding.nameET.setText("");
                    }else{
                        Toast.makeText(MainActivity.this, "Data not Saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Name field is empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewData(View view) {
        Intent intent = new Intent(MainActivity.this, StudentsListActivity.class);
        startActivity(intent);
    }
}
