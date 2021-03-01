package com.example.sputnik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sputnik.data.Contact;
import com.example.sputnik.view.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Contact> contacts = new ArrayList<>();
    int positionContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seed();
        TextView contactCount = (TextView) findViewById(R.id.ContactCountTextView);
        contactCount.setText(String.valueOf(contacts.size()));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ContactsRecyclerView);
        ContactAdapter adapter = new ContactAdapter(this, contacts);
        recyclerView.setAdapter(adapter);
    }

    private void seed() {
        contacts.add(new Contact("макч", "тест","89123456789", "OKEI"));
        contacts.add(new Contact("макщ", "текст","88005553535", "OKEI"));
        contacts.add(new Contact("факщ", "кекст","69-13-37", "OKEI"));
        contacts.add(new Contact("факс", "хехст","420-22-22", "OKEI"));
    }

}