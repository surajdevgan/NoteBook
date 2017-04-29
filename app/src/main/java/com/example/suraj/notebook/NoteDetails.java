package com.example.suraj.notebook;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetails extends AppCompatActivity {
    public static final String NEW_NOTE_EXTRA = "New Note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        createfragment();
    }


    private void createfragment()
    {
        // recieving the INTENT
        Intent intent = getIntent();
        MainActivity.FragmentToLaunch fragmentToLaunch = (MainActivity.FragmentToLaunch) intent.getSerializableExtra(MainActivity.NOTE_FRAGMENT_TO_LOADD_EXTRA);
//
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (fragmentToLaunch)
        {

            // choose the correct fragment to launch
            case EDIT:
            // create and add note edit fragment to note detail activity if that wht u want to launch
                NoteEditFragment noteEditFragment = new NoteEditFragment();
                setTitle("EditNote");
                fragmentTransaction.add(R.id.note_container,noteEditFragment,"NOTE_EDIT_FRAGMENT");
                break;

            case VIEW:
                // create and add note view fragment to note detail activity if that wht u want to launch

                NoteViewFragment noteViewFragment = new NoteViewFragment();
                setTitle(R.string.viewFragmentTitle); // to set the title of ViewNote fragment
                fragmentTransaction.add(R.id.note_container,noteViewFragment,"NOTE_VIEW_FRAGMENT");  // finally add the fragment
                break;

            case CREATE:
                // this case will work when user click on add note action bar button
                NoteEditFragment noteCreateFragment = new NoteEditFragment();
                setTitle("Crete Note");
                // we are using bundle here to set information that we are creating new note

                Bundle bundle = new Bundle();
                noteCreateFragment.setArguments(bundle);
                bundle.putBoolean(NEW_NOTE_EXTRA, true); // NEW_NOTE_EXTRA is a key and true is for that yes we are creating new note

                fragmentTransaction.add(R.id.note_container,noteCreateFragment,"NOTE_CREATE_FRAGMENT"); // note_container is present in note_details.xml
                break;


        }



        fragmentTransaction.commit();  // commit our changes so that everything works

    }
}
