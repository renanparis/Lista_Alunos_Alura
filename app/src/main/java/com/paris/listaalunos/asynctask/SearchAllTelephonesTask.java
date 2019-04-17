package com.paris.listaalunos.asynctask;

import android.os.AsyncTask;

import com.paris.listaalunos.database.dao.TelephoneDAO;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.model.Telephone;

import java.util.List;

public class SearchAllTelephonesTask extends AsyncTask<Void, Void, List<Telephone>> {


    private final TelephoneDAO telephoneDAO;
    private final Student student;
    private final whenTelephoneFoundListener listener;

    public SearchAllTelephonesTask(TelephoneDAO telephoneDAO, Student student, whenTelephoneFoundListener listener) {
        this.telephoneDAO = telephoneDAO;
        this.student = student;
        this.listener = listener;
    }

    @Override
    protected List<Telephone> doInBackground(Void... voids) {

        return telephoneDAO.searchAllPhones(student.getId());

    }

    @Override
    protected void onPostExecute(List<Telephone> telephones) {
        super.onPostExecute(telephones);

        listener.whenFound(telephones);
    }


    public interface whenTelephoneFoundListener{
        void whenFound(List<Telephone> telephones);
    }
}
