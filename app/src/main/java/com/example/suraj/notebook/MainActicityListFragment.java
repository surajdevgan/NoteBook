package com.example.suraj.notebook;


import android.content.Intent;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActicityListFragment extends ListFragment {  // this list fragment is linked with content_main.xml

    // In activity_main i included content_main.xml

    ArrayList<NoteBean> notes;
    NoteAdapter noteAdapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        notes = new ArrayList<NoteBean>();
        notes.add(new NoteBean("This is titleddfdfdfdf", "This is the body of noteffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", NoteBean.Category.PERSONAL));
        notes.add(new NoteBean("ass", "sfdffdfdfdfdfdfdfdfdfddfdd", NoteBean.Category.FINANCE));
        notes.add(new NoteBean("fdfdfdf","dfdfdfdfdfdf",NoteBean.Category.TECHNICAL));
        notes.add(new NoteBean("fdfdfdf","dfdfdfdfdfdf",NoteBean.Category.QUOTE));

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


