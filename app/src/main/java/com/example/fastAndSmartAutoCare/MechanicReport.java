package com.example.onroadremotevehicleassistantapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MechanicReport extends AppCompatActivity {
    TextInputEditText startdate, enddate;
    TextView fullname, email;
    Button generate, download;
    SearchView searchMech;
    private DatePickerDialog.OnDateSetListener mDateSetListener, mDateSetListener1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(R.string.appname);
        setContentView(R.layout.activity_mechanic_report);

        generate = findViewById(R.id.generate_mechreport);
        download = findViewById(R.id.download_mechreport);
        startdate = findViewById(R.id.startdate2);
        enddate = findViewById(R.id.enddate2);
        searchMech = findViewById(R.id.search_mech1);
        fullname = findViewById(R.id.adminname7);
        email = findViewById(R.id.adminemail7);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout1);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String aemail = i.getStringExtra("email");
        fullname.setText(name);
        email.setText(aemail);


        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MechanicReport.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setTitle("Set Date");
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String fm=""+month;
                String fd=""+day;
                month = month + 1;
                if(month<10){
                    fm ="0"+month;
                }
                if (day<10){
                    fd="0"+day;
                }
                String date =  fd + "/" + fm + "/" + year;
                startdate.setText(date);

            }
        };

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MechanicReport.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setTitle("Set Date");
                dialog.show();
            }
        });

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String fm=""+month;
                String fd=""+day;
                month = month + 1;
                if(month<10){
                    fm ="0"+month;
                }
                if (day<10){
                    fd="0"+day;
                }
                String date =  fd + "/" + fm + "/" + year;
                enddate.setText(date);


            }
        };




        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date1 = startdate.getText().toString();
                String date2 = enddate.getText().toString();

                DatabaseHelper dataHelper = new DatabaseHelper(MechanicReport.this);
                // Add header row
                TableRow rowHeader = new TableRow(MechanicReport.this);
                rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
                rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                tableLayout.setPadding(20, 20, 20, 30);
                String[] headerText = {"Mechanic Id", "Date", "FirstName", "LastName", "Email", "Phone No.", "Address", "Rating"};
                for (String c : headerText) {
                    TextView tv = new TextView(MechanicReport.this);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(18);
                    tv.setPadding(5, 5, 5, 5);
                    tv.setText(c);
                    rowHeader.addView(tv);
                }
                tableLayout.addView(rowHeader);

                // Get data from sqlite database and add them to the table
                // Open the database for reading
                SQLiteDatabase db = dataHelper.getReadableDatabase();
                // Start the transaction.
                db.beginTransaction();

                try {
                    Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.MECHANIC_TABLE + " WHERE " + DatabaseHelper.REG_DATE + " BETWEEN ? AND ? ", new String[]{(date1), (date2)});
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext()) {
                            int mechanicid = cursor.getInt(0);
                            String mfname = cursor.getString(1);
                            String mlname = cursor.getString(2);
                            String memail = cursor.getString(3);
                            String mpwd = cursor.getString(4);
                            String mphoneno = cursor.getString(5);
                            String maddress = cursor.getString(6);
                            byte[] mprofilephoto = cursor.getBlob(7);
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            Bitmap mphoto = BitmapFactory.decodeByteArray(mprofilephoto, 0, mprofilephoto.length, options);
                            String str = cursor.getString(8);
                            int misactive = Integer.parseInt(str);
                            float mrate = cursor.getFloat(9);
                            String mdate = cursor.getString(10);
                            // dara rows
                            TableRow row = new TableRow(MechanicReport.this);
                            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));
                            String[] colText = {mechanicid + "", mdate, mfname, mlname, memail, mphoneno, maddress, mrate + ""};
                            for (String text : colText) {
                                TextView tv = new TextView(MechanicReport.this);
                                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                        TableRow.LayoutParams.WRAP_CONTENT));
                                tv.setGravity(Gravity.CENTER);
                                tv.setTextSize(16);
                                tv.setPadding(5, 5, 5, 5);
                                tv.setText(text);
                                row.addView(tv);
                            }
                            tableLayout.addView(row);

                        }

                    }
                    db.setTransactionSuccessful();

                } catch (SQLiteException e) {
                    e.printStackTrace();

                } finally {
                    db.endTransaction();
                    // End the transaction.
                    db.close();
                    // Close database
                }

            }
        });

        searchMech.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });






        download.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                boolean success = exportDB();
                if (success == true) {
                    Toast.makeText(MechanicReport.this, "Report Downloaded Successfully...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MechanicReport.this, "Failed to Download Report...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private boolean exportDB() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(new Date());

        String date1 = startdate.getText().toString();
        String date2 = enddate.getText().toString();

        DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
        File exportDir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)));
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "RVA-MechanicReport-" + date + ".csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.MECHANIC_TABLE + " WHERE " + DatabaseHelper.REG_DATE + " BETWEEN ? AND ? ", new String[]{(date1), (date2)});
            String[] headerText = {"Mechanic Id", "Date", "FirstName", "LastName", "Email", "Phone No.", "Address", "Rating"};
            csvWrite.writeNext(headerText);
            while (cursor.moveToNext()) {
                String arrStr[] = {String.valueOf(cursor.getInt(0)), cursor.getString(10), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5), cursor.getString(6), String.valueOf(cursor.getFloat(9))};
                csvWrite.writeNext(arrStr);


            }
            csvWrite.close();
            cursor.close();
        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            Intent intent = new Intent(MechanicReport.this, AdminHomepage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item2) {
            Intent intent1 = new Intent(MechanicReport.this, AdminLogin.class);
            startActivity(intent1);
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}


