package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.studentinfo.databinding.ActivityAddSemesterBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AddSemesterActivity extends AppCompatActivity {
    private ActivityAddSemesterBinding binding;
    private String studentName, studentId;
    private String cgpa, semesterName;

    private List<Semester> semesterList;
    private DatabaseReference databaseSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_semester);

        studentName = getIntent().getStringExtra("StudentName");
        studentId = getIntent().getStringExtra("StudentId");

        databaseSemester = FirebaseDatabase.getInstance().getReference("semester").child(studentId);

        binding.stnNameTV.setText(studentName);

        binding.addSemesterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSemester();
            }
        });
    }

    private void saveSemester() {
        cgpa = binding.cgpaET.getText().toString().trim();
        semesterName = binding.semesterSn.getSelectedItem().toString().trim();

        if(!TextUtils.isEmpty(cgpa)){
            String id = databaseSemester.push().getKey();
            Semester semester = new Semester(id, semesterName, cgpa);
            databaseSemester.child(id).setValue(semester);
            Toast.makeText(this, "Data Save Successfully", Toast.LENGTH_SHORT).show();
            binding.cgpaET.setText("");
        }else{
            Toast.makeText(this, "Cgpa field is empty", Toast.LENGTH_SHORT).show();
        }
    }
}
