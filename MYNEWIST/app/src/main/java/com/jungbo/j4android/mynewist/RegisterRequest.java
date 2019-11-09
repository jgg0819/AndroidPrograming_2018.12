package com.jungbo.j4android.mynewist;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL="http://192.168.121.137/cookReceip_server/Register.php";
    private Map<String,String> map;

    public RegisterRequest(String userID,String  userPassword, String userMail, String userName,String userBirth, Response.Listener<String> listener)
    {
        super(Method.POST,URL,listener,null);  //POST방식으로 URL을 숨겨서 보내줘라
        map=new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPassword);
        map.put("userMail",userMail);
        map.put("userName",userName);
        map.put("userBirth",userBirth);
    }

    @Override
    public Map<String,String>  getParams() throws AuthFailureError{
        return map;

    }






}
