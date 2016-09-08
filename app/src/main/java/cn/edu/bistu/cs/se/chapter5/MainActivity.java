package cn.edu.bistu.cs.se.chapter5;

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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private final static String MyFileName="myfile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn_write=(Button) findViewById(R.id.btn_write);
        Button btn_read=(Button) findViewById(R.id.btn_read);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream out=null;
                try {
                    FileOutputStream fileOutputStream=openFileOutput(MyFileName,MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);
                    String content="hello world";
                    try {
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                        Toast.makeText(MainActivity.this,"已写入数据",Toast.LENGTH_SHORT).show();
                    }
                    finally {
                        if(out!=null)
                            out.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            });
        btn_read.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                InputStream in=null;
                try{
                    FileInputStream fileInputStream=openFileInput(MyFileName);
                    in= new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder stringBuilder= new StringBuilder("");
                    try {
                        while((c= in.read())!= -1){
                            stringBuilder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    }finally {
                        if(in!= null)
                            in.close();
                    }
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
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
