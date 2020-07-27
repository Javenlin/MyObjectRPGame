package com.example.e490318.myobjectrpgame;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by e490318 on 2018/4/23.
 */

/*
//     “@00_Newcomer初心者”
//     “@01_Fighter勇者” “@02_Mage法師”“@03_Archer弓箭手”
//     “@04_Paladin騎士” “@05_Warrior狂戰士”““@06_Assassin刺客”
//        @07_Specialist技師”

//     “@01_Fighter勇者_重擊” “@02_Mage法師_火球”“@03_Archer弓箭手_毒箭”
//     “@04_Paladin騎士_回血” “@05_Warrior狂戰士_狂化”““@06_Assassin刺客_暴擊”
//        @07_Specialist技師_砲擊”
*/

public class Career extends Role implements Parcelable {


    Context context;
    String Role_id;
    String career_level;
    String career_name;
    String career_skill;


    //    2. 設計建構子 Constructor

    public Career(Context context, String Role_id, String career_name, String career_level, String career_skill) {
        super();
//        super(context, id, name, skill);

        this.context = context;
        this.Role_id = Role_id;
        this.career_name = career_name;
        this.career_level = career_level;
        this.career_skill = career_skill;
    }

//覆蓋與重載___DEFAULT__CONSTRUCTOR
//    public Career(Class c_FromClass, Context c_Context, String c_id, String c_name, String c_skill) {
//
//    }

    public Career() {

    }


    //    3. 設計值取放規則 Getter/Setter


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getRole_id() {
        return Role_id;
    }

    public void setRole_id(String role_id) {
        Role_id = role_id;
    }

    public String getCareer_level() {
        return career_level;
    }

    public void setCareer_level(String career_level) {
        this.career_level = career_level;
    }

    public String getCareer_name() {
        return career_name;
    }

    public void setCareer_name(String career_name) {
        this.career_name = career_name;
    }

    public String getCareer_skill() {
        return career_skill;
    }

    public void setCareer_skill(String career_skill) {
        this.career_skill = career_skill;
    }

    /**@-------------------------@*/
    /**@__補充：_動作API__@*/
    /**@-------------------------@*/

    /**
     * @Smite_重擊
     */
    public Void SKILL_Smite() {
        Toast.makeText(context, "勇者_使用重擊!!!", Toast.LENGTH_LONG).show();
        Log.i("重擊技能", "勇者_使用重擊!!!");
        return null;
    }

    /**
     * @Fireball_火球
     */
    public void SKILL_Fireball() {
        Toast.makeText(context, "法師_使用火球!!!", Toast.LENGTH_LONG).show();
        Log.i("火球技能", "法師_使用火球!!!");
    }

    /**
     * @PoisonArrow_毒箭
     */
    public void SKILL_PoisonArrow() {
        Toast.makeText(context, "弓箭手_使用毒箭!!!", Toast.LENGTH_LONG).show();
        Log.i("毒箭技能", "弓箭手_使用毒箭!!!");
    }

    /**
     * @Curing_回血
     */
    public void SKILL_Curing() {
        Toast.makeText(context, "騎士_使用回血!!!", Toast.LENGTH_LONG).show();
        Log.i("回血技能", "騎士_使用回血!!!");
    }

    /**
     * @Madness_狂化
     */
    public void SKILL_Madness() {
        Toast.makeText(context, "狂戰士_使用狂化!!!", Toast.LENGTH_LONG).show();
        Log.i("重擊技能", "狂戰士_使用狂化!!!");
    }

    /**
     * @Lurking_潛伏
     */
    public void SKILL_Lurking() {
        Toast.makeText(context, "刺客_使用潛伏!!!", Toast.LENGTH_LONG).show();
        Log.i("潛伏技能", "刺客_使用潛伏!!!");
    }

    /**
     * @Shelling_砲擊
     */
    public void SKILL_Shelling() {
        Toast.makeText(context, "技師_使用砲擊!!!", Toast.LENGTH_LONG).show();
        Log.i("砲擊技能", "技師_使用砲擊!!!");
    }

    /**
     * @DoNotThing__甚麼都不做
     */
    public void SKILL_DoNotThing() {
        Toast.makeText(context, "可是我甚麼技能都不會!!!", Toast.LENGTH_LONG).show();
        Log.i("無技能", "初心者_甚麼技能都不會!!!");
    }

    //TODO Parcelable Method !!!
    /**@以下為新增的Parcelable部分*/

    // 讀取參數，參數順序要和建構子一樣
    protected Career(Parcel in) {
        Role_id = in.readString();
        career_name = in.readString();
        career_level = in.readString();
        career_skill = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // 寫入參數，參數順序要和建構子一樣
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Role_id);
        parcel.writeString(career_name);
        parcel.writeString(career_level);
        parcel.writeString(career_skill);
    }

    public static final Creator<Career> CREATOR = new Creator<Career>() {
        @Override
        public Career createFromParcel(Parcel in) {
            return new Career(in);
        }

        @Override
        public Career[] newArray(int size) {
            return new Career[size];
        }
    };


//     “@01_Fighter勇者_重擊” “@02_Mage法師_火球”“@03_Archer弓箭手_毒箭”
//     “@04_Paladin騎士_回血” “@05_Warrior狂戰士_狂化”““@06_Assassin刺客_潛伏”
//        @07_Specialist技師_砲擊”


}


