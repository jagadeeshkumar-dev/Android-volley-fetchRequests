package com.app.productsvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
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

    private static final String APIURL="https://cybertoday.ml/restapi.php";

    RecyclerView recyclerview;
    productadapter adapter;

    List<product>productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList =new ArrayList<>();

        recyclerview=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));


       loadproducts();




    }

    private void loadproducts(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET,APIURL,
                new
                       Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONArray products=new JSONArray(response);
                            for (int i=0; i<products.length();i++){

                                JSONObject productobject=products.getJSONObject(i);

                                int id= productobject.getInt("id");
                                String title=productobject.getString("title");
                                String shortdesc=productobject.getString("shortdesc");
                                double price= productobject.getDouble("price");
                                double rating=productobject.getDouble("rating");
                                String image=productobject.getString("image");

                                product product=new product(id,title,shortdesc,rating,price,image);

                                productList.add(product);


                            }
                            adapter= new productadapter(MainActivity.this,productList);
                            recyclerview.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

            },
                new
                        Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

        Volley.newRequestQueue(this).add(stringRequest);


    }
}
