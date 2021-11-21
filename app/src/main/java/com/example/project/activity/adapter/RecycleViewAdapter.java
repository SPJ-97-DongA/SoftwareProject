package com.example.project.activity.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.data.RegionDetailData;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.overlay.Marker;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Marker mark;
    private NaverMap naverMap;

    private List<RegionDetailData> list;

    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    private int prePosition = -1;

    public RecycleViewAdapter(List<RegionDetailData> list, Marker mark, NaverMap naverMap){
        this.list = list;
        this.mark = mark;
        this.naverMap = naverMap;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.placelist_item, parent,false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final RecycleViewHolder recycleViewHolder = (RecycleViewHolder) holder;
        final int selectedPos = recycleViewHolder.getAdapterPosition();

        recycleViewHolder.onBind(list.get(selectedPos), selectedPos, selectedItems);

        recycleViewHolder.setOnViewHolderItemClickListener((view, pos) -> {
            if(selectedItems.get(pos)){
                selectedItems.delete(pos);
            }else{
                selectedItems.delete(prePosition);
                selectedItems.put(pos, true);
            }

            if(prePosition != -1) notifyDataSetChanged();
            notifyItemChanged(pos);

            prePosition = pos;


            double latitude = list.get(selectedPos).getLatitude();
            double longitude = list.get(selectedPos).getLongitude();
            CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(latitude, longitude))
                    .animate(CameraAnimation.Fly, 1000);
            naverMap.moveCamera(cameraUpdate);


            mark.setPosition(new LatLng(latitude, longitude));
            mark.setMap(naverMap);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(RegionDetailData data){
        list.add(data);
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder{

        TextView placeTitle;
        TextView placeContent;

        OnViewHolderItemClickListener onViewHolderItemClickListener;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            placeTitle = itemView.findViewById(R.id.placeTitle);
            placeContent = itemView.findViewById(R.id.placeContent);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    if(onViewHolderItemClickListener != null)
                        onViewHolderItemClickListener.onItemClick(v, pos);
                }
            });
        }

        public void onBind(RegionDetailData data, int position, SparseBooleanArray selectedItems){
            placeTitle.setText(data.getPlace_title());
            placeContent.setText(data.getPlace_content());
            changeVisibility(selectedItems.get(position));
        }

        private void changeVisibility(final boolean isExpanded){
            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 600) : ValueAnimator.ofInt(600, 0);
            va.setDuration(500);
            va.addUpdateListener(animation -> {
                placeContent.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            });

            va.start();
        }

        public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener){
            this.onViewHolderItemClickListener = onViewHolderItemClickListener;
        }
    }

    public interface OnViewHolderItemClickListener {
        void onItemClick(View v, int pos);
    }
}
