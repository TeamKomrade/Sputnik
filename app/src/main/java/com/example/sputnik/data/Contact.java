package com.example.sputnik.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.sputnik.R;


@Entity
public class Contact {
    @PrimaryKey
    public int id;

    public Contact(String firstName, String lastName, String number, String organization) {
        FirstName = firstName;
        LastName = lastName;
        Number = number;
        Organization = organization;
    }

    private String FirstName;
    private String LastName;
    private String Number;
    private String Organization;

    @Ignore
    public String getFullNameOrPhone() {
        String result = FirstName + ' ' + LastName;
        if (result.length() == 0) result = String.valueOf(Number);
        return result;
    }

    public int getImageId() {
        return R.drawable.ic_baseline_search_24;
    }
}
