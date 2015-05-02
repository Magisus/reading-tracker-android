package hu.ait.android.maggie.readingtracker;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

/**
 * Created by Magisus on 5/2/2015.
 */
public class GetBooksTask extends AsyncTask<String, Void, String> {

    public static final String API_KEY = "AIzaSyCMPsi-ZuGWEEYH53iHmsMo14O9dYJVu_Y";

    private Context context;

    public GetBooksTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        HttpURLConnection connection = null;
        InputStream in = null;

        try{
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            int response = connection.getResponseCode();

            if(response == HttpURLConnection.HTTP_OK){
                in = connection.getInputStream();

                int ch;
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while((ch = in.read()) != -1){
                    out.write(ch);
                }

                result = new String(out.toByteArray());
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                connection.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG);
//        Gson gson = new Gson();
//        BookSearchResult books = gson.fromJson(result, BookSearchResult.class);
//
//        EventBus.getDefault().post(books);
    }
}
