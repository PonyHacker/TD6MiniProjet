package pro.lartigue.td6miniprojet;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dylan on 02/04/2017.
 */

public class Film {
    private String poster_path; //chemin de l'image
    private boolean adult; // si film pour adulte ou non
    private String otherview; // résumé du film
    private String release_date; //date de sortie
    private int id; //id du film
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int[] genreId;
    private int vote_count;
    private double vote_average;


    public Film(String film_data) {
        try {
            JSONObject film = new JSONObject(film_data);
            this.poster_path = film.get("poster_path").toString();
            this.id = (int) film.get("id");
            this.title = film.getString("title");
            this.vote_average = (int) film.get("vote_count");
            this.otherview = film.get("overview").toString();
            this.vote_count = (int) film.get("vote_count");
            this.popularity = Double.parseDouble(film.getString("popularity"));
            this.release_date = film.getString("date");
            //}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void affichage_poster(ImageView mImageView, Context c1) {
        String urlImage = "" + this.poster_path;
        String urlglobal = "https://image.tmdb.org/t/p/w640/" + urlImage;
        Picasso.with(c1).load(urlglobal).into(mImageView);
    }

    public String link_poster() {
        String urlImage = "" + this.poster_path;
        String urlglobal = "https://image.tmdb.org/t/p/w640/" + urlImage;
        return urlglobal;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOtherview() {
        return otherview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public int[] getGenreId() {
        return genreId;
    }

    public int getVote_count() {
        return vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }


}
