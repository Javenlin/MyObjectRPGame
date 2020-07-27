//package com.example.e490318.myobjectrpgame;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
///**
// * Created by e490318 on 2018/4/19.
// */
//
///**
// * @>>>>>____________外部適配器進階方式_______________________________________________
// */
//
//public class Outer_RLViewAdapter extends RecyclerView.Adapter<Outer_RLViewAdapter.MyViewHolder> {
//
//    private Class myNOTE_Recycleview;
//    private Context context;    //需要傳入context才能執行函式動作
//    //    private ArrayList<String> mylistData_id;
////    private ArrayList<String> mylistData_name;
////    private ArrayList<String> mylistData_age;
//    private ArrayList<UserInfo> mylistData_userinfo;
//    String age = "";
//    String id = "";
//    String name = "";
//
//
//    /**
//     * @物件化Get/Set取外部傳值
//     */
//    public void setAdapterData(Class NOTE_Recycleview, Context context, ArrayList<UserInfo> listData_userinfo) { //context 也能以單一方式傳入 ArrayList<String> listData_id, ArrayList<String> listData_name, ArrayList<String> listData_age
//        this.myNOTE_Recycleview = NOTE_Recycleview;
//        this.context = context;
////        this.mylistData_id = listData_id;
////        this.mylistData_name = listData_name;
////        this.mylistData_age = listData_age;
//        mylistData_userinfo = listData_userinfo;
////        Log.e("QQQQ", "" + mylistData_id + mylistData_name + mylistData_age);
//    }
//
////    /**@單一方式context外部傳值*/
////    public void setAdapter(Context context) {
////        this.context = context;
////    }
//    /**
//     * @@補充 原生抽象類別
//     * RecyclerView.Adapter　－－直接使用
//     * RecyclerView.ViewHolder　－－仍需另外設定
//     */
//
//    /**
//     * @@自動元件介紹
//     * 1)_onCreateViewHolder(ViewGroup, int) - 現有的 ViewHolder 不夠用，要求 Adapter 產生一個新的
//     * 2)_onBindViewHolder(ViewHolder, int)   - 重用之前產生的 ViewHolder，把特定位置的資料連結上去準備顯示
//     * 3)_getItemCount()                                 - Adapter 自己才知道如何儲存資料，所以它自己也才知道該怎麼數有多少資料
//     */
//
//    /**
//     * @@手動元件介紹
//     *  1)_系統liberary裡原本就提供一個原生類別 RecyclerView.ViewHolder，但我們還要對它進行加工與客製化，取名為MyViewHolder
//     *  2)_設置childview(列表子元件)
//     *  3)_將外傳進來的list_data設定到適配器內部的mylist_data
//     */
//
//
//    /**
//     * @自動元件_1
//     */ //--當ViewHolder不夠用 => 生產新的
//    @Override
//    public Outer_RLViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // RecyclerView外觀產生@_合併式
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_recycleview_childview, parent, false);
//        Outer_RLViewAdapter.MyViewHolder myViewHolder = new Outer_RLViewAdapter.MyViewHolder(view);
//        return myViewHolder;
//
//        //// RecyclerView外觀產生@_拆開式
//        //LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        //View _view = inflater.inflate(R.layout.note_recycleview_childview, parent, false);
//    }
//
//    /**
//     * @自動元件_2
//     */ //--當ViewHolder夠用 => 回收舊的_並設定子元件功能
//    @Override
//    public void onBindViewHolder(Outer_RLViewAdapter.MyViewHolder holder, final int position) {
//
////            RecyclerView.LayoutManager manager = RecyclerView.getLayoutManager();
////// manager.getItemCount();
////// manager.getChildCount();
////        for (int i = 0; i < manager.getChildCount();i++){
////        View view = manager.getChildAt(i);
////        }
//
////        final String age;
////        final String id;
////        final String name;
//        age = mylistData_userinfo.get(position).getAge();
//        id = mylistData_userinfo.get(position).getId();
//        name = mylistData_userinfo.get(position).getName();
//
//
//        /**@顯示元件內容*/
//        //獲取列表資料mylistData的位置將其值設定在子元件上
//        holder.childview.setText("帳號：" + id + " / 姓名：" + name + " / 年齡：" + age);
//
//        /**@設定元件動作*/
//        //當子元件被點擊時
//        holder.childview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                /**@測試*/
//                Log.e("JUMP", "JUMP");
//                Toast.makeText(context, "你點了第 " + position + " 項", Toast.LENGTH_SHORT).show();
//
//                /**@主要*/
//                Intent intent = new Intent(context, NOTE_Role_Enterpage.class);
//                Bundle bundle = new Bundle();            /* 通过Bundle对象存储需要传递的数据 */
//                bundle.putString("mylistData_id", id);
//                bundle.putString("mylistData_name", name);
//                bundle.putString("mylistData_age", age);
//                intent.putExtras(bundle);                /*把bundle对象assign给Intent*/
//                view.getContext().startActivity(intent);    // 方法_2: startActivityForResult(intent,0);
//
//                /**@參考:stackoverflow>> Unable to startActivity from Recycler View Adapter [duplicate] */
//                /** 外部startActivity異常報錯解決方式*/
//
//
//                /**@補充_1 字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
//                // bundle.putString("Name", "feng88724");
//                // bundle.putBoolean("Ismale", true);
//
//                /**@補充_2 @1_startActivity( )_@2_startActivityForResult( )*/
//                //startActivityForResult_額外有保存傳值的能力，當返回上一頁不會銷毀
//                //但是比較複雜後面再研究
//
//            }
//        });
//    }
//
//    /**
//     * @自動元件_3
//     */ //--計算資料數量
//    @Override
//    public int getItemCount() {
//
//        Log.e("Outer_RLViewAdapter", "getItemCount" );
////        + " / "+id+" / " + age+" / " + name
//        /**@自動生成為_return_0*/
//        /**@若此處資料數量設為零，適配器將不會執行*/
//        return mylistData_userinfo.size();
//    }
//
//    /**
//     * 建立ViewHolder傳入資料所需的constructor(建構子)與子元件
//     */
//
//    /**
//     * @手動元件(舊內部寫法)
//     */ //--將外傳進來的list_data設定到適配器內部的mylist_data
////        private ArrayList<String> mylistData_id;
////        private ArrayList<String> mylistData_name;
////        private ArrayList<String> mylistData_age;
//
////        public RLViewAdapter(ArrayList<String> listData_id, ArrayList<String> listData_name, ArrayList<String> listData_age) {
////            this.mylistData_id = listData_id;
////            this.mylistData_name = listData_name;
////            this.mylistData_age = listData_age;
////        }
//
//    /**
//     * @手動元件
//     */ //-- 設置childview(列表子元件)
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView childview; //此元件變數與onBindViewHolder共用
//
//        public MyViewHolder(View itemView) {
//            super(itemView); /*保存起來的 view 會放在 itemView 裡面*/
//            childview = itemView.findViewById(R.id.childview);
//        }
//    }
//
//
//}
//
//
////class Outer_RLViewAdapter {
////    private Context context;    //需要傳入context才能執行函式動作
////    private ArrayList<String> mylistData_id;
////    private ArrayList<String> mylistData_name;
////    private ArrayList<String> mylistData_age;
////
////
////    /**@物件化Get/Set取外部傳值*/
////    public void setAdapterData(Context context, ArrayList<String> listData_id, ArrayList<String> listData_name, ArrayList<String> listData_age) { //context 也能以單一方式傳入
////        this.context = context;
////        this.mylistData_id = listData_id;
////        this.mylistData_name = listData_name;
////        this.mylistData_age = listData_age;
////    }
////
//////    /**@單一方式context外部傳值*/
//////    public void setAdapter(Context context) {
//////        this.context = context;
//////    }
