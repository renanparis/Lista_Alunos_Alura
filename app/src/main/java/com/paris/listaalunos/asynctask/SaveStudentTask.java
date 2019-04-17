package com.paris.listaalunos.asynctask;

import android.os.AsyncTask;

import com.paris.listaalunos.database.dao.StudentDao;
import com.paris.listaalunos.database.dao.TelephoneDAO;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.model.Telephone;

public class SaveStudentTask extends AsyncTask<Void, Void, Void> {


    private final StudentDao studentDao;
    private final Student student;
    private final TelephoneDAO telephoneDAO;
    private final Telephone telephoneFixed;
    private final Telephone cellphone;
    private final whenStudentSavedListener listener;

    public SaveStudentTask(StudentDao studentDao,
                           Student student,
                           TelephoneDAO telephoneDAO,
                           Telephone telephoneFixed, Telephone cellphone, whenStudentSavedListener listener) {
        this.studentDao = studentDao;
        this.student = student;
        this.telephoneDAO = telephoneDAO;
        this.telephoneFixed = telephoneFixed;
        this.cellphone = cellphone;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        int studentId = studentDao.saveStudent(student).intValue();
        connectsTelephoneStudent(studentId, telephoneFixed, cellphone);
        telephoneDAO.saveTelephone(telephoneFixed, cellphone);

        return null;
    }

    private void connectsTelephoneStudent(int studentId, Telephone... telephones) {
        for (Telephone telephone :
                telephones) {
            telephone.setStudentId(studentId);

        }

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.whenSaved();
    }

    public interface whenStudentSavedListener{
        void whenSaved();
    }
}
