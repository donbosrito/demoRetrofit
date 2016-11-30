package com.application.don.demoretrofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.application.don.demoretrofit.Model.SinhVien;
import com.application.don.demoretrofit.R;

import java.util.List;

/**
 * Created by don on 11/28/16.
 */

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    private List<SinhVien> items;
    public SinhVienAdapter(Context context, List<SinhVien> list ) {
        super(context, 0, list);
        items = list;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final SinhVien item = items.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_item, parent, false);
        }
        TextView itemMSSV = (TextView) convertView.findViewById(R.id.txtMSSV);
        TextView itemTen = (TextView) convertView.findViewById(R.id.txtTen);
        TextView itemLop = (TextView) convertView.findViewById(R.id.txtLop);

        itemMSSV.setText(item.getMaSo());
        itemTen.setText(item.getTen());
        itemLop.setText("Lớp: " + item.getLop());

        return convertView;
    }
}
