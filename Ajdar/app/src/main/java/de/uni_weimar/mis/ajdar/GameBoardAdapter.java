package de.uni_weimar.mis.ajdar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.uni_weimar.mis.ajdar.GameBoard;
import de.uni_weimar.mis.ajdar.R;

public class GameBoardAdapter extends ArrayAdapter<GameBoard> {
    public GameBoardAdapter(Context context, ArrayList<GameBoard> users) {
        super(context, 0, users);
    }
//    public View getGameBoard(int position) {
//        // Get the data item for this position
//        GameBoard gameBoard = getItem(position);
//    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        GameBoard gameBoard = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gameborditem, parent, false);
        }
       TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
        tvName.setText(gameBoard.Name);
        tvId.setText(gameBoard.ID+"");
        // Return the completed view to render on screen
        return convertView;
    }
}