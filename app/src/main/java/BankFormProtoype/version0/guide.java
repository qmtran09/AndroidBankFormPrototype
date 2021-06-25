package BankFormProtoype.version0;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class guide extends AppCompatActivity {
    private boolean selFlag;
    private Button submit;
    private String val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_guide);

        Spinner chooseFlow = (Spinner) findViewById(R.id.chooseFlow);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.flowChoice, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        chooseFlow.setAdapter(adapter);

        submit = (Button) findViewById(R.id.begin);



        chooseFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String selected = parent.getItemAtPosition(position).toString();
                    if(selected.equals("Thẻ ATM VCB")){
                        selFlag = true;
                        submit.setBackgroundTintList(ColorStateList.valueOf(0xFF0087F3));
                        val = "ATM";

                    }
                    else if(selected.equals("Thẻ VISA VCB")){
                        selFlag = true;
                        submit.setBackgroundTintList(ColorStateList.valueOf(0xFF0087F3));
                        val = "VISA";
                    }
                    else if(selected.equals("Tài Khoản VCB")){
                        val = "TK";
                        selFlag = true;
                        submit.setBackgroundTintList(ColorStateList.valueOf(0xFF0087F3));
                    }else{
                        selFlag = false;
                        submit.setBackgroundTintList(ColorStateList.valueOf(0xFFA9AEB1));
                    }
                }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selFlag){
                    Intent intent = new Intent(guide.this,demoInfo.class);
                    intent.putExtra("flow",val);
                    startActivity(intent);
                }

            }
        });

    }
    public void openSuccess() {

    }
}