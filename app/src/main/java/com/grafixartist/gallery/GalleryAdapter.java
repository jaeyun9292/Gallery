package com.grafixartist.gallery;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suleiman19 on 10/22/15.
 */
public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<ImageModel> data = new ArrayList<>();

    public GalleryAdapter(Context context, List<ImageModel> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v;
            v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_item, parent, false);
            viewHolder = new MyItemHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            Glide.with(context).load(data.get(position).getUrl())       //with() - view, fragment 혹은 activity로부터 context를 가져온다
                    .thumbnail(0.5f)                                    //load() - 이미지를 로드한다.
                    .override(200,200)                    //thumbnail() - 실제 이미지를 50%의 화질로 불러온 후 원본 이미지의 화질로 전환
                    .crossFade()                                        //override() - 이미지 사이즈 조절
                    .diskCacheStrategy(DiskCacheStrategy.ALL)           //crossfade() - 로드하는 이미지를 부드럽게 페이딩
                    .into(((MyItemHolder) holder).mImg);                //disckCascheStrategy() - 디스크에 캐싱 여부 설정
                                                                        //into() - 이미지를 보여줄 view를 지정
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyItemHolder extends RecyclerView.ViewHolder {
        ImageView mImg;


        public MyItemHolder(View itemView) {
            super(itemView);

            mImg = (ImageView) itemView.findViewById(R.id.item_img);
        }

    }


}
