package com.paris.listaalunos.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.paris.listaalunos.database.ListStudentDataBase;
import com.paris.listaalunos.database.dao.StudentDao;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.ui.adapter.ListStudentAdapter;

import androidx.appcompat.app.AlertDialog;

public class ListStudentsView {

    private Context context;
    private StudentDao dao;
    private ListStudentAdapter adapter;

    public ListStudentsView(Context context) {
        this.context = context;
        this.adapter = new ListStudentAdapter(this.context);
        dao = ListStudentDataBase.getInstance(context).getRoomStudentDao();
    }

    public void confirmsStudentRemoval(final MenuItem item) {
        new AlertDialog.Builder(context).setTitle("Removendo o Aluno")
                .setMessage("Você tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Student studentClicked = adapter.getItem(menuInfo.position);
                        removeStudent(studentClicked);
                    }
                })
                .setNegativeButton("Não", null).show();
    }

    public void updateListStudents() {
        adapter.updates(dao.allStudents());
    }

    private void removeStudent(Student student) {
        dao.remove(student);
        adapter.remove(student);
    }

    public void configAdapter(ListView listStudents) {
        listStudents.setAdapter(adapter);
    }
}
