package com.paris.listaalunos.asynctask;

import android.os.AsyncTask;

import com.paris.listaalunos.database.dao.StudentDao;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.ui.adapter.ListStudentAdapter;

import java.util.List;

public class SearchStudentTask extends AsyncTask<Void, Void, List<Student>> {


    private final ListStudentAdapter adapter;
    private final StudentDao dao;

    public SearchStudentTask(ListStudentAdapter adapter, StudentDao dao) {
        this.adapter = adapter;
        this.dao = dao;
    }

    @Override
    protected List<Student> doInBackground(Void[] objects) {

        return dao.allStudents();
    }

    @Override
    protected void onPostExecute(List<Student> allStudents) {
        adapter.updates(allStudents);
    }
}
