package com.felight.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleCalculatorActivity extends AppCompatActivity {

    public void doCalculation(View view){
        double num1=0;
        try {

            num1 = Double.parseDouble(etFirstNumber.getText().toString());
        }catch (NumberFormatException e){
            tvResult.setText("Please enter valid value for First Number");
            return;
        }

        double num2=0;
        try {
            num2 = Double.parseDouble(etSecondNumber.getText().toString());
        }catch (NumberFormatException e){
            tvResult.setText("Please enter valid value for Second Number");
            return;
        }

        switch (view.getId()) {
            case R.id.btnAdd:
                tvResult.setText(""+(num1+num2));

                break;

            case R.id.btnSub:
                tvResult.setText(""+(num1-num2));

                break;

            case R.id.btnMul:
                tvResult.setText(""+(num1*num2));

                break;

            case R.id.btnDiv:
                tvResult.setText(""+(num1/num2));

                break;


        }
    }







    private EditText etFirstNumber,etSecondNumber;
    private Button btnAdd,btnSub,btnMul,btnDiv;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        etFirstNumber=(EditText)findViewById(R.id.etFirstNumber);
        etSecondNumber=(EditText)findViewById(R.id.etSecondNumber);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnSub=(Button)findViewById(R.id.btnSub);
        btnMul=(Button)findViewById(R.id.btnMul);
        btnDiv=(Button)findViewById(R.id.btnDiv);
        tvResult=(TextView)findViewById(R.id.tvResult);

    }
}
