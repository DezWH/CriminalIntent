package adroid.bigranch.com;

import android.content.Context;

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
    private List<Crime> mCrimes;

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
        //Java array to store the list elements.
        // Now I have a fully loaded model layer iwth 100 crimes.
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
        Crime crime = new Crime();
        crime.setTitle("Crime #" +1);
        crime.setSolved(i % 2 == 0); //Every other one
            mCrimes.add(crime);
        }
    }
    public void addCrime(Crime c)

    {
        // Add (c) to the object mCrimes.
        mCrimes.add(c);
    }

    //-------------------------------------------------------
    // Array List that gets Crimes method and returns mCrimes
    //-------------------------------------------------------
    public List<Crime> getCrimes()

    {   //returns mCrimes
        return mCrimes;
    }

    //----------------------------------------------
    // Accessor: Gets Crime(UUID id) parameters
    //----------------------------------------------
    public Crime getCrime(UUID id) {
        //
        for (Crime crime: mCrimes) {    //----------------------------------------------------------------------------
            // The getId method is placed on the crime object and .equals(id) examines the Id
            //  and returns crime.
            //----------------------------------------------------------------------------
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        //returns null
        return null;
    }
}