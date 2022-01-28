package com.example.proyectofinal;

import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.database.DatabaseHelper;
import com.example.proyectofinal.database.ProductData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private static final String TAG = ProductListFragment.class.getSimpleName();

    RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    ArrayList<ProductData> productArrayList;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.product_list_fragment, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));

        databaseHelper = new DatabaseHelper(this.getContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if(db != null){
            Log.e(TAG, " onResponse: siii ");
        } else {  ;}

        productArrayList = new ArrayList<>();

//        for(ProductData item: initProductEntryList(getResources())){
//            ProductData product = new ProductData(item.getName(), item.getBrand(),item.getCategory(), item.getStock(), item.getPrice());
//            databaseHelper.insertProducts(product);
//        }

        ProductListAdapter adapter = new ProductListAdapter(databaseHelper.getProducts());
        recyclerView.setAdapter(adapter);

        return view;
    }


    public static List<ProductData> initProductEntryList(Resources resources) {
        InputStream inputStream = resources.openRawResource(R.raw.products);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try{
            Reader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch(IOException e) {
            Log.e(TAG,"Error al leer o escribir el archivo JSON.", e);
        } finally {
            try{
                inputStream.close();
            } catch(IOException ex) {
                Log.e(TAG,"Error al cerrar la conexión.", ex);
            }
        }

        String jsonProductString = writer.toString();
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<ProductData>>() {
        }.getType();
        return gson.fromJson(jsonProductString, productListType);


    }


}
