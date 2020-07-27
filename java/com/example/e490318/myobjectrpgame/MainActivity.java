package com.example.e490318.myobjectrpgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @繼承的使用範例
 */

/**
 * @範例說明: 物件A_Role_繼承_物件B_Career
 */

public class MainActivity extends AppCompatActivity {


    //元件
    TextView btn_add_role;

    //列表
    private RecyclerView RLView_Sample;
    private LinearLayoutManager linearLayoutManager;
    private Inner_RLViewAdapter Inner_RLViewAdapter; //>>>內部適配器
    //    private Outer_RLViewAdapter Outer_RLViewAdapter;  //>>>@外部適配器


    //變數
    /**
     * @Role
     */

    /**
     * @全部舊資料
     */
    private String pos = "";
    private int Ipos;
    private String role_id = "";
    private String role_name = "";
    private String role_gender = "";
    private String role_career = "";
    private String career_level = "";
    private String career_skill = "";
    private Integer reset_idx;
    private String id_check_last = "";
    private Integer index_check_last;

    /**
     * @最新單筆
     */
//    String nPos ="";
//    String nRole_id="";
//    String nRole_name="";
//    String nRole_gender="";
//    String nRole_career="";
    private String nPos = "";
    private int nIPos;
    private String nRole_id = "";
    private String nRole_name = "";
    private String nRole_gender = "";
    private String nRole_career = "";
    private String nCareer_level = "";
    private String nCareer_skill = "";
    private Integer nReset_idx;
    //
    private ArrayList<String> listData_role_pos = new ArrayList<>();
    private ArrayList<String> listData_role_id = new ArrayList<>();
    private ArrayList<String> listData_role_name = new ArrayList<>();
    private ArrayList<String> listData_role_gender = new ArrayList<>();
    private ArrayList<String> listData_role_career = new ArrayList<>();
    private ArrayList<String> listData_career_level = new ArrayList<>();
    private ArrayList<String> listData_career_skill = new ArrayList<>();
    //
    private ArrayList<Role> listData_role_Object;
    private ArrayList<Career> listData_career_Object;
    //    public static listData_role_Object co_a = null; //一定要加static 且 public
    //
    //
    private String careers[] = {"勇者", "法師", "弓箭手", "騎士", "狂戰士", "刺客", "技師"};


    private void InitiComponent() {
        btn_add_role = (TextView) findViewById(R.id.btn_create_new_role);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_recycleview);


        //ROLE_生成假資料
        InitiComponent();

        /**@從新建角色接值*/
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {

            Log.e("listData_role_Object_看看", "listData_role_Object_看看");


            role_id = "";
            pos = "";
            Ipos = 0;
            role_id = "";
            role_name = "";
            role_gender = "";
            role_career = "";
            career_level = "";
            career_skill = "";


            //
            listData_role_Object = new ArrayList<>();
            listData_career_Object = new ArrayList<>();
            listData_role_Object = ((ArrayList<Role>) bundle.getSerializable("listData_role_Object"));
            listData_career_Object = ((ArrayList<Career>) bundle.getSerializable("listData_career_Object"));


            /**@獲得__<重設>__之單一角色資料*/
            /**@*/                        /**@*/
            /**@獲得__<新增>__之單一角色資料*/
            //＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
            /**@未分析回傳值*/
            nPos = bundle.getString("pos");
            nIPos = Integer.parseInt(nPos);
            nRole_id = bundle.getString("role_id");
            nRole_name = bundle.getString("role_name");
            nRole_gender = bundle.getString("role_gender");
            nRole_career = bundle.getString("role_career");
            nCareer_level = bundle.getString("career_level");
            nCareer_skill = bundle.getString("career_skill");
            Log.e("職業重選單一角色回傳", "*************************     " + pos + "/" + role_id + "/" + role_name + "/" + role_gender + "/" + role_career + "/" + career_level + "/" + career_skill + "    *********************************** \n");
            //＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊

            /**@獲得第一次登入之所有角色資料*/
            for (int i = 0; i < listData_role_Object.size(); i++) {

                role_id = listData_role_Object.get(i).getRole_id();
                role_name = listData_role_Object.get(i).getRole_name();
                role_gender = listData_role_Object.get(i).getRole_gender();
                role_career = listData_role_Object.get(i).getRole_career();
                career_level = listData_career_Object.get(i).getCareer_level();
                career_skill = listData_career_Object.get(i).getCareer_skill();
                //
                listData_role_id.add(role_id);
                listData_role_name.add(role_name);
                listData_role_gender.add(role_gender);
                listData_role_career.add(role_career);
                listData_career_level.add(career_level);
                listData_career_skill.add(career_skill);
                //
                Log.e("第一次登入所有角色",
                        listData_role_Object.get(i).getRole_id() + "\n" +
                                listData_role_Object.get(i).getRole_name() + "\n" +
                                listData_role_Object.get(i).getRole_gender() + "\n" +
                                listData_role_Object.get(i).getRole_career() + "\n" +
                                listData_career_Object.get(i).getCareer_name() + "\n" +
                                listData_career_Object.get(i).getCareer_level() + "\n" +
                                listData_career_Object.get(i).getCareer_skill() + "\n");
                //
                if (listData_role_Object.get(listData_role_Object.size() - 1).getRole_id() == listData_role_Object.get(i).getRole_id()) {
                    id_check_last = listData_role_Object.get(i).getRole_id();
                    index_check_last = i;
                    Log.e("id_check_last", "id_check_last(最後ID): " + id_check_last + " / index_check_last(最後Index): " + index_check_last);
                    break;
                }

            }
            //
            String LastRoleID = listData_role_Object.get(listData_role_Object.size() - 1).getRole_id();
            Log.e("role_id", "最後角色ID Last RoleID == " + LastRoleID);
            //
//            Log.e("role_id", "最後角色Last roleid == " + role_id + "__" + listData_role_Object.get( listData_role_Object.size()).getRole_id());
            //
            int newRole_Index = 0;
            int alterRole_Index = 0;
            /**@識別回傳資料*/
            for (int k = 0; k < listData_role_Object.size(); k++) {


                /**@識別回傳資料是否為_--_新增角色*/
                if (nIPos == index_check_last + 1) {
                    newRole_Index = listData_role_Object.size();
                    Log.e("newRole_Index", String.valueOf(newRole_Index));
                }
                /**@識別回傳資料是否為_--_修改角色*/
                else {
//                     Integer.parseInt(listData_role_Object.get(k).getPos())
                    alterRole_Index = nIPos;
                    Log.e("alterRole_Index", String.valueOf(alterRole_Index));
                }
            }
//            if (!role_id.equals(nRole_id_check_new)) {
            //
//                int newRole = listData_role_Object.size() - 1;
//            Log.e("獲得最底部SIZE", String.valueOf(listData_role_name.size() - 1));
            /**@識別完畢_--_新增角色*/
            if (nIPos == index_check_last + 1) {//nRole_id == id_check_last&& Ipos == index_check_last + 1
//                !role_id.equals(id_check_last) &&
                newRole_Index = listData_role_Object.size();

                Log.e("最後_nRole_id_check_new", id_check_last);
                Log.e("最新_newRole_Index", String.valueOf(nIPos));
                Role roleObject = new Role(nPos, nRole_id, nRole_name, nRole_gender, nRole_career);
                Career careerObject = new Career(getApplicationContext(), nRole_id, nRole_career, nCareer_level, nCareer_skill);
//                    public Career(Context context, String Role_id, String career_name, String career_level, String career_skill) {\
                listData_role_Object.add(roleObject);
                listData_career_Object.add(careerObject);
                //
                nPos = listData_role_Object.get(newRole_Index).getPos();
                nRole_id = listData_role_Object.get(newRole_Index).getRole_id();
                nRole_name = listData_role_Object.get(newRole_Index).getRole_name();
                nRole_gender = listData_role_Object.get(newRole_Index).getRole_gender();
                nRole_career = listData_role_Object.get(newRole_Index).getRole_career();
                nCareer_level = listData_career_Object.get(newRole_Index).getCareer_level();
                nCareer_skill = listData_career_Object.get(newRole_Index).getCareer_skill();
                //
                Log.e("確認角色物件矩陣獲得新資料", nPos + " _ " + nRole_id + " _ " + nRole_name + " _ " + nRole_gender + " _ " + nRole_career + " _ " + nCareer_level + " _ " + nCareer_skill);
                //
                InitRecyclerView(listData_role_Object, listData_career_Object);
            }
//            }

            /**@識別完畢_--_修改角色*/
            if (nIPos != index_check_last + 1) {

                reset_idx = nIPos;
                listData_role_Object.get(reset_idx).setPos(nPos);
                listData_role_Object.get(reset_idx).setRole_id(nRole_id);
                listData_role_Object.get(reset_idx).setRole_name(nRole_name);
                listData_role_Object.get(reset_idx).setRole_gender(nRole_gender);
                listData_role_Object.get(reset_idx).setRole_career(nRole_career);
                listData_career_Object.get(reset_idx).setCareer_level(nCareer_level);
                listData_career_Object.get(reset_idx).setCareer_skill(nCareer_skill);
                //
                Log.e("確認角色物件矩陣修改新資料", nRole_id + " _ " + nRole_name + " _ " + nRole_gender + " _ " + nRole_career + " _ " + nCareer_level + " _ " + nCareer_skill);
                //
                InitRecyclerView(listData_role_Object, listData_career_Object);

//                reset_idx = Integer.valueOf(nIPos); //listData_role_Object.get(i).getRole_id().indexOf(role_id);
//                listData_role_name.set(reset_idx, role_name);

            }
        } else {
            /**@第一次登入*/
            CREATE_ROLE_DATA_API();
        }
    }


    //
    //
    //
    //
    //_________________________________________________________________________________
    //_________________________________________________________________________________
    //
    //
    //
    //

    /**@*/

    private void CREATE_ROLE_DATA_API() {
        /**@初始化物件矩陣*/
        listData_role_Object = new ArrayList<>();
        listData_career_Object = new ArrayList<>();

        /**@>>>開始生成假資料*/
        for (int i = 0; i < 100; i++) {
            /**@Role_Pos*/
            listData_role_pos.add(String.format("%d", i));
//            Log.e("listData_role_pos", listData_role_pos.get(i));
            //
            /**@Role_ID*/
            listData_role_id.add("@" + String.format("%04d", i));
//            Log.e("listData_role_id", listData_role_id.get(i));
            //
            /**@Role_Name*/
            listData_role_name.add("角色 < " + String.format("%04d", i) + " >");
//            Log.e("listData_role_name", listData_role_name.get(i));
            //
            /**@Role_Gender*/
            String gender;
            int num1 = (int) (Math.random() * 2);
            if (num1 == 0) {
                gender = "男性";
            } else {
                gender = "女性";
            }
            listData_role_gender.add(gender);
            //
            /**@Role_Career*/
            /**@Role_Skill*/
            String career;
            String skill = null;
            int num2 = (int) (Math.random() * 7);
//            {"勇者", "法師", "弓箭手", "騎士", "狂戰士", "刺客", "技師"}
            switch (num2) {
                case 0:
                    career = careers[0];
                    skill = "重擊";
                    break;
                case 1:
                    career = careers[1];
                    skill = "火球";
                    break;
                case 2:
                    career = careers[2];
                    skill = "毒箭";
                    break;
                case 3:
                    career = careers[3];
                    skill = "回血";
                    break;
                case 4:
                    career = careers[4];
                    skill = "狂化";
                    break;
                case 5:
                    career = careers[5];
                    skill = "潛伏";
                    break;
                case 6:
                    career = careers[6];
                    skill = "砲擊";
                    break;
                default:
                    career = "初心者";
            }
            listData_role_career.add(career);
            listData_career_skill.add(skill);

            int num3 = (int) (Math.random() * 100);
            listData_career_level.add(String.valueOf(num3));

            pos = listData_role_pos.get(i);
            role_id = listData_role_id.get(i);
            role_name = listData_role_name.get(i);
            role_gender = listData_role_gender.get(i);
            role_career = listData_role_career.get(i);
            career_skill = listData_career_skill.get(i);
            career_level = listData_career_level.get(i);

//            Log.e("假資料生成確認", 對屬性矩陣取值);
//            Log.e("＊", "＊＊＊＊＊＊＊＊＊＊＊＊＊");
//            Log.e("role_id", role_id);
//            Log.e("role_name", role_name);
//            Log.e("role_gender", role_gender);
//            Log.e("role_career", role_career);
//            Log.e("career_skill", career_skill);
//            Log.e("career_level", career_level);
//            Log.e("＊", "＊＊＊＊＊＊＊＊＊＊＊＊＊");

/**@______________________________________________________________________________________________*/
            /**@封裝物件*/
            //
            //>>>Role_物件
            Role roleObject = new Role(pos, role_id, role_name, role_gender, role_career);
            Log.e("roleObject", pos + " /" + role_id + " /" + role_career + " /" + career_level + " /" + career_skill);
            Log.e("GET_userinfoObject",
                    roleObject.getPos() + " /" +
                    roleObject.getRole_id() + " /" +
                    roleObject.getRole_name() + " /" +
                    roleObject.getRole_gender() + " /" +
                    roleObject.getRole_career());
            //
            //>>>Career_物件
            Career careerObject = new Career(getApplicationContext(), role_id, role_career, career_level, career_skill);
            Log.e("careerObject", role_id + " /" + role_career + " /" + career_level + " /" + career_skill);
            Log.e("GET_careerObject",
                    careerObject.getRole_id() + " /" +
                    careerObject.getCareer_name() + " /" +
                    careerObject.getCareer_level() + " /" +
                    careerObject.getCareer_skill());
/**@______________________________________________________________________________________________*/




            /**@加入物件於矩陣中*/
            listData_role_Object.add(roleObject);
            listData_career_Object.add(careerObject);

            /**@進行Log確認*/
            for (int j = 0; j < listData_role_Object.size(); j++) {
                role_id = listData_role_Object.get(j).getRole_id();
                role_name = listData_role_Object.get(j).getRole_name();
                role_gender = listData_role_Object.get(j).getRole_gender();
            }
            Log.e("listData_userinfo",
                    "size: " + listData_role_Object.size() + " / "
                            + "role_id: " + role_id + " / "
                            + "role_name: " + role_name + " / "
                            + "role_gender: " + role_gender);
        }

        Log.e(" ", " \n\n");
        Log.e("listData_role_id_N", String.valueOf(listData_role_id.size()));
        Log.e("listData_role_name_N", String.valueOf(listData_role_name.size()));
        Log.e("listData_role_gender_N", String.valueOf(listData_role_gender.size()));

        InitRecyclerView(listData_role_Object, listData_career_Object);

    }

    //
    //
    //
    //
    //_________________________________________________________________________________
    //_________________________________________________________________________________
    //
    //
    //
    //


    /**@重要傳值方式:__跨函式傳值*/
    //A方:    InitRecyclerView(listData_userinfo);
    //B方:   InitRecyclerView( ArrayList<UserInfo> listData_userinfo )

    /**
     * @物件傳值應注意:
     */
    //____ ＿＿＿＿只宣告矩陣 InitRecyclerView( ArrayList  listData_userinfo  ) 而不於內宣告 <UserInfo>
    //＿＿＿＿＿＿程式將默認為一般字串矩陣，而非物件矩陣
    private void InitRecyclerView(ArrayList<Role> listData_role_Object, ArrayList<Career> listData_career_Object) {

        /**
         * 設定列表值[{"age":"0","id":"@000","name":"會員000人士"}
         */
        Log.e("InitRecyclerView", " __ __ __ " + "InitRecyclerView");
        for (int i = 0; i < listData_role_Object.size(); i++) {
            Log.e("InitRView_lst_role_Obj", " __ __ __ " + listData_role_Object.get(i).getRole_id() + " _ " + listData_role_Object.get(i).getRole_name() + " _ " + listData_role_Object.get(i).getRole_gender() + "\n");
        }

        /**
         * @>>>>>>>________設定列表元件
         */
        /*建立列表元件*/
        RLView_Sample = (RecyclerView) findViewById(R.id.RLView_Sample);

        /**
         * @>>>>>>>________建立適配器
         */
        /**@內部適配器一般方式*/
//        Inner_RLViewAdapter = new Inner_RLViewAdapter(listData_id, listData_name, listData_age);        //補充：放入要傳值的list，可以放一堆
        Inner_RLViewAdapter = new Inner_RLViewAdapter(listData_role_Object, listData_career_Object);        //補充：放入要傳值的list，可以放一堆

        /**@外部適配器進階方式 */
//        Outer_RLViewAdapter = new Outer_RLViewAdapter();
////        Outer_RLViewAdapter.setAdapterData(NOTE_Recycleview.class, getApplicationContext(), listData_id, listData_name, listData_age);
//        Outer_RLViewAdapter.setAdapterData(NOTE_Recycleview.class, getApplicationContext(), listData_userinfo);


        /**
         * @>>>>______建立布局管理器
         */
        /*建立布局管理器*/
        linearLayoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);  //補充：決定列表布局顯示方式，LinearLayout就是直的
        /*設定顯示方式*/
        RLView_Sample.setLayoutManager(linearLayoutManager);     //補充：可和上一行合併在一起
        /**@補充＊布局管理器@_合併式*/ //RLView_Sample.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false));

        /**@直接跳至卷軸指定位置*/
        MoveToPosition(linearLayoutManager, listData_role_Object.size());
        /**
         * @>>>>>>>>>________設定適配器
         */
        /**@內部適配器一般方式*/
//        RLView_Sample.setAdapter(Inner_RLViewAdapter);
        /**@外部適配器進階方式 */
//        RLView_Sample.setAdapter(Outer_RLViewAdapter);

        /**@當前執行的適配器*/
        /**@>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        /**@>>>>*/
        /**@>>>>*/RLView_Sample.setAdapter(Inner_RLViewAdapter);
        /**@>>>>*/
        /**@>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    }

    //
    //
    //
    //
    //_________________________________________________________________________________
    //_________________________________________________________________________________
    //
    //
    //
    //

    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {

        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }

/**
 * @適配器重要取值觀念: getItemCount計數器決定了Recycleview的進入與執行
 * ，是否能夠開始，若始終技術為０則不會啟動
 */

    /**
     * @>>>>>____________內部適配器一般方式_______________________________________________
     */
    private class Inner_RLViewAdapter extends RecyclerView.Adapter<Inner_RLViewAdapter.MyViewHolder> {// implements Parcelable


        /**
         * @自動元件_1
         */ //--當ViewHolder不夠用 => 生產新的
        @Override
        public Inner_RLViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.e("Inner_RLViewAdapter", "onCreateViewHolder");

            // RecyclerView外觀產生@_合併式
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_recycleview_childview, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;

            //// RecyclerView外觀產生@_拆開式
            //LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            //View _view = inflater.inflate(R.layout.note_recycleview_childview, parent, false);
        }

        /**
         * @自動元件_2
         */ //--當ViewHolder夠用 => 回收舊的_並設定子元件功能
        @Override
        public void onBindViewHolder(Inner_RLViewAdapter.MyViewHolder holder, final int position) {

            role_id = mylistData_role_Object.get(position).getRole_id();
            role_name = mylistData_role_Object.get(position).getRole_name();
            role_gender = mylistData_role_Object.get(position).getRole_gender();
            role_career = mylistData_role_Object.get(position).getRole_career();


            /**@顯示元件內容*/
            //獲取列表資料mylistData的位置將其值設定在子元件上
            holder.childview.setText("帳號: " + role_id + "   姓名: " + role_name + "\n   性別: " + role_gender + "   職業: " + role_career);

            /**@設定元件動作*/
            //當子元件被點擊時
            holder.childview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        //mylistData_role_Object
                        role_id = mylistData_role_Object.get(position).getRole_id();
                        role_name = mylistData_role_Object.get(position).getRole_name();
                        role_gender = mylistData_role_Object.get(position).getRole_gender();
                        role_career = mylistData_role_Object.get(position).getRole_career();
                        career_level = mylistData_career_Object.get(position).getCareer_level();
                        career_skill = mylistData_career_Object.get(position).getCareer_skill();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //mylistData_career_Object

                    Log.e("onClick", "帳號: " + role_id + "    姓名: " + role_name +
                            "性別: " + role_gender + "    職業: " + role_career + "    編號: " + position);


                    Bundle bundle = new Bundle();            /* 通过Bundle对象存储需要传递的数据 */
                    bundle.putString("pos", String.valueOf(position));
                    bundle.putString("role_id", role_id);
                    bundle.putString("role_name", role_name);
                    bundle.putString("role_gender", role_gender);
                    bundle.putString("role_career", role_career);
                    bundle.putString("career_level", career_level);
                    bundle.putString("career_skill", career_skill);
                    bundle.putSerializable("listData_role_Object", mylistData_role_Object);
                    bundle.putSerializable("listData_career_Object", mylistData_career_Object);

//                    ArrayList role_Object_list = new ArrayList();
//                    ArrayList career_Object_list = new ArrayList();
//                    role_Object_list.add(listData_role_Object);
//                    career_Object_list.add(listData_career_Object);
//                    bundle.putParcelableArrayList("listData_role_Object", role_Object_list);
//                    bundle.putParcelableArrayList("listData_career_Object ", career_Object_list);

//                    bundle.putString("mylistData_id", mylistData_id.get(position));
//                    bundle.putString("mylistData_name", mylistData_name.get(position));
//                    bundle.putString("mylistData_age", mylistData_age.get(position));
                    Intent intent = new Intent(MainActivity.this, NOTE_Role_Enterpage.class);
                    intent.putExtras(bundle);                /*把bundle对象assign给Intent*/
                    startActivity(intent);  // startActivityForResult(intent,0);

                }
            });

            /**@設定元件動作*/
            //當子元件被點擊時
            holder.delete_childview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("刪除點擊", "進行刪除__" + String.valueOf(position));
                    mylistData_role_Object.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                    Log.e("刪除完畢_", "刪除完畢__" + String.valueOf(position));
//                    mylistData_role_Object.remove(position).getRole_name();
//                    mylistData_role_Object.remove(position).getRole_gender();
//                    mylistData_role_Object.remove(position).getRole_career();
//                    mylistData_career_Object.remove(position).getCareer_level();
//                    mylistData_career_Object.remove(position).getCareer_skill();

                    for (int reset_idx = 0; reset_idx < mylistData_role_Object.size(); reset_idx++) {
                        if (reset_idx > position) {
                            mylistData_role_Object.get(reset_idx).setPos(String.valueOf(reset_idx - 1));
                            Log.e("刪除後項重排", mylistData_role_Object.get(reset_idx - 1).getPos());
//                            notifyItemRemoved(reset_idx-1);
                            notifyItemRangeChanged(reset_idx - 1, getItemCount());
                        }
                    }

                    for (int reset_idx = 0; reset_idx < mylistData_role_Object.size(); reset_idx++) {
                        Log.e("完畢_", mylistData_role_Object.get(reset_idx).getPos());
                    }


//
//                    listData_role_Object.get(reset_idx).setPos(nPos);
//                    listData_role_Object.get(reset_idx).setRole_id(nRole_id);
//                    listData_role_Object.get(reset_idx).setRole_name(nRole_name);
//                    listData_role_Object.get(reset_idx).setRole_gender(nRole_gender);
//                    listData_role_Object.get(reset_idx).setRole_career(nRole_career);
//                    listData_career_Object.get(reset_idx).setCareer_level(nCareer_level);
//                    listData_career_Object.get(reset_idx).setCareer_skill(nCareer_skill);
                }
            });

        }

        /**
         * @自動元件_3
         */ //--計算資料數量
        @Override
        public int getItemCount() {
            Log.e("getItemCount", String.valueOf(mylistData_role_Object.size()));
            /**@注意>>>>*/
            /**@自動生成為_return_0*/
            /**@若此處資料數量設為零，適配器將不會執行*/
//            return mylistData_id.size();
            return mylistData_role_Object.size();
        }

        /**
         * 建立ViewHolder傳入資料所需的constructor(建構子)與子元件
         */

        /**
         * @手動元件
         */ //--將外傳進來的list_data設定到適配器內部的mylist_data
//        private ArrayList<String> mylistData_id;
//        private ArrayList<String> mylistData_name;
//        private ArrayList<String> mylistData_age;
        private ArrayList<Role> mylistData_role_Object;
        private ArrayList<Career> mylistData_career_Object;

        public Inner_RLViewAdapter(ArrayList<Role> mylistData_role_Object, ArrayList<Career> mylistData_career_Object) {/*ArrayList<String> listData_id, ArrayList<String> listData_name, ArrayList<String> listData_age*/
//            this.mylistData_id = listData_id;
//            this.mylistData_name = listData_name;
//            this.mylistData_age = listData_age;
            this.mylistData_role_Object = mylistData_role_Object;
            this.mylistData_career_Object = mylistData_career_Object;
        }


        /**
         * @手動元件
         */ //-- 設置childview(列表子元件)
        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView childview; //此元件變數與onBindViewHolder共用
            TextView delete_childview;

            public MyViewHolder(View itemView) {
                super(itemView); /*保存起來的 view 會放在 itemView 裡面*/
                childview = (TextView) itemView.findViewById(R.id.childview);
                delete_childview = (TextView) itemView.findViewById(R.id.delete_childview);

            }
        }
    }


    //
    //
    //
    //
    //_________________________________________________________________________________
    //_________________________________________________________________________________
    //
    //
    //
    //

//    public ItemTouchHelper(ItemTouchHelper.Callback callback) {
//        mCallback = callback;
//    }


//    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);
//
//    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

    /**
     * @拖曳滑動功能
     */

//0则不执行拖动或者滑动
    ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();     //得到拖动ViewHolder的position
            int toPosition = target.getAdapterPosition();           //得到目标ViewHolder的position

            /**@由前往後移動*/
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {   //分别把中间所有的item的位置重新交换
                    Collections.swap(listData_role_Object, i, i + 1);
                }
            }
            /**@由後往前移動*/
            else {
                for (int i = fromPosition; i > toPosition; i--) {   //分别把中间所有的item的位置重新交换
                    Collections.swap(listData_role_Object, i, i - 1);
                }
            }
            Inner_RLViewAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            listData_role_Object.remove(position);
            Inner_RLViewAdapter.notifyItemRemoved(position);
        }
    };

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);


//    ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback(recyclerViewAdatper);
//    itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
//itemTouchHelper.attachToRecyclerView(recyclerView);

//    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

//    ItemDragHelperCallback callback=new ItemDragHelperCallback();
//    ItemTouchHelper helper=new ItemTouchHelper(callback);
//itemHelper.attachToRecyclerView(top_recyclerView);


//
//    //0则不执行拖动或者滑动
//    ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT) {
//        /**
//         * @param recyclerView
//         * @param viewHolder 拖动的ViewHolder
//         * @param target 目标位置的ViewHolder
//         * @return
//         */
//        @Override
//        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//            int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
//            int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
//            if (fromPosition < toPosition) {
//                //分别把中间所有的item的位置重新交换
//                for (int i = fromPosition; i < toPosition; i++) {
//                    Collections.swap(datas, i, i + 1);
//                }
//            } else {
//                for (int i = fromPosition; i > toPosition; i--) {
//                    Collections.swap(datas, i, i - 1);
//                }
//            }
//            mAdapter.notifyItemMoved(fromPosition, toPosition);
//            //返回true表示执行拖动
//            return true;
//        }
//
//        @Override
//        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//            int position = viewHolder.getAdapterPosition();
//            datas.remove(position);
//            mAdapter.notifyItemRemoved(position);
//        }
//
//        @Override
//        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//                //滑动时改变Item的透明度
//                final float alpha = 1 - Math.abs(dX) / (float)viewHolder.itemView.getWidth();
//                viewHolder.itemView.setAlpha(alpha);
//                viewHolder.itemView.setTranslationX(dX);
//            }
//        }
//
//    };
//    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);


    //
    //
    //
    //
    //_________________________________________________________________________________
    //_________________________________________________________________________________
    //
    //
    //
    //


    public void onClick(View v) {
        // Perform action on click
        switch (v.getId()) {
            case R.id.btn_add_role:
                Toast toast = Toast.makeText(this, "開始角色創立", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 300);
                toast.show();


                /**@找出矩陣內最大的ID編號*/
                String getLastPos = listData_role_Object.get(listData_role_Object.size() - 1).getPos();
                String getLastID = listData_role_Object.get(listData_role_Object.size() - 1).getRole_id();
                Log.e("getLastPos", getLastPos);
                Log.e("getLastID", getLastID);
                /**@最大ID編號進行重新組構*/
//                getLastID

                String[] tokens = getLastID.split("@");
/**@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
//                for (String token : tokens) { //FOREACH列出全部的分割字元
//                    Log.e("token", token); }
//                Log.e("tokens", tokens[0]);
//                Log.e("tokens", tokens[1]);
/**@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
                String newtokens = tokens[1].replaceFirst("^0*", ""); //處理
                int Int_token = Integer.parseInt(newtokens); //處理完畢額外轉成整數

//              Int_token = Integer.parseInt(String.valueOf(tokens[1]));
//              Log.e("Int_token", String.format("%d",Int_token));


                String pos;
                role_id = "@" + String.format("%04d", Int_token+1);
                pos = String.valueOf(listData_role_Object.size());

                Bundle bundle = new Bundle();            /* 通过Bundle对象存储需要传递的数据 */
                //放入本次登入之隨機假資料
                bundle.putSerializable("listData_role_Object", listData_role_Object);
                bundle.putSerializable("listData_career_Object", listData_career_Object);
                //配置給新角色ID和列表位置
                bundle.putString("role_id", role_id);
                bundle.putString("pos", pos);

                Intent intent3 = new Intent(MainActivity.this, Note_Create_New_Role_Enterpage.class);
                intent3.putExtras(bundle);
                startActivity(intent3);  // startActivityForResult(intent,0);
                break;
//            case R.id.buttonstop:
//                //Stop MediaPlayer
//                break;
        }
    }
}

//                for (pos = 0; pos < listData_role_Object.size(); pos++) {
//                    role_id = listData_role_Object.get(pos).setRole_id();
//                    role_name = listData_role_Object.get(pos).getRole_name();
//                    role_gender = listData_role_Object.get(pos).getRole_gender();
//                    role_career = listData_role_Object.get(pos).getRole_career();
//                }

//                Log.e("onClick", "帳號: " + role_id + "    姓名: " + role_name +
//                        "性別: " + role_gender + "    職業: " + role_career + "    編號: " + pos);


/**
 * @＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
 */
//        File directory = getFilesDir(); //or getExternalFilesDir(null); for external storage
//        File file = new File(directory, fileName);
//
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//            fos.write(IDNum.getBytes());
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//=========================================================
//=========================================================
//        FileOutputStream fos1 = null;
//        FileOutputStream fos2 = null;
//        BufferedOutputStream bos = null;
//        DataOutputStream dos = null;
//        File file;
//
//        FileOutputStream fos = null;
//        FileInputStream fis = null;
//        File file1;
//
//        //Cache 嘗試存入
//        Log.e("~~~~~~~~~~", "javenjaven" + getCacheDir());
//        try {
//
//            //写文件
//            file = File.createTempFile("demo1", ".txt");
////            File file = new File("demo1.txt");//抽象一个1.txt的文件，处理文件名或者路径
//            //构造两个FileOutPutStream的实例对象，先定义一个File类文件的做法比较规范，通用
//            fos1 = new FileOutputStream(file, false);//true表示如果文件已经存在，没执行程序一次便往文件追加一次内容，否则会每一次的执行结果会覆盖文件上一次执行结果
//            bos = new BufferedOutputStream(fos1);//把fos1封装成具有缓冲功能的文件输出流
//            fos2 = new FileOutputStream("demo2.txt");//用一个String构造一个FileOutputStream对象，String为文件名，这种构造方法也可以有两个参数，同上
//            dos = new DataOutputStream(fos2);//DataOutputStream提供了写入任意对象的能力
//
//            String s1 = "How are you?";
//            String s2 = "Fine.Thanks!";
//            dos.writeBytes(s1 + "   " + s2);
//            fos1.write(s1.getBytes());//把字符串s1以字节流形式写入1.txt文件中
//            fos2.write(s2.getBytes());//把字符串s2以字节形式流写入2.txt文件中
//            fos1.write(s2.getBytes());
//
//            //===========================================================//
//            //===========================================================//
//
//            //读出文件内容
//            fis = new FileInputStream(file);
//            byte[] b = new byte[fis.available()];//创建一个字节数组，数组长度与file中获得的字节数相等
//            while (fis.read(b) != -1) {
//                Log.e("5555555", new String(b) + "   打印出从file文件读取的内容");
////                System.out.println(new String(b) + "   打印出从file文件读取的内容");//打印出从file文件读取的内容
//            }
//
//            //复制文件
//            //用“.”把file的文件名分隔开
//            String[] s = file.getName().split("\\.");//因为"."在正则表达式中有特殊的含义，使用的时候必须进行转义。
//            String copyFName = s[0] + "_" + "copy" + "." + s[1];
//            file1 = new File(copyFName);
//            fos = new FileOutputStream(file1, true);
//            fos.write(b);//把刚刚从file 文件读出的内容写入副本file1


//===========================================================//
//            File file = File.createTempFile("TempFile", ".test");
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write("Temp_Content".getBytes());
//            fileOutputStream.close();
//===========================================================//
//=========================================================
//=========================================================
//        } catch (IOException e) {
//            Log.e("5555555", e.toString());
//
//            e.printStackTrace();
//        }
/**@＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊*/


/**@＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊*/
//        TCache使用方法
//        获得TCache对象, 默认是存储在 context.getCacheDir() 的 tcache 目录下的.
//                可以通过String类型的key缓存你想要缓存的数据, 如Bitmap, JSONObject, String,byte[] 等等.
//        可以获得对应key缓存的数据是否过期
//                可以清除指定key对应的缓存或清除所有缓存
//        可以指定自定义的缓存目录, 并管理缓存目录中文件的数量和缓存目录的空间.
//                可以通过自定义的对象字节转换器, 缓存你想缓存的对象数据, 如Samples中缓存Intent

//        TCache获取方法和接口介绍
//        1.获取TCache

//    //通过Context获取对象
//    public static TCache get(Context context)
//
//    //通过Context,并指定缓存的相对目录
//    public static TCache get(Context context, String relativeCacheDir)
//
//    //通过Context,缓存的相对目录,存储目录最多文件数,最大缓存目录空间
//    public static TCache get(Context context, String relativeCacheDir, int maxDiskTotalCount,
//                             int maxDiskTotalSpace)
//
//    ////通过Context,缓存的相对目录,存储目录最多文件数,最大缓存目录空间,指定缓存默认失效时间
//    public static TCache get(Context context, String relativeCacheDir, int maxDiskTotalCount,
//                             int maxDiskTotalSpace, int defCacheAge)
/**@＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊*/