package com.example.root.realheart;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.root.realheart.Service.AlarmActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/*
TeamId: Nill
Author List: Amit Vhatkar, Suraj Singh, Akshat Garg
FileName: MedicineFragment.java
Functions: android standard functions
Global Variables:UI related variables,Calendar variable,SimpleDateFormat Variable,Media Player Variable

This fragment used to create a schedule for medicine reminder

the whole amount of commenting is done in OnViewCreated as the all the other functions are generated by android by default and our main code
is in this function.
 */
public class MedicineFragment extends Fragment   {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Calendar cal=Calendar.getInstance();  //getting calendar instance to use calendar in our fragment
    Button btn_date,btn_time,btn_shedule; //variables for button UI elements
    SimpleDateFormat f1,f2;  // SimplemDateFormat variables for setting date format
    MediaPlayer mp;  //MediaPlayer variable to play sound when button click is performed
    public MedicineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicineFragment newInstance(String param1, String param2) {
        MedicineFragment fragment = new MedicineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_date=(Button)view.findViewById(R.id.btn_date);  //getting global variables object for our UI element date button
        btn_time=(Button)view.findViewById(R.id.btn_time);  //getting global variables object for our UI element time button
        btn_shedule=(Button)view.findViewById(R.id.btn_shedule); //getting global variables object for our UI element schedule button
        f1=new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()); //getting simpleDateFormat object
        f2=new SimpleDateFormat("hh:mm ", Locale.getDefault()); //getting simpleDateFormat object
        try
        {
            mp=MediaPlayer.create(getActivity(), R.raw.bubble);  //getting media player object with predefined tune stored in raw folder
            mp.start();
        }catch (Exception e) {
            // TODO: handle exception
        }
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  // creating onClickListener for date button
                try
                {
                    mp.start();  //playing sound
                }catch (Exception e) {
                    // TODO: handle exception
                }
                int year=cal.get(Calendar.YEAR);  // getting present year from calendar object we retreived previously
                int monthOfYear=cal.get(Calendar.MONTH); // getting present month from calendar object we retreived previously
                int dayOfMonth=cal.get(Calendar.DAY_OF_MONTH); // getting present dayOfMonth from calendar object we retreived previously
                DatePickerDialog dpd=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int curr_year, int month, int day) { //getting datepickerdialog and initializing with our above defined variables
                        // TODO Auto-generated method stub
                        cal.set(Calendar.YEAR, curr_year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, day);
                        btn_date.setText(f1.format(cal.getTimeInMillis())); //Setting text for date button

                    }
                }, year, monthOfYear, dayOfMonth);
                dpd.show();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  // setting setOnClickListener for time button
                try
                {
                    mp.start();  //playing sound
                }catch (Exception e) {
                    // TODO: handle exception
                }
                int h=cal.get(Calendar.HOUR_OF_DAY); // getting present hours from calendar object
                int m=cal.get(Calendar.MINUTE);      // getting present minutes from calendar object
                TimePickerDialog tpd=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker arg0, int h, int min) { //getting timePickerDialog and initializing with predefined variables
                        // TODO Auto-generated method stub
                        cal.set(Calendar.HOUR_OF_DAY, h);
                        cal.set(Calendar.MINUTE, min);

                        btn_time.setText(f2.format(cal.getTimeInMillis()));
                    }
                }, h, m, true);
                tpd.show();
            }
        });
        btn_shedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //setting setOnCLicklistener to schedule reminder
                try
                {
                    mp.start();  //playing sound
                }catch (Exception e) {
                    // TODO: handle exception
                }
                SimpleDateFormat sdf=new SimpleDateFormat("dd MMM yyyy  hh:mm:ss",Locale.getDefault()); //getting SimpleDateFormat
                if(System.currentTimeMillis()>=cal.getTimeInMillis())
                {
                    Toast.makeText(getActivity(), "Set different time", Toast.LENGTH_LONG).show();
                }
                else
                {
                    AlarmManager alm= (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);  //getting AlarmMAnager object to set the reminder
                    Intent intent=new Intent(getActivity(), AlarmActivity.class); // setting intent for launching activity
                    PendingIntent pdintent=PendingIntent.getActivity(getActivity(), 10, intent, PendingIntent.FLAG_UPDATE_CURRENT); // creating pending intent for launching an activity for reminder
                    cal.set(Calendar.SECOND, 0);
                    alm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pdintent); //setting alarm for reminder
                    Toast.makeText(getActivity(), "Alarm Sheduled Succesfully", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}
