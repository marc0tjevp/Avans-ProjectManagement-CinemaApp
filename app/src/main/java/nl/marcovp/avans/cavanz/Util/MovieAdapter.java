package nl.marcovp.avans.cavanz.Util;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Controller.MovieDetailActivity;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.view.LayoutInflater;
import java.util.ArrayList;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import nl.marcovp.avans.cavanz.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Inge on 1-4-2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>  {

    private static final String TAG = "MovieAdapter";
    private ArrayList<Movie> mDataset;
    private Context mContext;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private static final String TAG = "ViewHolder";
        public View view;
        public TextView movieTitle;
        public ImageView moviePoster;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.view.setOnClickListener(this);

            movieTitle = (TextView) view.findViewById(R.id.movie_offer_filmtitle);
            moviePoster = (ImageView) view.findViewById(R.id.movie_offer_filmposter);

        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: called");

            int position = getAdapterPosition();
            Movie movie = mDataset.get(position);

            Intent movieDetailIntent = new Intent(
                    view.getContext().getApplicationContext()
                    ,MovieDetailActivity.class);

            movieDetailIntent.putExtra("MOVIE", movie);
            view.getContext().startActivity(movieDetailIntent);
        }
    }

    //constructor for adapter
    public MovieAdapter(ArrayList<Movie> mDataset, Context mContext) {
        this.mDataset = mDataset;
        this.mContext = mContext;
    }

    //Making new views, invoked by layout manager
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.activity_movie_offer_listitem, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        CardView cardView = (CardView) view.getRootView().findViewById(R.id.listItem_cv);
        cardView.setMinimumWidth( (int ) (parent.getWidth()/2.2));

        return viewHolder;
    }

    //Placing values from dataset into view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Movie movie = mDataset.get(position);

        holder.movieTitle.setText(movie.getTitle());
        Picasso.with(mContext).load(movie.getImageUrl()).into(holder.moviePoster);

    }

    //Returns size of dataset
    @Override
    public int getItemCount() {
        int size = mDataset.size();
        Log.i(TAG, "getItemCount called, size:" + size);
        return size;
    }
}