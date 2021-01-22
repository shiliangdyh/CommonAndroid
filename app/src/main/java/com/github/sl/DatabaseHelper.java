package com.github.sl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.github.commonlib.utils.LogUtils;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "test.db";
    private static DatabaseHelper databaseHelper;

    public static final String TABLE_TEST = "Test";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context){
        if (null == databaseHelper) {
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("create table ");
        sb.append(TABLE_TEST);
        sb.append(" ( ");
//        sb.append(" ( ").append(BaseColumns._USER_PHONENO).append(" text ,");
        sb.append(UserColumns._ID);
        sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append(UserColumns.NAME);
        sb.append(" text not null,");
        sb.append(UserColumns.JOB);
        sb.append(" text ,");
        sb.append(UserColumns.AGE);
        sb.append(" INTEGER );");
        db.execSQL(sb.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.d(TAG, "onUpgrade: oldVersion=" + oldVersion + " ,newVersion=" + newVersion);
    }

    public interface UserColumns extends BaseColumns{
        String NAME = "name";
        String JOB = "job";
        String AGE = "age";
    }
}
