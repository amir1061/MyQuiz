package com.parkpoint.ubexquiz.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parkpoint.ubexquiz.Model.Shipments;
import com.parkpoint.ubexquiz.R;

import java.util.List;

public class ShipmentListAdapter extends ArrayAdapter<Shipments> {

    private Context context;
    private List<Shipments>shipmentsList;

    public ShipmentListAdapter(Context mContext, List<Shipments> shipmentsList1){
        super(mContext, R.layout.shipment_lay,shipmentsList1);
        this.context = mContext;
        this.shipmentsList = shipmentsList1;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from (context);
        convertView = layoutInflater.inflate (R.layout.shipment_lay,parent,false);

        Shipments shipments = shipmentsList.get (position);

        TextView t1 = convertView.findViewById (R.id.t1);
        TextView t2 = convertView.findViewById (R.id.t2);
        TextView t3 = convertView.findViewById (R.id.t3);
        TextView t4 = convertView.findViewById (R.id.t4);
        TextView t5 = convertView.findViewById (R.id.t5);
        TextView t6 = convertView.findViewById (R.id.t6);
        TextView t7 = convertView.findViewById (R.id.t7);

        t1.setText (shipments.getReference ());
        t2.setText (shipments.getFrom ());
        t3.setText (shipments.getStatus ());
        t4.setText (shipments.getCreated_at ());
        t5.setText (shipments.getDelivered ());
        t6.setText (shipments.getLocation ());
        t7.setText (shipments.getPrice ());


        return convertView;
    }
}
