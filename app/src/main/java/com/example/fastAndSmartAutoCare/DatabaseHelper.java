package com.example.onroadremotevehicleassistantapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Nullable;

import com.example.onroadremotevehicleassistantapp.Model.AdminModel;
import com.example.onroadremotevehicleassistantapp.Model.CartModel;
import com.example.onroadremotevehicleassistantapp.Model.FeedbackModel;
import com.example.onroadremotevehicleassistantapp.Model.MechanicModel;
import com.example.onroadremotevehicleassistantapp.Model.OrderModel;
import com.example.onroadremotevehicleassistantapp.Model.ProductModel;
import com.example.onroadremotevehicleassistantapp.Model.RequestModel;
import com.example.onroadremotevehicleassistantapp.Model.ServiceModel;
import com.example.onroadremotevehicleassistantapp.Model.UserModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String USER_TABLE = "USER_TABLE";
    public static final String USER_ID = "USER_ID";
    public static final String USER_FIRSTNAME = "USER_FIRSTNAME";
    public static final String USER_LASTNAME = "USER_LASTNAME";
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String USER_VEHICLENO = "USER_VEHICLENO";
    public static final String USER_PHONENO = "USER_PHONENO";
    public static final String USER_ADDRESS = "USER_ADDRESS";
    public static final String USER_PROFILEPHOTO = "USER_PROFILEPHOTO";


    public static final String ADMIN_TABLE = "ADMIN_TABLE";
    public static final String ADMIN_ID = "ADMIN_ID";
    public static final String ADMIN_FIRSTNAME = "ADMIN_FIRSTNAME";
    public static final String ADMIN_LASTNAME = "ADMIN_LASTNAME";
    public static final String ADMIN_EMAIL = "ADMIN_EMAIL";
    public static final String ADMIN_PASSWORD = "ADMIN_PASSWORD";
    public static final String ADMIN_PROFILEPHOTO = "ADMIN_PROFILEPHOTO";
    
    public static final String MECHANIC_TABLE = "MECHANIC_TABLE";
    public static final String MECHANIC_ID = "MECHANIC_ID";
    public static final String MECHANIC_FIRSTNAME = "MECHANIC_FIRSTNAME";
    public static final String MECHANIC_LASTNAME = "MECHANIC_LASTNAME";
    public static final String MECHANIC_EMAIL = "MECHANIC_EMAIL";
    public static final String MECHANIC_PASSWORD = "MECHANIC_PASSWORD";
    public static final String MECHANIC_PHONENO = "MECHANIC_PHONENO";
    public static final String MECHANIC_ADDRESS = "MECHANIC_ADDRESS";
    public static final String MECHANIC_PROFILEPHOTO = "MECHANIC_PROFILEPHOTO";
    public static final String MECHANIC_ISACTIVE = "MECHANIC_ISACTIVE";
    public static final String MECHANIC_RATING = "MECHANIC_RATING";
    public static final String REG_DATE = "REG_DATE";

    public static final String SERVICE_TABLE = "SERVICE_TABLE";
    public static final String SERVICE_ID = "SERVICE_ID";
    public static final String SERVICE_NAME = "SERVICE_NAME";
    public static final String SERVICE_DESC = "SERVICE_DESC";
    public static final String SERVICE_CHARGES = "SERVICE_CHARGES";
    public static final String SERVICE_PICTURE = "SERVICE_PICTURE";

    public static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String PRODUCT_NAME = "PRODUCT_NAME";
    public static final String PRODUCT_CATEGORY = "PRODUCT_CATEGORY";
    public static final String VEHICLE_TYPE = "VEHICLE_TYPE";
    public static final String PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    public static final String STOCK_QUANTITY = "STOCK_QUANTITY";
    public static final String PRODUCT_RATING = "PRODUCT_RATING";
    public static final String PRODUCT_IMAGE = "PRODUCT_IMAGE";
    
    public static final String CART = "CART";
    public static final String CART_ID = "CART_ID";
    public static final String CART_USER_ID = "CART_USER_ID";
    public static final String CART_PRODUCT_ID = "CART_PRODUCT_ID";
    public static final String CART_PRODUCT_NAME = "CART_PRODUCT_NAME";
    public static final String CART_PRODUCT_PRICE = "CART_PRODUCT_PRICE";
    public static final String CART_PRODUCT_IMAGE = "CART_PRODUCT_IMAGE";
    public static final String QUANTITY = "QUANTITY";

    public static final String ORDER_TABLE = "ORDER_TABLE";
    public static final String ORDER_ID = "ORDER_ID";
    public static final String ORDER_DATE = "ORDER_DATE";
    public static final String ORDER_PRICE = "ORDER_PRICE";
    public static final String ORDER_PRODUCT_NAME = "ORDER_PRODUCT_NAME";
    public static final String DELIVERY_ADDRESS = "DELIVERY_ADDRESS";
    public static final String MODE_OF_PAYMENT = "MODE_OF_PAYMENT";
    public static final String ORDER_STATUS = "ORDER_STATUS";
    public static final String ORDER_USER_ID = "ORDER_USER_ID";
    public static final String ORDER_CART_ID = "ORDER_CART_ID";
    public static final String ORDER_PRODUCT_IMAGE = "ORDER_PRODUCT_IMAGE";
    public static final String ORDER_QUANTITY = "ORDER_QUANTITY";
    public static final String ORDER_PRODUCT_ID = "ORDER_PRODUCT_ID";
    public static final String ORDER_PRODUCT_RATING = "ORDER_PRODUCT_RATING";

    public static final String REQUEST_TABLE = "REQUEST_TABLE";
    public static final String REQUEST_ID = "REQUEST_ID";
    public static final String REQUEST_USERID = "REQUEST_USERID";
    public static final String REQUEST_MECHANICID = "REQUEST_MECHANICID";
    public static final String REQUEST_VECHICLENO = "REQUEST_VECHICLENO";
    public static final String REQUEST_VEHICLETYPE = "REQUEST_VEHICLETYPE";
    public static final String REQUEST_LOCATION = "REQUEST_LOCATION";
    public static final String REQUEST_SERVICECHARGES = "REQUEST_SERVICECHARGES";
    public static final String REQUEST_SERVICENAME = "REQUEST_SERVICENAME";
    public static final String REQUEST_DESCRIPTION = "REQUEST_DESCRIPTION";
    public static final String REQUEST_STATUS = "REQUEST_STATUS";
    public static final String REQUEST_DATE = "REQUEST_DATE";

    public static final String FEEDBACK = "FEEDBACK";
    public static final String FEEDBACK_ID = "FEEDBACK_ID";
    public static final String FEEDBACK_USERID = "FEEDBACK_USERID";
    public static final String FEEDBACK_MECHANICID = "FEEDBACK_MECHANICID";
    public static final String FEEDBACK_REQUESTID = "FEEDBACK_REQUESTID";
    public static final String FEEDBACK_MECHANIC_RATING = "FEEDBACK_MECHANIC_RATING";
    public static final String FEEDBACK_SATISFACTION = "FEEDBACK_SATISFACTION";
    public static final String FEEDBACK_SUGGESTION = "FEEDBACK_SUGGESTION";
    public static final String FEEDBACK_DATE = "FEEDBACK_DATE";


    private ByteArrayOutputStream objectByteOutputStream;
    private  byte[] imageInByte;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "remotevehicleassistant.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_TABLE + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_FIRSTNAME + " VARCHAR(50)," + USER_LASTNAME + " VARCHAR(50), " + USER_EMAIL + " VARCHAR(50), " + USER_PASSWORD + " VARCHAR(20)," + USER_VEHICLENO + " VARCHAR(20)," + USER_PHONENO + " VARCHAR(10)," + USER_ADDRESS + " VARCHAR(2000)," + USER_PROFILEPHOTO + " BLOB)");
        db.execSQL("CREATE TABLE " + MECHANIC_TABLE + " (" + MECHANIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + MECHANIC_FIRSTNAME + " VARCHAR(50), " + MECHANIC_LASTNAME + " VARCHAR(50)," + MECHANIC_EMAIL + " VARCHAR(50), " + MECHANIC_PASSWORD + " VARCHAR(20)," + MECHANIC_PHONENO + " VARCHAR(10), " + MECHANIC_ADDRESS + " VARCHAR(2000)," + MECHANIC_PROFILEPHOTO + " BLOB, " + MECHANIC_ISACTIVE + " BOOLEAN," + MECHANIC_RATING + " FLOAT," + REG_DATE + " DATE)");
        db.execSQL("CREATE TABLE " + ADMIN_TABLE + " (" + ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ADMIN_FIRSTNAME + " VARCHAR(50)," + ADMIN_LASTNAME + " VARCHAR(50)," + ADMIN_EMAIL + " VARCHAR(50)," + ADMIN_PASSWORD + " VARCHAR(20)," + ADMIN_PROFILEPHOTO + " VARCHAR(2000))");
        db.execSQL("CREATE TABLE " + SERVICE_TABLE  + " (" + SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SERVICE_NAME + " VARCHAR(50)," + SERVICE_DESC + " TEXT," + SERVICE_CHARGES + " DOUBLE," + SERVICE_PICTURE + " BLOB)");
        db.execSQL("INSERT INTO " +ADMIN_TABLE + " VALUES (2,'Admin','Admin','admin123@gmail.com','Admin@123','@drawable/photo')");
        db.execSQL("CREATE TABLE " + PRODUCT_TABLE + " (" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_NAME + " VARCHAR(100), " + PRODUCT_CATEGORY + " VARCHAR(100)," + VEHICLE_TYPE + " VARCHAR(100), " + PRODUCT_PRICE + " DOUBLE, " + PRODUCT_DESCRIPTION + " TEXT, " + STOCK_QUANTITY + " INTEGER, " + PRODUCT_RATING + " FLOAT," + PRODUCT_IMAGE + " BLOB)");
        db.execSQL("CREATE TABLE  " + CART + " (" + CART_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," + CART_USER_ID + " INTEGER, " + CART_PRODUCT_ID + " INTEGER, " + CART_PRODUCT_NAME + " VARCHAR(100), " + CART_PRODUCT_PRICE + " DOUBLE, " + CART_PRODUCT_IMAGE + " BLOB," + QUANTITY + " INTEGER)");
        db.execSQL("CREATE TABLE " + ORDER_TABLE + " (" + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + ORDER_CART_ID + " INTEGER," + ORDER_USER_ID + " INTEGER," + ORDER_PRODUCT_ID + " INTEGER," + ORDER_DATE + " DATE," + ORDER_PRICE + " DOUBLE," + ORDER_PRODUCT_NAME + " VARCAHR(100)," + DELIVERY_ADDRESS + " TEXT," + MODE_OF_PAYMENT + " TEXT," + ORDER_STATUS + " INTEGER," + ORDER_QUANTITY + " INTEGER," + ORDER_PRODUCT_IMAGE + " BLOB," + ORDER_PRODUCT_RATING + " FLOAT)");
        db.execSQL("CREATE TABLE " + REQUEST_TABLE + " (" + REQUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + REQUEST_MECHANICID + " INTEGER, " + REQUEST_USERID + " INTEGER," + REQUEST_VECHICLENO + " VARCHAR(10)," + REQUEST_VEHICLETYPE + " VARCHAR(50)," + REQUEST_SERVICENAME + " VARCHAR(100)," + REQUEST_SERVICECHARGES + " DOUBLE," + REQUEST_LOCATION + " TEXT ," + REQUEST_DESCRIPTION + " TEXT," + REQUEST_DATE + " DATE," + REQUEST_STATUS + " BOOLEAN)");
        db.execSQL("CREATE TABLE " + FEEDBACK + " (" + FEEDBACK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FEEDBACK_USERID + " INTEGER, " + FEEDBACK_MECHANICID + " INTEGER," + FEEDBACK_REQUESTID + " INTEGER," + FEEDBACK_MECHANIC_RATING + " FLOAT," + FEEDBACK_SATISFACTION + " TEXT," + FEEDBACK_SUGGESTION + " TEXT," + FEEDBACK_DATE + " DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+CART);
    onCreate(sqLiteDatabase);
    }

    public List<AdminModel> viewAdminDetails(){
        List<AdminModel> returnList = new ArrayList<>();
        String qry="SELECT * FROM " + ADMIN_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry,null);
        if (cursor.moveToFirst()){
            do{
                int adminid=cursor.getInt(0);
                String adminfname=cursor.getString(1);
                String adminlname=cursor.getString(2);
                String adminemail=cursor.getString(3);
                String adminpwd=cursor.getString(4);


                AdminModel newadmin=new AdminModel(adminid,adminfname,adminlname,adminemail,adminpwd);
                returnList.add(newadmin);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean checkadminemail(String adminemail){
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM " + ADMIN_TABLE + " WHERE " + ADMIN_EMAIL + "=?",new String[] {adminemail});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean updateAdminPassword(String email,String pwd){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ADMIN_PASSWORD ,pwd);

        long update = db.update(ADMIN_TABLE, cv, ADMIN_EMAIL + "=?", new String[]{email});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }







    public boolean addOneUser(UserModel userModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(USER_FIRSTNAME,userModel.getFname());
        cv.put(USER_LASTNAME,userModel.getLname());
        cv.put(USER_EMAIL,userModel.getEmail());
        cv.put(USER_PASSWORD,userModel.getPwd());
        cv.put(USER_VEHICLENO,userModel.getVehicleno());
        cv.put(USER_PHONENO,userModel.getPhoneno());
        cv.put(USER_ADDRESS,userModel.getAdd());
        cv.put(USER_PROFILEPHOTO,"");

        long insert = db.insert(USER_TABLE,null,cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<UserModel> viewAllUser(String email){
        List<UserModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + USER_TABLE + " WHERE " + USER_EMAIL + "=?",new String[] {email});
        if (cursor.moveToFirst()){
            do{
                int uid=cursor.getInt(0);
                String ufname=cursor.getString(1);
                String ulname=cursor.getString(2);
                String uemail=cursor.getString(3);
                String upwd=cursor.getString(4);
                String uvehicleno=cursor.getString(5);
                String uphoneno=cursor.getString(6);
                String uaddress=cursor.getString(7);
                byte[] uprofilephoto=cursor.getBlob(8);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap uphoto = BitmapFactory.decodeByteArray(uprofilephoto, 0, uprofilephoto.length, options);


                UserModel newuser=new UserModel(uid,ufname,ulname,uemail,upwd,uvehicleno,uphoneno,uaddress,uphoto);
                returnList.add(newuser);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<UserModel> viewOneUser(int id){
        List<UserModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + USER_TABLE + " WHERE " + USER_ID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            do{
                int uid=cursor.getInt(0);
                String ufname=cursor.getString(1);
                String ulname=cursor.getString(2);
                String uemail=cursor.getString(3);
                String upwd=cursor.getString(4);
                String uvehicleno=cursor.getString(5);
                String uphoneno=cursor.getString(6);
                String uaddress=cursor.getString(7);
                byte[] uprofilephoto=cursor.getBlob(8);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap uphoto = BitmapFactory.decodeByteArray(uprofilephoto, 0, uprofilephoto.length, options);


                UserModel newuser=new UserModel(uid,ufname,ulname,uemail,upwd,uvehicleno,uphoneno,uaddress,uphoto);
                returnList.add(newuser);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean updateUser(UserModel userModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Bitmap imageToStore=userModel.getPhoto();
        objectByteOutputStream=new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG,100,objectByteOutputStream);
        imageInByte=objectByteOutputStream.toByteArray();
        cv.put(USER_FIRSTNAME,userModel.getFname());
        cv.put(USER_LASTNAME,userModel.getLname());
        cv.put(USER_EMAIL,userModel.getEmail());
        cv.put(USER_VEHICLENO,userModel.getVehicleno());
        cv.put(USER_PHONENO,userModel.getPhoneno());
        cv.put(USER_ADDRESS,userModel.getAdd());
        cv.put(USER_PROFILEPHOTO,imageInByte);
        long update = db.update(USER_TABLE, cv, USER_ID + "=?", new String[]{String.valueOf(userModel.getId())});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkuseremail(String useremail){
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + USER_EMAIL + "=?",new String[] {useremail});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkuseremailpassword(String uemail,String userpassword){
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + USER_EMAIL + "=? and " + USER_PASSWORD + "=?",new String[] {uemail,userpassword});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    @SuppressLint("SuspiciousIndentation")
    public String getUserName(int id) {
        String fname = null;
        String lname;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + USER_TABLE + " WHERE " + USER_ID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst())
            fname = cursor.getString(1);
            lname = cursor.getString(2);
        String total = fname+" "+lname;
        return total;
    }


    public boolean updateUserPassword(String email,String pwd){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(USER_PASSWORD ,pwd);

        long update = db.update(USER_TABLE, cv, USER_EMAIL + "=?", new String[]{email});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }









    public boolean addOneMechanic(MechanicModel mechanicModel){
        SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues cv1=new ContentValues();
        cv1.put(MECHANIC_FIRSTNAME,mechanicModel.getMfname());
        cv1.put(MECHANIC_LASTNAME,mechanicModel.getMlname());
        cv1.put(MECHANIC_EMAIL,mechanicModel.getMemail());
        cv1.put(MECHANIC_PASSWORD,mechanicModel.getMpwd());
        cv1.put(MECHANIC_PHONENO,mechanicModel.getMphoneno());
        cv1.put(MECHANIC_ADDRESS,mechanicModel.getMaddress());
        cv1.put(REG_DATE,mechanicModel.getRegDate());
        cv1.put(MECHANIC_PROFILEPHOTO,"");
        cv1.put(MECHANIC_ISACTIVE,false);
        cv1.put(MECHANIC_RATING,mechanicModel.getMrating());

        long insert1 = db1.insert(MECHANIC_TABLE,null,cv1);
        if(insert1==-1){
            return false;
        }
        else{
            return true;
        }
    }

    @SuppressLint("SuspiciousIndentation")
    public String getMechName(int id) {
        String fname = null;
        String lname;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + MECHANIC_TABLE + " WHERE " + MECHANIC_ID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst())
            fname = cursor.getString(1);
            lname = cursor.getString(2);
        String total = fname+" "+lname;
        return total;
    }

    public List<MechanicModel> viewAllMechanic1(){
        List<MechanicModel> returnList = new ArrayList<>();
        String qry = "SELECT * FROM " + MECHANIC_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry,null);
        if (cursor.moveToFirst()){
            do{
                int mechanicid=cursor.getInt(0);
                String mfname=cursor.getString(1);
                String mlname=cursor.getString(2);
                String memail=cursor.getString(3);
                String mpwd=cursor.getString(4);
                String mphoneno=cursor.getString(5);
                String maddress=cursor.getString(6);
                byte[] mprofilephoto=cursor.getBlob(7);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap mphoto = BitmapFactory.decodeByteArray(mprofilephoto, 0, mprofilephoto.length, options);
                String str = cursor.getString(8);
                int misactive=Integer.parseInt(str);
                float mrate = cursor.getFloat(9);
                String mdate=cursor.getString(10);


                MechanicModel newmechanic=new MechanicModel(mechanicid,mfname,mlname,memail,mpwd,mphoneno,maddress,mdate,mphoto,misactive,mrate);
                returnList.add(newmechanic);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<MechanicModel> viewAllMechanic(){
        List<MechanicModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MECHANIC_TABLE+ " WHERE " + MECHANIC_ISACTIVE + "=?",new String[] {String.valueOf(1)});
        if (cursor.moveToFirst()){
            do{
                int mechanicid=cursor.getInt(0);
                String mfname=cursor.getString(1);
                String mlname=cursor.getString(2);
                String memail=cursor.getString(3);
                String mpwd=cursor.getString(4);
                String mphoneno=cursor.getString(5);
                String maddress=cursor.getString(6);
                byte[] mprofilephoto=cursor.getBlob(7);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap mphoto = BitmapFactory.decodeByteArray(mprofilephoto, 0, mprofilephoto.length, options);
                String str = cursor.getString(8);
                int misactive=Integer.parseInt(str);
                float mrate = cursor.getFloat(9);
                String mdate=cursor.getString(10);


                MechanicModel newmechanic=new MechanicModel(mechanicid,mfname,mlname,memail,mpwd,mphoneno,maddress,mdate,mphoto,misactive,mrate);
                returnList.add(newmechanic);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<MechanicModel> viewMechanic(String email){
        List<MechanicModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + MECHANIC_TABLE + " WHERE " + MECHANIC_EMAIL + "=?",new String[] {email});
        if (cursor.moveToFirst()){
            do{
                int mechanicid=cursor.getInt(0);
                String mfname=cursor.getString(1);
                String mlname=cursor.getString(2);
                String memail=cursor.getString(3);
                String mpwd=cursor.getString(4);
                String mphoneno=cursor.getString(5);
                String maddress=cursor.getString(6);
                byte[] mprofilephoto=cursor.getBlob(7);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap mphoto = BitmapFactory.decodeByteArray(mprofilephoto, 0, mprofilephoto.length, options);
                String str = cursor.getString(8);
                int misactive=Integer.parseInt(str);
                float mrate = cursor.getFloat(9);
                String mdate=cursor.getString(10);


                MechanicModel newmechanic=new MechanicModel(mechanicid,mfname,mlname,memail,mpwd,mphoneno,maddress,mdate,mphoto,misactive,mrate);
                returnList.add(newmechanic);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<MechanicModel> viewOneMechanic(int id){
        List<MechanicModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + MECHANIC_TABLE + " WHERE " + MECHANIC_ID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            do{
                int mechanicid=cursor.getInt(0);
                String mfname=cursor.getString(1);
                String mlname=cursor.getString(2);
                String memail=cursor.getString(3);
                String mpwd=cursor.getString(4);
                String mphoneno=cursor.getString(5);
                String maddress=cursor.getString(6);
                byte[] mprofilephoto=cursor.getBlob(7);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap mphoto = BitmapFactory.decodeByteArray(mprofilephoto, 0, mprofilephoto.length, options);
                String str = cursor.getString(8);
                int misactive=Integer.parseInt(str);
                float mrate = cursor.getFloat(9);
                String mdate=cursor.getString(10);


                MechanicModel newmechanic=new MechanicModel(mechanicid,mfname,mlname,memail,mpwd,mphoneno,maddress,mdate,mphoto,misactive,mrate);
                returnList.add(newmechanic);


            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<MechanicModel> viewMechanicReport(String startdate,String enddate){
        List<MechanicModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MECHANIC_TABLE + " WHERE " + REG_DATE + " BETWEEN ? AND ? ",new String[] {(startdate),(enddate)});
        if (cursor.moveToFirst()){
            do{
                int mechanicid=cursor.getInt(0);
                String mfname=cursor.getString(1);
                String mlname=cursor.getString(2);
                String memail=cursor.getString(3);
                String mpwd=cursor.getString(4);
                String mphoneno=cursor.getString(5);
                String maddress=cursor.getString(6);
                byte[] mprofilephoto=cursor.getBlob(7);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap mphoto = BitmapFactory.decodeByteArray(mprofilephoto, 0, mprofilephoto.length, options);
                String str = cursor.getString(8);
                int misactive=Integer.parseInt(str);
                float mrate = cursor.getFloat(9);
                String mdate=cursor.getString(10);


                MechanicModel newmechanic=new MechanicModel(mechanicid,mfname,mlname,memail,mpwd,mphoneno,maddress,mdate,mphoto,misactive,mrate);
                returnList.add(newmechanic);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }


    public boolean updateMechanic(MechanicModel mechanicModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv1=new ContentValues();
        Bitmap imageToStore=mechanicModel.getMprofileimage();
        objectByteOutputStream=new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG,100,objectByteOutputStream);
        imageInByte=objectByteOutputStream.toByteArray();
        cv1.put(MECHANIC_FIRSTNAME,mechanicModel.getMfname());
        cv1.put(MECHANIC_LASTNAME,mechanicModel.getMlname());
        cv1.put(MECHANIC_EMAIL,mechanicModel.getMemail());
        cv1.put(MECHANIC_PHONENO,mechanicModel.getMphoneno());
        cv1.put(MECHANIC_ADDRESS,mechanicModel.getMaddress());
        cv1.put(MECHANIC_PROFILEPHOTO,imageInByte);
        long update = db.update(MECHANIC_TABLE, cv1, MECHANIC_ID + "=?", new String[]{String.valueOf(mechanicModel.getMid())});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean deleteMechanic(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        long delete=db.delete(MECHANIC_TABLE,MECHANIC_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
        if(delete==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkmechemail(String mechemail){
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM " + MECHANIC_TABLE + " WHERE " + MECHANIC_EMAIL + "=?",new String[] {mechemail});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkmechemailpassword(String mechemail,String mechpassword){
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM " + MECHANIC_TABLE + " WHERE " + MECHANIC_EMAIL + "=? AND " + MECHANIC_PASSWORD + "=?",new String[] {mechemail,mechpassword});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean updateMechanicPassword(String email,String pwd){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(MECHANIC_PASSWORD ,pwd);

        long update = db.update(MECHANIC_TABLE, cv, MECHANIC_EMAIL + "=?", new String[]{email});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public float viewMechanicRating(int mid) {
        float result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select "+ MECHANIC_RATING +" from " + MECHANIC_TABLE + " WHERE " + MECHANIC_ID + "=?",new String[] {String.valueOf(mid)});
        if (cursor.moveToFirst())
            result = cursor.getFloat(0);
        cursor.close();
        db.close();
        return result;
    }


    public boolean isMechanicActive(int id,boolean isactive){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv1=new ContentValues();
        cv1.put(MECHANIC_ISACTIVE,isactive);
        long update = db.update(MECHANIC_TABLE, cv1, MECHANIC_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }







    public boolean addService(ServiceModel serviceModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Bitmap imageToStore=serviceModel.getSpicture();
        objectByteOutputStream=new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG,100,objectByteOutputStream);
        imageInByte=objectByteOutputStream.toByteArray();
        cv.put(SERVICE_NAME,serviceModel.getSname());
        cv.put(SERVICE_DESC,serviceModel.getSdesc());
        cv.put(SERVICE_CHARGES,serviceModel.getScharges());
        cv.put(SERVICE_PICTURE,imageInByte);

        long insert = db.insert(SERVICE_TABLE,null,cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<ServiceModel> viewAllServices(){
        List<ServiceModel> returnList = new ArrayList<>();
        String qry="SELECT * FROM " + SERVICE_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry,null);
        if (cursor.moveToFirst()){
            do{
                int serviceid=cursor.getInt(0);
                String sname=cursor.getString(1);
                String sdesc=cursor.getString(2);
                String scharges=cursor.getString(3);
                byte[] spic=cursor.getBlob(4);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(spic, 0, spic.length, options);

                ServiceModel newservice=new ServiceModel(serviceid,sname,sdesc,scharges,bitmap);
                returnList.add(newservice);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<ServiceModel> viewServices(int id){
        List<ServiceModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + SERVICE_TABLE + " WHERE " + SERVICE_ID + "=?",new String[] {String.valueOf(id)});

        if (cursor.moveToFirst()){
            do{
                int serviceid=cursor.getInt(0);
                String sname=cursor.getString(1);
                String sdesc=cursor.getString(2);
                String scharges=cursor.getString(3);
                byte[] spic=cursor.getBlob(4);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(spic, 0, spic.length, options);

                ServiceModel newservice=new ServiceModel(serviceid,sname,sdesc,scharges,bitmap);
                returnList.add(newservice);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean updateService(ServiceModel serviceModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Bitmap imageToStore=serviceModel.getSpicture();
        objectByteOutputStream=new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG,100,objectByteOutputStream);
        imageInByte=objectByteOutputStream.toByteArray();
        cv.put(SERVICE_NAME ,serviceModel.getSname());
        cv.put(SERVICE_DESC,serviceModel.getSdesc());
        cv.put(SERVICE_CHARGES ,serviceModel.getScharges());
        cv.put(SERVICE_PICTURE,imageInByte);

        long update = db.update(SERVICE_TABLE, cv, SERVICE_ID + "=?", new String[]{String.valueOf(serviceModel.getId())});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean deleteService(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        long delete=db.delete(SERVICE_TABLE,SERVICE_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
        if(delete==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkservice(String servicename){
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM " + SERVICE_TABLE + " WHERE " + SERVICE_NAME + "=?",new String[] {servicename});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }



    
    public boolean checkadminemailpassword(String adminemail,String adminpassword){
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM " + ADMIN_TABLE + " WHERE " + ADMIN_EMAIL + "=? AND " + ADMIN_PASSWORD + "=?",new String[] {adminemail,adminpassword});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    

    public boolean addProduct(ProductModel productModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Bitmap imageToStore=productModel.getProductImage();
        objectByteOutputStream=new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG,100,objectByteOutputStream);
        imageInByte=objectByteOutputStream.toByteArray();
        cv.put(PRODUCT_NAME,productModel.getProductName());
        cv.put(PRODUCT_CATEGORY,productModel.getProductCategory());
        cv.put(VEHICLE_TYPE,productModel.getVehicleType());
        cv.put(PRODUCT_PRICE,productModel.getProductPrice());
        cv.put(PRODUCT_DESCRIPTION,productModel.getProductDescr());
        cv.put(STOCK_QUANTITY,productModel.getStockQuantity());
        cv.put(PRODUCT_RATING,productModel.getProductRating());
        cv.put(PRODUCT_IMAGE,imageInByte);

        long insert = db.insert(PRODUCT_TABLE,null,cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<ProductModel> viewAllProduct(){
        List<ProductModel> returnList = new ArrayList<>();
        String qry="SELECT * FROM " + PRODUCT_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry,null);
        if (cursor.moveToFirst()){
            do{
                int pid=cursor.getInt(0);
                String pname=cursor.getString(1);
                String pcate=cursor.getString(2);
                String pvt=cursor.getString(3);
                String ppri=cursor.getString(4);
                String pdesc=cursor.getString(5);
                int stqty=cursor.getInt(6);
                float prate=cursor.getFloat(7);
                byte[] ppic=cursor.getBlob(8);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(ppic, 0, ppic.length, options);

                ProductModel newproduct=new ProductModel(pid,prate,pname,pcate,pvt,ppri,pdesc,stqty,bitmap);
                returnList.add(newproduct);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<ProductModel> viewProduct(int id){
        List<ProductModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PRODUCT_TABLE + " WHERE " + PRODUCT_ID + "=?",new String[] {String.valueOf(id)} );
        if (cursor.moveToFirst()){
            do{
                int pid=cursor.getInt(0);
                String pname=cursor.getString(1);
                String pcate=cursor.getString(2);
                String pvt=cursor.getString(3);
                String ppri=cursor.getString(4);
                String pdesc=cursor.getString(5);
                int stqty=cursor.getInt(6);
                float prate=cursor.getFloat(7);
                byte[] ppic=cursor.getBlob(8);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(ppic, 0, ppic.length, options);

                ProductModel newproduct=new ProductModel(pid,prate,pname,pcate,pvt,ppri,pdesc,stqty,bitmap);
                returnList.add(newproduct);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }




    public float viewProductRating(int pid) {
        float result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select "+ PRODUCT_RATING +" from " + PRODUCT_TABLE + " WHERE " + PRODUCT_ID + "=?",new String[] {String.valueOf(pid)});
        if (cursor.moveToFirst())
            result = cursor.getFloat(0);
        cursor.close();
        db.close();
        return result;
    }


    public boolean updateProduct(ProductModel productModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Bitmap imageToStore=productModel.getProductImage();
        objectByteOutputStream=new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG,100,objectByteOutputStream);
        imageInByte=objectByteOutputStream.toByteArray();
        cv.put(PRODUCT_NAME,productModel.getProductName());
        cv.put(PRODUCT_CATEGORY,productModel.getProductCategory());
        cv.put(VEHICLE_TYPE,productModel.getVehicleType());
        cv.put(PRODUCT_PRICE,productModel.getProductPrice());
        cv.put(PRODUCT_DESCRIPTION,productModel.getProductDescr());
        cv.put(STOCK_QUANTITY,productModel.getStockQuantity());
        cv.put(PRODUCT_RATING,productModel.getProductRating());
        cv.put(PRODUCT_IMAGE,imageInByte);

        long update = db.update(PRODUCT_TABLE, cv, PRODUCT_ID + "=?", new String[]{String.valueOf(productModel.getProductId())});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateProductRating(int pid,float prating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_RATING, prating);

        long update = db.update(PRODUCT_TABLE, cv, PRODUCT_ID + "=?", new String[]{String.valueOf(pid)});
        db.close();
        if (update == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean deleteProduct(int pid){
        SQLiteDatabase db=this.getWritableDatabase();
        long delete=db.delete(PRODUCT_TABLE,PRODUCT_ID + "=?", new String[] {String.valueOf(pid)});
        db.close();
        if(delete==-1){
            return false;
        }
        else{
            return true;
        }
    }




    public boolean addItem(CartModel cartModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Bitmap imageToStore=cartModel.getProImg();
        objectByteOutputStream=new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG,100,objectByteOutputStream);
        imageInByte=objectByteOutputStream.toByteArray();
        cv.put(CART_USER_ID,cartModel.getUserId());
        cv.put(CART_PRODUCT_ID,cartModel.getProId());
        cv.put(CART_PRODUCT_NAME,cartModel.getProName());
        cv.put(CART_PRODUCT_PRICE,cartModel.getProPrice());
        cv.put(CART_PRODUCT_IMAGE,imageInByte);
        cv.put(QUANTITY,1);

        long insert = db.insert(CART,null,cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<CartModel> viewAllItems(int id){
        List<CartModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CART + " WHERE " + CART_USER_ID + "=?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
            do{
                int cartid=cursor.getInt(0);
                int userid=cursor.getInt(1);
                int proid=cursor.getInt(2);
                String pname=cursor.getString(3);
                String pprice=cursor.getString(4);
                byte[] ppic=cursor.getBlob(5);
                int qty = cursor.getInt(6);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(ppic, 0, ppic.length, options);

                CartModel newcart=new CartModel(cartid,userid,proid,qty,pname,pprice,bitmap);
                returnList.add(newcart);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<CartModel> viewItems(int id){
        List<CartModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CART + " WHERE " + CART_USER_ID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            do{
                int cartid=cursor.getInt(0);
                int userid=cursor.getInt(1);
                int proid=cursor.getInt(2);
                String pname=cursor.getString(3);
                String pprice=cursor.getString(4);
                byte[] ppic=cursor.getBlob(5);
                int qty = cursor.getInt(6);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(ppic, 0, ppic.length, options);

                CartModel newcart=new CartModel(cartid,userid,proid,qty,pname,pprice,bitmap);
                returnList.add(newcart);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<CartModel> viewCart(int id){
        List<CartModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CART + " WHERE " + CART_USER_ID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            do{
                int cartid=cursor.getInt(0);
                int userid=cursor.getInt(1);
                int proid=cursor.getInt(2);
                String pname=cursor.getString(3);
                String pprice=cursor.getString(4);
                byte[] ppic=cursor.getBlob(5);
                int qty = cursor.getInt(6);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(ppic, 0, ppic.length, options);

                CartModel newcart=new CartModel(cartid,userid,proid,qty,pname,pprice,bitmap);
                returnList.add(newcart);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean updateItem(int cid,int qty){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(QUANTITY,qty);

        long update = db.update(CART, cv, CART_ID + "=?", new String[]{String.valueOf(cid)});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean deleteItem(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        long delete=db.delete(CART,CART_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
        if(delete==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public String getSumValue(int id) {
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select sum("+ CART_PRODUCT_PRICE + " * " + QUANTITY + ") from " + CART + " WHERE " + CART_USER_ID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst())
            result = cursor.getInt(0);
        String total = String.valueOf(result);
        cursor.close();
        db.close();
        return total;
    }



    public boolean addOrder(OrderModel orderModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        Bitmap imageToStore=orderModel.getOrderproductPhoto();
        objectByteOutputStream=new ByteArrayOutputStream();
        imageToStore.compress(Bitmap.CompressFormat.JPEG,100,objectByteOutputStream);
        imageInByte=objectByteOutputStream.toByteArray();
        cv.put(ORDER_CART_ID,orderModel.getOrdercartId());
        cv.put(ORDER_USER_ID,orderModel.getOrderuserId());
        cv.put(ORDER_PRODUCT_ID,orderModel.getOrderproId());
        cv.put(ORDER_DATE,orderModel.getOrderDate());
        cv.put(ORDER_PRICE,orderModel.getOrderPrice());
        cv.put(ORDER_PRODUCT_NAME,orderModel.getOrderproductName());
        cv.put(DELIVERY_ADDRESS,orderModel.getOrderDeliveryAddress());
        cv.put(MODE_OF_PAYMENT,orderModel.getModeOfPayment());
        cv.put(ORDER_STATUS,orderModel.getOrderStatus());
        cv.put(ORDER_QUANTITY,orderModel.getOrderQuantity());
        cv.put(ORDER_PRODUCT_IMAGE,imageInByte);
        cv.put(ORDER_PRODUCT_RATING,orderModel.getOrderproductRating());

        long insert = db.insert(ORDER_TABLE,null,cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<OrderModel> viewUserOrders(int id){
        List<OrderModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ORDER_TABLE + " WHERE " + ORDER_USER_ID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            do{
                int orderid=cursor.getInt(0);
                int cartid=cursor.getInt(1);
                int userid=cursor.getInt(2);
                int productid=cursor.getInt(3);
                String odate=cursor.getString(4);
                String oprice=cursor.getString(5);
                String oname=cursor.getString(6);
                String oaddress=cursor.getString(7);
                String mop=cursor.getString(8);
                int status = cursor.getInt(9);
                int qty = cursor.getInt(10);
                byte[] opic=cursor.getBlob(11);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(opic, 0, opic.length, options);
                float orderrate = cursor.getFloat(12);


                OrderModel neworder=new OrderModel(orderid,cartid,userid,productid,odate,oprice,oname,oaddress,mop,status,qty,bitmap,orderrate);
                returnList.add(neworder);


            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }


    public List<OrderModel> viewAllOrders(){
        List<OrderModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ORDER_TABLE ,null);
        if (cursor.moveToFirst()){
            do{
                int orderid=cursor.getInt(0);
                int cartid=cursor.getInt(1);
                int userid=cursor.getInt(2);
                int productid=cursor.getInt(3);
                String odate=cursor.getString(4);
                String oprice=cursor.getString(5);
                String oname=cursor.getString(6);
                String oaddress=cursor.getString(7);
                String mop=cursor.getString(8);
                int status = cursor.getInt(9);
                int qty = cursor.getInt(10);
                byte[] opic=cursor.getBlob(11);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(opic, 0, opic.length, options);
                float orderrate = cursor.getFloat(12);


                OrderModel neworder=new OrderModel(orderid,cartid,userid,productid,odate,oprice,oname,oaddress,mop,status,qty,bitmap,orderrate);
                returnList.add(neworder);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<OrderModel> viewOrderReport(String startdate,String enddate){
        List<OrderModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ORDER_TABLE + " WHERE " + ORDER_DATE + " BETWEEN ? AND ? ",new String[] {(startdate),(enddate)});
        if (cursor.moveToFirst()){
            do{
                int orderid=cursor.getInt(0);
                int cartid=cursor.getInt(1);
                int userid=cursor.getInt(2);
                int productid=cursor.getInt(3);
                String odate=cursor.getString(4);
                String oprice=cursor.getString(5);
                String oname=cursor.getString(6);
                String oaddress=cursor.getString(7);
                String mop=cursor.getString(8);
                int status = cursor.getInt(9);
                int qty = cursor.getInt(10);
                byte[] opic=cursor.getBlob(11);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeByteArray(opic, 0, opic.length, options);
                float orderrate = cursor.getFloat(12);


                OrderModel neworder=new OrderModel(orderid,cartid,userid,productid,odate,oprice,oname,oaddress,mop,status,qty,bitmap,orderrate);
                returnList.add(neworder);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean updateStatus(int oid,int status){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ORDER_STATUS,status);

        long update = db.update(ORDER_TABLE, cv, ORDER_ID + "=?", new String[]{String.valueOf(oid)});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateAddress(int oid,String address){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DELIVERY_ADDRESS,address);

        long update = db.update(ORDER_TABLE, cv, ORDER_ID + "=?", new String[]{String.valueOf(oid)});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateOrderProductRating(int oid,float prating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ORDER_PRODUCT_RATING, prating);

        long update = db.update(ORDER_TABLE, cv, ORDER_ID + "=?", new String[]{String.valueOf(oid)});
        db.close();
        if (update == -1) {
            return false;
        } else {
            return true;
        }
    }





    public boolean makeRequest(RequestModel requestModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(REQUEST_MECHANICID,requestModel.getRequestMechanicId());
        cv.put(REQUEST_USERID,requestModel.getRequestUserId());
        cv.put(REQUEST_VECHICLENO,requestModel.getRequestVehicleno());
        cv.put(REQUEST_VEHICLETYPE,requestModel.getRequestVehicletype());
        cv.put(REQUEST_SERVICENAME,requestModel.getRequestServiceName());
        cv.put(REQUEST_SERVICECHARGES,requestModel.getRequestCharges());
        cv.put(REQUEST_LOCATION,requestModel.getRequestLocation());
        cv.put(REQUEST_DESCRIPTION,requestModel.getRequestDescription());
        cv.put(REQUEST_DATE,requestModel.getRequestDate());
        cv.put(REQUEST_STATUS,requestModel.isRequestStatus());


        long insert = db.insert(REQUEST_TABLE,null,cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<RequestModel> viewMechanicRequest(int id){
        List<RequestModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + REQUEST_TABLE + " WHERE " + REQUEST_MECHANICID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            do{
                int requestid=cursor.getInt(0);
                int requestmechid=cursor.getInt(1);
                int requestuserid=cursor.getInt(2);
                String requestvno=cursor.getString(3);
                String requestvtype=cursor.getString(4);
                String requestsnm=cursor.getString(5);
                String requestsch=cursor.getString(6);
                String requestlocation=cursor.getString(7);
                String requestdesc=cursor.getString(8);
                String requestDate=cursor.getString(9);
                int Status=cursor.getInt(10);
                boolean requestStatus;
                if (Status >= 1)
                {
                    requestStatus = true;
                }else {
                    requestStatus = false;
                }
                RequestModel newrequest=new RequestModel(requestid,requestmechid,requestuserid,requestvno,requestvtype,requestsnm,requestsch,requestlocation,requestdesc,requestDate,requestStatus);
                returnList.add(newrequest);



            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<RequestModel> viewUserRequest(int id){
        List<RequestModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + REQUEST_TABLE + " WHERE " + REQUEST_USERID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            do{
                int requestid=cursor.getInt(0);
                int requestmechid=cursor.getInt(1);
                int requestuserid=cursor.getInt(2);
                String requestvno=cursor.getString(3);
                String requestvtype=cursor.getString(4);
                String requestsnm=cursor.getString(5);
                String requestsch=cursor.getString(6);
                String requestlocation=cursor.getString(7);
                String requestdesc=cursor.getString(8);
                String requestDate=cursor.getString(9);
                int Status=cursor.getInt(10);
                boolean requestStatus;
                if (Status >= 1)
                {
                    requestStatus = true;
                }else {
                    requestStatus = false;
                }
                RequestModel newrequest=new RequestModel(requestid,requestmechid,requestuserid,requestvno,requestvtype,requestsnm,requestsch,requestlocation,requestdesc,requestDate,requestStatus);
                returnList.add(newrequest);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<RequestModel> viewRequestReport(String startdate,String enddate){
        List<RequestModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + REQUEST_TABLE + " WHERE " + REQUEST_DATE + " BETWEEN ? AND ? ",new String[] {(startdate),(enddate)});
        if (cursor.moveToFirst()){
            do{
                int requestid=cursor.getInt(0);
                int requestmechid=cursor.getInt(1);
                int requestuserid=cursor.getInt(2);
                String requestvno=cursor.getString(3);
                String requestvtype=cursor.getString(4);
                String requestsnm=cursor.getString(5);
                String requestsch=cursor.getString(6);
                String requestlocation=cursor.getString(7);
                String requestdesc=cursor.getString(8);
                String requestDate=cursor.getString(9);
                int Status=cursor.getInt(10);
                boolean requestStatus;
                if (Status >= 1)
                {
                    requestStatus = true;
                }else {
                    requestStatus = false;
                }
                RequestModel newrequest=new RequestModel(requestid,requestmechid,requestuserid,requestvno,requestvtype,requestsnm,requestsch,requestlocation,requestdesc,requestDate,requestStatus);
                returnList.add(newrequest);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }



    public List<RequestModel> viewAllRequest(){
        List<RequestModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + REQUEST_TABLE ,null);
        if (cursor.moveToFirst()){
            do{
                int requestid=cursor.getInt(0);
                int requestmechid=cursor.getInt(1);
                int requestuserid=cursor.getInt(2);
                String requestvno=cursor.getString(3);
                String requestvtype=cursor.getString(4);
                String requestsnm=cursor.getString(5);
                String requestsch=cursor.getString(6);
                String requestlocation=cursor.getString(7);
                String requestdesc=cursor.getString(8);
                String requestDate=cursor.getString(9);
                int Status=cursor.getInt(10);
                boolean requestStatus;
                if (Status >= 1)
                {
                    requestStatus = true;
                }else {
                     requestStatus = false;
                }
                RequestModel newrequest=new RequestModel(requestid,requestmechid,requestuserid,requestvno,requestvtype,requestsnm,requestsch,requestlocation,requestdesc,requestDate,requestStatus);
                returnList.add(newrequest);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean cancelRequest(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        long delete=db.delete(REQUEST_TABLE,REQUEST_ID + "=?", new String[] {String.valueOf(id)});
        db.close();
        if(delete==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateRequest(RequestModel requestModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(REQUEST_MECHANICID,requestModel.getRequestMechanicId());
        cv.put(REQUEST_USERID,requestModel.getRequestUserId());
        cv.put(REQUEST_VECHICLENO,requestModel.getRequestVehicleno());
        cv.put(REQUEST_VEHICLETYPE,requestModel.getRequestVehicletype());
        cv.put(REQUEST_SERVICENAME,requestModel.getRequestServiceName());
        cv.put(REQUEST_SERVICECHARGES,requestModel.getRequestCharges());
        cv.put(REQUEST_LOCATION,requestModel.getRequestLocation());
        cv.put(REQUEST_DESCRIPTION,requestModel.getRequestDescription());
        cv.put(REQUEST_DATE,requestModel.getRequestDate());
        cv.put(REQUEST_STATUS,requestModel.isRequestStatus());

        long insert = db.update(REQUEST_TABLE,cv,REQUEST_ID + "=?", new String[]{String.valueOf(requestModel.getRequestId())});
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean requestStatus(int id,boolean status){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv1=new ContentValues();
        cv1.put(REQUEST_STATUS,status);
        long update = db.update(REQUEST_TABLE, cv1, REQUEST_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        if(update==-1){
            return false;
        }
        else{
            return true;
        }
    }



    public boolean giveFeedback(FeedbackModel feedbackModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(FEEDBACK_USERID,feedbackModel.getFeedbackUserId());
        cv.put(FEEDBACK_MECHANICID,feedbackModel.getFeedbackMechanicId());
        cv.put(FEEDBACK_REQUESTID,feedbackModel.getFeedbackRequestId());
        cv.put(FEEDBACK_MECHANIC_RATING,feedbackModel.getFeedbackMechanicRating());
        cv.put(FEEDBACK_SATISFACTION,feedbackModel.getFeedbackSatisfaction());
        cv.put(FEEDBACK_SUGGESTION,feedbackModel.getFeedbackSuggestion());
        cv.put(FEEDBACK_DATE,feedbackModel.getFeedbackDate());

        long insert = db.insert(FEEDBACK,null,cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public List<FeedbackModel> viewUserFeedback(int rid){
        List<FeedbackModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FEEDBACK + " WHERE " + FEEDBACK_REQUESTID + "=?",new String[] {String.valueOf(rid)});
        if (cursor.moveToFirst()){
            do{
                int fid=cursor.getInt(0);
                int fuserid=cursor.getInt(1);
                int fmechanicid=cursor.getInt(2);
                int frequestid=cursor.getInt(3);
                float fmechanicrate = cursor.getFloat(4);
                String fsatisfaction=cursor.getString(5);
                String fsuggestion=cursor.getString(6);
                String fdate=cursor.getString(7);

                FeedbackModel newfeedback=new FeedbackModel(fid,fuserid,fmechanicid,frequestid,fmechanicrate,fsatisfaction,fsuggestion,fdate);
                returnList.add(newfeedback);


            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<FeedbackModel> viewMechanicFeedback(int id){
        List<FeedbackModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FEEDBACK + " WHERE " + FEEDBACK_REQUESTID + "=?",new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            do{
                int fid=cursor.getInt(0);
                int fuserid=cursor.getInt(1);
                int fmechanicid=cursor.getInt(2);
                int frequestid=cursor.getInt(3);
                float fmechanicrate = cursor.getFloat(4);
                String fsatisfaction=cursor.getString(5);
                String fsuggestion=cursor.getString(6);
                String fdate=cursor.getString(7);

                FeedbackModel newfeedback=new FeedbackModel(fid,fuserid,fmechanicid,frequestid,fmechanicrate,fsatisfaction,fsuggestion,fdate);
                returnList.add(newfeedback);


            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }


    public List<FeedbackModel> viewAllFeedback(){
        List<FeedbackModel> returnList = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FEEDBACK ,null);
        if (cursor.moveToFirst()){
            do{
                int fid=cursor.getInt(0);
                int fuserid=cursor.getInt(1);
                int fmechanicid=cursor.getInt(2);
                int frequestid=cursor.getInt(3);
                float fmechanicrate = cursor.getFloat(4);
                String fsatisfaction=cursor.getString(5);
                String fsuggestion=cursor.getString(6);
                String fdate=cursor.getString(7);

                FeedbackModel newfeedback=new FeedbackModel(fid,fuserid,fmechanicid,frequestid,fmechanicrate,fsatisfaction,fsuggestion,fdate);
                returnList.add(newfeedback);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean updateMechanicRating(int mid,float mrating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MECHANIC_RATING, mrating);

        long update = db.update(MECHANIC_TABLE, cv, MECHANIC_ID + "=?", new String[]{String.valueOf(mid)});
        db.close();
        if (update == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean updateFeedbackMechRating(int fid,float frating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FEEDBACK_MECHANIC_RATING, frating);

        long update = db.update(FEEDBACK, cv, FEEDBACK_ID + "=?", new String[]{String.valueOf(fid)});
        db.close();
        if (update == -1) {
            return false;
        } else {
            return true;
        }
    }

}
