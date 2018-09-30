package org.ourkidslearningjourney.discoverymovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterTrailer extends RecyclerView.Adapter<AdapterTrailer.TrailerViewHolder> {

    private Context mParentContext;
    private int mTotalItems;
    private List<MovieTrailer> mTrailerInfos;


    private final TrailerAdapterOnClickHandler mClickHandler;
    public interface TrailerAdapterOnClickHandler {
        void onClick(MovieTrailer trailerInfo);
    }

    public AdapterTrailer(TrailerAdapterOnClickHandler clickHandler, int totalItems, ArrayList<MovieTrailer> trailerInfos) {
        mClickHandler = clickHandler;
        mTotalItems = totalItems;
        mTrailerInfos = trailerInfos;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mParentContext = parent.getContext();
        int layoutIdForMovieList = R.layout.trailer_list_item;
        LayoutInflater inflater = LayoutInflater.from(mParentContext);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForMovieList, parent, shouldAttachToParentImmediately);
        TrailerViewHolder viewHolder = new TrailerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTotalItems;
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgTrailerView;
        TextView lblTrailerInfo;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            imgTrailerView = (ImageView) itemView.findViewById(R.id.img_trailer_youtubelogo);
            lblTrailerInfo = (TextView) itemView.findViewById(R.id.lbl_trailer_description);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            imgTrailerView.setBackgroundResource(R.drawable.icon_youtube);
            lblTrailerInfo.setText(mTrailerInfos.get(listIndex).getTrailerName() + "\n" + mTrailerInfos.get(listIndex).getTrailerType());
        }

        @Override
        public void onClick(View v) {
            int iPos = getAdapterPosition();
            MovieTrailer selectedTrailer = mTrailerInfos.get(iPos);
            mClickHandler.onClick(selectedTrailer);
        }
    }
}
