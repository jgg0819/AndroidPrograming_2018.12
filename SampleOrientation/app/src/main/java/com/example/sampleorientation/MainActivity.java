package com.example.sampleorientation;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    String name;
    EditText editText;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name",name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast("onCreate호출됨");

        editText=findViewById(R.id.editText);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                name=editText.getText().toString();
                showToast("입력된 값을 변수에 저장했습니다.:"+name);
            }
        });

        if(savedInstanceState!=null){
            name=savedInstanceState.getString("name");
            Toast.makeText(getApplicationContext(), "값을 복원했습니다 : " + name, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
       // showToast("onstart 호출됨");
    }

    @Override
    protected void onStop() {
        showToast("onStop 호출됨");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        showToast("onDestroy 호출됨");
        super.onDestroy();
    }

    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }
}
