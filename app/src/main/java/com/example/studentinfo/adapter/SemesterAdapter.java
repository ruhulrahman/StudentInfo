package com.example.studentinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinfo.R;
import com.example.studentinfo.databinding.ItemSemesterBinding;
import com.example.studentinfo.model.Semester;

import java.util.List;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.ViewHolder> {
    private List<Semester> semesterList;
    private Context context;

    public SemesterAdapter(List<Semester> semesterList, Context context) {
        this.semesterList = semesterList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSemesterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_semester, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Semester semester = semesterList.get(position);
        holder.binding.cgpaTV.setText("CGPA: "+String.valueOf(semester.getStudentCgpa()));
        holder.binding.semesterNameTV.setText("Semester Name: "+semester.getStudentSemester());
    }

    @Override
    public int getItemCount() {
        return semesterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSemesterBinding binding;
        public ViewHolder(@NonNull ItemSemesterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
