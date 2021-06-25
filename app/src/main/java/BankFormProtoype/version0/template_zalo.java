package BankFormProtoype.version0;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class template_zalo extends AppCompatActivity {

    private Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


//        ActionBar bar = getSupportActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0087F3")));
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_template_zalo);
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String str = intent.getStringExtra("flow2");

        map = (Button) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap(str);
            }
        });
    }
    public void openMap(String str) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("flow3",str);
        startActivity(intent);

    }
}
