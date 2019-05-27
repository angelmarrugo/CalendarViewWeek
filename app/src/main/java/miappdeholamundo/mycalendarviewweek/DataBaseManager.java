package miappdeholamundo.mycalendarviewweek;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by angel on 14/10/18.
 */

public abstract class DataBaseManager {

    private static final String TAG = DataBaseManager.class.getSimpleName();
    private Database helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        Log.d(TAG,"por aca");
        helper = new Database(context);
        db = helper.getWritableDatabase();
    }



    public void cerrar(){
        db.close();
    }

    abstract public void eliminarTodo();

    abstract void insertar_event(String id, String message, String reminder, String end, String day);

    public Database getHelper() {
        return helper;
    }

    public void setHelper(Database helper) {
        this.helper = helper;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
}
