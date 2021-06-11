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

        map = (Button) findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
    }
    public void openMap() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        System.out.println("OIEWFHJWEOIHFOIWEHFOEWHFOIWEFH");
    }
}
