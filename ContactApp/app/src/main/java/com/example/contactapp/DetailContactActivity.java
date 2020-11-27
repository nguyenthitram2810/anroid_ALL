package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DetailContactActivity extends AppCompatActivity {
    private TextView tvName, tvMail, tvPhone;
    private ContactDao contactDao;
    private ContactDatabase contactDatabase;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        tvName = findViewById(R.id.tv_name);
        tvMail = findViewById(R.id.tv_mail);
        tvPhone = findViewById(R.id.tv_phone);
        contact = (Contact) getIntent().getSerializableExtra("contact");
        tvName.setText(contact.getName());
        tvMail.setText(contact.getEmail());
        tvPhone.setText(contact.getMobile());

        contactDatabase = ContactDatabase.getInstance(getApplicationContext());
        contactDao = contactDatabase.contactDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_edit:
                Intent intent1 = new Intent(DetailContactActivity.this, AddContactActivity.class);
                intent1.putExtra("contact", contact);
                startActivity(intent1);
                return true;
            case R.id.mi_delete:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        contactDao.deleteContact(contact);
                    }
                });
                Intent intent2 = new Intent(DetailContactActivity.this, MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}