package com.jungbo.j4android.mynewist;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StyleUploadRequest extends StringRequest {
    final static private String URL="http://58.120.117.211/Upload.php";
    private Map<String,String> parameters;

    public StyleUploadRequest(String userID,String  styleName, String brandName, String totalPrice, String comment,Response.Listener<String> listener)
    {
        super(Method.POST,URL,listener,null);
        parameters=new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("styleName",styleName);
        parameters.put("brandName",brandName);
        parameters.put("totalPrice",totalPrice);   //문자열 형태로 바꿔줫네
        parameters.put("comment",comment);

    }

    @Override
    public Map<String,String> getParams(){
        return parameters;

    }
}
