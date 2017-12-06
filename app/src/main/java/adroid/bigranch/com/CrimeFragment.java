package adroid.bigranch.com;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import java.util.Date;

import static android.widget.CompoundButton.*;
import java.util.UUID;

import static adroid.bigranch.com.CriminalActivity.EXTRA_CRIME_ID;

/**
 * Created by Dez on 11/11/2017.
 */

public class CrimeFragment extends Fragment {

    //"com.adroid.bigranch.com.criminalintent.crime_id";
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;


    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;


    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mCrime = new Crime();

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher()
        {

            @Override
            public void beforeTextChanged
                    (CharSequence s, int i, int i1, int i2) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged
                    (CharSequence s, int i, int i1, int i2) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged
                    (Editable editable) {
            }
        });

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate();

        mDateButton.setOnClickListener(new View.onClickListener(){
            @Override
           public void onClick (View v){

                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFrament
                        .newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);

            }
        });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonview, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode != Activity.RESULT_OK) {

                return;

            }

            if (requestCode == REQUEST_DATE) {


                Date date = (Date) data

                        .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                mCrime.setDate(date);
                updateDate();
            }
        }
    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());

    }
}

