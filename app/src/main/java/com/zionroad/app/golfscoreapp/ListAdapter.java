package com.zionroad.app.golfscoreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    private final Context mContext;
    private final Hole[] mHoles;

    public ListAdapter(Context context, Hole[] holes) {
        mContext = context;
        mHoles = holes;
    }

    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int i) {
        return mHoles[i];
    }

    @Override
    public long getItemId(int i) {
        return 0; // Not implemented
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO: commented well!
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.holeLabel = (TextView) convertView.findViewById(R.id.holeView);
            holder.strokeCount = (TextView) convertView.findViewById(R.id.strokeCountView);
            holder.removeStroke = (Button) convertView.findViewById(R.id.removeStrokeButton);
            holder.addStroke = (Button) convertView.findViewById(R.id.addStrokeButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.holeLabel.setText(mHoles[position].getLabel());
        holder.strokeCount.setText(String.valueOf(mHoles[position].getStrokeCount()));
        holder.removeStroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer updateStrokeCount = mHoles[position].getStrokeCount() - 1;
                // (Stroke isn't will be 0 in golf)
                if (updateStrokeCount < 0) updateStrokeCount = 0;
                mHoles[position].setStrokeCount(updateStrokeCount);
                holder.strokeCount.setText(String.valueOf(updateStrokeCount));
            }
        });
        holder.addStroke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer updateStrokeCount = mHoles[position].getStrokeCount() + 1;
                mHoles[position].setStrokeCount(updateStrokeCount);
                holder.strokeCount.setText(String.valueOf(updateStrokeCount));
            }
        });

        return convertView;
    }

    // This contains every view in a row (based on list_item.xml it the layout folder)
    private static class ViewHolder{
        TextView holeLabel;
        TextView strokeCount;
        Button removeStroke;
        Button addStroke;
    }
}
