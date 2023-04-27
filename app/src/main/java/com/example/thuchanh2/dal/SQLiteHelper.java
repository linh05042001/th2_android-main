package com.example.thuchanh2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.thuchanh2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final  String DATABASE_NAME = "CongViec.db";
    private static int DATABASE_VERSION =1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE items("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ten TEXT,noidung TEXT, trangthai INTEGER,ngaydenhan TEXT,hinhthuc INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    //get all order by date desc
    public List<Item> getAll(){
        List<Item> list = new ArrayList<>();

        SQLiteDatabase st = getReadableDatabase();
        String orderBy = "date(ngaydenhan) ASC";
        Cursor rs = st.query("items", null, null,
                null, null, null, orderBy);
        while (rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String trangthai = rs.getString(3);
            String  ngaydenhan = rs.getString(4);
            String hinhthuc = rs.getString(5);

            list.add(new Item(id,ten,noidung,trangthai,ngaydenhan,hinhthuc));

        }

        return list;
    }
    // add
    public  long addItem(Item i){
        ContentValues values = new ContentValues();
        values.put("ten", i.getTen());
        values.put("noidung", i.getNoidung());
        values.put("trangthai", i.getTrangthai());
        values.put("ngaydenhan", i.getNgaydenhan());
        values.put("hinhthuc", i.getHinhthuc());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return  sqLiteDatabase.insert("items", null, values);
    }
    // lay cac item theo date
//    public  List<Item> getByDate(String date){
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date like ?";
//        String[] whereArgs = {date};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items", null, whereClause
//        , whereArgs, null, null, null);
//        while(rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String title = rs.getString(1);
//            String  category= rs.getString(2);
//            String price = rs.getString(3);
//
//            list.add(new Item(id,title,category,price,date));
//        }
//
//        return list;
//
//    }
    //update
    public int update(Item i){
        ContentValues values = new ContentValues();
        values.put("ten", i.getTen());
        values.put("noidung", i.getNoidung());
        values.put("trangthai", i.getTrangthai());
        values.put("ngaydenhan", i.getNgaydenhan());
        values.put("hinhthuc", i.getHinhthuc());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id= ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        return  sqLiteDatabase.update("items",  values,whereClause,whereArgs);

    }
    //delete
    public  int delete(int id){
        String whereClause = "id= ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items", whereClause, whereArgs);

    }

    public  List<Item> searchByTen(String key){
        List<Item> list = new ArrayList<>();
        String whereClause = "ten like ?";
        String[] whereArgs = {"%"+key+"%"};
        String orderBy = "date(ngaydenhan) ASC";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause
                , whereArgs, null, null, orderBy);
        while(rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String trangthai = rs.getString(3);
            String  ngaydenhan = rs.getString(4);
            String hinhthuc = rs.getString(5);
;
            list.add(new Item(id,ten,noidung,trangthai,ngaydenhan,hinhthuc));
        }

        return list;

    }

    public  List<Item> searchByNoiDung(String key){
        List<Item> list = new ArrayList<>();
        String whereClause = "noidung like ?";
        String[] whereArgs = {"%"+key+"%"};
        String orderBy = "date(ngaydenhan) ASC";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause
                , whereArgs, null, null, orderBy);
        while(rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String trangthai = rs.getString(3);
            String  ngaydenhan = rs.getString(4);
            String hinhthuc = rs.getString(5);
            list.add(new Item(id,ten,noidung,trangthai,ngaydenhan,hinhthuc));
        }

        return list;

    }
    public List<String> ThongKe() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT trangthai, COUNT(*) FROM items GROUP BY trangthai ORDER BY COUNT(*) DESC", null);
        if (cursor.moveToFirst()) {
            do {
                int status = cursor.getInt(0);
                int count = cursor.getInt(1);
                switch (status) {
                    case 0:
                        list.add("Chưa thực hiện: " + count);
                        break;
                    case 1:
                        list.add("Đang thực hiện: " + count);
                        break;
                    case 2:
                        list.add("Hoàn thành: " + count);
                        break;
                }
            } while (cursor.moveToNext());
        }

        return list;
    }

//    public  List<Item> searchByCategory(String category){
//        List<Item> list = new ArrayList<>();
//        String whereClause = "category like ?";
//        String[] whereArgs = {category};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items", null, whereClause
//                , whereArgs, null, null, null);
//        while(rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String t = rs.getString(1);
//            String  c= rs.getString(2);
//            String p = rs.getString(3);
//            String d = rs.getString(4);
//            ;
//            list.add(new Item(id,t,c,p,d));
//        }
//
//        return list;
//
//    }
//
//    public  List<Item> searchByDateFromTo(String from,String to){
//        List<Item> list = new ArrayList<>();
//        String whereClause = "date BETWEEN ? AND ?";
//        String[] whereArgs = {from.trim(),to.trim()};
//        SQLiteDatabase st = getReadableDatabase();
//        Cursor rs = st.query("items", null, whereClause
//                , whereArgs, null, null, null);
//        while(rs!=null && rs.moveToNext()){
//            int id = rs.getInt(0);
//            String t = rs.getString(1);
//            String  c= rs.getString(2);
//            String p = rs.getString(3);
//            String d = rs.getString(4);
//            ;
//            list.add(new Item(id,t,c,p,d));
//        }
//
//        return list;
//
//    }

}
