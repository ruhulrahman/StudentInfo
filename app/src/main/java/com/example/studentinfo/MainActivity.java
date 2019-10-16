package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.studentinfo.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String name, department;
    // Write a message to the database
    DatabaseReference databaseStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main);


        databaseStudent = FirebaseDatabase.getInstance().getReference("student");
        //Please see here. where is the problem in this line
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
            databaseStudent.child(id).setValue(student);
            Toast.makeText(this, "Data Save Successfully", Toast.LENGTH_SHORT).show();
            binding.nameET.setText("");
        }else{
            Toast.makeText(this, "Name field is empty", Toast.LENGTH_SHORT).show();
        }
    }
}
//apps crahsed