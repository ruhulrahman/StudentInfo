package com.example.studentinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentinfo.activity.AddSemesterActivity;
import com.example.studentinfo.R;
import com.example.studentinfo.activity.MainActivity;
import com.example.studentinfo.databinding.ItemStudentsBinding;
import com.example.studentinfo.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
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

        holder.binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DatabaseReference databaseStudent = FirebaseDatabase.getInstance().getReference("student").child(student.getId());
                databaseStudent.removeValue();
                notifyDataSetChanged();
                Toast.makeText(context, "Data Deleted of "+student.getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        holder.binding.studentCntxMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.binding.studentCntxMenu);
                popupMenu.inflate(R.menu.student_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.deleteId:
                                DatabaseReference databaseStudent = FirebaseDatabase.getInstance().getReference("student").child(student.getId());
                                databaseStudent.removeValue();
                                notifyDataSetChanged();
                                Toast.makeText(context, "Data Deleted of "+student.getName(), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.editId:
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.putExtra("StudentName", student.getName());
                                intent.putExtra("StudentId", student.getId());
                                context.startActivity(intent);
                                break;
                            case R.id.detailsId:
                                Intent intent2 = new Intent(context, AddSemesterActivity.class);
                                intent2.putExtra("StudentName", student.getName());
                                intent2.putExtra("StudentId", student.getId());
                                context.startActivity(intent2);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemStudentsBinding binding;
        public ViewHolder(@NonNull ItemStudentsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
