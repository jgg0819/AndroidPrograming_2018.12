package com.example.bantesting1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    Button btnAdd;
    Button btnDel;
    ArrayAdapter<String> adapter;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            listView = (ListView)findViewById(R.id.listView1);
            btnAdd = (Button)findViewById(R.id.btnAdd);
            btnDel = (Button)findViewById(R.id.btnDel);

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            listView.setAdapter((ListAdapter) adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = list.get(position);
                    Toast.makeText(MainActivity.this, "선택항목 : " + item, Toast.LENGTH_SHORT).show();

                }
            });

            final EditText edt1 = (EditText)findViewById(R.id.edt1);
            final EditText edt2 = (EditText)findViewById(R.id.edt2);

            btnAdd.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    String str = edt1.getText().toString();
                    list.add(str);
                    adapter.notifyDataSetChanged();
                    edt1.setText("");
                }
            });

            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String pos = edt2.getText().toString();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setMessage(pos + "를 밴하시겠습니까?").setCancelable(false).setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for(int a = 0; a<10; a++) {
                                        for (int i = 0; i < adapter.getCount(); i++) {
                                            if (list.get(i).contains(pos)) {
                                                list.remove(list.get(i));
                                                adapter.notifyDataSetChanged();
                                                listView.clearChoices();
                                            }
                                        }
                                    }
                                    Toast.makeText(getApplicationContext(), pos + "이(가) 밴되었습니다.", Toast.LENGTH_LONG).show();
                                }

                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 'No'
                            return;
                        }
                    });
                    AlertDialog alert = dialog.create();
                    alert.show();
                }
            });

        }
}
