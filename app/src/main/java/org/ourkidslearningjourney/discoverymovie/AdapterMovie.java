package org.ourkidslearningjourney.discoverymovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MovieViewHolder> {
    public static final String MOVIE_STARTURL = "http://image.tmdb.org/t/p/w780/";

    private Context mParentContext;
    private int mTotalItems;
    private List<MovieInfo> mMovieInfos;


    private final MovieAdapterOnClickHandler mClickHandler;
    public interface MovieAdapterOnClickHandler {
        void onClick(MovieInfo movieInfo);
    }

    public AdapterMovie(MovieAdapterOnClickHandler clickHandler, int totalItems, ArrayList<MovieInfo> movieInfos) {
        mClickHandler = clickHandler;
        mTotalItems = totalItems;
        mMovieInfos = movieInfos;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mParentContext = parent.getContext();
        int layoutIdForMovieList = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(mParentContext);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForMovieList, parent, shouldAttachToParentImmediately);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTotalItems;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgMovieView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imgMovieView = (ImageView) itemView.findViewById(R.id.img_movieview);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            Picasso.with(mParentContext).load(MOVIE_STARTURL + mMovieInfos.get(listIndex).getMoviePosterPath()).into(imgMovieView);
        }

        @Override
        public void onClick(View v) {
            int iPos = getAdapterPosition();
            MovieInfo selectedMovie = mMovieInfos.get(iPos);
            mClickHandler.onClick(selectedMovie);
        }
    }
}
