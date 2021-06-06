package BankFormProtoype.version0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText mCardNum;
    private TextView mDisplayDate;
    private TextView mNameLabel;
    private EditText mName;
    private TextView mExpiryLabel;
    private TextView mCVVlabel;
    private EditText mCVV;
    private TextView mSelectId;
    private TextView mTypeIDlabel;
    private EditText mTypeID;


    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.chooseID);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.idChoice, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        mDisplayDate = (TextView) findViewById(R.id.expiryDate);
        mCardNum = (EditText) findViewById(R.id.cardNum);
        mNameLabel = (TextView) findViewById(R.id.cardHolderLabel);
        mName = (EditText) findViewById(R.id.cardHolder);
        mExpiryLabel = (TextView) findViewById(R.id.expiryLabel);
        mCVVlabel = (TextView) findViewById(R.id.cvvlabel);
        mCVV = (EditText) findViewById(R.id.cvv);
        mSelectId = (TextView) findViewById(R.id.selectIDlabel);
        mTypeIDlabel = (TextView) findViewById(R.id.typeIDlabel);
        mTypeID = (EditText) findViewById(R.id.TypeID);

        mNameLabel.setVisibility(TextView.INVISIBLE);
        mName.setVisibility(EditText.INVISIBLE);
        mExpiryLabel.setVisibility(TextView.INVISIBLE);
        mDisplayDate.setVisibility(TextView.INVISIBLE);
        mCVVlabel.setVisibility(TextView.INVISIBLE);
        mCVV.setVisibility(EditText.INVISIBLE);
        mSelectId.setVisibility(TextView.INVISIBLE);
        spinner.setVisibility(Spinner.INVISIBLE);
        mTypeIDlabel.setVisibility(TextView.INVISIBLE);
        mTypeID.setVisibility(EditText.INVISIBLE);



        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getDatePicker().findViewById(getResources().getIdentifier("day","id","android")).setVisibility(View.GONE);
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

       mCardNum.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
               if(s.toString().trim().length() >0){
                   mNameLabel.setVisibility(TextView.VISIBLE);
                   mName.setVisibility(EditText.VISIBLE);
               }
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
               if(s.toString().trim().length() >0){
                   mExpiryLabel.setVisibility(TextView.VISIBLE);
                   mDisplayDate.setVisibility(EditText.VISIBLE);
                   mCVVlabel.setVisibility(TextView.VISIBLE);
                   mCVV.setVisibility(EditText.VISIBLE);
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
               if(s.toString().trim().length() >0){
                   mSelectId.setVisibility(TextView.VISIBLE);
                   spinner.setVisibility(EditText.VISIBLE);
                   mTypeIDlabel.setVisibility(TextView.VISIBLE);
                   mTypeID.setVisibility(EditText.VISIBLE);
               }
           }
       });


    }
}
