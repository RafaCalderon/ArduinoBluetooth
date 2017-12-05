package com.barium.rafacalderon.arduinobluetooth;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MiAdaptador extends ArrayAdapter<Integrante>{
    public MiAdaptador(Context context, List<Integrante> integranteList) {
        super(context, 0, integranteList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
        }
        ImageView img_circle = (ImageView) convertView.findViewById(R.id.img_item_circle);
        TextView txt_title = (TextView) convertView.findViewById(R.id.txt_item_title);
        TextView txt_subtitle = (TextView) convertView.findViewById(R.id.txt_item_subtitle);
        Integrante integrante = getItem(position);
        img_circle.setImageResource(integrante.getImagen());
        txt_title.setText(integrante.getNombre());
        txt_subtitle.setText(integrante.getGmail());
        return convertView;
    }
}
