package com.paris.listaalunos.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import com.paris.listaalunos.database.dao.TelephoneDAO;
import com.paris.listaalunos.model.Telephone;

public class searchFirstTelephoneStudentTask extends AsyncTask<Void, Void, Telephone> {

    private final TelephoneDAO dao;
    private final TextView fieldPhone;
    private final int studentId;

    public searchFirstTelephoneStudentTask(TelephoneDAO dao, TextView fieldPhone, int studentId) {
        this.dao = dao;
        this.fieldPhone = fieldPhone;
        this.studentId = studentId;
    }

    @Override
    protected Telephone doInBackground(Void... voids) {



        return dao.searchFirstTelephone(studentId);

    }

    @Override
    protected void onPostExecute(Telephone firstTelephone) {
        super.onPostExecute(firstTelephone);
        fieldPhone.setText(firstTelephone.getNumber());
    }
}
