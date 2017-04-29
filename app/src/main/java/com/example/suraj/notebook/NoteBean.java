package com.example.suraj.notebook;


public class NoteBean {

    String NoteTitle, NoteBody;
    Category category;
    long noteId, dateCreated;
    long datecreted;
    int image;

    public enum Category {
        PERSONAL, TECHNICAL, QUOTE, FINANCE;
    }

    public NoteBean(String noteTitle, String noteBody, Category category) {
        NoteTitle = noteTitle;
        NoteBody = noteBody;
        this.category = category;
        this.noteId = 0;
        this.datecreted = 0;
    }

    public NoteBean(String noteTitle, String noteBody, Category category, long noteId, long dateCreated, long datecreted, int image) {
        NoteTitle = noteTitle;
        NoteBody = noteBody;
        this.category = category;
        this.noteId = noteId;
        this.dateCreated = dateCreated;
        this.datecreted = datecreted;
        this.image = image;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getNoteTitle() {
        return NoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        NoteTitle = noteTitle;
    }

    public String getNoteBody() {
        return NoteBody;
    }

    public void setNoteBody(String noteBody) {
        NoteBody = noteBody;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public long getDatecreted() {
        return datecreted;
    }

    public void setDatecreted(long datecreted) {
        this.datecreted = datecreted;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "NoteBean{" +
                "NoteTitle='" + NoteTitle + '\'' +
                ", NoteBody='" + NoteBody + '\'' +
                ", category=" + category +
                ", noteId=" + noteId +
                ", dateCreated=" + dateCreated +
                ", datecreted=" + datecreted +
                ", image=" + image +
                '}';
    }

    public int getAssociatedDrawable(){

        return categoryToDrawable(category);
    }

    public static int categoryToDrawable(Category noteCategory) {
        switch (noteCategory) {
            case PERSONAL:
                return R.drawable.p;
            case QUOTE:
                return R.drawable.q;
            case TECHNICAL:
                return R.drawable.t;
            case FINANCE:
                return R.drawable.f;


        }
        return R.drawable.p;


    }


}


