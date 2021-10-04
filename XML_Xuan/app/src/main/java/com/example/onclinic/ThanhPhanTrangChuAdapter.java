package com.example.onclinic;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ThanhPhanTrangChuAdapter extends BaseAdapter{
    Context context;
    int layout;
    List<ThanhPhanTrangChu> arrayList;

    public ThanhPhanTrangChuAdapter(Context context, int layout, List<ThanhPhanTrangChu> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    private class ViewHolder{
        TextView tvTen;
        ImageView imgvThanhPhan;
        ImageView imgvKhung;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder =  new ViewHolder();

            //ánh xạ
            viewHolder.tvTen = (TextView)view.findViewById(R.id.txtTenThanhPhan);
            viewHolder.imgvThanhPhan = (ImageView)view.findViewById(R.id.imgvThanhPhan);
            viewHolder.imgvKhung = (ImageView)view.findViewById(R.id.imgvKhung);

            view.setTag(viewHolder);
        }else{
             viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvTen.setText(arrayList.get(i).getTenThanhPhan());
        viewHolder.imgvThanhPhan.setImageResource(arrayList.get(i).getAnh());
        viewHolder.imgvKhung.setImageResource(arrayList.get(i).getKhung());
        return view;
    }
}
