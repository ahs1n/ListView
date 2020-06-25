package com.example.listview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview.databinding.ActivityMainBinding;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bi;

    //Variables
    EditText et;    //For EditText
    Button bt;      //For Button
    ListView lv;    //For ListView

    //Create ArrayList and its variable
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;   //ArrayAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);


        //Now attaching xml layout elements like button, editText and ListView to their respective varaible
        et = findViewById(R.id.editText);
        bt = findViewById(R.id.button_addData);
        lv = findViewById(R.id.listview_addData);


        //Call ArrayList class in Main class
        arrayList = new ArrayList<String>();
        //Call ArrayAdapter class in Main class
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                arrayList);
        //Now set the adapter for ListView
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                Toast.makeText(MainActivity.this, arrayList.get(position), Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        onBtnClick();
    }

    // Create new function for onBtnClick
    public void onBtnClick() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = et.getText().toString();
                arrayList.add(result);
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Record Saved", Toast.LENGTH_SHORT).show();
                reset();
            }
        });
    }

    public void reset() {
        bi.editText.setText(null);
    }
}
