package com.example.jenny.jenny.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jenny.jenny.Coordinate;
import com.example.jenny.jenny.CoordinateParcelable;
import com.example.jenny.jenny.CoordinateSerializable;
import com.example.jenny.jenny.R;
import com.example.jenny.jenny.view.CustomViewGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvHello, tvResult;
    EditText editTextHello, editNum1, editNum2;
    Button btnCopy, btnCal;
    RadioButton rbPlus, rbMinus, rbMultiply, rbDivide;
    RadioGroup rGroup;
    CustomViewGroup ViewGroup1, ViewGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Toast.makeText(MainActivity.this, "Width =" + width + " Height =" + height, Toast.LENGTH_SHORT).show();

        InitInstances();
    }

    private void InitInstances() {
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        tvHello.setText(Html.fromHtml("<font color=\"#ff0000\"> La La La </font> <a href=\"https://www.google.co.th\"> link </a>"));

        editTextHello = (EditText) findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    tvHello.setText(editTextHello.getText());
                    return true;
                }
                return false;
            }
        });

        btnCopy = (Button) findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);

        editNum1 = (EditText) findViewById(R.id.editNum1);
        editNum2 = (EditText) findViewById(R.id.editNum2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnCal = (Button) findViewById(R.id.btnCal);
        btnCal.setOnClickListener(this);

        rbPlus = (RadioButton) findViewById(R.id.rbPlus);
        rbMinus = (RadioButton) findViewById(R.id.rbMinus);
        rbMultiply = (RadioButton) findViewById(R.id.rbMultiply);
        rbDivide = (RadioButton) findViewById(R.id.rbDivide);

        rGroup = (RadioGroup) findViewById(R.id.rGroup);

        ViewGroup1 = (CustomViewGroup) findViewById(R.id.ViewGroup1);
        ViewGroup2 = (CustomViewGroup) findViewById(R.id.ViewGroup2);

        ViewGroup1.setButtonText("Hello");
        ViewGroup2.setButtonText("World");
    }

    @Override
    public void onClick(View v) {
        if (v == btnCopy) {
            tvHello.setText(editTextHello.getText());
        } else if (v == btnCal) {
            int num1 = 0;
            int num2 = 0;
            int sum = 0;
            try {
                num1 = Integer.parseInt(editNum1.getText().toString());
            } catch (NumberFormatException e) {
            }

            try {
                num2 = Integer.parseInt(editNum2.getText().toString());
            } catch (NumberFormatException e) {
            }

            switch (rGroup.getCheckedRadioButtonId()) {
                case R.id.rbPlus:
                    sum = num1 + num2;
                    break;
                case R.id.rbMinus:
                    sum = num1 - num2;
                    break;
                case R.id.rbMultiply:
                    sum = num1 * num2;
                    break;
                case R.id.rbDivide:
                    sum = num1 / num2;
                    break;
            }

            tvResult.setText(sum + "");
            Log.d("Calculation", "Result =" + sum);
            Toast.makeText(MainActivity.this, "Result =" + sum, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("result", sum);
            Coordinate c1 = new Coordinate();
            c1.x = 5;
            c1.y = 10;
            c1.z = 20;
            Bundle bundle = new Bundle();
            bundle.putInt("x", c1.x);
            bundle.putInt("y", c1.y);
            bundle.putInt("z", c1.z);
            intent.putExtra("cBundle",bundle);

            CoordinateSerializable c2 = new CoordinateSerializable();
            c2.x =5;
            c2.y =10;
            c2.z =20;
            intent.putExtra("cSerializable",c2);

            CoordinateParcelable c3 = new CoordinateParcelable();
            c3.x = 5;
            c3.y = 10;
            c3.z = 20;
            intent.putExtra("cParcelable" ,c3);

            startActivityForResult(intent,12345);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 12345){
            if(resultCode == RESULT_OK){
                Toast.makeText(MainActivity.this, data.getStringExtra("result").toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString("text",tvResult.getText().toString());
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //tvResult.setText(savedInstanceState.getString("text"));
    }

}
