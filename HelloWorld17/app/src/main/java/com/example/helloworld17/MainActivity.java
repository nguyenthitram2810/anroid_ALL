package com.example.helloworld17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helloworld17.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> arr;
    private ArrayAdapter<String> adapter;
    private int count;
    private ActivityMainBinding binding;
    private MyViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        count = 0;
        arr = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        binding.lvNumber.setAdapter(adapter);
        binding.lvNumber.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            String data = arr.get(i);
            intent.putExtra("data", data);
            startActivity(intent);
        });
        binding.lvNumber.setOnItemLongClickListener((adapterView, view, i, l) -> {
            arr.remove(i);
            adapter.notifyDataSetChanged();
            return false;
        });

        model.getNumber().observe(this, integer -> binding.tvCounter.setText(""+integer));
    }

    public void clickOnCounterButton(View v) {
//        count++;
//        binding.tvCounter.setText("" + count);
//        arr.add("" + count);
//        adapter.notifyDataSetChanged();
        model.increaseNumber();
    }
}