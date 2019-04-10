package com.paris.listaalunos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.paris.listaalunos.R;
import com.paris.listaalunos.database.ListStudentDataBase;
import com.paris.listaalunos.database.dao.TelephoneDAO;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.model.Telephone;

import java.util.ArrayList;
import java.util.List;

public class ListStudentAdapter extends BaseAdapter {

    private final List<Student> students = new ArrayList<>();
    private final Context context;
    private final TelephoneDAO dao;

    public ListStudentAdapter(Context context) {
        this.context = context;
        dao =  ListStudentDataBase.getInstance(context).getTelephoneDAO();

    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View inflate = createView(viewGroup);
        Student studentReturn = students.get(position);
        connects(inflate, studentReturn);
        return inflate;

    }

    private void connects(View inflate, Student student) {
        TextView name = inflate.findViewById(R.id.item_student_name);
        name.setText(student.getName());
        TextView phone = inflate.findViewById(R.id.item_student_phone);
        Telephone firstTelephone = dao.searchFirstTelephone(student.getId());
        if (firstTelephone != null){
            phone.setText(firstTelephone.getNumber());

        }
    }

    private View createView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_student, viewGroup, false);
    }

    public void updates(List<Student> students){
        this.students.clear();
        this.students.addAll(students);

        notifiesAdapter();
    }


    public void remove(Student student) {

        students.remove(student);
        Toast.makeText(context, "Aluno removido com sucesso!", Toast.LENGTH_SHORT).show();
        notifiesAdapter();
    }

    private void notifiesAdapter() {
        this.notifyDataSetChanged();
    }
}

