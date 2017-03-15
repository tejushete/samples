package com.felight.activitynavigator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityNavigator extends AppCompatActivity implements View.OnClickListener {
    public static final String NEWs_TYPE="NEWS_TYPE";

    Button btnGreetUser, btnCalculator,btnGoogleNews,btnFelightNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        btnGreetUser = (Button)findViewById(R.id.btnGreetUser);
        btnGreetUser.setOnClickListener(this);

        btnCalculator = (Button)findViewById(R.id.btnCalculator);
        btnCalculator.setOnClickListener(this);


        btnGoogleNews = (Button)findViewById(R.id.btnGoogleNews);
        btnGoogleNews.setOnClickListener(this);



        btnFelightNews = (Button)findViewById(R.id.btnFelightNews);
        btnFelightNews.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnGreetUser:
                Intent intentGreetUser=new Intent(this,GreetUserActivity.class);
                startActivity(intentGreetUser);
                Toast.makeText(ActivityNavigator.this, "btn greet user", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnCalculator:
                Intent intentCalculator=new Intent(this,SimpleCalculatorActivity.class);
                startActivity(intentCalculator);
                Toast.makeText(ActivityNavigator.this, "btn calculator", Toast.LENGTH_LONG).show();
                break;


            case R.id.btnGoogleNews:
                Intent intentGoogleNews=new Intent(this,NewsActivity.class);
                intentGoogleNews.putExtra(NEWs_TYPE,"Google News");
                startActivity(intentGoogleNews);
                Toast.makeText(ActivityNavigator.this, "btn google news", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnFelightNews:
                Intent intentFelightNews=new Intent(this,NewsActivity.class);
                intentFelightNews.putExtra(NEWs_TYPE,"Felight News");
                startActivity(intentFelightNews);
                Toast.makeText(ActivityNavigator.this, "btn Felight News", Toast.LENGTH_LONG).show();
                break;
            default:
        }
    }
}
