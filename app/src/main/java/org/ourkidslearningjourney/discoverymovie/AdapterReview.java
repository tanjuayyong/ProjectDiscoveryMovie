package org.ourkidslearningjourney.discoverymovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterReview extends RecyclerView.Adapter<AdapterReview.ReviewViewHolder> {
    private Context mParentContext;
    private int mTotalItems;
    private List<MovieReview> mReviewInfos;

    public AdapterReview(int totalItems, ArrayList<MovieReview> reviewInfos) {
        mTotalItems = totalItems;
        mReviewInfos = reviewInfos;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mParentContext = parent.getContext();
        int layoutIdForMovieList = R.layout.review_list_item;
        LayoutInflater inflater = LayoutInflater.from(mParentContext);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForMovieList, parent, shouldAttachToParentImmediately);
        ReviewViewHolder viewHolder = new ReviewViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTotalItems;
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView lblContent;
        TextView lblAuthor;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            lblContent = (TextView) itemView.findViewById(R.id.lbl_review_content);
            lblAuthor = (TextView) itemView.findViewById(R.id.lbl_review_author);
        }

        void bind(int listIndex) {
            lblContent.setText(mReviewInfos.get(listIndex).getReviewContent());
            lblAuthor.setText(mReviewInfos.get(listIndex).getReviewAuthor());
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
