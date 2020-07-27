package com.example.e490318.myobjectrpgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

/**
 * Created by e490318 on 2018/4/19.
 */

public class NOTE_Role_Enterpage extends AppCompatActivity implements View.OnClickListener { //, Parcelable
    // TAG_SWITCH_HANDLE_BTN implements View.OnClickListener

    //元件
    private TextView tv_id;
    private TextView tv_name;
    private TextView tv_gender;
    private TextView tv_career;
    private TextView btn_add_career;
    private TextView btn_use_skill;

    //變數
    String pos = "";
    String role_id = "";
    String role_name = "";
    String role_gender = "";
    String role_career = "";
    String career_level = "";
    String career_skill = "";
    Career careerObject;
    Role roleObject;
    //    private static Serializable listData_role_Object = new ArrayList<>();
    private static ArrayList<Role> listData_role_Object = new ArrayList<>();
    private static ArrayList<Career> listData_career_Object = new ArrayList<>();


    private void initComponent() {
        //
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_career = (TextView) findViewById(R.id.tv_career);
        //
        btn_add_career = (TextView) findViewById(R.id.btn_add_career);
        btn_add_career.setOnClickListener(this);
        btn_use_skill = (TextView) findViewById(R.id.btn_use_skill);
        btn_use_skill.setOnClickListener(this);


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_role_enterpage);

        initComponent();
        Toast toast = Toast.makeText(this, "角色基本資料", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 300);
        toast.show();


        /**@1_startActivity_傳值方式 */
        /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        /*获取Bundle中的数据，注意类型和key*/
        pos = bundle.getString("pos");
        int ipos = Integer.parseInt(bundle.getString("pos"));
        role_id = bundle.getString("role_id");
        role_name = bundle.getString("role_name");
        role_gender = bundle.getString("role_gender");
        role_career = bundle.getString("role_career");
        career_level = bundle.getString("career_level");
        career_skill = bundle.getString("career_skill");
        listData_role_Object = ((ArrayList<Role>) bundle.getSerializable("listData_role_Object")); //强转成你自己定义的list，这样list2就是你传过来的那个list了。
        listData_career_Object = ((ArrayList<Career>) bundle.getSerializable("listData_career_Object"));
//        Log.e("Role_Page", pos + "\n" + role_id + "\n" + role_name + "\n" + role_gender + "\n" + role_career + "\n" + career_level + "\n" + career_skill);


        for (int i = 0; i < listData_role_Object.size(); i++) {
            Log.e("career_Object.size()", String.valueOf(listData_role_Object.size()));
            String Role_id = listData_role_Object.get(i).getRole_id();
            String role_name = listData_role_Object.get(i).getRole_name();
            String role_gender = listData_role_Object.get(i).getRole_gender();
            String role_career = listData_role_Object.get(i).getRole_career();
            String career_level = listData_career_Object.get(i).getCareer_level();
            String career_skill = listData_career_Object.get(i).getCareer_skill();
            Log.e("Role_Page_WITHOBJECT", Role_id + "\n" + role_name + "\n" + role_gender + "\n" + role_career + "\n" + career_level + "\n" + career_skill);
        }

        //TODO Parcelable Method !!!
        /**@Parcelable矩陣Bundle取值範例*/
        /************************************************************************************
         ArrayList role_Object_list_box = new ArrayList();
         ArrayList role_Object_list = bundle.getParcelableArrayList("listData_role_Object");
         role_Object_list_box = (ArrayList<Role>) role_Object_list.get(ipos);//强转成你自己定义的list，这样list2就是你传过来的那个list了。
         //
         ArrayList career_Object_list_box = new ArrayList();
         ArrayList career_Object_list = bundle.getParcelableArrayList("listData_career_Object");
         career_Object_list_box = (ArrayList<Role>) role_Object_list.get(ipos);//强转成你自己定义的list，这样list2就是你传过来的那个list了。

         Log.e("Role_Page_1", String.valueOf(role_Object_list_box.get(ipos)));
         ***********************************************************************************/


        //物件包裝
        careerObject = new Career(getApplicationContext(), role_id, role_career, career_level, career_skill);
        roleObject = new Role(pos, role_id, role_name, role_gender, role_career);
        Log.e("施放技能", roleObject.getRole_name() + " 使用 " + roleObject.getRole_career() + "的技能，" + "\n" +
                "結果施放出 => 等級" + careerObject.getCareer_level() + "的" + careerObject.getCareer_skill() + "之術！！！");


//        bundle.putString("mylistData_careerlevel", career_level);
//        bundle.putString("mylistData_careerskill", career_skill);
        //
        /**@bundle補充*/
        //String name = bundle.getString("Name");
        //boolean ismale = bundle.getBoolean("Ismale");
        Log.e("position", pos);
        tv_id.setText(role_id);
        tv_name.setText(role_name);
        tv_gender.setText(role_gender);
        tv_career.setText(role_career);

    }

    // TAG_SWITCH_HANDLE_BTN
    //implement the onClick method here
    public void onClick(View v) {
        // Perform action on click
        switch (v.getId()) {
            case R.id.btn_add_career:
                Intent intent = new Intent(NOTE_Role_Enterpage.this, NOTE_Career_Enterpage.class);
                Bundle bundle = new Bundle();            /* 通过Bundle对象存储需要传递的数据 */
                bundle.putString("pos", pos);
                bundle.putString("role_id", role_id);
                bundle.putString("role_name", role_name);
                bundle.putString("role_gender", role_gender);
                bundle.putString("role_career", role_career);
                bundle.putString("career_level", career_level);
                bundle.putString("career_skill", career_skill);
                bundle.putSerializable("listData_role_Object", listData_role_Object);
                bundle.putSerializable("listData_career_Object", listData_career_Object);
                intent.putExtras(bundle);                /*把bundle对象assign给Intent*/
                startActivity(intent);  // startActivityForResult(intent,0);

                break;
            case R.id.btn_use_skill:
                if (careerObject.getCareer_skill().equals("-")) {
                    Toast toast = Toast.makeText(this, roleObject.getRole_name() + " 使用 " + roleObject.getRole_career() + "的技能，" + "\n" +
                            "結果甚麼也沒發生...", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 300);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(this, roleObject.getRole_name() + " 使用 " + roleObject.getRole_career() + "的技能，" + "\n" +
                            "結果施放出 => 等級" + careerObject.getCareer_level() + "的" + careerObject.getCareer_skill() + "之術！！！", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 300);
                    toast.show();
                }


                Log.e("施放技能", roleObject.getRole_name() + " 使用 " + roleObject.getRole_career() + "的技能，" + "\n" +
                        "結果施放出 => 等級" + careerObject.getCareer_level() + "的" + careerObject.getCareer_skill() + "之術！！！");
                break;

        }
    }
}


/**
 * @2_startActivityForResult_傳值方式
 */
//    @Override
//    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//
//        switch(requestCode){
//            case RESULT_OK:
//
//                /*取得来自B页面的数据，并显示到画面*/
//                Bundle bundle = data.getExtras();
//
//                /*获取Bundle中的数据，注意类型和key*/
//                String id = bundle.getString("mylistData_id");
//                String name = bundle.getString("mylistData_name");
//                String age = bundle.getString("mylistData_age");
//
//                /**@補充*/
//                //String name = bundle.getString("Name");
//                //boolean ismale = bundle.getBoolean("Ismale");
//
//                tv_id.setText(id);
//                tv_name.setText(name);
//                tv_age.setText(age);
//
//
//        }
//    }