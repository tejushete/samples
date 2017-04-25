package com.example.studentbiodata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tejashree on 26-Apr-17.
 */

public class databaseHandler extends SQLiteOpenHelper{
    private static  int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RegisteredStudents";
    private static final String STUDENT_TABLE_NAME = "students";
    private static final String REFERENCES_TABLE_NAME = "refStudents";

    private static final String KEY_NAME = "name";
    private static final String KEY_FATHER_HUSBANDS_NAME ="FatherOrHusbandName";
    private static final String KEY_FATHER_HUSBANDS_OCCUPATION = "FatherOrHusbandOccupation";
    private static final String KEY_MOM_NAME = "momName";
    private static final String KEY_MOM_OCCU = "momOccupation";
    private static final String KEY_DOB = "dob";
    private static final String KEY_AGE = "age";
    private static final String KEY_PRESENT_ADDRESS = "presentAddress";
    private static final String KEY_PERMANENT_ADDRESS = "permanentAddress";
    private static final String KEY_MOBILE_NO = "mobileNo";
    private static final String KEY_EMAIL = "emailId";
    private static final String KEY_EXPERIENCE = "experience";
    private static final String KEY_CASTE = "caste";
    private static final String KEY_DESIGNATION = "designation";
    private static final String KEY_WORK_LOCATION = "workLocation";

    private static final String KEY_STUDENTS_MOBILE = "studentsMobileNo";

    private static final String KEY_COMPANY_NAME = "companyName";
    private static final String KEY_CONTACT_NO = "contactNo";
    private static final String KEY_KNOWN_YEARS = "knownForYears";

    public databaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + STUDENT_TABLE_NAME + "("
                +  KEY_NAME + " TEXT," + KEY_FATHER_HUSBANDS_NAME+" TEXT," + KEY_FATHER_HUSBANDS_OCCUPATION +" TEXT," +
                KEY_MOM_NAME+" TEXT," + KEY_MOM_OCCU +" TEXT," + KEY_DOB + " TEXT," + KEY_AGE + " TEXT," +
                KEY_PRESENT_ADDRESS+" TEXT," + KEY_PERMANENT_ADDRESS + " TEXT," + KEY_MOBILE_NO +" TEXT," +
                KEY_EMAIL+" TEXT, " +KEY_EXPERIENCE+" TEXT, " +KEY_CASTE+" TEXT, " +KEY_DESIGNATION+" TEXT, " +
                KEY_WORK_LOCATION+" TEXT, " +
                "PRIMARY KEY("+KEY_MOBILE_NO+")"+");";

        db.execSQL(CREATE_STUDENTS_TABLE);

        String CREATE_REERENCES_TABLE = "CREATE TABLE " + REFERENCES_TABLE_NAME + "("
                +  KEY_NAME + " TEXT," + KEY_COMPANY_NAME+" TEXT," + KEY_CONTACT_NO +" TEXT," +
                KEY_KNOWN_YEARS+" TEXT," + KEY_EMAIL +" TEXT," +
                KEY_STUDENTS_MOBILE +" TEXT,"+
                "PRIMARY KEY("+KEY_STUDENTS_MOBILE+","+KEY_CONTACT_NO+")"+");";
        db.execSQL(CREATE_REERENCES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + REFERENCES_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void addReference(student s, reference r, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENTS_MOBILE, s.getMobileNo());

        values.put(KEY_NAME, r.getName());
        values.put(KEY_COMPANY_NAME, r.getCompanyName());
        values.put(KEY_CONTACT_NO, r.getContactNo());
        values.put(KEY_KNOWN_YEARS, r.getKnownYears());
        values.put(KEY_EMAIL, r.getEmailId());

        Log.d("TAG", "references table ret:"+db.insert(REFERENCES_TABLE_NAME, null, values));
        Log.d("TAG", "references entry added");
    }

    public void addStudent(student s){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, s.getName());
        values.put(KEY_FATHER_HUSBANDS_NAME, s.getForHName());
        values.put(KEY_FATHER_HUSBANDS_OCCUPATION, s.getForHOccu());
        values.put(KEY_MOM_NAME, s.getMomName());
        values.put(KEY_MOM_OCCU, s.getMomOccu());
        values.put(KEY_DOB, s.getDob());
        values.put(KEY_AGE, s.getAge());
        values.put(KEY_PRESENT_ADDRESS, s.getPresentAddress());
        values.put(KEY_PERMANENT_ADDRESS, s.getPermanentAddress());
        values.put(KEY_MOBILE_NO, s.getMobileNo());
        values.put(KEY_EMAIL, s.getEmailId());
        values.put(KEY_EXPERIENCE, s.getExperience());
        values.put(KEY_CASTE, s.getCaste());
        values.put(KEY_DESIGNATION, s.getDesignation());
        values.put(KEY_WORK_LOCATION, s.getWorkLocation());

        Log.d("TAG", "student table ret:"+db.insert(STUDENT_TABLE_NAME, null, values));
        Log.d("TAG", "student entry added");

        addReference(s, s.getRef1(), db);
        addReference(s, s.getRef2(), db);
        addReference(s, s.getRef3(), db);

        db.close();
    }

    public List<student> getStudentDetails(){
        List<student> sList = new ArrayList<student>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from "+STUDENT_TABLE_NAME ,null);

        if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                student s = new student();
                s.setName(cursor.getString(0));
                s.setForHName(cursor.getString(1));
                s.setForHOccu(cursor.getString(2));
                s.setMomName(cursor.getString(3));
                s.setMomOccu(cursor.getString(4));
                s.setDob(cursor.getString(5));
                s.setAge(cursor.getString(6));
                s.setPresentAddress(cursor.getString(7));
                s.setPermanentAddress(cursor.getString(8));
                s.setMobileNo(cursor.getString(9));
                s.setEmailId(cursor.getString(10));
                s.setExperience(cursor.getString(11));
                s.setCaste(cursor.getString(12));
                s.setDesignation(cursor.getString(13));
                s.setWorkLocation(cursor.getString(14));

                sList.add(s);
                int referenceReadCounter = 0;
                reference rs[] = new reference[3];

                Cursor referencesCursor = db.rawQuery("select * from "+REFERENCES_TABLE_NAME ,null);
                if (referencesCursor.moveToFirst()) {
                    while (referencesCursor.isAfterLast() == false) {
                        reference r = new reference();

                        r.setName(referencesCursor.getString(0));
                        r.setCompanyName(referencesCursor.getString(1));
                        r.setContactNo(referencesCursor.getString(2));
                        r.setKnownYears(referencesCursor.getString(3));
                        r.setEmailId(referencesCursor.getString(4));

                        String mobile = referencesCursor.getString(5);

                        if(mobile.equals(s.getMobileNo())){
                            if(referenceReadCounter == 0){
                                s.setRef1(r);
                            }else if(referenceReadCounter == 1){
                                s.setRef2(r);
                            }else if(referenceReadCounter == 2){
                                s.setRef3(r);
                            }
                            Log.d("<>", "found ref "+r.getName()+", "+r.getCompanyName()+", "+referenceReadCounter);
                            rs[referenceReadCounter++] = r;
                        }

                        referencesCursor.moveToNext();
                    }

                }
                cursor.moveToNext();
            }
        }
        return sList;
    }

}
