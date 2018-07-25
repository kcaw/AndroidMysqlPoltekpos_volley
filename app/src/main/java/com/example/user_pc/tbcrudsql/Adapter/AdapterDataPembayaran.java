package com.example.user_pc.tbcrudsql.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user_pc.tbcrudsql.pembayaran.InsertPembayaran;
import com.example.user_pc.tbcrudsql.Model.ModelData;
import com.example.user_pc.tbcrudsql.R;

import java.util.List;

public class AdapterDataPembayaran extends RecyclerView.Adapter<AdapterDataPembayaran.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterDataPembayaran(Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_pembayaran,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;

    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvnsiswa.setText(md.getNsiswa());
        holder.tvnisn.setText(md.getNisn());

        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvnsiswa,tvnisn;
        ModelData md;

        public HolderData (View view)
        {
            super(view);

            tvnsiswa = (TextView) view.findViewById(R.id.nsiswa);
            tvnisn = (TextView) view.findViewById(R.id.nisn);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent update = new Intent(context, InsertPembayaran.class);
                    update.putExtra("update",1);
                    update.putExtra("nisn", md.getNisn());
                    update.putExtra("nsiswa", md.getNsiswa());
                    update.putExtra("pilprodi", md.getPilprodi());
                    update.putExtra("piljurusan", md.getPiljurusan());
                    update.putExtra("jumlah", md.getJumlah());

                    context.startActivity(update);
                }
            });
        }
    }
}
