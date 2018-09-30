package org.ourkidslearningjourney.discoverymovie;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class UtilsNetwork {
    public static final String MOVIEDB_APIKEY = "";

    public static final String MOVIEAPI_POPULARREQUEST =
            "https://api.themoviedb.org/3/movie/popular?api_key=";
    public static final String MOVIEAPI_TOPRATEDREQUEST =
            "https://api.themoviedb.org/3/movie/top_rated?api_key=";

    public static final String MOVIEAPI_START_REQUEST = "https://api.themoviedb.org/3/movie/";
    public static final String MOVIEAPI_MID_REVIEWREQUEST = "/reviews?api_key=";
    public static final String MOVIEAPI_MID_VIDEOREQUEST = "/videos?api_key=";
    public static final String MOVIEAPI_END_REQUEST = "&language=en-US&page=1";




    public static URL buildURL(String param) {
        Uri movieURI = Uri.parse(param);
        URL movieURL = null;

        try {
            movieURL = new URL(movieURI.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return movieURL;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
