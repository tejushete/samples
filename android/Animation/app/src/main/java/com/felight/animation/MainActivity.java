package com.felight.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import static com.felight.animation.R.id.btnZoomIn;

public class MainActivity extends AppCompatActivity {
    private ImageView ivResult;
    private Button btnFadeIn;
    private Button btnFadeOut;
    private Button btnZoomIn;
    private Button btnZoomOut;
    private Button btnRotate;
    private Button btnMove;
    private Button btnSlideUp;
    private Button btnSlideDown;
    private Button btnBounce;
    private Button btnShake;
    private Button btnCrossFade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivResult=(ImageView)findViewById(R.id.ivResult);
        btnFadeIn = (Button)findViewById(R.id.btnFadeIn);
        btnFadeOut=(Button)findViewById(R.id.btnFadeOut);
        btnZoomIn=(Button)findViewById(R.id.btnZoomIn);
        btnZoomOut=(Button)findViewById(R.id.btnZoomOut);
        btnRotate=(Button)findViewById(R.id.btnRotate);
        btnMove=(Button) findViewById(R.id.btnMove);
        btnSlideUp=(Button)findViewById(R.id.btnSlideUp);
        btnSlideDown=(Button)findViewById(R.id.btnSlideDown);
        btnBounce=(Button)findViewById(R.id.btnBounce);
        btnShake=(Button)findViewById(R.id.btnShake);
        btnCrossFade=(Button)findViewById(R.id.btnCrossFade);



        final Animation fadeInAnimation= AnimationUtils.loadAnimation(this,R.anim.fadein);
        btnFadeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(fadeInAnimation);

            }
        });
        final Animation fadeOutAnimation= AnimationUtils.loadAnimation(this,R.anim.fadeout);
        btnFadeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(fadeOutAnimation);
            }
        });
        final Animation zoomin=AnimationUtils.loadAnimation(this,R.anim.zoomin);
        btnZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(zoomin);
            }
        });
        final Animation zoomout=AnimationUtils.loadAnimation(this,R.anim.zoomout);
        btnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(zoomout);
            }
        });
        final Animation rotate=AnimationUtils.loadAnimation(this,R.anim.rotate);
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(rotate);
            }
        });
        final Animation move=AnimationUtils.loadAnimation(this,R.anim.moveltor);

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(move);
            }
        });
        final Animation slideup=AnimationUtils.loadAnimation(this,R.anim.slideup);
        btnSlideUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(slideup);
            }
        });
        final Animation slidedown=AnimationUtils.loadAnimation(this,R.anim.slidedown);
        btnSlideDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(slidedown);
            }
        });
        final Animation bounce=AnimationUtils.loadAnimation(this,R.anim.bounce);
        btnBounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(bounce);
            }
        });

        final Animation shake=AnimationUtils.loadAnimation(this,R.anim.shake);
        btnShake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(shake);
            }
        });
        final Animation crossfade=AnimationUtils.loadAnimation(this,R.anim.crossfade);
        btnCrossFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivResult.startAnimation(crossfade);
            }
        });







    }
}
