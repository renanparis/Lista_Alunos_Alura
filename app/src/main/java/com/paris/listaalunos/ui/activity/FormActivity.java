package com.paris.listaalunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.paris.listaalunos.R;
import com.paris.listaalunos.database.ListStudentDataBase;
import com.paris.listaalunos.database.dao.StudentDao;
import com.paris.listaalunos.database.dao.TelephoneDAO;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.model.Telephone;
import com.paris.listaalunos.model.TelephoneType;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class FormActivity extends AppCompatActivity implements ConstantActivity {

    private static final String TITLE_APPBAR_NEW_STUDENT = "Novo Aluno";
    private static final String TITLE_APPBAR_EDIT_STUDENT = "Edita Aluno";
    private EditText fieldName;
    private EditText fieldTelephone;
    private EditText fieldCellPhone;
    private EditText fieldEmail;
    private StudentDao studentDao;
    private Student student;
    private TelephoneDAO telephoneDAO;
    private List<Telephone> studentPhones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        startField();
        loadStudent();
        ListStudentDataBase db = ListStudentDataBase.getInstance(this);
        studentDao = db.getRoomStudentDao();
        telephoneDAO = db.getTelephoneDAO();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_form_menu_salvar) {

            endForm();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadStudent() {
        Intent date = getIntent();
        if (date.hasExtra(KEY_STUDENT)) {
            setTitle(TITLE_APPBAR_EDIT_STUDENT);
            student = (Student) date.getSerializableExtra(KEY_STUDENT);
            fillField();
        } else {
            setTitle(TITLE_APPBAR_NEW_STUDENT);
            student = new Student();
        }
    }

    private void fillField() {
        fieldName.setText(student.getName());
        fieldEmail.setText(student.getEmail());
        studentPhones = telephoneDAO.searchAllPhones(student.getId());
        for (Telephone telephone :
                studentPhones) {
            if (telephone.getType() == TelephoneType.TELEPHONE) {
                fieldTelephone.setText(telephone.getNumber());
            } else {
                fieldCellPhone.setText(telephone.getNumber());
            }

        }
    }

    private void fillForm() {

        String name = fieldName.getText().toString();
        String telephone = fieldTelephone.getText().toString();
        String cellPhone = fieldCellPhone.getText().toString();
        String email = fieldEmail.getText().toString();

        student.setName(name);
//        student.setTelephone(telephone);
//        student.setCellPhone(cellPhone);
        student.setEmail(email);

    }

    private void endForm() {
        fillForm();
        if (student.validId()) {
            studentDao.editStudent(student);
            String numberFixed = fieldTelephone.getText().toString();
            Telephone telephoneFixed = new Telephone(numberFixed, TelephoneType.TELEPHONE, student.getId());
            String numberCellphone = fieldCellPhone.getText().toString();
            Telephone cellphone = new Telephone(numberCellphone, TelephoneType.CELLPHONE, student.getId());
            for (Telephone telephone :
                    studentPhones) {
                if (telephone.getType() == TelephoneType.TELEPHONE) {
                    telephoneFixed.setId(telephone.getId());
                } else {
                    cellphone.setId(telephone.getId());
                }

            }
            telephoneDAO.update(telephoneFixed, cellphone);
            Toast.makeText(this, "Aluno Alterado com sucesso!", Toast.LENGTH_SHORT).show();

        } else {
            int studentId = studentDao.saveStudent(student).intValue();
            String numberFixed = fieldTelephone.getText().toString();
            Telephone telephoneFixed = new Telephone(numberFixed, TelephoneType.TELEPHONE, studentId);
            String numberCellphone = fieldCellPhone.getText().toString();
            Telephone cellphone = new Telephone(numberCellphone, TelephoneType.CELLPHONE, studentId);
            telephoneDAO.saveTelephone(telephoneFixed, cellphone);


            Toast.makeText(this, "Aluno salvo com sucesso", Toast.LENGTH_SHORT).show();
        }
        finish();
    }


    private void startField() {
        fieldName = findViewById(R.id.activiti_form_name);
        fieldTelephone = findViewById(R.id.activiti_form_telephone);
        fieldCellPhone = findViewById(R.id.activiti_form_cell_phone);
        fieldEmail = findViewById(R.id.activiti_form_email_adress);
    }

}

