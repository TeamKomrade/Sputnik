package com.example.sputnik.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sputnik.R;
import com.example.sputnik.data.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Contact> contacts;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.contacts = contacts;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.photoView.setImageResource(contact.getImageId());
        holder.nicknameTextView.setText(contact.getFullNameOrPhone());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView photoView;
        final TextView nicknameTextView;

        ViewHolder (View view) {
            super(view);
            photoView = (ImageView) view.findViewById(R.id.PhotoImageView);
            nicknameTextView = (TextView) view.findViewById(R.id.NicknameTextView);
        }
    }
}
