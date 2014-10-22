package mobi.soft4you.passgene;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Работа с базой
 */
public final class DB
{
    static final String DB_NAME = "passgene_db";
    static final int DB_VERSION = 1;
    
    private final Context mContext;
    DBHelper mDBHelper;
    SQLiteDatabase mDB;
    
    public DB(Context context)
    {
        mContext = context;
    }
    
    public void open()
    {
        mDBHelper = new DBHelper(mContext, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }
    
    public void close()
    {
        if (mDBHelper != null)
        {
            mDBHelper.close();
            mDBHelper = null;
        }
    }
    
    public Cursor getSites()
    {
        return mDB.query("t_sites", null, null, null, null, null, null);
    }
    
    public Cursor getLogins()
    {
        return mDB.query("t_logins", null, null, null, null, null, null);
    }
    
    class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper(Context context, String name, CursorFactory factory,
                int version)
        {
            super(context, name, factory, version);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("create table t_logins (_id integer primary key autoincrement,  a_name text);");
            db.execSQL("create table t_sites (_id integer primary key autoincrement, a_name text);");
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
        }
        
    }

    public void forgetSite(String site)
    {
        mDB.delete("t_sites", "a_name=?", new String[]{site});
    }

    public void rememberSite(String site)
    {
        forgetSite(site);
        ContentValues cv = new ContentValues();
        cv.put("a_name", site);
        long id = mDB.insert("t_sites", null, cv);
        Log.i(DB_NAME, "" + id);
        
    }
    public void forgetLogin(String login)
    {
        mDB.delete("t_logins", "a_name=?", new String[]{login});
    }

    public void rememberLogin(String login)
    {
        forgetLogin(login);
        ContentValues cv = new ContentValues();
        cv.put("a_name", login);
        long id = mDB.insert("t_logins", null, cv);
        Log.i(DB_NAME, "" + id);
    }

    public void clearData(boolean clearSites, boolean clearLogins)
    {
        if (clearSites)
            mDB.delete("t_sites", null, null);
        
        if (clearLogins)
            mDB.delete("t_logins", null, null);
    }

    public boolean existsSite(String site)
    {
        Cursor c = null;
        try
        {
            c = mDB.query("t_sites", null, "a_name=?", new String[]{site}, null, null, null);
            return c.getCount() > 0;
        }
        finally
        {
            if (c != null)
                c.close();
        }
    }

    public boolean existsLogin(String login)
    {
        Cursor c = null;
        try
        {
            c = mDB.query("t_logins", null, "a_name=?", new String[]{login}, null, null, null);
            return c.getCount() > 0;
        }
        finally
        {
            if (c != null)
                c.close();
        }
    }
}
