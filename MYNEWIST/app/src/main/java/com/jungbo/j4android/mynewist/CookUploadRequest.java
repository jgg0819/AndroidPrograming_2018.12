package com.jungbo.j4android.mynewist;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CookUploadRequest extends StringRequest {
    final static private String URL="http://192.168.121.142/cookReceip_server/Upload.php";
    private Map<String,String> map;

    public CookUploadRequest(String userID, String  title, String price, String comment,String date, Response.Listener<String> listener)
    {
        super(Method.POST,URL,listener,null);
        map=new HashMap<>();
        map.put("userID",userID);
        map.put("title",title);
        map.put("price",price);
        map.put("comment",comment);
        map.put("date",date);


    }

    @Override
    public Map<String,String> getParams() throws AuthFailureError {
        return map;

    }
}
