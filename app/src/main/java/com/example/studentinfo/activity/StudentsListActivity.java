package com.example.studentinfo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.studentinfo.R;
import com.example.studentinfo.adapter.StudentAdapter;
import com.example.studentinfo.databinding.ActivityStudentsListBinding;
import com.example.studentinfo.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentsListActivity extends AppCompatActivity {
    private ActivityStudentsListBinding binding;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;
    private RecyclerView studentRV;
    DatabaseReference databaseStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_students_list);

        databaseStudent = FirebaseDatabase.getInstance().getReference("student");

        studentRV = findViewById(R.id.studentRV);
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList, this);

        getStudents();
        configRecyclerView();
    }

    private void getStudents() {
        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    studentList.clear();
                    for (DataSnapshot data:dataSnapshot.getChildren()){
                        Student student = data.getValue(Student.class);
                        studentList.add(student);
                        studentAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(StudentsListActivity.this, "Data Load Failed"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configRecyclerView() {
        studentRV.setLayoutManager(new LinearLayoutManager(this));
        studentRV.setAdapter(studentAdapter);
    }
}
