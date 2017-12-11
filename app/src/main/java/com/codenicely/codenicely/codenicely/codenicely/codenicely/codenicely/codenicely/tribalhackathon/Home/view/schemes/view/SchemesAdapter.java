package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.home.Home;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.views.SplashView;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.view.SubSchemesFragment;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrihas on 11/12/17.
 */

public class SchemesAdapter extends RecyclerView.Adapter<SchemesAdapter.ViewHolder>{

    LayoutInflater layoutInflater;
    SharedPrefs sharedPreferences;
    Context context;
    private List<SchemesData>  schemesDataList = new ArrayList<>();

    public SchemesAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setSchemesDataList(List<SchemesData> schemesDataList) {
        this.schemesDataList = schemesDataList;
    }

    @Override
    public SchemesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.schemes_items,parent,false);
        sharedPreferences = new SharedPrefs(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SchemesAdapter.ViewHolder holder, int position) {
        final SchemesData data = schemesDataList.get(position);


        holder.title_schemes.setText(data.getTitle());
        holder.cardView_scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setKeyId(data.getId());
                ((Home)context).addFragment(new SubSchemesFragment());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title_schemes;
        CardView cardView_scheme;

        public ViewHolder(View itemView)
        {
            super(itemView);
            title_schemes = (TextView) itemView.findViewById(R.id.scheme_title);
            cardView_scheme = (CardView) itemView.findViewById(R.id.scheme_card);
        }
    }





}
