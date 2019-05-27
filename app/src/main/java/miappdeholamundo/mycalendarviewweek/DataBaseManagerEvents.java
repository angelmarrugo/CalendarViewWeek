package miappdeholamundo.mycalendarviewweek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by angel on 14/10/18.
 */

public class DataBaseManagerEvents extends DataBaseManager {

    private static final String TAG = DataBaseManagerEvents.class.getSimpleName();

    private static final String NOMBRE_TABLA = "events";

    private static final String CN_ID = "_id";
    private static final String CN_MESSAGE = "message";
    private static final String CN_REMINDER = "reminder";
    private static final String CN_END = "end";
    private static final String CN_DAY = "day";

    public static final String CREATE_TABLE = "create table "+NOMBRE_TABLA+" ("
            +CN_ID+" integer PRIMARY KEY AUTOINCREMENT, "
            +CN_MESSAGE+" text NOT NULL, "
            +CN_REMINDER+" text NOT NULL, "
            +CN_END+" text NOT NULL, "
            +CN_DAY+" text NOT NULL"
            +");";

    public DataBaseManagerEvents(Context context) {

        super(context);
    }

    @Override
    public void eliminarTodo() {
        super.getDb().execSQL("DELETE FROM "+NOMBRE_TABLA+";");
        Log.d(TAG,"Tabla 'Events' Eliminada");
    }

    @Override
    void insertar_event(String id, String message, String reminder, String end, String day) {

        getDb().insert(NOMBRE_TABLA,null,generarContentValues(id, message, reminder, end, day));
    }

    private ContentValues generarContentValues(String id, String message, String reminder, String end, String day){
        ContentValues values = new ContentValues();
        values.put(CN_ID,id);
        values.put(CN_MESSAGE,message);
        values.put(CN_REMINDER,reminder);
        values.put(CN_END,end);
        values.put(CN_DAY,day);

        return values;
    }

    public List<EventObjects> getAllFutureEvents(){
        List<EventObjects> events = new ArrayList<>();
        String query = "select * from "+NOMBRE_TABLA;
        Cursor cursor = getDb().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String message = cursor.getString(cursor.getColumnIndexOrThrow("message"));
                String startDate = cursor.getString(cursor.getColumnIndexOrThrow("reminder"));
                String endDate = cursor.getString(cursor.getColumnIndexOrThrow("end"));
                String day = cursor.getString(cursor.getColumnIndexOrThrow("day"));

                Date reminderDate = convertStringToDate(startDate);
                Date end = convertStringToDate(endDate);

                    events.add(new EventObjects(id, message, reminderDate, end,day));

            }while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }

    private Date convertStringToDate(String dateInString){
        DateFormat format = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = format.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}