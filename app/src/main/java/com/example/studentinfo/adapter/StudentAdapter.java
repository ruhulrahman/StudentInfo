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
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_students, parent, false);
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Student student = studentArrayList.get(position);
        holder.binding.nameTV.setText("Name: "+student.getName());
        holder.binding.departmentTV.setText("Department: "+student.getDepartment());



        holder.binding.getRoot().setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, final View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add("Update").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        Intent intent = new Intent(context,MainActivity.class);
//                        intent.putExtra("id",currentUser.getId());
//                        intent.putExtra("name",currentUser.getName());
//                        intent.putExtra("age",currentUser.getAge());
//                        intent.putExtra("address",currentUser.getAddress());
//                        intent.putExtra("phone",currentUser.getPhone());
//                        context.startActivity(intent);
                        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                contextMenu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
//                        dataBaseHelper.deleteData(currentUser.getId());
//                        usersList.remove(position);
//                        Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show();
//                        notifyDataSetChanged();
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        });


//        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, AddSemesterActivity.class);
//                intent.putExtra("StudentName", student.getName());
//                intent.putExtra("StudentId", student.getId());
//                context.startActivity(intent);
//            }
//        });



//        holder.binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                DatabaseReference databaseStudent = FirebaseDatabase.getInstance().getReference("student").child(student.getId());
//                databaseStudent.removeValue();
//                notifyDataSetChanged();
//                Toast.makeText(context, "Data Deleted of "+student.getName(), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
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
