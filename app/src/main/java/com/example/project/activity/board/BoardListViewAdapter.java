package com.example.project.activity.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.data.PostData;

import java.util.ArrayList;

public class BoardListViewAdapter extends BaseAdapter {

    private TextView titleTextView;
    private TextView contentTextView;

    private ArrayList<PostData> listViewItemList = new ArrayList<PostData>();
    private Context context;

    public BoardListViewAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        titleTextView = convertView.findViewById(R.id.title);
        contentTextView = convertView.findViewById(R.id.content);

        PostData data = listViewItemList.get(position);

        titleTextView.setText(data.getTitle());
        contentTextView.setText(data.getContents());

        return convertView;
    }

    public void addItem(PostData data){
        System.out.println(data);
        listViewItemList.add(data);
    }

    public int getSize(){
        return listViewItemList.size();
    }
}
