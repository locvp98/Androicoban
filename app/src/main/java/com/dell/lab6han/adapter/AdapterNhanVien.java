package com.dell.lab6han.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dell.lab6han.MainActivity;
import com.dell.lab6han.R;
import com.dell.lab6han.model.NhanVien;

import java.util.List;

public class AdapterNhanVien extends ArrayAdapter<NhanVien> {
    private MainActivity context;
    private int resource;
    private List<NhanVien> nhanVienList;


    public AdapterNhanVien(@NonNull MainActivity context, int resource, @NonNull List<NhanVien> nhanVienList) {
        super(context, resource, nhanVienList);
        this.context=context;
        this.resource=resource;
        this.nhanVienList=nhanVienList;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_nhanvien,parent,false);
            viewHolder.tvma = convertView.findViewById(R.id.tvma);
            viewHolder.tvten = convertView.findViewById(R.id.tvten);
            viewHolder.tvluong = convertView.findViewById(R.id.tvluong);
            viewHolder.imgxoa = convertView.findViewById(R.id.imgxoa);

            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        NhanVien nhanVien=nhanVienList.get(position);
        viewHolder.tvma.setText(nhanVien.getmMa());
        viewHolder.tvten.setText(nhanVien.getmTennv());
        viewHolder.tvluong.setText(nhanVien.getMluong());

        viewHolder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(position);
            }
        });

        return convertView;


    }

    public  class ViewHolder{

        TextView tvma;
        TextView tvten;
        TextView tvluong;
        ImageView imgxoa;
    }
}
