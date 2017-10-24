package com.harshshah.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class AddTask extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();
    TextView display;
    DatabaseHelper mDatabaseHelper;

    /**
     * Dialogue for the date picker in the addTask screen.
     */

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            display.setText(" " + (monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mDatabaseHelper = new DatabaseHelper(this);


        final Button addTask = (Button) findViewById(R.id.addButtonClick);//default
        final Button cancelTask = (Button) findViewById(R.id.cancelButton);
        final EditText tText = (EditText) findViewById(R.id.DField);//new
        final Button dateButton = (Button) findViewById(R.id.datepickerB);
        display = (TextView) findViewById(R.id.currentDateLabel);
        final TextView choosenDate = (TextView) findViewById(R.id.currentDateLabel);

        /*
        on clickListeners for both buttons on the add task screen. one to cancel the task and go back to the main screen.
        Another button is "ADD" which adds the task to the mains screen.
         */
        addTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String newEntry = tText.getText().toString();
                String newDateEntry = dateButton.getText().toString();
                Intent intent = new Intent(AddTask.this, MainActivity.class);


                addData(newEntry); //video mark 4:50
                addData(newDateEntry);

                startActivity(intent);


            }
        });


        cancelTask.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {

                                              Intent cancelTask = new Intent(AddTask.this, MainActivity.class);
                                              startActivity(cancelTask);

                                          }


                                      }

        );


    }


    public void addData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Task Added");
        } else {
            toastMessage("Error, something went wrong");
        }


    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


}







