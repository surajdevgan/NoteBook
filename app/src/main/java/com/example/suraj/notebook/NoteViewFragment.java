package com.example.suraj.notebook;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteViewFragment extends Fragment {


    public NoteViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View FragmentLayout = inflater.inflate(R.layout.fragment_note_view,container,false);
        TextView title = (TextView) FragmentLayout.findViewById(R.id.viewNoteTitle);
        TextView message = (TextView) FragmentLayout.findViewById(R.id.viewnotemessage);
        ImageView icon = (ImageView) FragmentLayout.findViewById(R.id.viewNoteIcon);

        // Recieving the intent
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
         message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA));
        NoteBean.Category notecat = (NoteBean.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA); // getSerializableExtra is use to recieve the enum of category
        icon.setImageResource(NoteBean.categoryToDrawable(notecat));

        return FragmentLayout;
    }

}
