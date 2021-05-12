package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DataUser dataUser;
    Button btn_add, btn_rm, btn_cl;
    EditText name_edt;
    ListView name_lv;
    ArrayList nameList;
    ArrayAdapter adapter;
    ArrayList idList;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idList = new ArrayList();
        dataUser = new DataUser(this, "userdb.sqlite", null, 1);
        nameList = new ArrayList();
        name_edt = findViewById(R.id.name_edt);
        btn_add = findViewById(R.id.btn_add);
        btn_rm = findViewById(R.id.btn_rm);
        btn_cl = findViewById(R.id.btn_cl);
        name_lv = findViewById(R.id.name_lv);

        dataUser.addUser(new User("Quang Duc "));
        getNameList();
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, nameList);
        name_lv.setAdapter(adapter);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.addUser(new User(name_edt.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
            }
        });
        btn_rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dataUser.removeUser((int)idList.get(index));
               getNameList();
               adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        name_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                index = i;
            }
        });
    }
        private ArrayList getNameList () {
            nameList.clear();
            idList.clear();
            for (Iterator iterator = dataUser.getALl().iterator(); iterator.hasNext(); ) {
                User user = (User) iterator.next();
                nameList.add(user.getName());
                idList.add(user.getId());


            }
            return nameList;

        }

    }

