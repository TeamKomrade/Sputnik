package com.example.sputnik.repository.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sputnik.repository.database.dao.ContactDao;
import com.example.sputnik.repository.database.entity.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
}