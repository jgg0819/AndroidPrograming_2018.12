package com.jungbo.j4android.mynewist;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {
    final static private String URL="http://192.168.121.137/cookReceip_server/validate.php";
    private Map<String,String> map;

    public ValidateRequest(String userID, Response.Listener<String> listener)
    {
        super(Method.POST,URL,listener,null);
        map=new HashMap<>();
        map.put("userID",userID);

    }
    @Override

    public Map<String,String> getParams(){
        return map;
    }

}

