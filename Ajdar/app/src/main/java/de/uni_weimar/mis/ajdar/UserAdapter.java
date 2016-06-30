package de.uni_weimar.mis.ajdar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by matpa on 6/19/2016.
 */
public class UserAdapter extends ArrayAdapter<AjdarUser> {
    public UserAdapter(Context context, ArrayList<AjdarUser> users) {
        super(context, 0, users);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        AjdarUser ajdarUser = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gameborditem, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
        tvName.setText(ajdarUser.Name);
        tvId.setText(ajdarUser.Status+"");
        // Return the completed view to render on screen
        return convertView;
    }
}
