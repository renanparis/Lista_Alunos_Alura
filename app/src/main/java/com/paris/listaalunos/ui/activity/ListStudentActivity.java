package com.paris.listaalunos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.paris.listaalunos.R;
import com.paris.listaalunos.model.Student;
import com.paris.listaalunos.ui.ListStudentsView;

import static com.paris.listaalunos.ui.activity.ConstantActivity.KEY_STUDENT;

public class ListStudentActivity extends AppCompatActivity {

    private static final String TITLE_APPBAR = "Lista de Alunos";
   private ListStudentsView listStudentsView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITLE_APPBAR);
        setContentView(R.layout.activity_list_student);
        listStudentsView = new ListStudentsView(this);
        configListStudent();
        configFabNewStudent();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_students_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_list_students_menu_remover) {

            listStudentsView.confirmsStudentRemoval(item);
        }

        return super.onContextItemSelected(item);

    }

    private void configFabNewStudent() {
        FloatingActionButton newStudent = findViewById(R.id.activity_list_student_add_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFormAddStudent();
            }
        });
    }

    private void startFormAddStudent() {
        startActivity(new Intent(this, FormActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
       listStudentsView.updateListStudents();
    }

    private void configListStudent() {
        ListView listStudents = findViewById(R.id.activity_list_students);
        listStudentsView.configAdapter(listStudents);
        configListenerItemClicked(listStudents);
        registerForContextMenu(listStudents);
    }

    private void configListenerItemClicked(ListView listStudents) {
        listStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Student studentClicked = (Student) adapterView.getItemAtPosition(position);
                startFormEdit(studentClicked);

            }
        });
    }

    private void startFormEdit(Student student) {
        Intent intent = new Intent(ListStudentActivity.this, FormActivity.class);
        intent.putExtra(KEY_STUDENT, student);
        startActivity(intent);
    }
}
