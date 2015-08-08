package pe.edu.upc.baby_sitter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    EditText emailval;
    EditText passwordval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginOnClick(View v) throws Exception {
        String url = "https://babysitterapp-davisrixi.c9.io/clients.json";
        emailval = (EditText) findViewById(R.id.editText);
        passwordval = (EditText) findViewById(R.id.editText2);


        final JsonArrayRequest jsonRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // the response is already constructed as a JSONObject!
                boolean authenticated = false;
                String emailv = String.valueOf(emailval.getText());
                String passwordv = String.valueOf(passwordval.getText());
                System.out.println("--->" + emailv);
                System.out.println("--->" + passwordv);
                try {
                    //int resultsCount = response.getString("numFound");
                    System.out.println("Results: " + response);
                    // int limit = resultsCount > 10 ? 10 : resultsCount;
                    for (int position = 0; position < response.length(); position++) {
                        JSONObject result = response.getJSONObject(position);
                        String email = result.getString("email");
                        String password = result.getString("password");
                        if (!email.isEmpty() && !password.isEmpty()) {
                            System.out.println("---> email:" + email);
                            System.out.println("---> password:" + password);
                            if (email.equals(emailv) && password.equals(passwordv)) {
                                System.out.println("---> Encontró usuario!");
                                authenticated = true;
                                break;
                            }
                        }
                    }

                    System.out.println("authenticated: " + authenticated);

                    if (authenticated) {
                        Intent intent = new Intent(LoginActivity.this, CustomerActivity.class);
                        startActivity(intent);
                    } else {
                        System.out.println("Client doesn't exist!");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );

        String url1 = "https://babysitterapp-davisrixi.c9.io/babysitters.json";
        final JsonArrayRequest jsonRequest1 = new JsonArrayRequest(
                Request.Method.GET, url1, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // the response is already constructed as a JSONObject!
                boolean authenticated = false;
                String emailv = String.valueOf(emailval.getText());
                String passwordv = String.valueOf(passwordval.getText());
                System.out.println("--->" + emailv);
                System.out.println("--->" + passwordv);
                try {
                    //int resultsCount = response.getString("numFound");
                    System.out.println("Results: " + response);
                    // int limit = resultsCount > 10 ? 10 : resultsCount;
                    for (int position = 0; position < response.length(); position++) {
                        JSONObject result = response.getJSONObject(position);
                        String email = result.getString("email");
                        String password = result.getString("password");
                        if(!email.isEmpty() && !password.isEmpty()) {
                            System.out.println("---> email:" +  email);
                            System.out.println("---> password:" +  password);
                            if (email.equals(emailv) && password.equals(passwordv)) {
                                System.out.println("---> Encontró usuario!");
                                authenticated = true;
                                break;
                            }
                        }
                    }

                    System.out.println("authenticated: " + authenticated);

                    if(authenticated){
                        Intent intent = new Intent(LoginActivity.this, CustomerActivity.class);
                        startActivity(intent);
                    }else{
                        System.out.println("Babysitter doesn't exist!");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );

        Volley.newRequestQueue(this).add(jsonRequest);
        Volley.newRequestQueue(this).add(jsonRequest1);


    }
}
