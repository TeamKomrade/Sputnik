package com.example.sputnik.repository.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.sputnik.R;


@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "number")
    public String number;

    @ColumnInfo(name = "organization")
    public String organization;

    @ColumnInfo(name = "profile_pic")
    public String profilePictureURL;

    @ColumnInfo(name = "melody")
    public String melody;

    @Ignore
    public String getFullNameOrPhone() {
        String result = firstName + ' ' + lastName;
        if (result.length() == 0) result = String.valueOf(number);
        return result;
    }
}
