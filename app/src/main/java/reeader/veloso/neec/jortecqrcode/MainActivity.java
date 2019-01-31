package reeader.veloso.neec.jortecqrcode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String email;
    int inscrito = 0;
    int almoco = 0;
    int passou = 0;
    int almocou = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView temail = (TextView) findViewById(R.id.textView9);


         Button lercodigo = (Button) findViewById(R.id.button);

        //server
         final Button binscrito = (Button) findViewById(R.id.button3);
         final Button brealmoco = (Button) findViewById(R.id.button1);
         final Button bentrou = (Button) findViewById(R.id.button2);
         final Button blevantou = (Button) findViewById(R.id.button4);


        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
        temail.setText(settings.getString("email", "jveloso077@gmail.com").toString());
        temail.setTextSize(15);

        //get servidor
        String obtido = settings.getString("email", "Nada").toString();



// Response received from the server
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                       inscrito =  jsonResponse.getInt("inscrito");
                       almoco =  jsonResponse.getInt("almoco");
                       passou =  jsonResponse.getInt("passou");
                       almocou = jsonResponse.getInt("levantou");


                        if(almocou == 0){
                            blevantou.setText("Não Levantou almoço");
                            blevantou.setBackgroundColor(Color.RED);
                        }

                        if(almocou == 1){
                            blevantou.setText("Levantou almoço");
                            blevantou.setBackgroundColor(Color.GREEN);
                        }

                        if(passou == 0){
                            bentrou.setText("Não entrou JORTEC");
                            bentrou.setBackgroundColor(Color.RED);
                        }

                        if(passou == 1){
                            bentrou.setText("Entrou JORTEC");
                            bentrou.setBackgroundColor(Color.GREEN);
                        }


                        if(inscrito == 0){
                            binscrito.setText("Não inscrito JORTEC");
                            binscrito.setBackgroundColor(Color.RED);
                        }

                        if(inscrito == 1){
                            binscrito.setText("Inscrito JORTEC");
                            binscrito.setBackgroundColor(Color.GREEN);
                        }

                        if(almoco == 0){
                            brealmoco.setText("Não inscrito Almoco");
                            brealmoco.setBackgroundColor(Color.RED);
                        }

                        if(almoco == 1){
                            brealmoco.setText("Inscrito Almoco");
                            brealmoco.setBackgroundColor(Color.GREEN);
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            infoRequest loginRequest = new infoRequest ( obtido,responseListener);
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(loginRequest);


        //fim do get


        lercodigo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {

                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

                    startActivityForResult(intent, 0);


                } catch (Exception e) {

                    Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
                    startActivity(marketIntent);

                }

            }
        });


        blevantou.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Button bentrou = (Button) findViewById(R.id.button2);
                if( almocou == 0)
                    almocou = 1;
                else
                    almocou = 0;

                Log.d("entrou","passou aqui");

// Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonOResponse = null;
                        try {
                            jsonOResponse = new JSONObject(response);
                            boolean sucess = jsonOResponse.getBoolean("success");
                            if(sucess == true){

                                if(almocou == 0){
                                    blevantou.setText("Não levantou almoço");
                                    blevantou.setBackgroundColor(Color.RED);
                                }

                                if(almocou == 1){
                                    blevantou.setText("levantou almoço");
                                    blevantou.setBackgroundColor(Color.GREEN);
                                }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }









                    }
                };

                SharedPreferences settings = getSharedPreferences("UserInfo", 0);

                levantouRequest loginRequest = new levantouRequest ( settings.getString("email", "jveloso077@gmail.com").toString(),passou,responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(loginRequest);


            }
        });






        bentrou.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Button bentrou = (Button) findViewById(R.id.button2);
                if(passou == 0)
                    passou = 1;
                else
                    passou = 0;



// Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonOResponse = null;

                        try {
                            jsonOResponse = new JSONObject(response);
                            boolean sucess = jsonOResponse.getBoolean("success");
                            if(sucess == true){

                                if(passou == 0){
                                    bentrou.setText("Não entrou JORTEC");
                                    bentrou.setBackgroundColor(Color.RED);
                                }

                                if(passou == 1){
                                    bentrou.setText("Entrou JORTEC");
                                    bentrou.setBackgroundColor(Color.GREEN);
                                }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }







                    }
                };

                SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                entrouRequest loginRequest = new entrouRequest ( settings.getString("email", "jveloso077@gmail.com").toString(),passou,responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(loginRequest);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                email = data.getStringExtra("SCAN_RESULT");

                SharedPreferences settings = getSharedPreferences("UserInfo", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("email", email);
                editor.commit();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );

                startActivity(i);



            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
            }
        }
    }
}
