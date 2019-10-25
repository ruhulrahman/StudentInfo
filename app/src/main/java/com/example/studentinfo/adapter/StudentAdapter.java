package com.example.studentinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinfo.activity.AddSemesterActivity;
import com.example.studentinfo.R;
import com.example.studentinfo.databinding.ItemStudentsBinding;
import com.example.studentinfo.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> studentArrayList;
    private Context context;

    public StudentAdapter(List<Student> studentArrayList, Context context) {
        this.studentArrayList = studentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStudentsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_students,parent,false);
        return new ViewHolder(binding);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_students, parent, false);
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Student student = studentArrayList.get(position);
        holder.binding.nameTV.setText("Name: "+student.getName());
        holder.binding.departmentTV.setText("Department: "+student.getDepartment());
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddSemesterActivity.class);
                intent.putExtra("StudentName", student.getName());
                intent.putExtra("StudentId", student.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       // private TextView name, department;
        private ItemStudentsBinding binding;
        public ViewHolder(@NonNull ItemStudentsBinding binding) {
            super(binding.getRoot());
//            name = itemView.findViewById(R.id.nameTV);
//            department = itemView.findViewById(R.id.departmentTV);
            this.binding = binding;
        }
    }
}
