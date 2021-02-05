package com.example.reminderexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private Calendar mCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, "My Event");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Event Location");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "What the Event is about");

// Setting dates
        mCalendar = Calendar.getInstance();
        String mTime = mCalendar.get(Calendar.HOUR_OF_DAY) + ":" + mCalendar.get(Calendar.MINUTE);
        GregorianCalendar calDate = new GregorianCalendar(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH) , mCalendar.get(Calendar.DATE));
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                mTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                mTime);

// make it a full day event
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

// make it a recurring Event
        //intent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");


// Making it private and shown as busy
        intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        startActivity(intent);
    }
}