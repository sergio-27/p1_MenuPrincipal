package com.stucom.alu2015018.p1_menuprincipal.ajustes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stucom.alu2015018.p1_menuprincipal.R;
import com.stucom.alu2015018.p1_menuprincipal.tasks.SearchUserRankingTask;
import com.stucom.alu2015018.p1_menuprincipal.tasks.UserSettingPostTask;
import com.stucom.alu2015018.p1_menuprincipal.user.User;
import com.stucom.alu2015018.p1_menuprincipal.user.UserList;

import org.json.JSONException;
import org.json.JSONObject;

public class AjustesActivity extends AppCompatActivity
        implements SearchUserRankingTask.WeakReference, UserSettingPostTask.Weakreference {

    private static final int CAMERA_REQUEST = 10;
    ImageView foto_jugador;
    Button btn_takePhoto, btn_guardar;
    EditText apellidoJugador, emailJugador, nombreJugador;
    TextView userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        //reutilizamos la task creada para obtener el ranking de un jugador


        userName = findViewById(R.id.userName);
        nombreJugador = findViewById(R.id.nameET);
        btn_takePhoto = findViewById(R.id.btn_takePhoto);
        btn_guardar = findViewById(R.id.btn_sendDataPlayer);
        foto_jugador = findViewById(R.id.fotoJugador);
        apellidoJugador = findViewById(R.id.apellidoET);
        emailJugador = findViewById(R.id.emailET);
        final String userId = "1";

        new SearchUserRankingTask(this).execute(userId);

        btn_takePhoto.setEnabled(false);

        //acceder a la camara
        btn_takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camara_intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camara_intent, CAMERA_REQUEST);

            }
        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //guardamos datos
                //Log.d("sergio", nombreJugador.getText().toString());
                if (apellidoJugador.getText().toString().equals("")
                        || emailJugador.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "No puede dejar campos vacios.", Toast.LENGTH_SHORT).show();
                } else {
                    String firstName = nombreJugador.getText().toString();
                    String sndName = apellidoJugador.getText().toString();
                    String playerEmail = emailJugador.getText().toString();

                    try {

//                        JSONObject jsonObject = new JSONObject();
//                        jsonObject.put("firstname", firstName);
//                        jsonObject.put("lastname", sndName);
//                        jsonObject.put("email", playerEmail);

                        String text = "key=48d256f9583d5e3a1932242f152fcbf6&firstname=" + firstName; //+ "&lastname=" + sndName + "&email=" + playerEmail;

                        new UserSettingPostTask(AjustesActivity.this).execute(userId, text);

                        Toast.makeText(getApplicationContext(), "Usuario actualizado", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


            }
        });


    }

    //guardar la foto bitmap en un imageview
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            Bitmap playerPhoto = (Bitmap) data.getExtras().get("data");

            foto_jugador.setImageBitmap(playerPhoto);

        }
    }


    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void finished(UserList userList) {
        User user = userList.getResultUsers();
        userName.setText(user.getUsername());
        nombreJugador.setText(user.getFirstname());
        apellidoJugador.setText(user.getLastname());
        emailJugador.setText(user.getEmail());
    }
}
