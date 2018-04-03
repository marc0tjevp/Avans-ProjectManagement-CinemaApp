package nl.marcovp.avans.cavanz.Util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import nl.marcovp.avans.cavanz.Domain.TicketType;
import nl.marcovp.avans.cavanz.R;

/**
 * Created by marco on 4/3/18.
 */

public class TicketTypeAdapter extends ArrayAdapter<TicketType> {

    public TicketTypeAdapter(@NonNull Context context, @NonNull List<TicketType> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TicketType ticketType = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tickettype, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.item_tickettype_textview_type);
        TextView textViewPrice = convertView.findViewById(R.id.item_tickettype_textview_price);

        textViewName.setText(ticketType.getTicketTypeName());
        textViewPrice.setText(String.valueOf(ticketType.getTicketPrice()));

        return convertView;
    }

}
