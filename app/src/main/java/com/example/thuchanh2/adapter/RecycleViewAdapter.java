package com.example.thuchanh2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.thuchanh2.R;
import com.example.thuchanh2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends
        RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder >{

    private List<Item> list;
    private ItemListener itemListener;

    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Item getItem(int position){
        return list.get(position);

    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
       Item item = list.get(position);
       holder.Ten.setText(item.getTen());
       holder.NgayDenHan.setText(item.getNgaydenhan());
        holder.NgayDenHan.setText(item.getNgaydenhan());
        holder.NoiDung.setText(item.getNoidung());
        holder.TrangThai.setText(item.getTrangthai());
        holder.HinhThuc.setText(item.getHinhthuc());

//        int tt = item.getTrangthai();
//        if(tt==0) holder.TrangThai.setText("chưa thực hiện");
//        else if(tt==1) holder.TrangThai.setText("đang thực hiện");
//        else holder.TrangThai.setText("hoàn thành");
//
//        int ht = item.getHinhthuc();
//        if(ht==0) holder.HinhThuc.setText("1 mình");
//        else holder.HinhThuc.setText("làm chung");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder
                                implements View.OnClickListener{
        private TextView Ten,NgayDenHan,TrangThai,HinhThuc,NoiDung;

        public HomeViewHolder(@NonNull View v) {
            super(v);

            Ten = v.findViewById(R.id.tvten);
            NgayDenHan = v.findViewById(R.id.tvngaydenhan);
            NoiDung = v.findViewById(R.id.tvnoidung);
            TrangThai = v.findViewById(R.id.tvtrangthai);
            HinhThuc = v.findViewById(R.id.tvHinhthuc);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(itemListener!=null){
                itemListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
    public interface ItemListener{
        void onItemClick(View v,int position);
    }
}
