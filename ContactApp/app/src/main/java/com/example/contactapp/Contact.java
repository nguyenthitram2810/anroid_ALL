package com.example.contactapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "id")
    private int id;
    @ColumnInfo (name = "name")
    private String name;
    @ColumnInfo (name = "mobile")
    private String mobile;
    @ColumnInfo (name = "avatar")
    private String avatar;
    @ColumnInfo (name = "email")
    private String email;

    public Contact(String name, String mobile, String avatar, String email) {
        this.name = name;
        this.mobile = mobile;
        this.avatar = avatar;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
