package com.example.contactapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {

    private EditText edtName, edtPhone, edtMail;
    private Contact contact;
    private ContactDatabase contactDatabase;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        contactDatabase = ContactDatabase.getInstance(getApplicationContext());
        contactDao = contactDatabase.contactDao();
        edtName = findViewById(R.id.edt_name);
        edtPhone = findViewById(R.id.edt_phone);
        edtMail = findViewById(R.id.edt_mail);
        if((contact = (Contact) getIntent().getSerializableExtra("contact")) != null) {
            edtName.setText(contact.getName());
            edtPhone.setText(contact.getMobile());
            edtMail.setText(contact.getEmail());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_done:
                if(contact != null) {
                    Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                    Intent intent1 = new Intent (AddContactActivity.this, DetailContactActivity.class);
                    contact.setName(edtName.getText().toString());
                    contact.setEmail(edtMail.getText().toString());
                    contact.setMobile(edtPhone.getText().toString());
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            contactDao.updateContact(contact);
                        }
                    });
                    intent1.putExtra("contact", contact);
                    startActivities(new Intent[]{intent, intent1});
                }
                else {
                    Intent intent = new Intent();
                    Contact newContact = new Contact(edtName.getText().toString(), edtPhone.getText().toString(), "", edtMail.getText().toString());
                    intent.putExtra("contact", newContact);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}