package com.example.todo_app;

public class NotesPojo {
    String mTitle;
    String mDescription;
    String mId;

    public NotesPojo(){


}

    public NotesPojo(String id,String tile ,String deesc ){
        this.mDescription = deesc;
        this.mId = id;
        this.mTitle = tile;

    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmId() {
        return mId;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
}
