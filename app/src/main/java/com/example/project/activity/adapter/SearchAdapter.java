package com.example.project.activity.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.project.R;
import com.example.project.data.board.PostData;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    private TextView titleTextView;
    private TextView contentTextView;

    private List<PostData> listViewItemList;
    private Context context;
    private ViewHolder viewHolder;

    public SearchAdapter(List<PostData> listViewItemList, Context context){
        this.listViewItemList = listViewItemList;
        this.context = context;
    }

    public void setListViewItemList(List<PostData> list){
        this.listViewItemList = list;
    }

    @Override
    public int getCount() { return listViewItemList.size(); }

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

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_listview, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.label = convertView.findViewById(R.id.label);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.label.setText(listViewItemList.get(position).getTitle());

        return convertView;
    }

    public void addItem(PostData data){
        System.out.println(data);
        listViewItemList.add(data);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean findID(PostData data){
        return listViewItemList.stream().anyMatch(item -> item.getId() == data.getId());
    }

    public int getSize(){
        return listViewItemList.size();
    }

    class ViewHolder{
        public TextView label;
    }
}
