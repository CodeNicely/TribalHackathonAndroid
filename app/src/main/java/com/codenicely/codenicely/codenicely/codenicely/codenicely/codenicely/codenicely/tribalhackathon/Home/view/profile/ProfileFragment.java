package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String company_name1, mobile1,aadhaar,address;

    @BindView(R.id.address)
    EditText Address;

    @BindView(R.id.aadhaar)
    EditText Aadhaar;

    @BindView(R.id.company_name)
    EditText company_name;

    @BindView(R.id.mobile)
    EditText mobile;

    @BindView(R.id.proceed)
    Button proceed;

    SharedPrefs sharedPrefs;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        sharedPrefs = new SharedPrefs(getContext());
        final Random rand = new Random();
        initialise();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                company_name1 = company_name.getText().toString();
                mobile1 = mobile.getText().toString();
                address = Address.getText().toString();
                aadhaar= Aadhaar.getText().toString();
                hideKeyboard();

                if (mobile1.equals("") || mobile1.equals(null)) {
                    mobile.setError("Please Fill Mobile no.");
                    mobile.setFocusable(true);
                } else if (mobile1.length() != 10) {
                    mobile.setError("Invalid Mobile No.");
                    mobile.setFocusable(true);
                } else if (aadhaar.length() != 12) {
                    Aadhaar.setError("Invalid Aadhar No.");
                    Aadhaar.setFocusable(true);
                } else if(rand.nextInt(50) % 2 != 0)
                {
                    Aadhaar.setError("Invalid Aadhar No.");
                    Aadhaar.setFocusable(true);
                }
                else if (company_name1.equals("") || company_name1.equals(null)) {
                    company_name.setError("Please Fill Company Name");
                    company_name.setFocusable(true);
                }else if (aadhaar.equals("") || company_name1.equals(null)) {
                    company_name.setError("Please Fill Address");
                    company_name.setFocusable(true);
                }
                else {
                    Toast.makeText(getContext(),"Data updated successfully.",Toast.LENGTH_LONG);
                    sharedPrefs.setCompanyName(company_name1);
                    sharedPrefs.setEmail(address);
                    sharedPrefs.setMobile(mobile1);
                    sharedPrefs.setAadhaar(aadhaar);
                }
            }
        });

        return view;
    }
    private void initialise()
    {
        company_name.setText(sharedPrefs.getCompanyName());
        mobile.setText(sharedPrefs.getMobile());
        Address.setText(sharedPrefs.getADDRESS());
        Aadhaar.setText(sharedPrefs.getAadhaar());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
