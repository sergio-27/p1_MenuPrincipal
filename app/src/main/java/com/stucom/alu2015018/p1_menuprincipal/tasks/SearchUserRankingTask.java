package com.stucom.alu2015018.p1_menuprincipal.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.stucom.alu2015018.p1_menuprincipal.R;
import com.stucom.alu2015018.p1_menuprincipal.user.User;
import com.stucom.alu2015018.p1_menuprincipal.user.UserList;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by alu2015018 on 09/01/2018.
 */

public class SearchUserRankingTask extends AsyncTask<String, Void, UserList> {

    public interface WeakReference {
        Context getContext();
        void finished(UserList userList);
    }

    private WeakReference ref;

    public SearchUserRankingTask(WeakReference ref) {
        super();
        this.ref = ref;
    }

    @Override
    protected UserList doInBackground(String... strings) {

        InputStream in = null;
        try {

            String userId = strings[0];

            userId = URLEncoder.encode(userId, "utf-8");

            String searchUserRanking = ref.getContext().getString(R.string.searchUserRanking, userId);

            //Log.d("sergio", "URL User Ranking: " + searchUserRanking);

            URL url = new URL(searchUserRanking);

            URLConnection con = url.openConnection();
            con.setConnectTimeout(1000);
            con.setReadTimeout(2000);

            in = url.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int total = 0, nRead;

            while ((nRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, nRead);
                total += nRead;
            }

            String json = new String(out.toByteArray());

            //Log.d("sergio", "JSON USER: " + json);

            Gson gson = new Gson();

            UserList userList = gson.fromJson(json, UserList.class);

            //User users = userList.getResultUsers();

            //Log.d("sergio", "Nombre: " + users.);

            return userList;

        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception ignored) {
            }
        }

    }

    @Override
    protected void onPostExecute(UserList userList) {
        ref.finished(userList);
    }
}
