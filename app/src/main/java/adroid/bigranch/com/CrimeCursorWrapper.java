package adroid.bigranch.com;

import android.database.CursorWrapper;

import java.util.UUID;

/**
 * Created by Dez on 2/10/2018.
 */

    // Write a Cursor subclkass is to use CursorWrapper.
    // Let you wrap a Cursor you received from another place and add new
    // methods on top of it.
    // You are reading from the CrimeLab in the CrimeCursorWrapper
public class CrimeCursorWrapper extends CursorWrapper
{
    // Reading from suspect column
    public CrimeCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }
    public Crime getCrime()
    {

            String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
            String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
            long data = getLong(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));
            int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
            String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));

            Crime crime = new Crime(UUID.fromString(uuidString));
            crime.setTitle(title);
            crime.setDate(new Date(date));
            crime.setSolved(isSolved !=0);
            crime.setmSuspect(suspect);

            return crime;
            //return null;

    }
}
