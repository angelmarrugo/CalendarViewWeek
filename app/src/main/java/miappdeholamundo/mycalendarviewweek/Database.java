package miappdeholamundo.mycalendarviewweek;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by angel on 14/10/18.
 */

public class Database extends SQLiteOpenHelper {

    private static final String TAG = Database.class.getSimpleName();
    private static final String DB_NOMBRE = "eventsweek.sqlite";
    private static int DB_VERSION = 1;

    public Database(Context context) {

        super(context, DB_NOMBRE, null, DB_VERSION);
        Log.d(TAG,"ahora aca");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"Tabla creada");
        db.execSQL(DataBaseManagerEvents.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+DB_NOMBRE);
        onCreate(db);
    }
}
