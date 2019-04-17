package com.paris.listaalunos.asynctask;

import android.os.AsyncTask;

import com.paris.listaalunos.database.dao.StudentDao;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.ui.adapter.ListStudentAdapter;

public class removeStudentTask extends AsyncTask<Void, Void, Void> {

    private final StudentDao dao;
    private final ListStudentAdapter adapter;
    private final Student student;

    public removeStudentTask(StudentDao dao, ListStudentAdapter adapter, Student student) {
        this.dao = dao;
        this.adapter = adapter;
        this.student = student;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        dao.remove(student);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remove(student);
    }
}
