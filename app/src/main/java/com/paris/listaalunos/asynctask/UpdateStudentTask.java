package com.paris.listaalunos.asynctask;

import android.os.AsyncTask;

import com.paris.listaalunos.database.dao.StudentDao;
import com.paris.listaalunos.database.dao.TelephoneDAO;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.model.Telephone;
import com.paris.listaalunos.model.TelephoneType;

import java.util.List;

public class UpdateStudentTask extends AsyncTask<Void, Void, Void> {


    private final StudentDao studentDao;
    private final Student student;
    private final Telephone telephoneFixed;
    private final Telephone cellphone;
    private final TelephoneDAO telephoneDAO;
    private final List<Telephone> studentPhones;
    private final whenUpdateListener listener;

    public UpdateStudentTask(StudentDao studentDao,
                             Student student,
                             Telephone telephoneFixed,
                             Telephone cellphone,
                             TelephoneDAO telephoneDAO,
                             List<Telephone> studentPhones,
                             whenUpdateListener listener) {
        this.studentDao = studentDao;
        this.student = student;
        this.telephoneFixed = telephoneFixed;
        this.cellphone = cellphone;
        this.telephoneDAO = telephoneDAO;
        this.studentPhones = studentPhones;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        studentDao.editStudent(student);
        connectsTelephoneStudent(student.getId(), telephoneFixed, cellphone);

        updatesIdStudent(telephoneFixed, cellphone);
        telephoneDAO.update(telephoneFixed, cellphone);

        return null;
    }

    private void connectsTelephoneStudent(int studentId, Telephone... telephones) {
        for (Telephone telephone :
                telephones) {
            telephone.setStudentId(studentId);

        }

    }

    private void updatesIdStudent(Telephone telephoneFixed, Telephone cellphone) {
        for (Telephone telephone :
                studentPhones) {
            if (telephone.getType() == TelephoneType.TELEPHONE) {
                telephoneFixed.setId(telephone.getId());
            } else {
                cellphone.setId(telephone.getId());
            }

        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.whenUpdate();
    }

    public interface  whenUpdateListener{
        void whenUpdate();
    }
}
