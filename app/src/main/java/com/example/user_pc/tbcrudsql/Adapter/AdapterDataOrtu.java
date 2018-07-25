package com.example.user_pc.tbcrudsql.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user_pc.tbcrudsql.ortu.InsertOrtu;
import com.example.user_pc.tbcrudsql.Model.ModelData;
import com.example.user_pc.tbcrudsql.R;

import java.util.List;

public class AdapterDataOrtu extends RecyclerView.Adapter<AdapterDataOrtu.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterDataOrtu(Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_ortu,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;

    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvnortu.setText(md.getNortu());
        holder.tvnik.setText(md.getNik());

        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvnortu,tvnik;
        ModelData md;

        public HolderData (View view)
        {
            super(view);

            tvnortu = (TextView) view.findViewById(R.id.nortu);
            tvnik = (TextView) view.findViewById(R.id.nik);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent updatee = new Intent(context, InsertOrtu.class);
                    updatee.putExtra("update",1);
                    updatee.putExtra("nik", md.getNik());
                    updatee.putExtra("nortu", md.getNortu());
                    updatee.putExtra("pekerjaan", md.getPekerjaan());
                    updatee.putExtra("alamat", md.getAlamat());

                    context.startActivity(updatee);
                }
            });
        }
    }
}
