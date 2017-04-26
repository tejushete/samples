package com.example.studentbiodata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity{

    GridView gVReferencesDetails;
    gridAdapter mGridAdapter;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details);

        gVReferencesDetails = (GridView)findViewById(R.id.gvDetails);//grid view references
        mGridAdapter = new gridAdapter(this);
        gVReferencesDetails.setAdapter(mGridAdapter);

        index = getIntent().getExtras().getInt("index");

        student s = sharedData.studentsInfoList.get(index);

        //logs for debugging crash issues.
        Log.d("<>", 1+"");
        ((TextView)findViewById(R.id.tvNameDetails)).setText(s.getName());
        Log.d("<>", 2+"");
        ((TextView)findViewById(R.id.tvForHName)).setText(s.getForHName());
        Log.d("<>", 3+"");
        ((TextView)findViewById(R.id.tvForHOccu)).setText(s.getForHOccu());
        Log.d("<>", 4+"");
        ((TextView)findViewById(R.id.tvMomName)).setText(s.getMomName());
        Log.d("<>", 5+"");
        ((TextView)findViewById(R.id.tvMomOccu)).setText(s.getMomOccu());
        Log.d("<>", 6+"");
        ((TextView)findViewById(R.id.tvDob)).setText(s.getDob());
        Log.d("<>", 7+"");
        ((TextView)findViewById(R.id.tvAgeDetails)).setText(s.getAge());
        Log.d("<>", 8+"");
        ((TextView)findViewById(R.id.tvCurrAddress)).setText(s.getPresentAddress());
        Log.d("<>", 9+"");
        ((TextView)findViewById(R.id.tvPermAddress)).setText(s.getPermanentAddress());
        Log.d("<>", 10+"");
        ((TextView)findViewById(R.id.tvMobileNo)).setText(s.getMobileNo());
        Log.d("<>", 11+"");
        ((TextView)findViewById(R.id.tvEmailId)).setText(s.getEmailId());
        Log.d("<>", 12+"");
        ((TextView)findViewById(R.id.tvExperience)).setText(s.getExperience());
        Log.d("<>", 13+"");
        ((TextView)findViewById(R.id.tvCaste)).setText(s.getCaste());
        Log.d("<>", 14+"");
        ((TextView)findViewById(R.id.tvDesignation)).setText(s.getDesignation());
        Log.d("<>", 15+"");
        ((TextView)findViewById(R.id.tvWorkLocation)).setText(s.getWorkLocation());
        Log.d("<>", 16+"");

        String mEncodedString = s.getmEncodedPic();
        byte[] data = Base64.decode(mEncodedString, Base64.DEFAULT);

        Bitmap bmp;
        if(data.length == 0){
          bmp =  BitmapFactory.decodeResource(getResources(), R.drawable.blank_photo);
        }else{
            bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        }
        ImageView iv = (ImageView)findViewById(R.id.ivPicDetail);
        iv.setImageBitmap(bmp);

    }

    private class gridAdapter extends BaseAdapter {

        Context mContext;

        gridAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View convertView, ViewGroup viewGroup) {
            View grid = convertView;
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (grid == null) {
                grid = new View(mContext);
                grid = inflater.inflate(R.layout.show_single_reference, null);
            }

            Log.d("<><>", i + "");
            TextView tv = (TextView) grid.findViewById(R.id.tvShowRef);

            if (i == 0) {
                tv.setText("NAME");
                tv.setTypeface(null, Typeface.BOLD);
            } else if (i == 1) {
                tv.setText("COMPANY NAME");
                tv.setTypeface(null, Typeface.BOLD);
            } else if (i == 2) {
                tv.setText("CONTACT NO");
                tv.setTypeface(null, Typeface.BOLD);
            } else if (i == 3) {
                tv.setText("KNOWN TO CANDIDATE(YEARS)");
                tv.setTypeface(null, Typeface.BOLD);
            } else if (i == 4) {
                tv.setText("Email ID");
                tv.setTypeface(null, Typeface.BOLD);
            } else {

                int row = i / 5 - 1;
                int column = i % 5;


                if(column == 0){
                    if(row == 0){
                        reference ref1 = sharedData.studentsInfoList.get(index).getRef1();
                        tv.setText(ref1.getName());
                    }else if(row == 1){
                        reference ref2 = sharedData.studentsInfoList.get(index).getRef2();
                        tv.setText(ref2.getName());
                    }else if(row == 2){
                        reference ref3 = sharedData.studentsInfoList.get(index).getRef3();
                        tv.setText(ref3.getName());
                    }
                }else if(column == 1){
                    if(row == 0){
                        reference ref1 = sharedData.studentsInfoList.get(index).getRef1();
                        tv.setText(ref1.getCompanyName());
                    }else if(row == 1){
                        reference ref2 = sharedData.studentsInfoList.get(index).getRef2();
                        tv.setText(ref2.getCompanyName());
                    }else if(row == 2){
                        reference ref3 = sharedData.studentsInfoList.get(index).getRef3();
                        tv.setText(ref3.getCompanyName());
                    }
                }else if(column == 2){
                    if(row == 0){
                        reference ref1 = sharedData.studentsInfoList.get(index).getRef1();
                        tv.setText(ref1.getContactNo());
                    }else if(row == 1){
                        reference ref2 = sharedData.studentsInfoList.get(index).getRef2();
                        tv.setText(ref2.getContactNo());
                    }else if(row == 2){
                        reference ref3 = sharedData.studentsInfoList.get(index).getRef3();
                        tv.setText(ref3.getContactNo());
                    }
                }else if(column == 3){
                    if(row == 0){
                        reference ref1 = sharedData.studentsInfoList.get(index).getRef1();
                        tv.setText(ref1.getKnownYears());
                    }else if(row == 1){
                        reference ref2 = sharedData.studentsInfoList.get(index).getRef2();
                        tv.setText(ref2.getKnownYears());
                    }else if(row == 2){
                        reference ref3 = sharedData.studentsInfoList.get(index).getRef3();
                        tv.setText(ref3.getKnownYears());
                    }
                }else if(column == 4){
                    if(row == 0){
                        reference ref1 = sharedData.studentsInfoList.get(index).getRef1();
                        tv.setText(ref1.getEmailId());
                    }else if(row == 1){
                        reference ref2 = sharedData.studentsInfoList.get(index).getRef2();
                        tv.setText(ref2.getEmailId());
                    }else if(row == 2){
                        reference ref3 = sharedData.studentsInfoList.get(index).getRef3();
                        tv.setText(ref3.getEmailId());
                    }
                }

                tv.setTypeface(null, Typeface.NORMAL);
            }
            return grid;
        }
    }
}
