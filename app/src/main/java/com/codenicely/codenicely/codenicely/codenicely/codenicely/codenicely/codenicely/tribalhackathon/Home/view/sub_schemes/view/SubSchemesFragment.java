package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.model.SubSchemesData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.presenter.SubSchemesPresenter;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.presenter.SubSchemesPresenterImpl;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.provider.RetrofitSubSchemesProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubSchemesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubSchemesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubSchemesFragment extends Fragment implements SubSchemesView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.recycler_sub_schemes)
    RecyclerView schemes_recycler;

    @BindView(R.id.progressBar_sub_schemes)
    ProgressBar schemes_progressBar;

    WebView webView;

    LinearLayoutManager linearLayoutManager;
    SubSchemesAdapter adapter;
    SubSchemesPresenter subSchemesPresenter;
    SharedPrefs sharedPrefs;


    public SubSchemesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubSchemesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubSchemesFragment newInstance(String param1, String param2) {
        SubSchemesFragment fragment = new SubSchemesFragment();
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
        View view  = inflater.inflate(R.layout.fragment_sub_scheme, container, false);

        sharedPrefs = new SharedPrefs(getContext());
        schemes_recycler.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        schemes_recycler.setLayoutManager(linearLayoutManager);
        adapter = new SubSchemesAdapter(getContext());
        schemes_recycler.setAdapter(adapter);
        schemes_recycler.setNestedScrollingEnabled(false);

        subSchemesPresenter = new SubSchemesPresenterImpl(this,new RetrofitSubSchemesProvider());
        subSchemesPresenter.requestSchemes("access_token",false,sharedPrefs.getId());


        return view;
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

    @Override
    public void showProgressBar(boolean show) {
        if (show){
            schemes_progressBar.setVisibility(View.VISIBLE);
        }else {
            schemes_progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setSchemeData(List<SubSchemesData> subSchemesDataList) {

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
