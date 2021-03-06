package BankFormProtoype.version0;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.support.v7.app.AppCompatActivity;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.Month;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.currentTimeMillis;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String uniqueID = UUID.randomUUID().toString();
    private EditText mCardNum;
    private TextView mDisplayDate;
    private TextView mDisplayDate2;
    private TextView mNameLabel;
    private EditText mName;
    private TextView mExpiryLabel;
    private TextView mExpiryLabel2;
    private TextView mCVVlabel;
    private EditText mCVV;
    private TextView mSelectId;
    private TextView mTypeIDlabel;
    private EditText mTypeID;
    private TextView mSelectIdA;
    private TextView mTypeIDlabelA;
    private EditText mTypeIDA;
    private ImageButton vcbI;
    private ImageView vcbI2;
    private ImageButton bvbI;
    private ImageButton msb;
    private ImageButton vtb;
    private ImageView msb2;
    private ImageView vtb2;
    private ImageView bvbI2;

    private RelativeLayout chooseIDb;
    private RelativeLayout chooseIDAb;
    private boolean accountFlag;
    private boolean ccIFlag;
    private boolean dcFlag;
    private boolean vcbATMFlag;
    private ImageView visaI;
    private Button submit;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    private ImageButton more;
    private boolean moreFlag;
    private Button showMore;

    private int cMonth;
    private int cYear;
    private boolean idFlag2;
    private int idFlag;
    private boolean nameFlag;
    private boolean cvvFlag;
    private boolean cardNumFlag;
    private boolean dateFlag;

    private int counter;
    private DatabaseReference db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0087F3")));
        bar.setTitle("Li??n K???t Ng??n H??ng");
        db = FirebaseDatabase.getInstance("https://bank-form-prototype-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        counter = 0;
        Spinner chooseID = (Spinner) findViewById(R.id.chooseID);
        Spinner chooseIDA = (Spinner) findViewById(R.id.chooseIDA);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.idChoice, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        chooseID.setAdapter(adapter);
        chooseIDA.setAdapter(adapter);

        mDisplayDate = (TextView) findViewById(R.id.expiryDate);
        mDisplayDate2 = (TextView) findViewById(R.id.expiryDate2);

        mCardNum = (EditText) findViewById(R.id.cardNum);
        mNameLabel = (TextView) findViewById(R.id.cardHolderLabel);
        mName = (EditText) findViewById(R.id.cardHolder);
        mExpiryLabel = (TextView) findViewById(R.id.expiryLabel);
        mExpiryLabel2 = (TextView) findViewById(R.id.expiryLabel2);
        mCVVlabel = (TextView) findViewById(R.id.cvvlabel);
        mCVV = (EditText) findViewById(R.id.cvv);
        mSelectId = (TextView) findViewById(R.id.selectIDlabel);
        mTypeIDlabel = (TextView) findViewById(R.id.typeIDlabel);
        mTypeID = (EditText) findViewById(R.id.TypeID);
        mSelectIdA = (TextView) findViewById(R.id.selectIDlabelA);
        mTypeIDlabelA = (TextView) findViewById(R.id.typeIDlabelA);
        mTypeIDA = (EditText) findViewById(R.id.AtypeID);
        vcbI = (ImageButton) findViewById(R.id.vcb);
        bvbI = (ImageButton) findViewById(R.id.bvb);
        submit = (Button) findViewById(R.id.submit);


        vcbI2 = (ImageView) findViewById(R.id.vcb2);
        bvbI2 = (ImageView) findViewById(R.id.bvb2);
        visaI = (ImageView) findViewById(R.id.visa);
        vtb = (ImageButton) findViewById(R.id.vtb);
        msb = (ImageButton) findViewById(R.id.msb);
        vtb2 = (ImageView) findViewById(R.id.vtb2);
        msb2 = (ImageView) findViewById(R.id.msb2);
        more = (ImageButton) findViewById(R.id.more);
        showMore = (Button) findViewById(R.id.showInfo);

//        visademo = (ImageView) findViewById(R.id.visa);
//        atmdemo = (ImageView) findViewById(R.id.atmD);
//        acctdemo = (TextView) findViewById(R.id.acct);

        chooseIDAb = (RelativeLayout) findViewById(R.id.chooseIDAb);
        chooseIDb = (RelativeLayout) findViewById(R.id.chooseIDb);
        mNameLabel.setVisibility(TextView.INVISIBLE);
        mName.setVisibility(EditText.INVISIBLE);
        mExpiryLabel.setVisibility(TextView.INVISIBLE);
        mDisplayDate.setVisibility(TextView.INVISIBLE);
        mExpiryLabel2.setVisibility(TextView.INVISIBLE);
        mDisplayDate2.setVisibility(TextView.INVISIBLE);
        mCVVlabel.setVisibility(TextView.INVISIBLE);
        mCVV.setVisibility(EditText.INVISIBLE);
        mSelectId.setVisibility(TextView.INVISIBLE);
        mSelectIdA.setVisibility(TextView.INVISIBLE);
        chooseID.setVisibility(Spinner.INVISIBLE);
        chooseIDA.setVisibility(Spinner.INVISIBLE);
        mTypeIDlabel.setVisibility(TextView.INVISIBLE);
        mTypeID.setVisibility(EditText.INVISIBLE);
        mTypeIDlabelA.setVisibility(TextView.INVISIBLE);
        mTypeIDA.setVisibility(EditText.INVISIBLE);
        vcbI.setVisibility(ImageButton.INVISIBLE);
        bvbI.setVisibility(ImageButton.INVISIBLE);
        vcbI2.setVisibility(ImageView.INVISIBLE);
        bvbI2.setVisibility(ImageView.INVISIBLE);
        visaI.setVisibility(ImageView.INVISIBLE);
        submit.setVisibility(Button.INVISIBLE);
        more.setVisibility(ImageButton.INVISIBLE);
        msb.setVisibility(ImageButton.INVISIBLE);
        vtb.setVisibility(ImageButton.INVISIBLE);
        msb2.setVisibility(ImageView.INVISIBLE);
        vtb2.setVisibility(ImageView.INVISIBLE);
        chooseIDAb.setVisibility(RelativeLayout.INVISIBLE);
        chooseIDb.setVisibility(RelativeLayout.INVISIBLE);


        //make name all caps only accept alphabet
        mName.setFilters(new InputFilter[]{getEditTextFilter(),new InputFilter.AllCaps()});
        mTypeID.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        mTypeIDA.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        moreFlag = true;

        LocalDate currentdate = LocalDate.now();
        cMonth = currentdate.getMonthValue();
        cYear = currentdate.getYear();

        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String str = intent.getStringExtra("flow3");





        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.demo_card, null,false);
                if(str.equals("ATM")){
                    dialoglayout.findViewById(R.id.atmD).setVisibility(View.VISIBLE);
                    dialoglayout.findViewById(R.id.visa).setVisibility(View.INVISIBLE);
                    dialoglayout.findViewById(R.id.acct).setVisibility(View.INVISIBLE);

                }else if(str.equals("VISA")){
                    dialoglayout.findViewById(R.id.atmD).setVisibility(View.INVISIBLE);
                    dialoglayout.findViewById(R.id.visa).setVisibility(View.VISIBLE);
                    dialoglayout.findViewById(R.id.acct).setVisibility(View.INVISIBLE);

                }else if(str.equals("TK")){
                    dialoglayout.findViewById(R.id.atmD).setVisibility(View.INVISIBLE);
                    dialoglayout.findViewById(R.id.visa).setVisibility(View.INVISIBLE);
                    dialoglayout.findViewById(R.id.acct).setVisibility(View.VISIBLE);

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Th??ng tin: ");
                builder.setView(dialoglayout);




                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });


        //initialize data tracking
//        List<String> event = new ArrayList<String>();
//        List<String> metadata = new ArrayList<String>();
//        List<String> time = new ArrayList<String>();
//        List<String> id = new ArrayList<String>();
//        event.add("dummy");
//        metadata.add("dummy");
//        time.add("dummy");
//        id.add("dummy");
//        Data newD = new Data(event,metadata,time,id);
//        db.child("Click Data").setValue(newD);

        updateData("load bank form","");

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(moreFlag){
                    msb.setVisibility(ImageButton.VISIBLE);
                    vtb.setVisibility(ImageButton.VISIBLE);
                    moreFlag = false;
                } else{
                    msb.setVisibility(ImageButton.INVISIBLE);
                    vtb.setVisibility(ImageButton.INVISIBLE);
                    moreFlag = true;
                }

            }
        });




        chooseID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               counter++;
               if(counter > 0){
                   String selected = parent.getItemAtPosition(position).toString();
                   if(selected.equals("CMND")){
                       mTypeIDlabel.setVisibility(TextView.VISIBLE);
                       mTypeID.setVisibility(EditText.VISIBLE);
                       mTypeIDlabel.setText("??i???n S??? Ch???ng Minh Nh??n D??n:");
                       mTypeID.setInputType(InputType.TYPE_CLASS_NUMBER);
                       mTypeID.setText("");
                       idFlag = 1;
                       updateData("select ID","'Flow: "+flowCheck()+"' 'IDtype: CMND'");

                   }
                   else if(selected.equals("CCCD")){
                       mTypeIDlabel.setVisibility(TextView.VISIBLE);
                       mTypeID.setInputType(InputType.TYPE_CLASS_NUMBER);
                       mTypeID.setVisibility(EditText.VISIBLE);
                       mTypeIDlabel.setText("??i???n S??? C??n C?????c C??ng D??n:");
                       mTypeID.setText("");
                       idFlag = 2;
                       updateData("select ID","'Flow: "+flowCheck()+"' 'IDtype: CCCD'");
                   }
                   else if(selected.equals("H??? Chi???u")){
                       mTypeID.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                       mTypeIDlabel.setVisibility(TextView.VISIBLE);
                       mTypeID.setVisibility(EditText.VISIBLE);
                       mTypeIDlabel.setText("??i???n S??? H??? Chi???u:");
                       mTypeID.setText("");
                       idFlag = 3;
                       updateData("select ID","'Flow: "+flowCheck()+"' 'IDtype: Passport'");
                   }else{
                       mTypeIDlabel.setVisibility(TextView.INVISIBLE);
                       mTypeID.setVisibility(EditText.INVISIBLE);
                       mTypeID.setInputType(InputType.TYPE_CLASS_NUMBER);
                       mTypeID.setText("");
                       submit.setVisibility(Button.INVISIBLE);
                       idFlag = 0;
                       idFlag2 = false;
                   }
               }
           }


           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        mTypeID.addTextChangedListener(new TextValidator(mTypeID) {
            @Override
            public void validate(TextView textView, String text) {
                if(idFlag==1){
                    if(text.length()!=9){
                        mTypeID.setError("S??? CMND c?? 9 s???");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                }
                else if(idFlag==2){
                    if(text.length()!=12){

                        mTypeID.setError("S??? CCCD c?? 12 s???");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                }
                else if(idFlag==3){
                    if(text.length()!=8){


                        mTypeID.setError("H??? Chi???u c?? 8 s???/Ch???");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                } else {
                    idFlag2 = false;
                }
                if(text.length()==6){
                    updateData("Type ID","'first6ID: "+text+"' 'Flow: "+flowCheck()+"'");
                }
                submitCheck();
            }
        });

        chooseIDA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                counter++;
                if(counter>0){
                    String selected = parent.getItemAtPosition(position).toString();
                    if(selected.equals("CMND")){
                        mTypeIDlabelA.setVisibility(TextView.VISIBLE);
                        mTypeIDA.setVisibility(EditText.VISIBLE);
                        mTypeIDlabelA.setText("??i???n S??? Ch???ng Minh Nh??n D??n:");
                        idFlag = 1;
                        mTypeIDA.setInputType(InputType.TYPE_CLASS_NUMBER);
                        mTypeIDA.setText("");
                        updateData("select ID","'Flow: "+flowCheck()+"' 'IDtype: CMND'");
                    }
                    else if(selected.equals("CCCD")){
                        mTypeIDlabelA.setVisibility(TextView.VISIBLE);
                        mTypeIDA.setVisibility(EditText.VISIBLE);
                        mTypeIDlabelA.setText("??i???n S??? C??n C?????c C??ng D??n:");
                        idFlag = 2;
                        mTypeIDA.setInputType(InputType.TYPE_CLASS_NUMBER);
                        mTypeIDA.setText("");
                        updateData("select ID","'Flow: "+flowCheck()+"' 'IDtype: CCCD'");
                    }
                    else if(selected.equals("H??? Chi???u")){
                        mTypeIDlabelA.setVisibility(TextView.VISIBLE);
                        mTypeIDA.setVisibility(EditText.VISIBLE);
                        mTypeIDlabelA.setText("??i???n S??? H??? Chi???u:");
                        mTypeIDA.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        idFlag = 3;
                        mTypeIDA.setText("");
                        updateData("select ID","'Flow: "+flowCheck()+"' 'IDtype: Passport'");
                    }else{
                        mTypeIDlabelA.setVisibility(TextView.INVISIBLE);
                        mTypeIDA.setVisibility(EditText.INVISIBLE);
                        mTypeIDA.setInputType(InputType.TYPE_CLASS_NUMBER);
                        mTypeIDA.setText("");
                        submit.setVisibility(Button.INVISIBLE);
                        idFlag = 0;
                        idFlag2 = false;
                    }

                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mTypeIDA.addTextChangedListener(new TextValidator(mTypeIDA) {
            @Override
            public void validate(TextView textView, String text) {
                if(idFlag==1){
                    if(text.length()!=9){

                        mTypeIDA.setError("S??? CMND c?? 9 s???");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                }
                else if(idFlag==2){
                    if(text.length()!=12){

                        mTypeIDA.setError("S??? CCCD c?? 12 s???");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                }
                else if(idFlag==3){
                    if(text.length()!=8){

                        mTypeIDA.setError("H??? Chi???u c?? 8 s???/Ch???");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                } else {
                    idFlag2 = false;
                }
                if(text.length()==6){
                    updateData("Type ID","'first6ID: "+text+"' 'Flow: "+flowCheck()+"'");
                }
                submitCheck();
            }

        });

        mTypeID.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String idType = "";
                if(idFlag==1){
                    idType = "CMND";
                }else if(idFlag == 2){
                    idType = "CCCD";
                }else if(idFlag == 3){
                    idType = "Passport";
                }

                if(hasFocus){
                    updateData("click on ID field","'IDtype: "+idType+"' 'IDvalue: "+((EditText)v).getText().toString().trim()+"' 'Flow: "+flowCheck()+"'");
                }else{
                    updateData("click out of ID field","'IDtype: "+idType+"' 'IDvalue: "+((EditText)v).getText().toString().trim()+"' 'Flow: "+flowCheck()+"'");
                }
            }
        });

        mTypeIDA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String idType = "";
                if(idFlag==1){
                    idType = "CMND";
                }else if(idFlag == 2){
                    idType = "CCCD";
                }else if(idFlag == 3){
                    idType = "Passport";
                }

                if(hasFocus){
                    updateData("click on ID field","'IDtype: "+idType+"' 'IDvalue: "+((EditText)v).getText().toString().trim()+"' 'Flow: "+flowCheck()+"'");
                }else{
                    updateData("click out of ID field","'IDtype: "+idType+"' 'IDvalue: "+((EditText)v).getText().toString().trim()+"' 'Flow: "+flowCheck()+"'");
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (ccIFlag && cardNumFlag && cvvFlag && nameFlag && dateFlag) {
                    updateData("click submit success","'Flow: "+flowCheck()+"'");
                    openSuccess();
                } else if (dcFlag && cardNumFlag && dateFlag && idFlag2) {
                    updateData("click submit success","'Flow: "+flowCheck()+"'");
                    openSuccess();
                } else if (vcbATMFlag && nameFlag && cardNumFlag && dateFlag && idFlag2) {
                    updateData("click submit success","'Flow: "+flowCheck()+"'");
                    openSuccess();
                } else if (accountFlag && nameFlag && idFlag2) {
                    updateData("click submit success","'Flow: "+flowCheck()+"'");
                    openSuccess();
                }else{
                    updateData("click submit failed","'Flow: "+flowCheck()+"'");
                }

            }
        });

        mTypeIDA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    submit.setVisibility(Button.VISIBLE);
                } else{
                    submit.setVisibility(Button.INVISIBLE);
                }

            }
        });

        mTypeID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    submit.setVisibility(Button.VISIBLE);

                }else{
                    submit.setVisibility(Button.INVISIBLE);
                }

            }
        });

//        mDisplayDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        MainActivity.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                        mDateSetListener,
//                        year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getDatePicker().findViewById(getResources().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
//                dialog.show();
//
//                updateData("Click on date picker","'Flow: "+flowCheck()+"'");
//
//            }
//        });

//        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        MainActivity.this,
//                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                        mDateSetListener2,
//                        year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getDatePicker().findViewById(getResources().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
//                dialog.show();
//                updateData("Click on date picker","'Flow: "+flowCheck()+"'");
//
//            }
//        });
//
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;

                        String date = month + "/" + year;
                        mDisplayDate.setText(date);
        //                mTypeIDlabel.setVisibility(TextView.VISIBLE);
        //                mTypeID.setVisibility(EditText.VISIBLE);
                        if(!ccIFlag){
                            mSelectId.setVisibility(TextView.VISIBLE);
                            chooseID.setVisibility(Spinner.VISIBLE);
                            chooseIDb.setVisibility(RelativeLayout.VISIBLE);

                        }

                        if(vcbATMFlag){
                            if(year > cYear){
                                mDisplayDate.setError("N??m C???p Th??? Kh??ng Th??? h??n n??m n??y");
                                Toast.makeText(MainActivity.this,"N??m C???p Th??? Kh??ng Th??? h??n n??m n??y",Toast.LENGTH_LONG).show();

                                dateFlag = false;
                            } else if(year==cYear && month>cMonth){
                                mDisplayDate.setError("Th??ng C???p Th??? Kh??ng Th??? h??n th??ng n??y");
                                Toast.makeText(MainActivity.this,"Th??ng C???p Th??? Kh??ng Th??? h??n th??ng n??y",Toast.LENGTH_LONG).show();

                                dateFlag = false;
                            } else {
                                mDisplayDate.setError(null);
                                dateFlag = true;
                            }

                        }else{
                            if(year < cYear){
                                mDisplayDate.setError("???? qu?? ng??y h???t h???n");
                                Toast.makeText(MainActivity.this,"???? qu?? ng??y h???t h???n",Toast.LENGTH_LONG).show();
                                dateFlag = false;
                            } else if(year==cYear && month<cMonth){
                                mDisplayDate.setError("???? qu?? ng??y h???t h???n");
                                Toast.makeText(MainActivity.this,"???? qu?? ng??y h???t h???n",Toast.LENGTH_LONG).show();
                                dateFlag = false;
                            } else {
                                mDisplayDate.setError(null);
                                dateFlag = true;
                            }

                        }
                        updateData("select date","'Date: "+date+"' 'Flow: "+flowCheck()+"'");
                        submitCheck();
                    }
                }, year, month, day) {

                    final int month = getContext().getResources().getIdentifier("android:id/month", null, null);
                    final String[] monthNumbers = new String[]{ "01","02","03","04","05","06","07","08","09","10","11","12"};

                    @Override
                    public void onDateChanged(@NonNull DatePicker view, int y, int m, int d) {
                        super.onDateChanged(view, y, m, d);
                        // Since DatePickerCalendarDelegate updates the month spinner too, we need to change months as numbers here also
                        if(month != 0){
                            NumberPicker monthPicker = findViewById(month);
                            if(monthPicker != null){
                                monthPicker.setDisplayedValues(monthNumbers);
                            }
                        }
                    }

                    @Override
                    protected void onCreate(Bundle savedInstanceState)
                    {
                        super.onCreate(savedInstanceState);
                        // Hide day spinner
                        int day = getContext().getResources().getIdentifier("android:id/day", null, null);
                        if(day != 0){
                            NumberPicker dayPicker = findViewById(day);
                            if(dayPicker != null){
                                dayPicker.setVisibility(View.GONE);
                            }
                        }
                        // Show months as Numbers
                        if(month != 0){
                            NumberPicker monthPicker = findViewById(month);
                            if(monthPicker != null){
                                monthPicker.setDisplayedValues(monthNumbers);
                            }
                        }
                    }
                };
                mDatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

                updateData("Click on date picker","'Flow: "+flowCheck()+"'");

            }
        });




        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;

                        String date = month + "/" + year;
                        mDisplayDate2.setText(date);
//                mTypeIDlabelA.setVisibility(TextView.VISIBLE);
//                mTypeIDA.setVisibility(EditText.VISIBLE);


                        mSelectIdA.setVisibility(TextView.VISIBLE);
                        chooseIDA.setVisibility(Spinner.VISIBLE);
                        chooseIDAb.setVisibility(RelativeLayout.VISIBLE);
                        if(vcbATMFlag){
                            if(year > cYear){
                                mDisplayDate2.setError("N??m C???p Th??? Kh??ng Th??? h??n n??m n??y");
                                Toast.makeText(MainActivity.this,"N??m C???p Th??? Kh??ng Th??? h??n n??m n??y",Toast.LENGTH_LONG).show();
                                dateFlag = false;
                            } else if(year==cYear && month>cMonth){
                                mDisplayDate2.setError("Th??ng C???p Th??? Kh??ng Th??? h??n th??ng n??y");
                                Toast.makeText(MainActivity.this,"Th??ng C???p Th??? Kh??ng Th??? h??n th??ng n??y",Toast.LENGTH_LONG).show();
                                dateFlag = false;
                            } else {
                                dateFlag = true;
                                mDisplayDate2.setError(null);
                            }

                        }else{
                            if(year < cYear){
                                mDisplayDate2.setError("???? qu?? ng??y h???t h???n");
                                Toast.makeText(MainActivity.this,"???? qu?? ng??y h???t h???n",Toast.LENGTH_LONG).show();
                                dateFlag = false;
                            } else if(year==cYear && month<cMonth){
                                mDisplayDate2.setError("???? qu?? ng??y h???t h???n");
                                Toast.makeText(MainActivity.this,"???? qu?? ng??y h???t h???n",Toast.LENGTH_LONG).show();
                                dateFlag = false;
                            } else {
                                mDisplayDate2.setError(null);
                                dateFlag = true;
                            }

                        }
                        updateData("select date","'Date: "+date+"' 'Flow: "+flowCheck()+"'");
                        submitCheck();

                    }
                }, year, month, day) {

                    final int month = getContext().getResources().getIdentifier("android:id/month", null, null);
                    final String[] monthNumbers = new String[]{ "01","02","03","04","05","06","07","08","09","10","11","12"};

                    @Override
                    public void onDateChanged(@NonNull DatePicker view, int y, int m, int d) {
                        super.onDateChanged(view, y, m, d);
                        // Since DatePickerCalendarDelegate updates the month spinner too, we need to change months as numbers here also
                        if(month != 0){
                            NumberPicker monthPicker = findViewById(month);
                            if(monthPicker != null){
                                monthPicker.setDisplayedValues(monthNumbers);
                            }
                        }
                    }

                    @Override
                    protected void onCreate(Bundle savedInstanceState)
                    {
                        super.onCreate(savedInstanceState);
                        // Hide day spinner
                        int day = getContext().getResources().getIdentifier("android:id/day", null, null);
                        if(day != 0){
                            NumberPicker dayPicker = findViewById(day);
                            if(dayPicker != null){
                                dayPicker.setVisibility(View.GONE);
                            }
                        }
                        // Show months as Numbers
                        if(month != 0){
                            NumberPicker monthPicker = findViewById(month);
                            if(monthPicker != null){
                                monthPicker.setDisplayedValues(monthNumbers);
                            }
                        }
                    }
                };
                mDatePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDatePicker.setTitle("Select Date");
                updateData("Click on date picker","'Flow: "+flowCheck()+"'");
                mDatePicker.show();

            }
        });



//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//
//                String date = month + "/" + year;
//                mDisplayDate.setText(date);
////                mTypeIDlabel.setVisibility(TextView.VISIBLE);
////                mTypeID.setVisibility(EditText.VISIBLE);
//                if(!ccIFlag){
//                    mSelectId.setVisibility(TextView.VISIBLE);
//                    chooseID.setVisibility(Spinner.VISIBLE);
//                    chooseIDb.setVisibility(RelativeLayout.VISIBLE);
//
//                }
//
//                if(vcbATMFlag){
//                    if(year > cYear){
//                        mDisplayDate.setError("N??m C???p Th??? Kh??ng Th??? h??n n??m n??y");
//
//                        dateFlag = false;
//                    } else if(year==cYear && month>cMonth){
//                        mDisplayDate.setError("Th??ng C???p Th??? Kh??ng Th??? h??n th??ng n??y");
//
//                        dateFlag = false;
//                    } else {
//                        mDisplayDate.setError(null);
//                        dateFlag = true;
//                    }
//
//                }else{
//                    if(year < cYear){
//                        mDisplayDate.setError("???? qu?? ng??y h???t h???n");
//
//                        dateFlag = false;
//                    } else if(year==cYear && month<cMonth){
//                        mDisplayDate.setError("???? qu?? ng??y h???t h???n");
//
//                        dateFlag = false;
//                    } else {
//                        mDisplayDate.setError(null);
//                        dateFlag = true;
//                    }
//
//                }
//                updateData("select date","'Date: "+date+"' 'Flow: "+flowCheck()+"'");
//                submitCheck();
//            }
//        };

//        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//
//                String date = month + "/" + year;
//                mDisplayDate2.setText(date);
////                mTypeIDlabelA.setVisibility(TextView.VISIBLE);
////                mTypeIDA.setVisibility(EditText.VISIBLE);
//
//
//                mSelectIdA.setVisibility(TextView.VISIBLE);
//                chooseIDA.setVisibility(Spinner.VISIBLE);
//                chooseIDAb.setVisibility(RelativeLayout.VISIBLE);
//                if(vcbATMFlag){
//                    if(year > cYear){
//                        mDisplayDate2.setError("N??m C???p Th??? Kh??ng Th??? h??n n??m n??y");
//                        mDisplayDate2.requestFocus();
//                        dateFlag = false;
//                    } else if(year==cYear && month>cMonth){
//                        mDisplayDate2.setError("Th??ng C???p Th??? Kh??ng Th??? h??n th??ng n??y");
//                        mDisplayDate2.requestFocus();
//                        dateFlag = false;
//                    } else {
//                        dateFlag = true;
//                        mDisplayDate2.setError(null);
//                    }
//
//                }else{
//                    if(year < cYear){
//                        mDisplayDate2.setError("???? qu?? ng??y h???t h???n");
//                        mDisplayDate2.requestFocus();
//                        dateFlag = false;
//                    } else if(year==cYear && month<cMonth){
//                        mDisplayDate2.setError("???? qu?? ng??y h???t h???n");
//                        mDisplayDate2.requestFocus();
//                        dateFlag = false;
//                    } else {
//                        mDisplayDate2.setError(null);
//                        dateFlag = true;
//                    }
//
//                }
//                updateData("select date","'Date: "+date+"' 'Flow: "+flowCheck()+"'");
//                submitCheck();
//
//            }
//        };



        vcbI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameLabel.setVisibility(TextView.VISIBLE);
                mName.setVisibility(EditText.VISIBLE);
                vcbI2.setVisibility(ImageButton.VISIBLE);
                mNameLabel.setText("??i???n T??n Ch??? T??i Kho???n:");
                bvbI.setVisibility(ImageButton.INVISIBLE);
                vcbI.setVisibility(ImageButton.INVISIBLE);
                more.setVisibility(ImageButton.INVISIBLE);
                vtb.setVisibility(ImageButton.INVISIBLE);
                msb.setVisibility(ImageButton.INVISIBLE);



            }
        });

        bvbI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameLabel.setVisibility(TextView.VISIBLE);
                mName.setVisibility(EditText.VISIBLE);
                bvbI.setVisibility(ImageButton.INVISIBLE);
                vcbI.setVisibility(ImageButton.INVISIBLE);
                bvbI2.setVisibility(ImageButton.VISIBLE);
                more.setVisibility(ImageButton.INVISIBLE);
                vtb.setVisibility(ImageButton.INVISIBLE);
                msb.setVisibility(ImageButton.INVISIBLE);
                mNameLabel.setText("??i???n T??n Ch??? T??i Kho???n:");
            }
        });

        msb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameLabel.setVisibility(TextView.VISIBLE);
                mName.setVisibility(EditText.VISIBLE);
                bvbI.setVisibility(ImageButton.INVISIBLE);
                vcbI.setVisibility(ImageButton.INVISIBLE);
                bvbI2.setVisibility(ImageButton.INVISIBLE);
                more.setVisibility(ImageButton.INVISIBLE);
                vtb.setVisibility(ImageButton.INVISIBLE);
                msb.setVisibility(ImageButton.INVISIBLE);
                vtb2.setVisibility(ImageView.INVISIBLE);
                msb2.setVisibility(ImageView.VISIBLE);
                mNameLabel.setText("??i???n T??n Ch??? T??i Kho???n:");
            }
        });

        vtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameLabel.setVisibility(TextView.VISIBLE);
                mName.setVisibility(EditText.VISIBLE);
                bvbI.setVisibility(ImageButton.INVISIBLE);
                vcbI.setVisibility(ImageButton.INVISIBLE);
                bvbI2.setVisibility(ImageButton.INVISIBLE);
                more.setVisibility(ImageButton.INVISIBLE);
                vtb.setVisibility(ImageButton.INVISIBLE);
                msb.setVisibility(ImageButton.INVISIBLE);
                vtb2.setVisibility(ImageView.VISIBLE);
                msb2.setVisibility(ImageView.INVISIBLE);
                mNameLabel.setText("??i???n T??n Ch??? T??i Kho???n:");
                updateData("select bank","VTB");
            }
        });



        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0 && accountFlag) {

                    mSelectIdA.setVisibility(TextView.VISIBLE);
                    chooseIDA.setVisibility(Spinner.VISIBLE);
                    chooseIDAb.setVisibility(RelativeLayout.VISIBLE);
                   // mTypeIDlabelA.setVisibility(TextView.VISIBLE);
                   // mTypeIDA.setVisibility(EditText.VISIBLE);

                }
                else if(s.toString().trim().length() > 0 && ccIFlag){
                    mExpiryLabel.setText("Ng??y H???t H???n:");
                    mExpiryLabel.setVisibility(TextView.VISIBLE);
                    mDisplayDate.setVisibility(EditText.VISIBLE);
                    mCVVlabel.setVisibility(TextView.VISIBLE);
                    mCVV.setVisibility(EditText.VISIBLE);

                }
                else if(s.toString().trim().length() > 0 && vcbATMFlag){
                    mExpiryLabel.setText("Ng??y C???p Th???:");
                    mExpiryLabel.setVisibility(TextView.VISIBLE);
                    mDisplayDate.setVisibility(EditText.VISIBLE);


                }
            }
        });

        mCVV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0) {
                    submit.setVisibility(Button.VISIBLE);
                    //mSelectId.setVisibility(TextView.VISIBLE);
                    //chooseID.setVisibility(EditText.VISIBLE);
                    //mTypeIDlabel.setVisibility(TextView.VISIBLE);
                    //mTypeID.setVisibility(EditText.VISIBLE);
                }else{
                    submit.setVisibility(Button.INVISIBLE);
                }
                submitCheck();
            }
        });
        mName.addTextChangedListener(new TextValidator(mName) {
            @Override
            public void validate(TextView textView, String text) {

                if(text.contains(" ")){
                    nameFlag = true;
                }else{
                    textView.setError("T??n Ph??i c?? d???u c??ch");
                    nameFlag = false;
                }

                if(text.length()==6){
                    updateData("type name","name: "+text+" Flow: "+flowCheck());
                }
                submitCheck();

            }
        });
        mName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    updateData("click on name","'name: "+((EditText)v).getText().toString().trim()+"' 'Flow: "+flowCheck()+"'");
                }else {
                    updateData("click out of name","'name: "+((EditText)v).getText().toString().trim()+"' Flow: "+flowCheck()+"'");
                }
            }
        });

        mCVV.addTextChangedListener(new TextValidator(mCVV) {
            @Override
            public void validate(TextView textView, String text) {
                if(text.length()<3){
                    textView.setError("CVV c?? 3 s??? (3 s??? ??? m???t sau c???a th???)");
                    cvvFlag = false;
                } else {
                    cvvFlag = true;
                }
                if(text.length()==3){
                    updateData("Type CVV", "'CVV: "+text+"' 'Flow: "+flowCheck()+"'");
                }
                submitCheck();
            }
        });

        mCVV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    updateData("click on CVV","'CVV: "+((EditText)v).getText().toString().trim()+"' 'Flow' "+flowCheck()+"'");
                }
            }
        });



        mCardNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus){
                    updateData("click out of cardnum","'cardNum: "+((EditText)v).getText().toString().trim()+"' 'Flow: "+flowCheck()+"'");

                }else {
                    updateData("click on cardnum","'cardNum: "+((EditText)v).getText().toString().trim()+"' 'Flow: "+flowCheck()+"'");


                }

            }
        });


        mCardNum.addTextChangedListener(new TextValidator(mCardNum) {
            @Override
            public void validate(TextView textView, String text) {


                //International credit card VCB
              if(text.startsWith("514003") ||text.startsWith("438103")||text.startsWith("547886")||text.startsWith("546285")||text.startsWith("546284")||text.startsWith("477389")||text.startsWith("469174")||text.startsWith("461136")||text.startsWith("412975")||text.startsWith("4212976")||text.startsWith("356771")||text.startsWith("356770")||text.startsWith("356435")) {

                  mNameLabel.setVisibility(TextView.VISIBLE);
                  mName.setVisibility(EditText.VISIBLE);
                  visaI.setVisibility(TextView.VISIBLE);
                  ccIFlag = true;


                  if(text.length() != 16 && ccIFlag){
                      mCardNum.setError("Th??? VISA c???n 16 s???");
                      cardNumFlag = false;
                  } else{
                      cardNumFlag = true;
                  }

                //VCB international debit card
              } else if(text.startsWith("436762") || text.startsWith("412977")|| text.startsWith("412647") || text.startsWith("412645")|| text.startsWith("428310")|| text.startsWith("452404") || text.startsWith("469173") || text.startsWith("403277") || text.startsWith("477390") || text.startsWith("526418")|| text.startsWith("222806")|| text.startsWith("377160")|| text.startsWith("621295") ){
                    vcbI2.setVisibility(ImageView.VISIBLE);
                    mExpiryLabel2.setVisibility(TextView.VISIBLE);
                    mDisplayDate2.setVisibility(TextView.VISIBLE);

                    dcFlag = true;


                    if(text.length() != 16 && dcFlag){
                       mCardNum.setError("Th??? debit c???n 16 s???");
                       cardNumFlag = false;
                    } else{
                        cardNumFlag = true;
                    }





              }else if(text.startsWith("970436")){
                  mNameLabel.setVisibility(TextView.VISIBLE);
                  mName.setVisibility(EditText.VISIBLE);
                  vcbI2.setVisibility(TextView.VISIBLE);
                  vcbATMFlag = true;



                  if(text.length() != 19 && vcbATMFlag){
                      mCardNum.setError("Th??? ATM c???n 19 s???");
                      cardNumFlag = false;
                  } else{
                      cardNumFlag = true;
                  }

              }
              //acct VCB and BVB
              else if(text.length()==13 || text.length()==10){
//                    vcbI.setVisibility(ImageButton.VISIBLE);
//                    bvbI.setVisibility(ImageButton.VISIBLE);
//                    more.setVisibility(ImageButton.VISIBLE);
                    accountFlag = true;
                    cardNumFlag = true;
                    mNameLabel.setVisibility(TextView.VISIBLE);
                    mName.setVisibility(EditText.VISIBLE);
                    vcbI2.setVisibility(ImageButton.VISIBLE);
                    mNameLabel.setText("??i???n T??n Ch??? T??i Kho???n:");

                }else{
                    vcbI.setVisibility(ImageButton.INVISIBLE);
                    bvbI.setVisibility(ImageButton.INVISIBLE);
                    vcbI2.setVisibility(ImageButton.INVISIBLE);
                    bvbI2.setVisibility(ImageButton.INVISIBLE);
                    mTypeIDlabelA.setVisibility(TextView.INVISIBLE);
                    mTypeIDA.setVisibility(EditText.INVISIBLE);
                    chooseIDA.setVisibility(Spinner.INVISIBLE);
                    mSelectIdA.setVisibility(TextView.INVISIBLE);
                    mNameLabel.setVisibility(TextView.INVISIBLE);
                    mName.setVisibility(EditText.INVISIBLE);
                    mExpiryLabel.setVisibility(TextView.INVISIBLE);
                    mDisplayDate.setVisibility(EditText.INVISIBLE);
                    mCVVlabel.setVisibility(TextView.INVISIBLE);
                    mCVV.setVisibility(EditText.INVISIBLE);
                    mSelectId.setVisibility(TextView.INVISIBLE);
                    chooseID.setVisibility(EditText.INVISIBLE);
                    mTypeIDlabel.setVisibility(TextView.INVISIBLE);
                    mTypeID.setVisibility(EditText.INVISIBLE);
                    visaI.setVisibility(ImageView.INVISIBLE);
                    submit.setVisibility(Button.INVISIBLE);
                    mExpiryLabel2.setVisibility(TextView.INVISIBLE);
                    mDisplayDate2.setVisibility(TextView.INVISIBLE);
                    more.setVisibility(ImageButton.INVISIBLE);
                    msb.setVisibility(ImageButton.INVISIBLE);
                    vtb.setVisibility(ImageButton.INVISIBLE);
                    msb2.setVisibility(ImageView.INVISIBLE);
                    vtb2.setVisibility(ImageView.INVISIBLE);
                    chooseIDAb.setVisibility(RelativeLayout.INVISIBLE);
                    chooseIDb.setVisibility(RelativeLayout.INVISIBLE);
                    accountFlag = false;
                    ccIFlag = false;
                    dcFlag = false;
                    vcbATMFlag = false;
                    cardNumFlag = false;
                }

                if(text.length()==6){
                    updateData("type cardnum","'cardNum "+text+"' 'Flow: "+flowCheck()+"'");
                }

                submitCheck();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void submitCheck(){
        if (ccIFlag && cardNumFlag && cvvFlag && nameFlag && dateFlag) {
            submit.setBackgroundTintList(ColorStateList.valueOf(0xFF0087F3));
        } else if (dcFlag && cardNumFlag && dateFlag && idFlag2) {
            submit.setBackgroundTintList(ColorStateList.valueOf(0xFF0087F3));
        } else if (vcbATMFlag && nameFlag && cardNumFlag && dateFlag && idFlag2) {
            submit.setBackgroundTintList(ColorStateList.valueOf(0xFF0087F3));
        } else if (accountFlag && nameFlag && idFlag2) {
            submit.setBackgroundTintList(ColorStateList.valueOf(0xFF0087F3));
        } else{
            submit.setBackgroundTintList(ColorStateList.valueOf(0xFFA9AEB1));
        }
    }



    public static InputFilter getEditTextFilter() {
        return new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                boolean keepOriginal = true;
                StringBuilder sb = new StringBuilder(end - start);
                for (int i = start; i < end; i++) {
                    char c = source.charAt(i);
                    if (isCharAllowed(c)) // put your condition here
                        sb.append(c);
                    else
                        keepOriginal = false;
                }
                if (keepOriginal)
                    return null;
                else {
                    if (source instanceof Spanned) {
                        SpannableString sp = new SpannableString(sb);
                        TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                        return sp;
                    } else {
                        return sb;
                    }
                }
            }

            private boolean isCharAllowed(char c) {
                Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
                Matcher ms = ps.matcher(String.valueOf(c));
                return ms.matches();
            }
        };
    }

    public void openSuccess() {
        Intent intent = new Intent(this,success.class);
        startActivity(intent);
    }

    public void updateData(String newEvent, String newMetadata){
        db.child("Click Data").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("firebase", "Error getting data", task.getException());
                }else{ Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    Data oldD = task.getResult().getValue(Data.class);
                    List<String> event = oldD.event;
                    List<String> metadata = oldD.metadata;
                    List<String> time = oldD.time;
                    List<String> id = oldD.userId;
                    event.add(newEvent);
                    metadata.add(newMetadata);
                    time.add(Long.toString(currentTimeMillis()));
                    id.add(uniqueID);
                    Data newD = new Data(event,metadata,time,id);
                    db.child("Click Data").setValue(newD);


                }
            }
        });
    }

    public String flowCheck(){
        if(ccIFlag){
            return("VCB international credit card");

        }else if(vcbATMFlag){
            return("VCB ATM card");

        }else if(dcFlag){
            return("VCB debit card");

        }else if(accountFlag) {
            return("VCB account");
        }else{
            return("");
        }
    };

}