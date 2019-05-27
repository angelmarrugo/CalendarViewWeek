package miappdeholamundo.mycalendarviewweek;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    int px;
    private DataBaseManagerEvents mQuery;
    private RelativeLayout lunes,martes,miercoles,jueves,viernes,sabado,domingo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"Start");

        px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,30,getResources().getDisplayMetrics());
        mQuery = new DataBaseManagerEvents(this);
        mQuery.eliminarTodo();
        createEvents();
        displayWeekEvents();
    }

    private void displayWeekEvents() {
        List<EventObjects> listEvents = mQuery.getAllFutureEvents();

        for (EventObjects eventObjects : listEvents){
            Date start = eventObjects.getDate();
            Date end = eventObjects.getEnd();
            String message = eventObjects.getMessage();
            String day = eventObjects.getDay();
            int eventBlockHeight = getEventTimeFrame(start,end);
            displayEventSection(start,eventBlockHeight,message,day);
        }
    }

    private int getEventTimeFrame(Date start, Date end){
        long timeDifference = end.getTime() - start.getTime();
        Calendar mCal = Calendar.getInstance();
        mCal.setTimeInMillis(timeDifference);
        mCal.set(Calendar.HOUR,(int)timeDifference/3600000);
        int hours = mCal.get(Calendar.HOUR);
        int minutes = mCal.get(Calendar.MINUTE);
        Log.d(TAG,"MY hour "+timeDifference/3600000);
        Log.d(TAG,"timedifference: hour "+hours+" minutes "+minutes);
        Log.d(TAG,"return "+(hours * px) + ((minutes * px) / 30));
        return  ((hours * px) + ((minutes * px) / 30));
    }
    private void displayEventSection(Date eventDate, int height, String message,String day){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        String displayValue = timeFormatter.format(eventDate);
        String[]hourMinutes = displayValue.split(":");
        int hours = Integer.parseInt(hourMinutes[0]);
        int minutes = Integer.parseInt(hourMinutes[1]);
        Log.d(TAG, "Hour value " + hours);
        Log.d(TAG, "Minutes value " + minutes);
        int topViewMargin = (int) ((hours * px) + ((minutes * px) / 30));
        Log.d(TAG, "Margin top " + topViewMargin);
        bindLayout();
        createEventView(topViewMargin, height, message,day);
    }

    private void bindLayout(){
        lunes = (RelativeLayout) findViewById(R.id.left_event_column_lunes);
        martes = (RelativeLayout) findViewById(R.id.left_event_column_martes);
        miercoles= (RelativeLayout) findViewById(R.id.left_event_column_miercoles);
        jueves = (RelativeLayout) findViewById(R.id.left_event_column_jueves);
        viernes = (RelativeLayout) findViewById(R.id.left_event_column_viernes);
        sabado = (RelativeLayout) findViewById(R.id.left_event_column_sabado);
        domingo = (RelativeLayout) findViewById(R.id.left_event_column_domingo);
    }

    private void createEventView(int topMargin, int height, String message,String day){
        TextView mEventView = new TextView(MainActivity.this);
        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lParam.topMargin = topMargin ;

        mEventView.setLayoutParams(lParam);
        mEventView.setPadding(0, 0, 0, 0);
        mEventView.setHeight(height );
        mEventView.setGravity(0x11);
        mEventView.setTextColor(Color.parseColor("#ffffff"));
        mEventView.setText(message);
        mEventView.setAlpha((float) 0.5);
        mEventView.setBackgroundColor(Color.parseColor("#3F51B5"));

        switch (day){
            case"Lunes":
                lunes.addView(mEventView);
                break;
            case"Martes":
                martes.addView(mEventView);
                break;
            case"Miercoles":
                miercoles.addView(mEventView);
                break;
            case"Jueves":
                jueves.addView(mEventView);
                break;
            case"Viernes":
                viernes.addView(mEventView);
                break;
            case"Sabado":
                sabado.addView(mEventView);
                break;
            case"Domingo":
                domingo.addView(mEventView);
                break;
        }
    }

    private void createEvents() {
        mQuery.insertar_event(null,"Sociales","7:00","9:00","Lunes");
        mQuery.insertar_event(null,"Sociales","7:00","8:00","Miercoles");

        mQuery.insertar_event(null,"Matematicas","9:30","11:00","Lunes");
        mQuery.insertar_event(null,"Matematicas","9:00","12:00","Miercoles");

        mQuery.insertar_event(null,"Deporte","10:00","11:30","Martes");
        mQuery.insertar_event(null,"Religion","7:00","8:00","Martes");
        mQuery.insertar_event(null,"Quimica","10:00","15:00","Jueves");
        mQuery.insertar_event(null,"Programacion","13:00","17:00","Viernes");
        mQuery.insertar_event(null,"Ingles","11:00","18:30","Sabado");

    }
}
