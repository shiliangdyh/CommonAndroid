package com.github.sl;

import android.database.Cursor;

public class User {
    private String name;
    private String psw;
    private int age;

    public static User readCursor(Cursor cursor) {
        User user = new User();
        user.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.UserColumns.NAME)));
        user.setPsw(cursor.getString(cursor.getColumnIndex(DatabaseHelper.UserColumns.PSW)));
        user.setAge(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.UserColumns.AGE)));
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
