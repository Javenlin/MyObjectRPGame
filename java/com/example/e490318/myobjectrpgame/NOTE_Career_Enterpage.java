package com.example.e490318.myobjectrpgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.e490318.myobjectrpgame.R.attr.height;


/**
 * Created by e490318 on 2018/4/24.
 */


public class NOTE_Career_Enterpage extends AppCompatActivity implements View.OnClickListener {

    //元件
//    private TextView btn_add_career;
    private TextView tv_id;
    private TextView tv_name;
    private TextView tv_gender;
    private TextView tv_career;
    private TextView tv_set_Fighter;
    private TextView tv_set_Mage;
    private TextView tv_set_Archer;
    private TextView tv_set_Paladin;
    private TextView tv_set_Warrior;
    private TextView tv_set_Assassin;
    private String role_id = "";
    private String role_name = "";
    private String role_gender = "";
    private String role_career = "";
    private String career_level = "";
    private String career_skill = "";
    private String pos = "";
    private static ArrayList<Role> listData_role_Object = new ArrayList<>();
    private static ArrayList<Career> listData_career_Object = new ArrayList<>();


    //變數
    private ArrayList<String> listData_role_career = new ArrayList<>();
    private String career[] = {"勇者", "法師", "弓箭手", "騎士", "狂戰士", "刺客", "技師"};
    private String skill[] = {"重擊", "火球", "毒箭", "回血", "狂化", "潛伏", "砲擊"};

    // 初心者
// 勇者  法師 弓箭手
// 騎士  狂戰士 刺客
// 技師

    private void initComponent() {

        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_career = (TextView) findViewById(R.id.tv_career);
        //
        tv_set_Fighter = (TextView) findViewById(R.id.tv_set_Fighter);
        tv_set_Mage = (TextView) findViewById(R.id.tv_set_Mage);
        tv_set_Archer = (TextView) findViewById(R.id.tv_set_Archer);
        tv_set_Paladin = (TextView) findViewById(R.id.tv_set_Paladin);
        tv_set_Warrior = (TextView) findViewById(R.id.tv_set_Warrior);
        tv_set_Assassin = (TextView) findViewById(R.id.tv_set_Assassin);
        //
        tv_set_Fighter.setOnClickListener(this);
        tv_set_Mage.setOnClickListener(this);
        tv_set_Archer.setOnClickListener(this);
        tv_set_Paladin.setOnClickListener(this);
        tv_set_Warrior.setOnClickListener(this);
        tv_set_Assassin.setOnClickListener(this);

//        btn_add_career = (TextView) findViewById(R.id.btn_add_career);
//        btn_add_career.setOnClickListener(this);

        /**@1_startActivity_傳值方式 */
        //
        /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        //
        /*获取Bundle中的数据，注意类型和key*/
        pos = bundle.getString("pos");
        role_id = bundle.getString("role_id");
        role_name = bundle.getString("role_name");
        role_gender = bundle.getString("role_gender");
        role_career = bundle.getString("role_career");
        career_level = bundle.getString("career_level");
        career_skill = bundle.getString("career_skill");
        listData_role_Object = ((ArrayList<Role>) bundle.getSerializable("listData_role_Object"));
        listData_career_Object = ((ArrayList<Career>) bundle.getSerializable("listData_career_Object"));

        Log.e("所有客戶資料串", "所有客戶資料串");
        for (int i = 0; i < listData_role_Object.size(); i++) {
            Log.e("所有客戶資料串",
                    listData_role_Object.get(i).getRole_id() + "\n" +
                            listData_role_Object.get(i).getRole_name() + "\n" +
                            listData_role_Object.get(i).getRole_gender() + "\n" +
                            listData_role_Object.get(i).getRole_career() + "\n" +
                            listData_career_Object.get(i).getCareer_name() + "\n" +
                            listData_career_Object.get(i).getCareer_level() + "\n" +
                            listData_career_Object.get(i).getCareer_skill() + "\n");
        }
        //
        /**@補充*/
        //String name = bundle.getString("Name");
        //boolean ismale = bundle.getBoolean("Ismale");
        //
        tv_id.setText(role_id);
        tv_name.setText(role_name);
        tv_gender.setText(role_gender);
        tv_career.setText(role_career);
        if (role_career == "" || role_career == "初心者")
            tv_career.setText("初心者");

        Log.e("pos", pos);

//        //矩陣塞入技能值
//        for (int i = 0; i < career.length; i++) {
//            listData_role_career.add(career[i]);
//        }
//
//        //確認技能已經置入矩陣
//        String career = "";
//        for (int j = 0; j < listData_role_career.size(); j++) {
//            career += listData_role_career.get(j) + " / ";
//        }
//        Log.e("career", career);
// FOREACH
//            int a[]={100,101,103};
//            for (int i : a) {  //i in a 之意
//                System.out.print(i);   //輸出 100101103
//            }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_career_enterpage);

        initComponent();
        Toast toast = Toast.makeText(this, "選擇職業", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 300);
        toast.show();
    }


    // TAG_SWITCH_HANDLE_BTN
    //implement the onClick method here
    public void onClick(View v) {
        String nCareer = "";
        String nSkill = "";
        String id = role_id;
        int i_pos = Integer.valueOf(pos);
        Log.e("ss", "sssssssssssss      ___    " + pos);
        //
        // Perform action on click
        switch (v.getId()) {
// 初心者
// 勇者  法師 弓箭手
// 騎士  狂戰士 刺客
// 技師
            case R.id.tv_set_Fighter:
                nCareer = career[0];
                nSkill = skill[0];
//                listData_role_career.add(career[0]);
//                listData_role_career.get(i_pos).set(career[0]);
//                nCareer = listData_role_career.get(i_pos).get;
                click_confirm(id, nCareer , nSkill);
//                Toast.makeText(getApplicationContext(), "你選擇了 勇者 !! ", Toast.LENGTH_SHORT).show();
//                Log.e("使用者選擇", id + " 號玩家，" + "已經成為 " + nCareer + " !!!");
                break;
            //
            case R.id.tv_set_Mage:
                nCareer = career[1];
                nSkill = skill[1];
                click_confirm(id, nCareer , nSkill);
//                listData_role_career.add(career[1]);
//                Toast.makeText(getApplicationContext(), "你選擇了 法師 !!", Toast.LENGTH_SHORT).show();
//                Log.i("使用者選擇", "法師 已選擇 !!");
                break;
            //
            case R.id.tv_set_Archer:
                nCareer = career[2];
                nSkill = skill[2];
                click_confirm(id, nCareer , nSkill);
//                listData_role_career.add(career[2]);
//                Toast.makeText(getApplicationContext(), "你選擇了 弓箭手 !!", Toast.LENGTH_SHORT).show();
//                Log.i("使用者選擇", "弓箭手 已選擇 !!");
                break;
            //
            case R.id.tv_set_Paladin:
                nCareer = career[3];
                nSkill = skill[3];
                click_confirm(id, nCareer , nSkill);
//                listData_role_career.add(career[3]);
//                Toast.makeText(getApplicationContext(), "你選擇了 騎士 !!", Toast.LENGTH_SHORT).show();
//                Log.i("使用者選擇", "騎士 已選擇 !!");
                break;
            //
            case R.id.tv_set_Warrior:
                nCareer = career[4];
                nSkill = skill[4];
                click_confirm(id, nCareer , nSkill);
//                listData_role_career.add(career[4]);
//                Toast.makeText(getApplicationContext(), "你選擇了 狂戰士 !!", Toast.LENGTH_SHORT).show();
//                Log.i("使用者選擇", "狂戰士 已選擇 !!");
                break;
            //
            case R.id.tv_set_Assassin:
                nCareer = career[5];
                nSkill = skill[5];
                click_confirm(id, nCareer , nSkill);
//                listData_role_career.add(career[5]);
//                Toast.makeText(getApplicationContext(), "你選擇了 刺客 !!", Toast.LENGTH_SHORT).show();
//                Log.i("使用者選擇", "刺客 已選擇 !!");
                break;
            //
            case R.id.tv_set_Specialist:
                nCareer = career[6];
                nSkill = skill[6];
                click_confirm(id, nCareer , nSkill);
//                listData_role_career.add(career[6]);
//                Toast.makeText(getApplicationContext(), "你選擇了 技師 !!", Toast.LENGTH_SHORT).show();
//                Log.i("使用者選擇", "技師 已選擇 !!");
                break;
            default:
                Toast.makeText(getApplicationContext(), "發生錯誤 請再選擇一次 !!", Toast.LENGTH_SHORT).show();
                Log.i("使用者選擇", "發生錯誤 要求重選 ");
                break;
            //            case R.id.buttonstop:
            //                //New btn function
            //                 break;
        }
    }


    private void click_confirm(String id, final String nCareer,final String nSkill) {
        Log.e("55555555", "click_confirm" + nCareer);
        //產生視窗物件
        new AlertDialog.Builder(NOTE_Career_Enterpage.this)
                .setTitle("轉職確認")//設定視窗標題
                .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                .setMessage("您確定要轉職成為 " + nCareer + " 嗎？")//設定顯示的文字
                .setPositiveButton("是的！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_career.setText(nCareer);
                        Toast.makeText(getApplicationContext(), "你選擇了 " + nCareer + " !!", Toast.LENGTH_SHORT).show();
                        Log.i("使用者轉職", nCareer + "已選擇 !!");
                        role_career = nCareer;
                        career_skill = nSkill;
                        Bundle bundle = new Bundle();            /* 通过Bundle对象存储需要传递的数据 */
                        bundle.putString("pos", String.valueOf(pos));
//                        Log.e("Career_Enterpage(pos)",String.valueOf(pos));
                        bundle.putString("role_id", role_id);
                        bundle.putString("role_name", role_name);
                        bundle.putString("role_gender", role_gender);
                        bundle.putString("role_career", role_career);
                        bundle.putString("career_level", career_level);
                        bundle.putString("career_skill", career_skill);
                        bundle.putSerializable("listData_role_Object", listData_role_Object);
                        bundle.putSerializable("listData_career_Object", listData_career_Object);

                        Intent intent = new Intent(NOTE_Career_Enterpage.this, MainActivity.class);
                        intent.putExtras(bundle);                /*把bundle对象assign给Intent*/
                        startActivity(intent);  // startActivityForResult(intent,0);
                        finish();
                    }
                })
                .setNegativeButton("再考慮看看..", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                    }

                })//設定結束的子視窗//設定結束的子視窗
                .show();//呈現對話視窗
    }
}


/**@字串替換範例*/
//        String str = (new Integer("0050580")).toString();
//        String s_id = strid.replaceFirst("^0*", "");

//        //替換全部
//        String replaceTest = "abcabcabc";
//        String replaced = replaceTest.replaceAll("abc", "123");
//        System.out.println("replaceAll = "+replaced);
////replaceAll = 123123123
//
////替換最先找到那個
//        replaced = replaceTest.replaceFirst("abc", "123");
//        System.out.println("replaceFirst = "+replaced);
////replaceFirst = 123abcabc


//        String s_id = strid.replaceFirst("^0*", "");
//        s_id.replaceAll("\\0", "");
//        s_id.replaceAll("\@", "");
//
//        int n_id = Integer.parseInt(id);