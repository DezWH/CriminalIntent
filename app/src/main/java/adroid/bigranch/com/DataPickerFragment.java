package adroid.bigranch.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.Layout;
import android.util.AndroidException;
import android.view.LayoutInflater;

/**
 * Created by Dez on 11/23/2017.
 */

public class DataPickerFragment extends DialogFragment {
     public static final String EXTRA_DATE =
        "com.adroid.bigranch.com.criminalIntent.date";
    private static final String ARG_DATE = "date";

    private DatePicker mDatePicker;

    public static DatePickerFragment newInstance(date date){
        Bundle args = new Bundle()
                args.putSerializable(ARG_DATE, date);

        DatePickerDialog fragement = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override

    public Dialog onCreateDialog(Bundle savedIntanceState)
    {
        Date date = (Date) getArgument().getSerializable(ARG_DATE);

        Calender calender = Calender.getInstance();
        calender.setTime(date);
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calender.MONTH);
        int day = calender.get(Calender.DAY_OF_MONTH);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);
        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        mDdatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();

        DatePicker datePicker = new DatePicker(getActivity());
    }
        private void sendResult(int resultCode, Date date){
        if(getTargetFragment()==null){

            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode()), resultCode, intent);

        }
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

    return new AlertDialog.Builder(getActivity())
        .setView(v)
        .setTitle(R.string.date_picker_title)
            //.setPositiveButton(android.R.String.ok, null)
            .setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                @Override
                        public  void onClick(DialogInterface dialog, int which)
        {
        int year = mDatePicker.getYear();
        int month = mDatePicker.getMonth();
        int day = mDatePicker.GetDayofMonth();
        Date date = new GregorianCalendar(year, month, day).gettimes();
        resendResult(Activity.RESULT_OK, date);
    }
    })
            .create();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) mDatePicker
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            mDateButton.setText(mCrime.geDate().toString());
        }
    }
}

    public AlertDialog.Builder settitle(int titleId)
        public AlertDialog.Builder setPositiveButton(int textId
            DialogInterface.OnClickListener listener)

}
