package com.denjand.tugasroom.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denjand.tugasroom.R;
import com.denjand.tugasroom.crud.kasir.UpdateKasirActivity;
import com.denjand.tugasroom.database.model.Kasir;

import java.util.List;

public class KasirAdapter extends RecyclerView.Adapter<KasirAdapter.KasirViewHolder> {

    private Context mCtx;
    private List<Kasir> kasirList;

    public KasirAdapter(Context mCtx, List<Kasir> kasirList) {
        this.mCtx = mCtx;
        this.kasirList = kasirList;
    }

    @Override
    public KasirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false);
        return new KasirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KasirViewHolder holder, int position) {
        Kasir p = kasirList.get(position);
        holder.textViewNama.setText(p.getNama());
        holder.textViewEmail.setText(p.getEmail());
        holder.textViewJnsKelamin.setText(p.getJns_Kelamin());
        holder.textViewNoHP.setText(p.getNoHP());
        holder.textViewStatus.setText("Info Produk");
    }

    @Override
    public int getItemCount() {
        return kasirList.size();
    }

    class KasirViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewNama, textViewEmail, textViewJnsKelamin, textViewNoHP, textViewStatus;

        public KasirViewHolder(View itemView) {
            super(itemView);

            textViewNama = itemView.findViewById(R.id.textViewNama);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewJnsKelamin = itemView.findViewById(R.id.textViewJnsKelamin);
            textViewNoHP = itemView.findViewById(R.id.textViewNoHP);
            textViewStatus = itemView.findViewById(R.id.textViewInfo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Kasir kasir = kasirList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateKasirActivity.class);
            intent.putExtra("kasir", kasir);

            mCtx.startActivity(intent);
        }
    }
}