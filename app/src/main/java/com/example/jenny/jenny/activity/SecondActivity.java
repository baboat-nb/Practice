package com.example.jenny.jenny.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jenny.jenny.CoordinateParcelable;
import com.example.jenny.jenny.CoordinateSerializable;
import com.example.jenny.jenny.R;

public class SecondActivity extends AppCompatActivity {

    int sum = 0;
    TextView tvResult;
    Button btnOk;
    EditText editTextResult;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        sum = intent.getIntExtra("result", 0);
        Bundle bundle = intent.getBundleExtra("cBundle");
        bundle.getInt("x");
        bundle.getInt("y");
        bundle.getInt("z");

        CoordinateSerializable c2 = (CoordinateSerializable) intent.getSerializableExtra("cSerializable");

        CoordinateParcelable c3 = (CoordinateParcelable) intent.getParcelableExtra("cParcelable");
        int x = c3.x;

        initInstances();
    }

    private void initInstances() {

        editTextResult = (EditText) findViewById(R.id.editTextResult);


        tvResult = (TextView) findViewById(R.id.tvResult);
        tvResult.setText("Result = " + sum);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = editTextResult.getText().toString();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",result);

                setResult(RESULT_OK,returnIntent);
                finish();

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
