package pro.lartigue.td6miniprojet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dylan on 02/04/2017.
 */

public class FilmAdapter extends ArrayAdapter<Film> {
    public FilmAdapter(Context context, List<Film> list_film) {
        super(context, 0, list_film);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_film, parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new TweetViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.titre);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.affiche = (ImageView) convertView.findViewById(R.id.affiche);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<film> list_film
        Film f = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.titre.setText(f.getTitle());
        viewHolder.text.setText(f.getOtherview());
        Picasso.with(this.getContext()).load(f.link_poster()).into(viewHolder.affiche);
        return convertView;
    }

    private class TweetViewHolder {
        public TextView titre;
        public TextView text;
        public ImageView affiche;
    }
}


