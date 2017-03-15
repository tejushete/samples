package com.felight.instagramclone;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Button btnGallary,btnCamera;
    int CAMERA_REQUEST = 1, GALLERY_REQUEST = 0;
    public Bitmap img,smallimg;
    boolean firstLoad = true;
    public static Bitmap doGreyscale(Bitmap src) {
        // constant factors
        final double GS_RED = 0.299;
        final double GS_GREEN = 0.587;
        final double GS_BLUE = 0.114;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        // return final image
        return bmOut;
    }

	public static Bitmap doRedScale(Bitmap src) {
        // constant factors
        final double GS_RED = 0.7;
        final double GS_GREEN = 0.0;
        final double GS_BLUE = 0.0;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, 0, 0));
            }
        }
        // return final image
        return bmOut;
    }

    public static Bitmap doGreenScale(Bitmap src) {
        // constant factors
        final double GS_RED = 0.0;
        final double GS_GREEN = 0.7;
        final double GS_BLUE = 0.0;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, 0, G, 0));
            }
        }
        // return final image
        return bmOut;
    }


    public static Bitmap doBlueScale(Bitmap src) {
        // constant factors
        final double GS_RED = 0.0;
        final double GS_GREEN = 0.0;
        final double GS_BLUE = 0.9;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, 0, 0, B));
            }
        }
        // return final image
        return bmOut;
    }


    public static Bitmap doPinkScale(Bitmap src) {
        // constant factors
        final double GS_RED = 0.3;
        final double GS_GREEN = 0.0;
        final double GS_BLUE = 0.0;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, 0, 0));
            }
        }
        // return final image
        return bmOut;
    }


    public static Bitmap doSaturateScale(Bitmap src) {
        // constant factors
        final double GS_RED = 0.1;
        final double GS_GREEN = 0.1;
        final double GS_BLUE = 0.1;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        // return final image
        return bmOut;
    }

    ImageView im, iv, iv2, iv3, iv4, iv5, iv6;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.imageView2);
       btnGallary=(Button)findViewById(R.id.btnGallary);
       btnCamera=(Button)findViewById(R.id.btnCamera);
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Bitmap img ;
                img = BitmapFactory.decodeResource(getResources(), R.drawable.smallteju);
                iv.setImageBitmap(doGreyscale(img));
            }
        };
        handler.postDelayed(r, 1);

		iv2 = (ImageView)findViewById(R.id.imageView3);
		Handler handler2 = new Handler();
        Runnable r2= new Runnable() {
            public void run() {
                iv2.setImageBitmap(doRedScale(BitmapFactory.decodeResource(getResources(), R.drawable.smallteju)));
            }
        };
        handler2.postDelayed(r2, 1);

       iv3 = (ImageView)findViewById(R.id.imageView4);
       Handler handler3 = new Handler();
       Runnable r3= new Runnable() {
           public void run() {
               iv3.setImageBitmap(doGreenScale(BitmapFactory.decodeResource(getResources(), R.drawable.smallteju)));
           }
       };
       handler3.postDelayed(r3, 1);


       iv4 = (ImageView)findViewById(R.id.imageView5);
       Handler handler4 = new Handler();
       Runnable r4= new Runnable() {
           public void run() {
               iv4.setImageBitmap(doBlueScale(BitmapFactory.decodeResource(getResources(), R.drawable.smallteju)));
           }
       };
       handler4.postDelayed(r4, 1);

       iv5 = (ImageView)findViewById(R.id.imageView6);
       Handler handler5 = new Handler();
       Runnable r5= new Runnable() {
           public void run() {
               iv5.setImageBitmap(doPinkScale(BitmapFactory.decodeResource(getResources(), R.drawable.smallteju)));
           }
       };
       handler5.postDelayed(r5, 1);

       iv6 = (ImageView)findViewById(R.id.imageView7);
       Handler handler6 = new Handler();
       Runnable r6= new Runnable() {
           public void run() {
               iv6.setImageBitmap(doSaturateScale(BitmapFactory.decodeResource(getResources(), R.drawable.smallteju)));
           }
       };
       handler6.postDelayed(r6, 1);

       iv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               im = (ImageView)findViewById(R.id.imageView);
               im.setImageResource(R.drawable.loading);
               Handler handler = new Handler();
               Runnable r= new Runnable() {
                   public void run() {
                       Bitmap bitmap;
                       if(firstLoad == true){
                           bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.teju);
                       }else{
                           bitmap = img;
                       }
                       im.setImageBitmap(doGreyscale(bitmap));
                   }
               };
               handler.postDelayed(r, 0);
           }
       });


       iv2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               im = (ImageView)findViewById(R.id.imageView);
               im.setImageResource(R.drawable.loading);
               Handler handler = new Handler();
               Runnable r= new Runnable() {
                   public void run() {
                       Bitmap bitmap;
                       if(firstLoad == true){
                           bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.teju);
                       }else{
                           bitmap = img;
                       }

                       im.setImageBitmap(doRedScale(img));
                   }
               };
               handler.postDelayed(r, 1);
           }
       });


       iv3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               im = (ImageView)findViewById(R.id.imageView);
               im.setImageResource(R.drawable.loading);
               Handler handler = new Handler();
               Runnable r= new Runnable() {
                   public void run() {
                       Bitmap bitmap;
                       if(firstLoad == true){
                           bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.teju);
                       }else{
                           bitmap = img;
                       }



                       im.setImageBitmap(doGreenScale(img));
                   }
               };
               handler.postDelayed(r, 1);
           }
       });


       iv4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               im = (ImageView)findViewById(R.id.imageView);
               im.setImageResource(R.drawable.loading);
               Handler handler = new Handler();
               Runnable r= new Runnable() {
                   public void run() {
                       Bitmap bitmap;
                       if(firstLoad == true){
                           bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.teju);
                       }else{
                           bitmap = img;
                       }
                       im.setImageBitmap(doBlueScale(img));
                   }
               };
               handler.postDelayed(r, 1);
           }
       });

       iv5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               im = (ImageView)findViewById(R.id.imageView);
               im.setImageResource(R.drawable.loading);
               Handler handler = new Handler();
               Runnable r= new Runnable() {
                   public void run() {
                       Bitmap bitmap;
                       if(firstLoad == true){
                           bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.teju);
                       }else{
                           bitmap = img;
                       }
                       im.setImageBitmap(doPinkScale(img));
                   }
               };
               handler.postDelayed(r, 1);
           }
       });

       iv6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bitmap bitmap;
               if(firstLoad == true){
                   bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.teju);
               }else{
                   bitmap = img;
               }
               im = (ImageView)findViewById(R.id.imageView);
               im.setImageResource(R.drawable.loading);
               Handler handler = new Handler();
               Runnable r= new Runnable() {
                   public void run() {
                       im.setImageBitmap(doSaturateScale(img));
                   }
               };
               handler.postDelayed(r, 1);
           }
       });


       btnGallary.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                   Intent intent = new Intent(Intent.ACTION_PICK,
                           MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                   startActivityForResult(intent,0);
               }


       });
       btnCamera.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
               cameraIntent.putExtra("crop", "true");
               cameraIntent.putExtra("aspectX", 0);
               cameraIntent.putExtra("aspectY", 0);
               cameraIntent.putExtra("outputX", 550);
               cameraIntent.putExtra("outputY", 250);
               startActivityForResult(cameraIntent, CAMERA_REQUEST);
           }

       });
    }
    public void loadAllthumbnails(){

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                iv.setImageResource(R.drawable.loading);
                iv.setImageBitmap(doGreyscale(smallimg));
            }
        };
        handler.postDelayed(r, 0);

        Handler handler2 = new Handler();
        Runnable r2= new Runnable() {
            public void run() {
                iv2.setImageResource(R.drawable.loading);

                iv2.setImageBitmap(doRedScale(smallimg));
            }
        };        handler2.postDelayed(r2, 0);


        Handler handler3 = new Handler();
        Runnable r3= new Runnable() {
            public void run() {
                iv3.setImageResource(R.drawable.loading);

                iv3.setImageBitmap(doGreenScale(smallimg));
            }
        };        handler3.postDelayed(r3, 0);

        Handler handler4 = new Handler();
        Runnable r4= new Runnable() {
            public void run() {
                iv4.setImageResource(R.drawable.loading);

                iv4.setImageBitmap(doBlueScale(smallimg));
            }
        };        handler4.postDelayed(r4, 0);

        Handler handler5 = new Handler();
        Runnable r5= new Runnable() {
            public void run() {
                iv5.setImageResource(R.drawable.loading);

                iv5.setImageBitmap(doPinkScale(smallimg));
            }
        };        handler5.postDelayed(r5, 0);

        Handler handler6 = new Handler();
        Runnable r6= new Runnable() {
            public void run() {
                iv6.setImageResource(R.drawable.loading);

                iv6.setImageBitmap(doSaturateScale(smallimg));
            }
        };        handler6.postDelayed(r6, 0);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            img = mphoto;
            smallimg = img;
            ImageView mimageView;
            mimageView = (ImageView)findViewById(R.id.imageView);
            mimageView.setImageBitmap(mphoto);
            firstLoad = false;
            loadAllthumbnails();
        }

        try {
            // When an Image is picked
            if (requestCode == GALLERY_REQUEST) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                firstLoad = false;

                ImageView mimageView;
                mimageView = (ImageView)findViewById(R.id.imageView);
                Bitmap mphoto = BitmapFactory
                        .decodeFile(imgDecodableString);
                img = mphoto;
                smallimg =  img;
                mimageView.setImageBitmap(mphoto);
                loadAllthumbnails();

            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
