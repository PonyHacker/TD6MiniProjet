package pro.lartigue.td6miniprojet;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private EditText mEdit;
    private TextView mTextView;
    private ImageView mImageView;
    private ListView mListView;
    private static final int MOVIE_PAGE_LIMIT = 10;
    private String API_KEY = "5820fbc35916a7a6163b22166f557130";
    String str = "";

    public MainActivity() throws JSONException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.mButton);
        mEdit = (EditText) findViewById(R.id.mEdit);
        mTextView = (TextView) findViewById(R.id.mTextView);
        mImageView = (ImageView) findViewById(R.id.mImageView);


        final RequestQueue queue = Volley.newRequestQueue(this);
        final Context context = this;

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nombre_recherche, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        mButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        String film = mEdit.getText().toString().trim();

                        final String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=" + film;
                        final List<Film> films = new ArrayList<Film>();

                        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // Display the first 500 characters of the response string.

                                        try {

                                            JSONObject obj = new JSONObject(response);
                                            JSONArray results = (JSONArray) obj.get("results");
                                            for (int i = 0; i < results.length(); i++) {
                                                JSONObject obj1 = (JSONObject) results.get(i);
                                                Film film_parcours = new Film(obj1.toString());
                                                films.add(film_parcours);
                                                //mTextView.append("Titre : " + film_parcours.getTitle());
                                                // film_parcours.affichage_poster(mImageView, context);//
                                            }


                                            FilmAdapter adapter = new FilmAdapter(MainActivity.this, films);
                                            mListView = (ListView) findViewById(R.id.listView);
                                            mListView.setAdapter(adapter);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        //mTextView.setText("Response is: "+ response.substring(0,500)); //
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mTextView.setText("That didn't work!");
                            }
                        });
                        // Add the request to the RequestQueue.
                        queue.add(stringRequest);
                    }
                });
    }
}