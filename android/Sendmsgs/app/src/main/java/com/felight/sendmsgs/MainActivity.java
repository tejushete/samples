package com.felight.sendmsgs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etMobNo,etMsg;
    private Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMobNo = (EditText) findViewById(R.id.etMoNo);
        etMsg = (EditText) findViewById(R.id.etMsg);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {

                     sendSMS(etMobNo.getText().toString(),etMsg.getText().toString());
                    Toast.makeText(getBaseContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                }
                catch
                        (Exception e) {
                    Toast.makeText(getBaseContext(),"SMS failed, please try again.", Toast.LENGTH_LONG).show();
                     }
            }
        });
    }


    public void sendSMS(String mobNo,String message){
        SmsManager smsManager=SmsManager.getDefault();

        smsManager.sendTextMessage("phoneNo",null,"sms message",null,null);

    }


}

