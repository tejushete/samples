package com.example.studentbiodata;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    GridView gVReferences;
    gridAdapter mGridAdapter;
    int date = 26, month = 11, year = 1991;
    reference ref1 = new reference(), ref2 = new reference(), ref3 = new reference();
    Bitmap mBitmap = null;

    private void clearForm(){
        ((EditText)findViewById(R.id.etName)).setText("");
        ((EditText)findViewById(R.id.etForHName)).setText("");
        ((EditText)findViewById(R.id.etForHOccu)).setText("");
        ((EditText)findViewById(R.id.etMomName)).setText("");
        ((EditText)findViewById(R.id.etMomOccu)).setText("");
        ((EditText)findViewById(R.id.etCurrAddress)).setText("");
        ((EditText)findViewById(R.id.etPermAddress)).setText("");
        ((EditText)findViewById(R.id.etMobileNo)).setText("");
        ((EditText)findViewById(R.id.etEmailId)).setText("");
        ((EditText)findViewById(R.id.etExperience)).setText("");
        ((EditText)findViewById(R.id.etCaste)).setText("");
        ((EditText)findViewById(R.id.etDesignation)).setText("");
        ((EditText)findViewById(R.id.etWorkLocation)).setText("");

        //clears edit texts in the references table view
        mGridAdapter.notifyDataSetChanged();

        ref1.setName("");
        ref1.setContactNo("");
        ref1.setCompanyName("");
        ref1.setKnownYears("");
        ref1.setEmailId("");

        ref2.setName("");
        ref2.setContactNo("");
        ref2.setCompanyName("");
        ref2.setKnownYears("");
        ref2.setEmailId("");

        ref3.setName("");
        ref3.setContactNo("");
        ref3.setCompanyName("");
        ref3.setKnownYears("");
        ref3.setEmailId("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        gVReferences = (GridView)findViewById(R.id.gvReferences);//grid view references
        mGridAdapter = new gridAdapter(this);
        gVReferences.setAdapter(mGridAdapter);

        sharedData.mDataBaseHandler = new databaseHandler(this);

        ImageView iv = (ImageView)findViewById(R.id.ivPic);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);
            }
        });

        Button btnDatePicker = (Button)findViewById(R.id.btnDOB);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, MainActivity.this, 1991, 11, 26);
                                                 dialog.show();
                                             }
                                         });
                Button registerButton = (Button) findViewById(R.id.btnSave);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String name = ((EditText)findViewById(R.id.etName)).getText().toString();
                String forHName = ((EditText)findViewById(R.id.etForHName)).getText().toString();//father or husband's name
                String forHOccu = ((EditText)findViewById(R.id.etForHOccu)).getText().toString();//father or husband's occupation
                String momName = ((EditText)findViewById(R.id.etMomName)).getText().toString();//mother's name
                String momOccu = ((EditText)findViewById(R.id.etMomOccu)).getText().toString();//mother's occupation
                String dob = date+"/"+month+"/"+year;//dob
                double age = year + (float)month/12.0;
                Calendar calendar = Calendar.getInstance();
                int todaysYear = calendar.get(Calendar.YEAR);
                int todaysMonth = calendar.get(Calendar.MONTH);

                age = (todaysYear+(float)todaysMonth/12.0) - age;
                age = Math.round(age*100)/100;
                String sAge = age + " years"; // age

                String presentAddress = ((EditText)findViewById(R.id.etCurrAddress)).getText().toString();//present address
                String prmntAddress = ((EditText)findViewById(R.id.etPermAddress)).getText().toString();//permanent address
                String mobile = ((EditText)findViewById(R.id.etMobileNo)).getText().toString();//mobile no
                String email = ((EditText)findViewById(R.id.etEmailId)).getText().toString();//email id
                String experience = ((EditText)findViewById(R.id.etExperience)).getText().toString();// experience
                String caste = ((EditText)findViewById(R.id.etCaste)).getText().toString();// caste
                String designation = ((EditText)findViewById(R.id.etDesignation)).getText().toString();//designation
                String workLocation = ((EditText)findViewById(R.id.etWorkLocation)).getText().toString();//work Location

                //TODO: Teju verify whether references have empty values
                //TODO: Teju please validate all fields

                student s = new student();
                s.setAge(sAge);
                s.setDesignation(designation);
                s.setDob(dob);
                s.setEmailId(email);
                s.setExperience(experience);
                s.setForHName(forHName);
                s.setForHOccu(forHOccu);
                s.setMobileNo(mobile);
                s.setMomName(momName);
                s.setMomOccu(momOccu);
                s.setPermanentAddress(prmntAddress);
                s.setPresentAddress(presentAddress);
                s.setWorkLocation(workLocation);
                s.setName(name);
                s.setCaste(caste);

                String mEncodedPic = "";
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                if(mBitmap == null){
                    mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blank_photo);
                }
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                mEncodedPic = Base64.encodeToString(byteArray, Base64.DEFAULT);

                s.setmEncodedPic(mEncodedPic);

                s.setRef1(ref1);
                s.setRef2(ref2);
                s.setRef3(ref3);

                sharedData.mDataBaseHandler.addStudent(s);

                clearForm();

                ((ImageView) findViewById(R.id.ivPic)).setImageResource(R.drawable.blank_photo);

                ((LinearLayout)findViewById(R.id.lvAgeView)).setVisibility(View.GONE);

                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menuallstudents, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, AllStudents.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Log.d("TAG", i+","+i1+","+i2+".");
        year = i;
        month = i1+1;
        date = i2;

        double age = year + (float)month/12.0;
        Calendar calendar = Calendar.getInstance();
        int todaysYear = calendar.get(Calendar.YEAR);
        int todaysMonth = calendar.get(Calendar.MONTH);

        age = (todaysYear+(float)todaysMonth/12.0) - age;
        age = Math.round(age*100)/100;
        String sAge = age + " years"; // age

        ((LinearLayout)findViewById(R.id.lvAgeView)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tvAge)).setText(sAge);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == 0) {
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

                ImageView mimageView;
                mimageView = (ImageView)findViewById(R.id.ivPic);
                Bitmap mphoto = BitmapFactory
                        .decodeFile(imgDecodableString);
                mimageView.setImageBitmap(mphoto);
                mBitmap = mphoto;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private class gridAdapter extends BaseAdapter{

        Context mContext;
        gridAdapter(Context c){
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
                grid = inflater.inflate(R.layout.get_ref, null);
            }

            Log.d("<><>",i+"");
            if(i == 0){
                TextView tv = (TextView)grid.findViewById(R.id.tvHeading);
                EditText et = (EditText)grid.findViewById(R.id.etReadValue);
                tv.setVisibility(View.VISIBLE);
                et.setVisibility(View.GONE);
                tv.setText("NAME");
            }
            else if(i == 1){
                TextView tv = (TextView)grid.findViewById(R.id.tvHeading);
                EditText et = (EditText)grid.findViewById(R.id.etReadValue);
                tv.setVisibility(View.VISIBLE);
                et.setVisibility(View.GONE);
                tv.setText("COMPANY NAME");
            }
            else if(i == 2){
                TextView tv = (TextView)grid.findViewById(R.id.tvHeading);
                EditText et = (EditText)grid.findViewById(R.id.etReadValue);
                tv.setVisibility(View.VISIBLE);
                et.setVisibility(View.GONE);
                tv.setText("CONTACT NO");
            }
            else if(i == 3){
                TextView tv = (TextView)grid.findViewById(R.id.tvHeading);
                EditText et = (EditText)grid.findViewById(R.id.etReadValue);
                tv.setVisibility(View.VISIBLE);
                et.setVisibility(View.GONE);
                tv.setText("KNOWN TO CANDIDATE(YEARS)");
            }
            else if(i == 4){
                TextView tv = (TextView)grid.findViewById(R.id.tvHeading);
                EditText et = (EditText)grid.findViewById(R.id.etReadValue);
                tv.setVisibility(View.VISIBLE);
                et.setVisibility(View.GONE);
                tv.setText("Email ID");
            }else{
                TextView tv = (TextView)grid.findViewById(R.id.tvHeading);
                EditText et = (EditText)grid.findViewById(R.id.etReadValue);
                tv.setVisibility(View.GONE);
                et.setVisibility(View.VISIBLE);
                et.setText("");
                et.setTag(i);
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        Log.d("<>", i+""+ editable.toString());
                        int row = i / 5 - 1 ;
                        int column = i % 5;
                        Log.d("<>", "row:"+row);
                        if(column == 0){
                            if(row == 0){
                                ref1.setName(editable.toString());
                            }else if(row == 1){
                                ref2.setName(editable.toString());
                            }else if(row == 2){
                                ref3.setName(editable.toString());
                            }
                        }else if(column == 1){
                            if(row == 0){
                                ref1.setCompanyName(editable.toString());
                            }else if(row == 1){
                                ref2.setCompanyName(editable.toString());
                            }else if(row == 2){
                                ref3.setCompanyName(editable.toString());
                            }
                        }else if(column == 2){
                            if(row == 0){
                                ref1.setContactNo(editable.toString());
                            }else if(row == 1){
                                ref2.setContactNo(editable.toString());
                            }else if(row == 2){
                                ref3.setContactNo(editable.toString());
                            }
                        }else if(column == 3){
                            if(row == 0){
                                ref1.setKnownYears(editable.toString());
                            }else if(row == 1){
                                ref2.setKnownYears(editable.toString());
                            }else if(row == 2){
                                ref3.setKnownYears(editable.toString());
                            }
                        }else if(column == 4){
                            if(row == 0){
                                ref1.setEmailId(editable.toString());
                            }else if(row == 1){
                                ref2.setEmailId(editable.toString());
                            }else if(row == 2){
                                ref3.setEmailId(editable.toString());
                            }
                        }

                    }
                });
            }
            return grid;
        }
    }
}
