package adroid.bigranch.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dez on 11/11/2017.
 */

public class CrimeLab {//Beginning of Crime Class

    //Global private static variables
    private static CrimeLab sCrimeLab;

    // mCrimes holds an ArrayList
    // ArrayList is also a List  Both are valied typs for mCrimes.
    //private List<Crime> mCrimes;

   private List<Crime> mCrimes; // is returned by getCrimes()
    private Context mContext;
    private SQLiteDatabase mDatabase;

    //-------------------------------------------------------------------------------------------
    // Accessor: Public static CrimeLab that get's the parameter context and returns sCrimeLab
    // context obj is passed in.
    //------------------------------------------------------------------------------------------
    public static CrimeLab get(Context context)
    {
        //If sCrime == nul value
        if (sCrimeLab == null)
        {
            //Instatitates sCrimeLab obj.

            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    // return sCrimeLab;
    // Populate the list with 100 boring Crime objects.
    private CrimeLab(Context context)
    {
        mContext = context.getApplicationContext();

        // Class designed to get rid of the gnt work of opening
        // a SQLiteDatabase
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase(); //
       // mCrimes = new ArrayList<>();
    }


        //Java array to store the list elements.
        // Now I have a fully loaded model layer iwth 100 crimes.
        //mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
        Crime crime = new Crime();
        crime.setTitle("Crime #" +1);
        crime.setSolved(i % 2 == 0); //Every other one
          //  mCrimes.add(crime);
        }
    }
    // Add rows the database.
    public void addCrime(Crime c)
    {
        ContentValues values = getContentValues(c);
        {
                                            //The first argument is the table you want to insert
                                           // into here CrimeTable.NAME.
                                           // The Second Arugmjent is called the nullColumnHack
            mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values)
        }
        // Add (c) to the object mCrimes.
        //mCrimes.add(c);
    }

    //-------------------------------------------------------
    // Array List that gets Crimes method and returns mCrimes
    //-------------------------------------------------------
    public List<Crime> getCrimes()

    {
            // Add code to query for all crimes
            // wal the cursor, and populate a Crime List

        // return mCrimes;
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrime(
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[] { id.toString()}
        );

        try {
            if (cursor.getCocunt() == 0) {
                return null;

        }


        cursor.moveToFirst();
       return cursor.getCrime();

            //    while (!cursor.isAfterLast()) {
            //     crimes.add(cursor.getCrime());
            //   cursor.moveToNext();
    }finally{
        cursor.close();  // Important to call close on your cursor.
        }
        return crimes;

}
    //----------------------------------------------
    // Accessor: Gets Crime(UUID id) parameters
    //----------------------------------------------
    public Crime getCrime(UUID id) {

        //for (Crime crime: mCrimes) {    //----------------------------------------------------------------------------
            // The getId method is placed on the crime object and .equals(id) examines the Id
            //  and returns crime.
            //----------------------------------------------------------------------------
            // if (crime.getId().equals(id)) {
            // return crime;
           // }
           // }
        // returns null
        return null;
    }


    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values,
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[] { uuidString});
    }


    // Writes and updates to database are done with the assistance of a class called
    //ContentValues
    private static ContentValues getContentValues(Crime crime)
    {
        //They specify the columns that you want to insert or update.
        //Every Column is specified here except for
        // Creating ContentValues is a key-value. It's specifically designed
        // to store the kinds of data SQLite can hold.
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved()? 1: 0); // If you use ?,  thorugh, your code will do what you
        values.put(CrimeTable.Cols.SUSPECT, crime.getmSuspect());  // Write new column in CrimeLab.getContentValues(Crime)                                                    // It's best to use as habit, because it
                                                                   // will always do what you want it to.

        return values;

    }
}
        // A Cursor give you raw column values
        // You need to write this code on more more time.
        //
//private Cursor query Crimes(String whereClause, String [] whereArgs)
private CrimeCursorWrapper queryCrimes(String whereClause, String [] whereArgs)
        {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null, //columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBry
                null, // having
                null // orderBy

        };

      //  return cursor;
        return new CrimeCursorWrapper(cursor);

}
