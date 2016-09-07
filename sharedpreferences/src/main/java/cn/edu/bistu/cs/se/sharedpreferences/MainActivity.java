package cn.edu.bistu.cs.se.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String SharedPreferencesFileName="config";
    //键
    private final static String Key_Name="Name";
    private final static String Key_Num="Number";
    private final static String Key_Sex="Sex";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences=getSharedPreferences(SharedPreferencesFileName,MODE_PRIVATE);
        editor=preferences.edit();
        Button btnWrite=(Button)findViewById(R.id.btn_Write);
        Button btnRead=(Button)findViewById(R.id.btn_Read);
        btnWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //写入键值对
                editor.putString(Key_Name, "小红");
                editor.putString(Key_Num, "2014011223");
                editor.putString(Key_Sex, "女");
                //提交修改
                editor.apply();
                Toast.makeText(MainActivity.this,"已写入数据",Toast.LENGTH_SHORT).show();
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = preferences.getString(Key_Name, null);
                String strNum = preferences.getString(Key_Num, null);
                String strSex=preferences.getString(Key_Sex,null);
                if(strName !=null && strNum!=null)
                    Toast.makeText(MainActivity.this,"姓名："+strName+" 学号："+strNum+" 性别："+strSex,Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"无数据",Toast.LENGTH_LONG).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
