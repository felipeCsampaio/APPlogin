package br.com.saolucas.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static String nome = "BancoDados.db";
    private static int versao=1;
    public DBHelper(Context context){
        super(context, nome,null,versao);
}
 @Override
 public void onCreate(SQLiteDatabase db) {
     String str = "CREATE TABLE utilizador(username TEXT PRIMARY\n" +
             "KEY, password TEXT);";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion){
        db.execSQL("DROP TABLE IF EXISTS utilizador;");
        onCreate(db);
    }
    public long criarUtilizador(String userName, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",userName);
        cv.put("password",password);
        long result = db.insert("utilizador", null,cv);
        return result;
    }
    public String validarLogin(String userName, String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from utilizador where\n"+
                "username=? and password=?", new String[] {userName,password});
        if(c.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }

}
