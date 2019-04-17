package com.paris.listaalunos.asynctask;

import com.paris.listaalunos.database.dao.StudentDao;
import com.paris.listaalunos.database.dao.TelephoneDAO;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.model.Telephone;
import com.paris.listaalunos.model.TelephoneType;

import java.util.List;

public class UpdateStudentTask extends BaseStudentTask {


    private final StudentDao studentDao;
    private final Student student;
    private final Telephone telephoneFixed;
    private final Telephone cellphone;
    private final TelephoneDAO telephoneDAO;
    private final List<Telephone> studentPhones;

    public UpdateStudentTask(StudentDao studentDao,
                             Student student,
                             Telephone telephoneFixed,
                             Telephone cellphone,
                             TelephoneDAO telephoneDAO,
                             List<Telephone> studentPhones,
                             EndsListener listener) {
        super(listener);
        this.studentDao = studentDao;
        this.student = student;
        this.telephoneFixed = telephoneFixed;
        this.cellphone = cellphone;
        this.telephoneDAO = telephoneDAO;
        this.studentPhones = studentPhones;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        studentDao.editStudent(student);
        connectsTelephoneStudent(student.getId(), telephoneFixed, cellphone);

        updatesIdStudent(telephoneFixed, cellphone);
        telephoneDAO.update(telephoneFixed, cellphone);

        return null;
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

}
