package com.example.project.activity.board;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.project.R;
import com.example.project.data.board.CommentData;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    private TextView writerView;
    private TextView commentView;

    private ArrayList<CommentData> CommentItemList = new ArrayList<>();
    private Context context;

    public CommentAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() { return CommentItemList.size(); }

    @Override
    public Object getItem(int position) {
        return CommentItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout. comment_item, parent, false);
        }


        writerView = convertView.findViewById(R.id.writer);
        commentView = convertView.findViewById(R.id.comment);

        CommentData data = CommentItemList.get(position);

        writerView.setText(data.getWriter());
        commentView.setText(data.getComment());

        int height = 0;
        height += writerView.getMeasuredHeight();
        height += commentView.getMeasuredHeight();
        height += 100;
        convertView.setMinimumHeight(height);
        return convertView;
    }

    public void addItem(CommentData data){
        CommentItemList.add(data);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean findDup(CommentData data){
        return CommentItemList.stream().anyMatch(item -> item.getCreatedAt().compareTo(data.getCreatedAt()) == 0);
    }

    public int getSize(){
        return CommentItemList.size();
    }
}
