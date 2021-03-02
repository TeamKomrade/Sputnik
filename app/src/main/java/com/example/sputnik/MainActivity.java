package com.example.sputnik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sputnik.databinding.ActivityContactInfoBinding;
import com.example.sputnik.databinding.ActivityMainBinding;
import com.example.sputnik.databinding.ItemContactBinding;
import com.example.sputnik.repository.AppData;
import com.example.sputnik.repository.database.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Contact> contacts = new ArrayList<>();
    private int positionContact;
    private AppData appData;
    private ContactAdapter adapter;
    private LayoutInflater inflater;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
        binding = ActivityMainBinding.inflate(inflater);
        setContentView(binding.getRoot());
        appData = AppData.getAppDataInstance(getApplicationContext());

        binding.AddContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editActivity = new Intent(MainActivity.this, ContactAddActivity.class);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation(MainActivity.this,
                                binding.AddContactButton,
                                "appName");
                startActivity(editActivity, options.toBundle());
            }
        });

        setRecycler();
    }

    private void setRecycler() {
        adapter = new ContactAdapter(this, contacts);
        binding.ContactsRecyclerView.setAdapter(adapter);

        appData.contactDatabase.contactDao().getAll().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contactList) {
                contacts = contactList;
                adapter.notifyDataSetChanged();
                binding.ContactCountTextView.setText(String.valueOf(contacts.size()));
            }
        });
        adapter.notifyDataSetChanged();
    }

    public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
        private LayoutInflater inflater;
        private AppData appData;

        public ContactAdapter(Context context, List<Contact> contacts) {
            appData = AppData.getAppDataInstance(context);
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(ItemContactBinding.inflate(inflater,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            final Contact contact = contacts.get(position);
            holder.contactBinding.NicknameTextView.setText(contact.getFullNameOrPhone());
            appData.loadImage(contact.profilePictureURL, holder.contactBinding.PhotoImageView);
            holder.contactBinding.ItemFrameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent activity = new Intent(MainActivity.this, ContactInfoActivity.class);
                    activity.putExtra(appData.ID, contact.id);
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(MainActivity.this,
                                    holder.contactBinding.PhotoImageView,
                                    "appName");
                    startActivity(activity, options.toBundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ItemContactBinding contactBinding;

            public ViewHolder (@NonNull ItemContactBinding contactBinding) {
                super(contactBinding.getRoot());
                this.contactBinding = contactBinding;
            }
        }
    }
}