package com.jungbo.j4android.mynewist;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    Button btn1;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText=(EditText) findViewById(R.id.idText);
        final EditText passwordText=(EditText) findViewById(R.id.passwordText);
        final Button loginButton=(Button)findViewById(R.id.loginButton);
        final TextView registerButton=(TextView) findViewById(R.id.registerButton);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent=new Intent(LoginActivity.this , RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userID=idText.getText().toString();
                final String userPassword=passwordText.getText().toString();

                Response.Listener<String> responserListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success)
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                                dialog=builder.setMessage("로그인에 성공했습니다.").setPositiveButton("확인",null).create();
                                dialog.show();

                                String userID=jsonResponse.getString("userID");
                                String userPassword=jsonResponse.getString("userPassword");
                                boolean checker=true;
                                Intent intent=new Intent(LoginActivity.this,testActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPassword",userPassword);
                                intent.putExtra("checker",checker);
                                LoginActivity.this.startActivity(intent);
                                finish();
                            }
                            else
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                                dialog=builder.setMessage("계정을 다시 확인하세요.").setNegativeButton("다시시도",null).create();
                                dialog.show();
                            }

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest=new LoginRequest(userID, userPassword, responserListener);
                RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if(dialog!=null)
        {
            dialog.dismiss();
            dialog=null;
        }
    }
}
