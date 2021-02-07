package com.example.android_acquaintance;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String title;
    private String content;
    private String creationDate;
    private int color;

    public Note(String title, String content, String creationDate, int color) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.color = color;
    }

    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
        creationDate = in.readString();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(creationDate);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public int getColor() {
        return color;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
