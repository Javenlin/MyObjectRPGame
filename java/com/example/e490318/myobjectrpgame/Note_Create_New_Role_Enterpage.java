package com.example.e490318.myobjectrpgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by e490318 on 2018/4/25.
 */

public class Note_Create_New_Role_Enterpage extends AppCompatActivity {

    //元件
    private TextView btn_create_new_role;
    private TextView btn_gender_male;
    private TextView btn_gender_female;
    private TextView btn_name_confirm;
    private TextView tv_id;
    private TextView tv_name;
    private TextView tv_gender;
    private TextView tv_career;
    private EditText et_role_name;
    //
    //變數
    private String pos;
    private String role_id;
    private String role_name;
    private String role_gender;
    private String role_career;
    private String career_level = "-";
    private String career_skill = "-";
    private ArrayList<Role> listData_role_Object = new ArrayList<Role>();
    private ArrayList<Career> listData_career_Object = new ArrayList<Career>();
    static final int PICK_CONTACT_REQUEST = 1;  // The request code


    private void initComponent() {
        btn_create_new_role = (TextView) findViewById(R.id.btn_create_new_role);
        btn_gender_male = (TextView) findViewById(R.id.btn_gender_male);
        btn_gender_female = (TextView) findViewById(R.id.btn_gender_female);
        btn_name_confirm = (TextView) findViewById(R.id.btn_name_confirm);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_career = (TextView) findViewById(R.id.tv_career);
        et_role_name = (EditText) findViewById(R.id.et_role_name);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_create_new_role_enterpage);
        initComponent();

        /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        listData_role_Object = (ArrayList<Role>) bundle.getSerializable("listData_role_Object");
        listData_career_Object = (ArrayList<Career>) bundle.getSerializable("listData_career_Object");
        role_id = bundle.getString("role_id");
        pos = bundle.getString("pos");

        tv_id.setText(role_id);
        Log.e("NewRole_Enterpage", "role_id ------->> " + role_id);
        tv_career.setText("初心者");
        role_career = "初心者";
        role_gender = "男性";
        tv_gender.setText("男性");

//        Log.e("初心者", "role_id: " + role_id + " / " + "pos: " + pos);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gender_male:
                Toast.makeText(getApplicationContext(), "你選擇男性", Toast.LENGTH_SHORT).show();
                tv_gender.setText("男性");
                role_gender = "男性";
                Log.e("男性點選", role_gender);
                break;
            case R.id.btn_gender_female:
                Toast.makeText(getApplicationContext(), "你選擇女性", Toast.LENGTH_SHORT).show();
                tv_gender.setText("女性");
                role_gender = "女性";
                Log.e("女性點選", role_gender);
                break;
            case R.id.btn_name_confirm:
                String newname = String.valueOf(et_role_name.getText());
                tv_name.setText(newname);
                role_name = newname;
                Log.e("名稱點選", role_name);
                Toast.makeText(getApplicationContext(), "名稱可使用!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_create_new_role:
                Log.e("角色創建", role_id + " _ " + role_name + " _ " + role_gender + " _ " + role_career);
                Toast.makeText(getApplicationContext(), "新角色建立完成!!!", Toast.LENGTH_SHORT).show();


//                role_id = mylistData_role_Object.get(position).getRole_id();
//                role_name = mylistData_role_Object.get(position).getRole_name();
//                role_gender = mylistData_role_Object.get(position).getRole_gender();
//                role_career = mylistData_role_Object.get(position).getRole_career();
//
//                Log.e("onClick", "帳號: " + role_id + "    姓名: " + role_name +
//                        "性別: " + role_gender + "    職業: " + role_career + "    編號: " + position);


                Bundle bundle = new Bundle();            /* 通过Bundle对象存储需要传递的数据 */
                bundle.putSerializable("listData_role_Object", listData_role_Object);
                bundle.putSerializable("listData_career_Object", listData_career_Object);
                bundle.putString("pos", pos);
                bundle.putString("role_id", role_id);
                bundle.putString("role_name", role_name);
                bundle.putString("role_gender", role_gender);
                bundle.putString("role_career", role_career);
                bundle.putString("career_level", career_level);
                bundle.putString("career_skill", career_skill);

                Intent intent = new Intent(Note_Create_New_Role_Enterpage.this, MainActivity.class);
                intent.putExtras(bundle);                /*把bundle对象assign给Intent*/
                setResult(RESULT_OK, intent);
                startActivity(intent);
                break;
        }
    }

}
