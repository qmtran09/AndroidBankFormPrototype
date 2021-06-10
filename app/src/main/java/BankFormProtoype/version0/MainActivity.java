package BankFormProtoype.version0;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;



import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    private TextView mSelectIdA;
    private TextView mTypeIDlabelA;
    private EditText mTypeIDA;
    private ImageButton vcbI;
    private ImageView vcbI2;
    private ImageButton bvbI;
    private ImageView bvbI2;
    private boolean accountFlag;
    private boolean ccIFlag;
    private ImageView visaI;
    private Button submit;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        mCardNum = (EditText) findViewById(R.id.cardNum);
        mNameLabel = (TextView) findViewById(R.id.cardHolderLabel);
        mName = (EditText) findViewById(R.id.cardHolder);
        mExpiryLabel = (TextView) findViewById(R.id.expiryLabel);
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

        mNameLabel.setVisibility(TextView.INVISIBLE);
        mName.setVisibility(EditText.INVISIBLE);
        mExpiryLabel.setVisibility(TextView.INVISIBLE);
        mDisplayDate.setVisibility(TextView.INVISIBLE);
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
        //make name all caps only accept alphabet
        mName.setFilters(new InputFilter[]{getEditTextFilter(),new InputFilter.AllCaps()});

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSuccess();
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
                }

            }
        });

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
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getDatePicker().findViewById(getResources().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
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

//        mCardNum.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString().trim().length() > 0) {
//                    mNameLabel.setVisibility(TextView.VISIBLE);
//                    mName.setVisibility(EditText.VISIBLE);
//                }
//            }
//        });

        vcbI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameLabel.setVisibility(TextView.VISIBLE);
                mName.setVisibility(EditText.VISIBLE);
                bvbI.setVisibility(ImageButton.INVISIBLE);
                vcbI.setVisibility(ImageButton.INVISIBLE);
                vcbI2.setVisibility(ImageButton.VISIBLE);
                mNameLabel.setText("Điền Tên Chủ Tài Khoản:");
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
                mNameLabel.setText("Điền Tên Chủ Tài Khoảng:");
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
                    mTypeIDlabelA.setVisibility(TextView.VISIBLE);
                    mTypeIDA.setVisibility(EditText.VISIBLE);

                }
                else if(s.toString().trim().length() > 0 && ccIFlag){
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
                if (s.toString().trim().length() > 0) {
                    submit.setVisibility(Button.VISIBLE);
                    //mSelectId.setVisibility(TextView.VISIBLE);
                    //chooseID.setVisibility(EditText.VISIBLE);
                    //mTypeIDlabel.setVisibility(TextView.VISIBLE);
                    //mTypeID.setVisibility(EditText.VISIBLE);
                }
            }
        });
        mName.addTextChangedListener(new TextValidator(mName) {
            @Override
            public void validate(TextView textView, String text) {
                if(text.contains(" ")){

                }else{
                    textView.setError("Name must contain a space");
                }

            }
        });

        mCVV.addTextChangedListener(new TextValidator(mCVV) {
            @Override
            public void validate(TextView textView, String text) {
                if(text.length()<3){
                    textView.setError("CVV có 3 số");
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


                  mCardNum.addTextChangedListener(new TextValidator(mCardNum) {
                      @Override
                      public void validate(TextView textView, String text) {
                          if(text.length() != 14 && ccIFlag){
                              mCardNum.setError("Thẻ VISA cần 14 số");
                          }
                      }
                  });


                }else if(text.length()==13){
                    vcbI.setVisibility(ImageButton.VISIBLE);
                    bvbI.setVisibility(ImageButton.VISIBLE);
                    accountFlag = true;
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
                    accountFlag = false;
                    ccIFlag = false;

                }
            }
        });

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
}