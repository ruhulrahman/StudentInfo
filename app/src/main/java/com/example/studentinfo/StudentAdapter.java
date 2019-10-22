package com.example.studentinfo;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinfo.databinding.ItemStudentsBinding;

import java.util.ArrayList;
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
        ItemStudentsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_students,parent,false);
        return new ViewHolder(binding);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_students, parent, false);
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentArrayList.get(position);
        holder.binding.nameTV.setText("Name: "+student.getName());
        holder.binding.departmentTV.setText("Department: "+student.getDepartment());
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
