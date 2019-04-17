package com.paris.listaalunos.asynctask;

import com.paris.listaalunos.database.dao.StudentDao;
import com.paris.listaalunos.database.dao.TelephoneDAO;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.model.Telephone;

public class SaveStudentTask extends BaseStudentTask {


    private final StudentDao studentDao;
    private final Student student;
    private final TelephoneDAO telephoneDAO;
    private final Telephone telephoneFixed;
    private final Telephone cellphone;

    public SaveStudentTask(StudentDao studentDao,
                           Student student,
                           TelephoneDAO telephoneDAO,
                           Telephone telephoneFixed, Telephone cellphone, EndsListener listener) {
        super(listener);
        this.studentDao = studentDao;
        this.student = student;
        this.telephoneDAO = telephoneDAO;
        this.telephoneFixed = telephoneFixed;
        this.cellphone = cellphone;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        int studentId = studentDao.saveStudent(student).intValue();
        connectsTelephoneStudent(studentId, telephoneFixed, cellphone);
        telephoneDAO.saveTelephone(telephoneFixed, cellphone);

        return null;
    }

}
