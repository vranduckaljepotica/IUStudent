package ius.iustudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import ius.iustudent.models.Event;
import ius.iustudent.models.Exam;

public class ShowExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Exam exam = getIntent().getParcelableExtra("exam");
        setTitle(exam.getCourse() + " exam");
        TextView nameView = (TextView) findViewById(R.id.name_view);
        TextView dateView = (TextView) findViewById(R.id.date_view);
        TextView startView = (TextView) findViewById(R.id.start_view);
        nameView.setText(exam.getCourse());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMM.yyyy");
        dateView.setText(dateFormat.format(exam.getDate()));
        dateFormat = new SimpleDateFormat("hh:mm");
        startView.setText(dateFormat.format(exam.getDate()));
    }
}
