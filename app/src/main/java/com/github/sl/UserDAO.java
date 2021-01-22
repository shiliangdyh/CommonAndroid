package com.github.sl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static void insert(Context context, User user) {
        ContentValues values = new ContentValues();
        bindAppValue(values, user);
        context.getContentResolver().insert(MyProvider.TEST_URI, values);
    }

    public static List<User> queryUsers(Context context) {
        List<User> users = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MyProvider.TEST_URI, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                User user = User.readCursor(cursor);
                users.add(user);
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        return users;
    }


    private static void bindAppValue(ContentValues values, User user) {
        values.put(DatabaseHelper.UserColumns.NAME, user.getName());
        values.put(DatabaseHelper.UserColumns.JOB, user.getJob());
        values.put(DatabaseHelper.UserColumns.AGE, user.getAge());
    }
}
