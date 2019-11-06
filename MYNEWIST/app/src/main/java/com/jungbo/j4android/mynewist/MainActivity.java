package com.jungbo.j4android.mynewist;

import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView view;
    private DataListAdapter adapter;
    private List<Data> dataList;
    private List<Data> saveList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent Lintent=getIntent();
        boolean checker=false;


        view=(RecyclerView)findViewById(R.id.main_recyclerview);
        //view.setLayoutManager(new LinearLayoutManager(this));
        //view.setAdapter(new CardViewTestAdapter());
        dataList=new ArrayList<Data>();
        saveList=new ArrayList<Data>();
        adapter=new DataListAdapter(getApplicationContext(),dataList,this,saveList);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        try{

            JSONObject jsonObject=new JSONObject(Lintent.getStringExtra("userData"));
            JSONArray jsonArray=jsonObject.getJSONArray("response");
            int count=0;
            String userID,styleName,brandName,totalPrice,comment;
            while(count<jsonArray.length())
            {
                JSONObject object=jsonArray.getJSONObject(count);
                userID=object.getString("userID");
                styleName=object.getString("styleName");
                brandName=object.getString("brandName");
                totalPrice=object.getString("totalPrice");
                comment=object.getString("comment");

                Data user=new Data(userID,styleName,brandName,totalPrice,comment);
                if(!userID.equals("admin"))
                {
                    dataList.add(user);
                    saveList.add(user);
                }
                count++;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        //검색하는기능
        EditText search=(EditText)findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUser(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.findViewById(R.id.toolbar_eamil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "아직 도착한 메세지가 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView=(NavigationView) findViewById(R.id.main_navigationView);

        checker=Lintent.getBooleanExtra("checker",false);

        if(checker==true)
        {
            View view=(View) getLayoutInflater().inflate(R.layout.side_bar,navigationView);
            TextView text1=(TextView)view.findViewById(R.id.SidebarID);
            text1.setText(Lintent.getStringExtra("userID"));
            Menu menu=navigationView.getMenu();
            menu.findItem(R.id.login).setEnabled(false);
            menu.findItem(R.id.join).setEnabled(false);

        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.login){

                    Intent intent=new Intent(MainActivity.this , LoginActivity.class);
                    MainActivity.this.startActivity(intent);
                }

                if(menuItem.getItemId()==R.id.join){

                    Intent intent=new Intent(MainActivity.this , RegisterActivity.class);
                    MainActivity.this.startActivity(intent);
                }

                if(menuItem.getItemId()==R.id.upload){
                    String userID= Lintent.getStringExtra("userID");
                    Intent intent=new Intent(MainActivity.this , StyleUpload.class);
                    intent.putExtra("userID",userID);
                    MainActivity.this.startActivity(intent);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
/*
        TextView idText=(TextView) findViewById(R.id.idText);
        TextView passwordText=(TextView) findViewById(R.id.passwordText);
        TextView welcomeMessage=(TextView) findViewById(R.id.welcomeMessage);
        Button managementButton=(Button) findViewById(R.id.managementButton);

        Intent intent=getIntent();
        String userID=intent.getStringExtra("userID");
        String userPassword=intent.getStringExtra("userPassword");
        String message="환영합니다."+userID+"님!";

        idText.setText(userID);
        passwordText.setText(userPassword);
        welcomeMessage.setText(message);

        if(!userID.equals("admin"))
        {
            managementButton.setVisibility(View.GONE);
        }

        managementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });*/
    }
    public  boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.nav_item,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return true;
    }

   public boolean onPrepareOptionsMenu(Menu menu)
    {
        boolean checker=false;
        Intent Rintent=getIntent();
        checker=Rintent.getBooleanExtra("checker",false);
        if(checker==true)
        {
            //Toast.makeText(MainActivity.this, "성공",Toast.LENGTH_SHORT).show();
            menu.findItem(R.id.login).setEnabled(false);
            menu.findItem(R.id.join).setEnabled(false);

        }

        return super.onPrepareOptionsMenu(menu);
    }


    public void searchUser(String search)
    {
        dataList.clear();
        for(int i=0;i<saveList.size();i++)
        {
            if(saveList.get(i).getUserID().contains(search) || saveList.get(i).getStyleName().contains(search) || saveList.get(i).getBrandName().contains(search) || saveList.get(i).getComment().contains(search)) {
                dataList.add(saveList.get(i));
            }


        }
        adapter.notifyDataSetChanged();
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
            Intent intent=new Intent(MainActivity.this,ManagementActivity.class);
            intent.putExtra("userList",result);
            MainActivity.this.startActivity(intent);
        }


    }

}
