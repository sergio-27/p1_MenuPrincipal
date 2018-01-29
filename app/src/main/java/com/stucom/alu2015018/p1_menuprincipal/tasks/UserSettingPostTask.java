package com.stucom.alu2015018.p1_menuprincipal.tasks;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.stucom.alu2015018.p1_menuprincipal.R;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by alu2015018 on 15/01/2018.
 */

public class UserSettingPostTask extends AsyncTask<String, Void, String>{

    private HttpURLConnection httpURLConnection;

    public interface Weakreference{
        Context getContext();
    }

    private Weakreference ref;

    public UserSettingPostTask(Weakreference ref) {
        this.ref = ref;
    }

    @Override
    protected String doInBackground(String... strings) {

        String data = "";

        try{

            String userId = strings[0];
            String jsonResult = strings[1];


            userId = URLEncoder.encode(userId, "utf-8");
            Log.d("sergio", "Json antes de encode: " + jsonResult);
            //jsonResult = URLEncoder.encode(jsonResult, "utf-8");

            String apiKey = ref.getContext().getResources().getString(R.string.apiKey);

            String urlUser = ref.getContext().getResources().getString(R.string.updateUserName);
            Log.d("sergio", "POST URL: " + urlUser);
            String userURL = ref.getContext().getResources().getString(R.string.updateUserName, userId);
            //obtenemos el json decodificado



            //actualizamos el json
            httpURLConnection = (HttpURLConnection) new URL(urlUser).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.addRequestProperty("Content-Type", "text/plain");
            httpURLConnection.addRequestProperty("Accept","application/json");

            httpURLConnection.connect();


//            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
//            wr.write(jsonResult);


            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(jsonResult);
            Log.d("sergio", "Json Guardado: " + jsonResult);
            //Log.d("sergio", "Responde Message: " + httpURLConnection.getRequestMethod());
            wr.flush();
            wr.close();



        }catch(Exception e){

        }finally{
           if (httpURLConnection != null)
               httpURLConnection.disconnect();
        }

        return data;
    }

}
