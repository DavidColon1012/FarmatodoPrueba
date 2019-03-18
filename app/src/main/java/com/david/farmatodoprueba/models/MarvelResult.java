
package com.david.farmatodoprueba.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarvelResult implements Parcelable {

    public static String TAG = MarvelResult.class.getSimpleName();

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("fullName")
    @Expose
    private String fullName;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("modified")
    @Expose
    private String modified;

    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;


    protected MarvelResult(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        title = in.readString();
        fullName = in.readString();
        description = in.readString();
        modified = in.readString();
        resourceURI = in.readString();
    }

    public static final Creator<MarvelResult> CREATOR = new Creator<MarvelResult>() {
        @Override
        public MarvelResult createFromParcel(Parcel in) {
            return new MarvelResult(in);
        }

        @Override
        public MarvelResult[] newArray(int size) {
            return new MarvelResult[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(fullName);
        dest.writeString(description);
        dest.writeString(modified);
        dest.writeString(resourceURI);
    }
}
