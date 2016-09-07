package com.example.lenovo.bbqu.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.lenovo.bbqu.R;
import com.example.lenovo.bbqu.controller.NewPostHelper;

public class NewPostActivity extends AppCompatActivity {


    EditText postName,mainPost,user;
    Button newP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        postName=(EditText)findViewById(R.id.postName);
        mainPost=(EditText)findViewById(R.id.mainPost);
        user=(EditText)findViewById(R.id.user);
        newP=(Button)findViewById(R.id.button3);
        newP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //存储新帖子
                NewPostHelper helper=new NewPostHelper(NewPostActivity.this,"postHistory",null,1);
                SQLiteDatabase db=helper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("postName",postName.getText().toString());
                values.put("postMain",mainPost.getText().toString());
                values.put("postUser",user.getText().toString());
                Log.d("My", postName.getText().toString());
                db.insert("postHistory", null, values);
                db.close();
                helper.close();
                finish();
            }
        });
    }
}
