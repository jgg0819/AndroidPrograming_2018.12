package com.jungbo.j4android.mynewist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StyleUpload extends AppCompatActivity {
    private static final int CAMERA_CODE = 0;
    private static final int GALLERY_CODE = 10;
    private String mCurrentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_upload);
        final EditText edt1,edt2,edt3,edt4;
        Button uploadBtn;
        edt1=(EditText) findViewById(R.id.edtStyleName);
        edt2=(EditText) findViewById(R.id.edtBrandName);
        edt3=(EditText) findViewById(R.id.edtPrice);
        edt4=(EditText) findViewById(R.id.edtComment);
        uploadBtn=(Button)findViewById(R.id.upload_button);

        Button saveBtn=(Button) findViewById(R.id.save_button);
        //디비 업로드

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Pintent=getIntent();
                String styleName= edt1.getText().toString();
                String brandName= edt2.getText().toString();
                String totalPrice= edt3.getText().toString();
                String comment= edt4.getText().toString();
               final String userID= Pintent.getStringExtra("userID");


                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success)
                            {
                                boolean checker=true;
                                //String userID1=jsonResponse.getString("userID");
                                AlertDialog.Builder builder=new AlertDialog.Builder(StyleUpload.this);
                                builder.setMessage("업로드 성공!.").setPositiveButton("확인",null).create().show();
                                new BackgroundTask().execute();
                                Intent intent=new Intent(StyleUpload.this,MainActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("checker",checker);
                                StyleUpload.this.startActivity(intent);
                                //finish();
                            }
                            else
                            {
                                AlertDialog.Builder builder=new AlertDialog.Builder(StyleUpload.this);
                                builder.setMessage("업로드 실패!.").setNegativeButton("다시시도",null).create().show();
                                Intent intent=new Intent(StyleUpload.this, MainActivity.class);
                                StyleUpload.this.startActivity(intent);
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                };

                StyleUploadRequest styleUploadRequest=new StyleUploadRequest(userID,styleName,brandName,totalPrice,comment,responseListener);
                RequestQueue queue=Volley.newRequestQueue(StyleUpload.this);
                queue.add(styleUploadRequest);
            }
        });

        //사진
        requirePermission();

        Button gallery=(Button) findViewById(R.id.gallery_button);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUpPicture();

            }
        });

        Button save_Button=(Button) findViewById(R.id.save_button);
        save_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryAddPic();
            }
        });

        Button button=(Button) findViewById(R.id.camera_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean camera=ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

                boolean write=ContextCompat.checkSelfPermission(v.getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED;

                if(camera && write)
                {
                    //사직찍은 인텐트 코드 넣기
                    takePicture();
                }else
                {
                    Toast.makeText(StyleUpload.this,"카메라 권한 및 쓰기 권한을 주지 않았습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute()
        {
            target="http://58.120.117.211/List.php";
        }

        @Override
        protected String doInBackground(Void... voids)
        {
            try
            {
                URL url=new URL(target);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder=new StringBuilder();
                while((temp=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        public void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);
        }

        public void onPostExecute(String result)
        {
            Intent intent=new Intent(StyleUpload.this,MainActivity.class);
            intent.putExtra("userData",result);
            StyleUpload.this.startActivity(intent);
        }
    }


    void requirePermission(){
        String [] permissions=new String[] {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ArrayList<String> listPermissionNeeded=new ArrayList<>();

        for(String permission: permissions){
            if(ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED){
                //권한이 허가가 안됬을 경우 요청할 권한을 모집하는 부분
                listPermissionNeeded.add(permission);
            }
        }
        if(!listPermissionNeeded.isEmpty())
        {
            //권한 요청 하는 부분
            ActivityCompat.requestPermissions(this,listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]),1);
        }
    }

    void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            File phtoFile = createImageFile();
            Uri photoUri = FileProvider.getUriForFile(this, "com.jungbo.j4android.mynewist.fileprovider", phtoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void pickUpPicture(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setType("image/*");
        startActivityForResult(intent,10);

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CAMERA_CODE)
        {
            ImageView imageView=(ImageView) findViewById(R.id.ImageView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        }

        if(requestCode==GALLERY_CODE && resultCode==RESULT_OK)
        {
            Uri uri=data.getData();
            ImageView imageView=(ImageView)findViewById(R.id.ImageView);
            imageView.setImageURI(uri);
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
        Toast.makeText(this, "사진이 저장되었습니다.", Toast.LENGTH_SHORT).show();
    }


}
