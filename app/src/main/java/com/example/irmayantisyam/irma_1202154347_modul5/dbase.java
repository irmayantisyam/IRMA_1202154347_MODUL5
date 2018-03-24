package com.example.irmayantisyam.irma_1202154347_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by IRMAYANTI SYAM on 3/23/2018.
 */
//membuat class database
public class dbase extends SQLiteOpenHelper{
    //mendeklarasikan variabel-variabel
    Context con;
    SQLiteDatabase data;
    //membuat nama databasenya
    public static final String db_name = "todoosas.db";
    //membuat nama tabelnya
    public static final String table_name = "todo";
    //membuat nama nama kolom yang menyimpan datanya
    public static final String col_1 = "nama";
    public static final String col_2 = "descr";
    public static final String col_3 = "prio";

    public dbase(Context context) { //membuat class database
        super(context, db_name, null, 1);
        this.con = context;
        data = this.getWritableDatabase(); //perintah yang mengolah database sehingga dalam pembaruannya tidak akan berlangsung lama
        //karena sudah mendapat repositori data
        //mengatur primary key, jumlah karakter, dan jenis input yang digunakan
        data.execSQL("create table if not exists "+table_name+" (nama varchar(50) primary key, descr varchar(50), prio varchar(5))");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //membuat database dengan menggunakan SQLite sesuai dengan primary key, jumlah da jenis karakter yang diinputkan
        sqLiteDatabase.execSQL("create table if not exists "+table_name+" (nama varchar(50) primary key, descr varchar(50), prio varchar(5))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //perintah ini merupakan penanda bahwa database ini hanya cache online
        sqLiteDatabase.execSQL("drop table if exists "+table_name);
        //digunakan untuk sekedar membuang data dan memulai kembali
        onCreate(sqLiteDatabase);
    }
    public boolean insertdata(itemtodo satuan) { //method yang memasukkan data
        ContentValues cv = new ContentValues();
        //membuat peta kolom sehingga data yang dimasukkan sesuai dengan kolom
        cv.put(col_1, satuan.getName());
        cv.put(col_2, satuan.getDescription());
        cv.put(col_3, satuan.getPriority());
        long result = data.insert(table_name, null, cv);
        //memasukkan baris baru dan mengembalikan nilai kunci utama dari baru baru tersebut
        if (result == -1) { //pengkodisian untuk pengecekan
            return false;
        } else {
            return true;
        }
    }
    public boolean deletedata(String name){ //method untuk menghapus data
        return data.delete(table_name, col_1+"=\""+name+"\"", null)>0;
        //mengembalikan data berupa data dari database telah dihapus
    }
    public void getAllItems(ArrayList<itemtodo> list){//mendapatkan semua item yang diinputkan
        Cursor cr = this.getReadableDatabase().rawQuery("select nama, descr, prio from "+table_name, null);
        while(cr.moveToNext()){
            list.add(new itemtodo(cr.getString(0), cr.getString(1), cr.getString(2))); //menambahkan item yang telah diset pada array
        }
    }
    public void ClearTabel(){ //mengahpus tabel
        data.execSQL("delete from "+table_name);
    }
}
