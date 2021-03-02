package com.example.sputnik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sputnik.databinding.ActivityContactAddBinding;
import com.example.sputnik.repository.AppData;
import com.example.sputnik.repository.database.entity.Contact;

public class ContactAddActivity extends AppCompatActivity {
    private ActivityContactAddBinding binding;
    private LayoutInflater inflater;
    private AppData appData;
    private Contact contact;
    private boolean valid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
        binding = ActivityContactAddBinding.inflate(inflater);
        setContentView(binding.getRoot());
        appData = AppData.getAppDataInstance(getApplicationContext());

        contact = new Contact();

        binding.NumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Long.parseLong(s.toString());
                    valid = true;
                } catch (NumberFormatException ex) {
                    valid = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.ImageUrlEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    appData.loadImage(s.toString(), binding.PhotoImageView);
                } catch (NumberFormatException ex) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.AddContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!valid) return;
                contact.firstName = binding.FirstNameEditText.getText().toString();
                contact.lastName = binding.LastNameEditText.getText().toString();
                contact.number = binding.NumberEditText.getText().toString();
                contact.organization = binding.OrganizationEditText.getText().toString();
                contact.melody = binding.MelodyEditText.getText().toString();
                contact.profilePictureURL = binding.ImageUrlEditText.getText().toString();
                appData.contactDatabase.contactDao().insert(contact);
                finish();
            }
        });
    }
}