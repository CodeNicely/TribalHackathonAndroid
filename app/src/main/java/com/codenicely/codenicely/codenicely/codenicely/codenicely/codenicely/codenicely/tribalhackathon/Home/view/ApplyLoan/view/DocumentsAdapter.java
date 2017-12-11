package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentDetails;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.home.Home;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.view.SubSchemesFragment;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vrihas on 11/12/17.
 */

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.ViewHolder>{

    LayoutInflater layoutInflater;
    SharedPrefs sharedPreferences;
    Context context;
    private List<DocumentDetails>  schemesDataList = new ArrayList<>();

    public DocumentsAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setSchemesDataList(List<DocumentDetails> documentDetailsList) {
        this.schemesDataList = documentDetailsList;
    }

    @Override
    public DocumentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.schemes_items,parent,false);
        sharedPreferences = new SharedPrefs(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DocumentsAdapter.ViewHolder holder, int position) {
        final DocumentDetails data = schemesDataList.get(position);


        holder.title_schemes.setText(data.getId());
        holder.cardView_scheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.setKeyId(data.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return schemesDataList.size();
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
