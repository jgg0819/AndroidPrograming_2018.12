package com.jungbo.j4android.mynewist;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL="http://58.120.117.211/Register.php";
    private Map<String,String> parameters;

    public RegisterRequest(String userID,String  userPassword, String userName, int userAge, String userMail,String userFood, Response.Listener<String> listener)
    {
        super(Method.POST,URL,listener,null);  //POST방식으로 URL을 숨겨서 보내줘라
        parameters=new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userName",userName);
        parameters.put("userAge",userAge+"");   //문자열 형태로 바꿔줫네
        parameters.put("userMail",userMail);
        parameters.put("userFood",userFood);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;

    }






}
