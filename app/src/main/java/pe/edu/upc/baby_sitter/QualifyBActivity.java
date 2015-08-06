package pe.edu.upc.baby_sitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class QualifyBActivity extends AppCompatActivity {

    TextView txtbs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualify_b);

        String valorString = getIntent().getStringExtra("valorTest");
        txtbs = (TextView) findViewById(R.id.txt_babysister);
        //txtbs.setText(valorString);

//        try
//        {
////            HttpResponse response = httpClient.execute(httpPost);
////            String jsonResult = inputStreamToString(response.getEntity().getContent().toString());
////            JSONObject object = new JSONObject(jsonResult);
////
////            String name = object.getString("lastname");
////            txtbs.setText(name);
//
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost("https://babysitterapp-fzacariasf.c9.io/clients");
//            httpPost.setHeader("content-type", "application/json");
//            JSONObject dato = new JSONObject();
//
//            dato.put("lastname", txtbs.getText().toString());
//         //   dato.put("phone", Integer.parseInt(txtTelefono.getText().toString()));
//
//            StringEntity entity = new StringEntity(dato.toString());
//            httpPost.setEntity(entity);
//
//        }
//        catch (JSONException e)
//        {
//            txtbs.setText("Ocurrio Error.. " + e.getMessage());
//        }

        HttpClient httpClient = new DefaultHttpClient();

        String id = "1" ;
                //txtId.getText().toString();

        HttpGet del = new HttpGet("https://babysitterapp-fzacariasf.c9.io/clients/1.json");

        del.setHeader("content-type", "application/json");

        try
        {
            HttpResponse resp = httpClient.execute(del);
            String respStr = EntityUtils.toString(resp.getEntity());

            JSONObject respJSON = new JSONObject(respStr);

            int idCli = respJSON.getInt("id");
            String nombCli = respJSON.getString("name");
            int telefCli = respJSON.getInt("phone");

            txtbs.setText("" + idCli + "-" + nombCli + "-" + telefCli);
        }
        catch(Exception ex)
        {
            //Log.e("ServicioRest","Error!", ex);
            txtbs.setText("Ocurrio Error.. " + ex.getMessage());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qualify_b, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
