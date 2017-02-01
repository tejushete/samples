package com.felight.currencyconvertor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CurrencyConvertorActivity extends AppCompatActivity {

    final double USD_TO_INR = 68.2;
    final double USD_TO_EUR = 0.93;
    final double USD_TO_POUND = 0.80;
    final double INR_TO_EUR = 0.014;
    final double INR_TO_POUND = 0.012;
    final double EUR_TO_POUND = 0.85;

    //INR = 0, USD = 1, EUR = 2, POUND = 3
    private double[][] convertor = {    {1, 1.0/USD_TO_INR, INR_TO_EUR, INR_TO_POUND},
                                        {USD_TO_INR, 1 , USD_TO_EUR ,USD_TO_POUND},
                                        {1.0/INR_TO_EUR , 1.0/USD_TO_EUR ,1 ,EUR_TO_POUND},
                                        {1.0/INR_TO_POUND ,1.0/USD_TO_POUND,1.0/EUR_TO_POUND ,1}
                                    };
    private int inputIndex = 0, outputIndex = 0;
    private Spinner spCurrencySource;
    private Spinner spCurrencyDestination;
    private EditText etCurrency1;
    private TextView tvResult;

    private Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_convertor_layout);
        spCurrencySource=(Spinner)findViewById(R.id.spCurrencySource);
        spCurrencyDestination=(Spinner)findViewById(R.id.spCurrencyDestination);
        etCurrency1=(EditText)findViewById(R.id.etCurrency);
        tvResult=(TextView)findViewById(R.id.tvResult);
        btnConvert = (Button)findViewById(R.id.btnConvert);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double inputValue=0;
                try {
                    inputValue = Double.parseDouble(etCurrency1.getText().toString());
                }catch (Exception e){
                    Toast.makeText(CurrencyConvertorActivity.this, "Enter proper value", Toast.LENGTH_SHORT).show();
                }
                double output = ((int)(inputValue * convertor[inputIndex][outputIndex]*100))/100.0;
                String displayText = String.valueOf(output);
                tvResult.setText(displayText);
            }
        });
    }
@Override
protected void onStart() {
    super.onStart();
    spCurrencyDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            outputIndex = i;
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView){

        }
    });

    spCurrencySource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            inputIndex = i;
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView){

        }
    });

}


}
