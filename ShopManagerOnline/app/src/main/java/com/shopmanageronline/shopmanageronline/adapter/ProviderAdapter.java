package com.shopmanageronline.shopmanageronline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shopmanageronline.shopmanageronline.R;
import com.shopmanageronline.shopmanageronline.entity.Provider;

import java.util.List;

/**
 * Created by ThanhDH - LA on 5/14/2017.
 */

public class ProviderAdapter extends ArrayAdapter<Provider> {

    public ProviderAdapter(Context context, int resource, List<Provider> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listview_item, null);
        }

        Provider p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.textView1);
            TextView tt2 = (TextView) v.findViewById(R.id.textView2);
            TextView tt3 = (TextView) v.findViewById(R.id.textView3);

            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt2 != null) {
                tt2.setText(p.getPhone());
            }

            if (tt3 != null) {
                tt3.setText(p.getAddress());
            }
        }

        return v;
    }

}