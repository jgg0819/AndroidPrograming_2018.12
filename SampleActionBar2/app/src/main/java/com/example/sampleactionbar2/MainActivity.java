package com.example.sampleactionbar2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleactionbar2.R;

public class MainActivity extends AppCompatActivity {
    ActionBar abar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abar=getSupportActionBar();
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                abar.setLogo(R.drawable.home);
                abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
            }
        });
    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        View v=menu.findItem(R.id.menu_search).getActionView();

        if(v!=null){
            EditText editText=v.findViewById(R.id.edit_text);
            if(editText!=null){
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener(){
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                        Toast.makeText(getApplicationContext(),"입력됨",Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
            }

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "설정 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
