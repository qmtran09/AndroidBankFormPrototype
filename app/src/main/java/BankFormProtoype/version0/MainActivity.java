package BankFormProtoype.version0;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.time.LocalDate;
import java.time.Month;



import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

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

    private int cMonth;
    private int cYear;
    private boolean idFlag2;
    private int idFlag;
    private boolean nameFlag;
    private boolean cvvFlag;
    private boolean cardNumFlag;
    private boolean dateFlag;
    private int counter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0087F3")));

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
                       mTypeIDlabel.setText("Điền Số Chứng Minh Nhân Dân:");
                       mTypeID.setInputType(InputType.TYPE_CLASS_NUMBER);
                       mTypeID.setText("");
                       idFlag = 1;

                   }
                   else if(selected.equals("CCND")){
                       mTypeIDlabel.setVisibility(TextView.VISIBLE);
                       mTypeID.setInputType(InputType.TYPE_CLASS_NUMBER);
                       mTypeID.setVisibility(EditText.VISIBLE);
                       mTypeIDlabel.setText("Điền Số Căn Cước Nhân Dân:");
                       mTypeID.setText("");
                       idFlag = 2;
                   }
                   else if(selected.equals("Hộ Chiếu")){
                       mTypeID.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                       mTypeIDlabel.setVisibility(TextView.VISIBLE);
                       mTypeID.setVisibility(EditText.VISIBLE);
                       mTypeIDlabel.setText("Điền Số Hố Chiếu:");
                       mTypeID.setText("");
                       idFlag = 3;
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
                        mTypeID.setError("Số CMND có 9 số");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                }
                else if(idFlag==2){
                    if(text.length()!=12){

                        mTypeID.setError("Số CCND có 12 số");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                }
                else if(idFlag==3){
                    if(text.length()!=8){


                        mTypeID.setError("Hộ Chiếu có 8 số/Chứ");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                } else {
                    idFlag2 = false;
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
                        mTypeIDlabelA.setText("Điền Số Chứng Minh Nhân Dân:");
                        idFlag = 1;
                        mTypeIDA.setInputType(InputType.TYPE_CLASS_NUMBER);
                        mTypeIDA.setText("");
                    }
                    else if(selected.equals("CCND")){
                        mTypeIDlabelA.setVisibility(TextView.VISIBLE);
                        mTypeIDA.setVisibility(EditText.VISIBLE);
                        mTypeIDlabelA.setText("Điền Số Căn Cước Nhân Dân:");
                        idFlag = 2;
                        mTypeIDA.setInputType(InputType.TYPE_CLASS_NUMBER);
                        mTypeIDA.setText("");
                    }
                    else if(selected.equals("Hộ Chiếu")){
                        mTypeIDlabelA.setVisibility(TextView.VISIBLE);
                        mTypeIDA.setVisibility(EditText.VISIBLE);
                        mTypeIDlabelA.setText("Điền Số Hố Chiếu:");
                        mTypeIDA.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                        idFlag = 3;
                        mTypeIDA.setText("");
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

                        mTypeIDA.setError("Số CMND có 9 số");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                }
                else if(idFlag==2){
                    if(text.length()!=12){

                        mTypeIDA.setError("Số CCND có 12 số");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                }
                else if(idFlag==3){
                    if(text.length()!=8){

                        mTypeIDA.setError("Hộ Chiếu có 8 số/Chứ");
                        idFlag2 = false;
                    }else{
                        idFlag2 = true;
                    }

                } else {
                    idFlag2 = false;
                }
                submitCheck();
            }

        });

        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (ccIFlag && cardNumFlag && cvvFlag && nameFlag && dateFlag) {
                    openSuccess();
                } else if (dcFlag && cardNumFlag && dateFlag && idFlag2) {
                    openSuccess();
                } else if (vcbATMFlag && nameFlag && cardNumFlag && dateFlag && idFlag2) {
                    openSuccess();
                } else if (accountFlag && nameFlag && idFlag2) {
                    openSuccess();
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

        mDisplayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
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
//                mTypeIDlabel.setVisibility(TextView.VISIBLE);
//                mTypeID.setVisibility(EditText.VISIBLE);
                if(!ccIFlag){
                    mSelectId.setVisibility(TextView.VISIBLE);
                    chooseID.setVisibility(Spinner.VISIBLE);
                    chooseIDb.setVisibility(RelativeLayout.VISIBLE);

                }





                if(vcbATMFlag){
                    if(year > cYear){
                        mDisplayDate.setError("Năm Cấp Thẻ Không Thể hơn năm này");

                        dateFlag = false;
                    } else if(year==cYear && month>cMonth){
                        mDisplayDate.setError("Tháng Cấp Thẻ Không Thể hơn tháng này");

                        dateFlag = false;
                    } else {
                        mDisplayDate.setError(null);
                        dateFlag = true;
                    }

                }else{
                    if(year < cYear){
                        mDisplayDate.setError("đã quá ngày hết hạn");

                        dateFlag = false;
                    } else if(year==cYear && month<cMonth){
                        mDisplayDate.setError("đã quá ngày hết hạn");

                        dateFlag = false;
                    } else {
                        mDisplayDate.setError(null);
                        dateFlag = true;
                    }

                }

                submitCheck();
            }
        };

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
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
                        mDisplayDate2.setError("Năm Cấp Thẻ Không Thể hơn năm này");
                        mDisplayDate2.requestFocus();
                        dateFlag = false;
                    } else if(year==cYear && month>cMonth){
                        mDisplayDate2.setError("Tháng Cấp Thẻ Không Thể hơn tháng này");
                        mDisplayDate2.requestFocus();
                        dateFlag = false;
                    } else {
                        dateFlag = true;
                        mDisplayDate2.setError(null);
                    }

                }else{
                    if(year < cYear){
                        mDisplayDate2.setError("đã quá ngày hết hạn");
                        mDisplayDate2.requestFocus();
                        dateFlag = false;
                    } else if(year==cYear && month<cMonth){
                        mDisplayDate2.setError("đã quá ngày hết hạn");
                        mDisplayDate2.requestFocus();
                        dateFlag = false;
                    } else {
                        mDisplayDate2.setError(null);
                        dateFlag = true;
                    }

                }

                submitCheck();

            }
        };



        vcbI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameLabel.setVisibility(TextView.VISIBLE);
                mName.setVisibility(EditText.VISIBLE);
                bvbI.setVisibility(ImageButton.INVISIBLE);
                vcbI.setVisibility(ImageButton.INVISIBLE);
                more.setVisibility(ImageButton.INVISIBLE);
                vtb.setVisibility(ImageButton.INVISIBLE);
                msb.setVisibility(ImageButton.INVISIBLE);
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
                more.setVisibility(ImageButton.INVISIBLE);
                vtb.setVisibility(ImageButton.INVISIBLE);
                msb.setVisibility(ImageButton.INVISIBLE);
                mNameLabel.setText("Điền Tên Chủ Tài Khoản:");
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
                mNameLabel.setText("Điền Tên Chủ Tài Khoản:");
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
                mNameLabel.setText("Điền Tên Chủ Tài Khoản:");
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
                    mExpiryLabel.setText("Ngày Hết Hạn:");
                    mExpiryLabel.setVisibility(TextView.VISIBLE);
                    mDisplayDate.setVisibility(EditText.VISIBLE);
                    mCVVlabel.setVisibility(TextView.VISIBLE);
                    mCVV.setVisibility(EditText.VISIBLE);

                }
                else if(s.toString().trim().length() > 0 && vcbATMFlag){
                    mExpiryLabel.setText("Ngày Cấp Thẻ:");
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
                    textView.setError("Tên Phái có dấu cách");
                    nameFlag = false;
                }
                submitCheck();

            }
        });

        mCVV.addTextChangedListener(new TextValidator(mCVV) {
            @Override
            public void validate(TextView textView, String text) {
                if(text.length()<3){
                    textView.setError("CVV có 3 số (3 số ở mặt sau của thẻ)");
                    cvvFlag = false;
                } else {
                    cvvFlag = true;
                }

                submitCheck();
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
                      mCardNum.setError("Thẻ VISA cần 16 số");
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
                       mCardNum.setError("Thẻ debit cần 16 số");
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
                      mCardNum.setError("Thẻ ATM cần 19 số");
                      cardNumFlag = false;
                  } else{
                      cardNumFlag = true;
                  }

              }
              //acct VCB and BVB
              else if(text.length()==13){
                    vcbI.setVisibility(ImageButton.VISIBLE);
                    bvbI.setVisibility(ImageButton.VISIBLE);
                    more.setVisibility(ImageButton.VISIBLE);
                    accountFlag = true;
                    cardNumFlag = true;

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
}