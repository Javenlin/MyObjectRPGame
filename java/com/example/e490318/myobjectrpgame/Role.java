package com.example.e490318.myobjectrpgame;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by e490318 on 2018/4/23.
 */

public class Role implements Parcelable {

    //    1. 設計屬性

    String pos;
    String role_id;
    String role_name;
    String role_gender;
    String role_career;


//    2. 設計建構子 Constructor

//    /**
//     * @繼承_Career_建構子
//     */
//    public Role(Context context, String id, String name, String skill) {
//        super(context, id, name, skill);
//    }

    /**
     * @_Role_建構子
     */
    public Role(String pos,String role_id, String role_name, String role_gender, String role_career) {
//        super();    //覆蓋與重載
        this.pos = pos;
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_gender = role_gender;
        this.role_career = role_career;
    }

    public Role() {

    }


//    /**
//     * @繼承_Career_建構子
//     */
//    public Role(Context Context, String id, String name, String skill) {
//        super(Context, id, name, skill);
//    }
//
//    /**
//     * @_Role_建構子
//     */
//    public Role(Context context,String id, String name, String gender) {
//        super();    //覆蓋與重載
//        this.Role_context = context;
//        this.Role_id = id;
//        this.Role_Name = name;
//        this.Role_Gender = gender;
//    }


    //    3. 設計值取放規則 Getter/Setter

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_gender() {
        return role_gender;
    }

    public void setRole_gender(String role_gender) {
        this.role_gender = role_gender;
    }

    public String getRole_career() {
        return role_career;
    }

    public void setRole_career(String role_career) {
        this.role_career = role_career;
    }


    /**@以下為新增的Parcelable部分 */

    // 讀取參數，參數順序要和建構子一樣
    protected Role(Parcel in) {
        pos = in.readString();
        role_id = in.readString();
        role_name = in.readString();
        role_gender = in.readString();
        role_career = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // 寫入參數，參數順序要和建構子一樣
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pos);
        parcel.writeString(role_id);
        parcel.writeString(role_name);
        parcel.writeString(role_gender);
        parcel.writeString(role_career);
    }

    public static final Creator<Role> CREATOR = new Creator<Role>() {
        @Override
        public Role createFromParcel(Parcel in) {
            return new Role(in);
        }

        @Override
        public Role[] newArray(int size) {
            return new Role[size];
        }
    };
}
