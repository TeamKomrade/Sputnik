package com.example.sputnik;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.sputnik.databinding.ActivityContactInfoBinding;
import com.example.sputnik.repository.AppData;
import com.example.sputnik.repository.database.entity.Contact;

public class ContactInfoActivity extends AppCompatActivity {
    private AppData appData;
    private Contact contact;
    private ActivityContactInfoBinding binding;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appData = AppData.getAppDataInstance(getApplicationContext());
        id = getIntent().getIntExtra(AppData.ID, -1);
        appData.contactDatabase.contactDao().findById(id).observe(this, new Observer<Contact>() {
            @Override
            public void onChanged(Contact contactFromDatabase) {
                if (contactFromDatabase == null) finish();
                contact = contactFromDatabase;
                binding.setContact(contact);
                appData.loadImage(contact.profilePictureURL, binding.PhotoImageView);
            }
        });
    }
}
