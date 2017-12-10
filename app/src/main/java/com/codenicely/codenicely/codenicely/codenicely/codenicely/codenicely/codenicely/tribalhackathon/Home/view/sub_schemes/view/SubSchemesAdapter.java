package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrihas on 11/12/17.
 */

public class SubSchemesAdapter extends RecyclerView.Adapter<SubSchemesAdapter.ViewHolder>{

    LayoutInflater layoutInflater;
    Context context;
    private List<SchemesData>  schemesDataList = new ArrayList<>();

    public SubSchemesAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setSchemesDataList(List<SchemesData> schemesDataList) {
        this.schemesDataList = schemesDataList;
    }

    @Override
    public SubSchemesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.sub_scheme_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubSchemesAdapter.ViewHolder holder, int position) {
        SchemesData data = schemesDataList.get(position);
        holder.title_schemes.setText(data.getTitle());
        holder.cardView_scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            title_schemes = (TextView) itemView.findViewById(R.id.sub_scheme_title);
            cardView_scheme = (CardView) itemView.findViewById(R.id.sub_scheme_card);
        }
    }
}
