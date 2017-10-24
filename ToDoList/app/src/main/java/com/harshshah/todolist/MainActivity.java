package com.harshshah.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    /**
     * Default, front page of the app, where all tasks are listed, ListView is the format.
     */
    // private taskItems;
    private static final String TAG = "MainActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); //activity_main
        mListView = (ListView) findViewById(R.id.AllTaskView);
        mDatabaseHelper = new DatabaseHelper(this);


        //populateListView();


        Button bClicked = (Button) findViewById(R.id.Add);

        bClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent AddTpage = new Intent(MainActivity.this, AddTask.class);
                startActivity(AddTpage);


            }


        });


    }


    /**
     * display the final task list with populateListView method.
     */
    private void populateListView() {

        Cursor taskData = mDatabaseHelper.getData();
        ArrayList<String> taskList = new ArrayList<>();
        while (taskData.moveToNext()) {
            taskList.add(taskData.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        mListView.setAdapter(adapter);

    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}







