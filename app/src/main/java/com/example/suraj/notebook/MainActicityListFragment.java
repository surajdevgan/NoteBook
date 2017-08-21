package com.example.suraj.notebook;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActicityListFragment extends ListFragment {  // this list fragment is linked with content_main.xml

    // In activity_main i included content_main.xml

    ArrayList<NoteBean> notes;
    NoteAdapter noteAdapter;
    ContentResolver resolver;






    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Calendar calendar = Calendar.getInstance();
        //calendar.
resolver = getActivity().getContentResolver();
        notes = new ArrayList<>();
       // notes.add(new NoteBean("assa","sdsdsdd",NoteBean.Category.QUOTE));
        RetrieveNotes();





    }

    void RetrieveNotes()
    {
        //1. Retrieve Data from DB
        //2. Convert Each Record into an Object of Type NoteBean
        //3. Put the objects into ArrayList


        String [] projection = {Util.COLUMN_TITLE,Util.COLUMN_MESSAGE,Util.COLUMN_CATEGORY};

        Cursor cursor = resolver.query(Util.NOTE_URI,projection,null,null,null);
        if (cursor!=null) {

            String t = "", m = "";
            NoteBean.Category c;


            while (cursor.moveToNext()) {
                t = cursor.getString(cursor.getColumnIndex(Util.COLUMN_TITLE));
                m = cursor.getString(cursor.getColumnIndex(Util.COLUMN_MESSAGE));
                c = NoteBean.Category.valueOf(cursor.getString(cursor.getColumnIndex(Util.COLUMN_CATEGORY)));
                notes.add(new NoteBean(t, m, c));

            }
        }

            noteAdapter = new NoteAdapter(getActivity(), R.layout.list_rows, notes);
            setListAdapter(noteAdapter);
            registerForContextMenu(getListView()); // registering the context menu so that we can see it
            // when anyone longPress on listViewItem onCreateContextMenu method will be called





    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        launhNoteDetails(MainActivity.FragmentToLaunch.VIEW, position);  // by clicking any list item open the viewnote fragment
    }


    // this method is for long_press_menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
        // after inflating the menu we need to register the menu in onActivityCreated

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Give me the position whatever note i longedpressed
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowposition = info.position;


        switch (item.getItemId()) { // return the id of menu item that we clicked
            case R.id.edit:
                // if we press edit
                launhNoteDetails(MainActivity.FragmentToLaunch.EDIT, rowposition);
                return true; // if we r handling the action we need to return true

            case R.id.Delete:
        }

        return super.onContextItemSelected(item); // if we don't handling anything we need to return  ContextItemSelected
    }

    private void launhNoteDetails(MainActivity.FragmentToLaunch ftl, int position) {

        NoteBean noteBean = (NoteBean) getListAdapter().getItem(position);  // grab the note information associated with whatever note item we clicked

        Intent intent = new Intent(getActivity(), NoteDetails.class);  //
        // the keys that we r using here are declared in mainactivity
        // i did this because i don't need to remember all the keys
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, noteBean.getNoteTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, noteBean.getNoteBody());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, noteBean.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, noteBean.getNoteId());

        switch (ftl) {
            case VIEW: // this case will open the noteview
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOADD_EXTRA, MainActivity.FragmentToLaunch.VIEW);
                break;

            case EDIT:  // this case will open noteedit fragment
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOADD_EXTRA, MainActivity.FragmentToLaunch.EDIT);
                break;






        }
        startActivity(intent);
    }
}


