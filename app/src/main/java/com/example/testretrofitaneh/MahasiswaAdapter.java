package com.example.testretrofitaneh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {
    private List<MahasiswaResponseModel> lists;
    private  Context context;

    OnClickListener onClickListener;

    public  MahasiswaAdapter(Context context, List<MahasiswaResponseModel> lists){
        this.context = context;
        this.lists = lists;
        this.onClickListener = (OnClickListener)context ;
    }
    @NonNull
    @Override
    public MahasiswaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.MyViewHolder holder, int position) {
        MahasiswaResponseModel mhs = lists.get(position);

        holder.tvNim.setText(mhs.getId_siswa());
        holder.tvAddress.setText(mhs.getAlamat());
        holder.tvNama.setText(mhs.getNama());
        holder.tvGender.setText(mhs.getJenis_kelamin());
        holder.tvPhone.setText(mhs.getNo_telp());

        //nah ini ketika iteviewnya diklik nanti jalanin method onclick yang ada di interface tadi,
        //terus ngirim data sesuai dengan posisi data yang diklik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(lists.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNim,tvNama,tvAddress,tvGender, tvPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNim = itemView.findViewById(R.id.txt_id);
            tvNama = itemView.findViewById(R.id.txt_nama);
            tvAddress = itemView.findViewById(R.id.txt_alamat);
            tvGender = itemView.findViewById(R.id.txt_jk);
            tvPhone = itemView.findViewById(R.id.txt_no_telp);

        }
    }

    //cara nambahi event clik itu ada 2
    //bisa di holder, bisa nambah interface diadapternya,
    //tapi biasanya kalo click terus ngirim data biasanya aku pake yang interface
    //jadi gini
    //nah gitu

    interface OnClickListener {
        void onClick(MahasiswaResponseModel mhs);
    }
}
