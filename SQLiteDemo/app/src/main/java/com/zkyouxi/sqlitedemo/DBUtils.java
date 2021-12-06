package com.zkyouxi.sqlitedemo;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBUtils {
    private static com.zkyouxi.sqlitedemo.DBUtils dbUtils;
    private SQLiteDatabase db;
    public String sql;
    private Class bClass;



    public static DBUtils getInstance(){
        if(dbUtils==null){
            dbUtils=new com.zkyouxi.sqlitedemo.DBUtils();
            return dbUtils;
        }
        return dbUtils;
    }
    public void creates(Context context,String sql){
        String path=context.getCacheDir().getPath()+"/sqlite.db";
        db=SQLiteDatabase.openOrCreateDatabase(path,null);
         db.execSQL(sql);
    }
    @SuppressLint("Range")
    public void query(String table,Class aClass,String a){
        Cursor cursor=db.query(table,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            bClass=aClass;
             int id=cursor.getInt(cursor.getColumnIndex("id"));
             //可在这里改变要查询的字段
             String aa=cursor.getString(cursor.getColumnIndex("a"));
            ArrayList<Class>list=new ArrayList<>();
            list.add(bClass);

        }
        if (cursor!=null){
            cursor.close();
        }
    }

    public int delete(int id,String table){
        int inde=db.delete(table,"id=?",new String[Integer.parseInt(String.valueOf(id))]);
        Log.e("--Main--","======删除了====="+inde);
        return inde;
    }

    public int modifyData(int id,ContentValues contentValues,String table){
        int index=db.update(table,contentValues,"id=?",new String[Integer.parseInt(String.valueOf(id))]);
        Log.e("--Main--","======修改了======"+index);
        return index;
    }
    public  long insertData(String table,ContentValues contentValues){
        long dataSize=db.insert(table,null,contentValues);
        Log.e("--Main--","修改成功");
        return dataSize;
    }
}
