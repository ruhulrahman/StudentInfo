package com.example.studentinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.studentinfo.databinding.ActivityAddSemesterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddSemesterActivity extends AppCompatActivity {
    private ActivityAddSemesterBinding binding;
    private String studentName, studentId;
    private String cgpa, semesterName;


    private List<Semester> semesterList;
    private SemesterAdapter semesterAdapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_semester);

        init();

        studentName = getIntent().getStringExtra("StudentName");
        studentId = getIntent().getStringExtra("StudentId");

        binding.stnNameTV.setText(studentName);

        binding.addSemesterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSemester(studentId);
            }
        });

        getSemesters(studentId);
    }

    private void init() {
        semesterList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        semesterAdapter = new SemesterAdapter(semesterList, this);
        binding.semesterRV.setLayoutManager(new LinearLayoutManager(this));
        binding.semesterRV.setAdapter(semesterAdapter);
    }

    private void getSemesters(String studentId) {
        DatabaseReference databaseSemester = databaseReference.child("student").child(studentId).child("semester");
        databaseSemester.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    semesterList.clear();
                    for(DataSnapshot data:dataSnapshot.getChildren()){
                        Semester semester = data.getValue(Semester.class);
                        semesterList.add(semester);
                        semesterAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveSemester(String studentId) {
        DatabaseReference databaseSemester = databaseReference.child("student").child(studentId).child("semester");
        cgpa = binding.cgpaET.getText().toString().trim();
        semesterName = binding.semesterSn.getSelectedItem().toString().trim();

        if(!TextUtils.isEmpty(cgpa)){
            String semesterId = databaseSemester.push().getKey();
            Semester semester = new Semester(semesterId, semesterName, cgpa);
            databaseSemester.child(semesterId).setValue(semester).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AddSemesterActivity.this, "Data Save Successfully", Toast.LENGTH_SHORT).show();
                        binding.cgpaET.setText("");
                    }else{
                        Toast.makeText(AddSemesterActivity.this, "Data not saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Cgpa field is empty", Toast.LENGTH_SHORT).show();
        }
    }
}
