package com.paris.listaalunos.asynctask;

import android.os.AsyncTask;

import com.paris.listaalunos.model.Telephone;

public abstract class BaseStudentTask extends AsyncTask<Void, Void, Void> {

    private final EndsListener listener;

     BaseStudentTask(EndsListener listener) {
        this.listener = listener;
    }

     void connectsTelephoneStudent(int studentId, Telephone... telephones) {
        for (Telephone telephone :
                telephones) {
            telephone.setStudentId(studentId);

        }

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.whenItEnds();

    }

    public interface EndsListener {
        void whenItEnds();
    }
}
