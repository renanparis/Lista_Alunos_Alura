package com.paris.listaalunos.asynctask;

import android.os.AsyncTask;

import com.paris.listaalunos.database.dao.TelephoneDAO;
import com.paris.listaalunos.model.Telephone;

public class SearchFirstTelephoneStudentTask extends AsyncTask<Void, Void, Telephone> {

    private final TelephoneDAO dao;
    private final int studentId;
    private final firstNumberFoundListener listener;

    public SearchFirstTelephoneStudentTask(TelephoneDAO dao, int studentId, firstNumberFoundListener listener) {
        this.dao = dao;
        this.studentId = studentId;
        this.listener = listener;
    }

    @Override
    protected Telephone doInBackground(Void... voids) {

        return dao.searchFirstTelephone(studentId);

    }

    @Override
    protected void onPostExecute(Telephone firstTelephone) {
        super.onPostExecute(firstTelephone);
        listener.whenFound(firstTelephone);
    }

    public interface firstNumberFoundListener {
        void whenFound(Telephone telephoneFound);
    }
}
