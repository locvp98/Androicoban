package com.dell.lab6han.sqliteDAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dell.lab6han.database.DBManager;
import com.dell.lab6han.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private DBManager dbManager;

    public NhanVienDAO(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void themnhanvien(NhanVien nhanVien){
        SQLiteDatabase db=dbManager.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DBManager.MA,nhanVien.getmMa());
        contentValues.put(DBManager.TENNV,nhanVien.getmTennv());
        contentValues.put(DBManager.LUONGNV,nhanVien.getMluong());

        db.insert(DBManager.TB_NhanVIEN,null,contentValues);
        db.close();
    }
    public List<NhanVien> getallnhanvien(){

        SQLiteDatabase db=dbManager.getWritableDatabase();
        List<NhanVien> nhanVienList=new ArrayList<>();
        String truyvan=" SELECT * FROM " + DBManager.TB_NhanVIEN;
        Cursor cursor=db.rawQuery(truyvan,null);

        if (cursor.moveToFirst()){
            do {
               String ma=cursor.getString(cursor.getColumnIndex(DBManager.MA));
                String ten=cursor.getString(cursor.getColumnIndex(DBManager.TENNV));
               String luong=cursor.getString(cursor.getColumnIndex(DBManager.LUONGNV));
                NhanVien nhanVien=new NhanVien(ma,ten,luong);
                nhanVienList.add(nhanVien);

            }while (cursor.moveToNext());
        }
        db.close();

        return nhanVienList;
    }

    public void deletenhanvien(String nhanvien){
        SQLiteDatabase sqLiteDatabase=dbManager.getWritableDatabase();
        sqLiteDatabase.delete(DBManager.TB_NhanVIEN,DBManager.MA+"=?",new String[]{nhanvien});
        sqLiteDatabase.close();
    }
    public void updatenhanvien(NhanVien nhanVien){
        SQLiteDatabase db=dbManager.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DBManager.MA,nhanVien.getmMa());
        values.put(DBManager.TENNV,nhanVien.getmTennv());
        values.put(DBManager.LUONGNV,nhanVien.getMluong());

        db.update(DBManager.TB_NhanVIEN,values,DBManager.MA+"=?",new String[]{nhanVien.getmMa()});
        db.close();

    }

}
