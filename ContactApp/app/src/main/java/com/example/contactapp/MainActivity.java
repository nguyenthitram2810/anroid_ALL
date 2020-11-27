package com.example.contactapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private RecyclerView rvContacts;
    private ArrayList<Contact> arrayList;
    private MyAdapter adapter;
    private SearchView search;
    private ContactDatabase contactDatabase;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContacts = findViewById(R.id.rv_contact);
        search = (SearchView) findViewById(R.id.sv_name);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<Contact>();

        adapter = new MyAdapter(arrayList, this);
        rvContacts.setAdapter(adapter);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                contactDatabase = ContactDatabase.getInstance(getApplicationContext());
                contactDao = contactDatabase.contactDao();
                List<Contact> dbContacts = contactDao.getAllContacts();
                for(Contact contact:dbContacts) {
                    arrayList.add(contact);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        search.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String searchText) {
        adapter.getFilter().filter(searchText);
        return false;
    }

    public void clickOnAddContact (View view) {
        Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
        startActivityForResult(intent, 567);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 567) {
            final Contact newContact = (Contact) data.getSerializableExtra("contact");
            arrayList.add(newContact);
            adapter.notifyDataSetChanged();
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    contactDao.insertContact(newContact);
                }
            });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}