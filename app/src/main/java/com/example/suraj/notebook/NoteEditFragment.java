package com.example.suraj.notebook;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

      private ImageButton noteCatButton;
    private NoteBean.Category savedButtonCategory;
    private AlertDialog categoryDialogObkect,confirmDialogObject;
    EditText title,message;
    private static final String MODIFIED_CATEGORY = "Modified Category";
    private boolean newNote = false;



    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // grab the bundle that sends along weather or not our noteedit fragment is creating a new note or noot

        Bundle bundle = this.getArguments(); // getArgument retrieve the bundle that we send to notedit fragment
        if(bundle!=null)  // it means that we have information in iur bundle
        {
            newNote = bundle.getBoolean(NoteDetails.NEW_NOTE_EXTRA,false);
        }


        if(savedInstanceState!=null)
        {
            savedButtonCategory = (NoteBean.Category) savedInstanceState.get(MODIFIED_CATEGORY);
        }

        // inflate our editFragment layout


        View FragmentLayout = inflater.inflate(R.layout.fragment_note_edit,container,false);

        title = (EditText) FragmentLayout.findViewById(R.id.editnotetitle);
         message = (EditText) FragmentLayout.findViewById(R.id.editnotemessage);
        noteCatButton = (ImageButton) FragmentLayout.findViewById(R.id.editnoteButton);
        Button saveButton = (Button) FragmentLayout.findViewById(R.id.savenote);

        // populate widgets with note data

        Intent intent = getActivity().getIntent();
        // here the come we use "" because getString method of Intent is overloaded if it does not find extra in this intent then it will return the empty string
        // the second parameter will work for adding the new note because we are not using any of theses keys while creating the new note, so it will make both title and body empty
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA,""));
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA,""));

        if(savedButtonCategory!=null)
        {
            noteCatButton.setImageResource(NoteBean.categoryToDrawable(savedButtonCategory));

        }
        else if (!newNote)  // it means we don't have a new note we are just editing our saved note

            {

            NoteBean.Category notecat = (NoteBean.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);

            savedButtonCategory = notecat;

            noteCatButton.setImageResource(NoteBean.categoryToDrawable(notecat));
        }

                 CategoryDialog();
                 buildDialog();


        noteCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialogObkect.show();

            }
        });

        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogObject.show();
            }
        });
              return FragmentLayout;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putSerializable(MODIFIED_CATEGORY , savedButtonCategory);

    }

    public void CategoryDialog()  // creating dialog box for category imagebutton, so that user can select the note catergory in editnote fragment
    {

        final String[] catergories = new String[]{ "Personal","Technical","Quote","Finance"}; // these are the list of option for category

        AlertDialog.Builder categoryBuilder  = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle("Choose note Type");
        categoryBuilder.setSingleChoiceItems(catergories, 0, new DialogInterface.OnClickListener() {  // 0 is for default type that is Personal
            @Override
            public void onClick(DialogInterface dialog, int item) {

                categoryDialogObkect.cancel();

                switch (item)
                {
                    case 0:
                        savedButtonCategory = NoteBean.Category.PERSONAL; // save the category that user selected
                        noteCatButton.setImageResource(R.drawable.p);

                        break;

                    case 1:
                        savedButtonCategory = NoteBean.Category.FINANCE;
                        noteCatButton.setImageResource(R.drawable.f);
                        break;

                    case 2:
                        savedButtonCategory = NoteBean.Category.QUOTE;
                        noteCatButton.setImageResource(R.drawable.q);
                        break;

                    case 3:
                        savedButtonCategory = NoteBean.Category.TECHNICAL;
                        noteCatButton.setImageResource(R.drawable.t);
                        break;
                }


            }
        });
        categoryDialogObkect = categoryBuilder.create(); // to show the dialog box


    }

    private void buildDialog() // this is for confirmation dialog box
    {

        AlertDialog.Builder confirmBuilder  = new AlertDialog.Builder(getActivity());

        confirmBuilder.setTitle("Are You Sure ?");
        confirmBuilder.setMessage("Are You Sure You Want To Save Note ?");
        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Save Note","Note title" + title.getText() + "Note Message" + message.getText() + "Note Category" + savedButtonCategory);
                 // after saving the note we are throwing user to main activity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });

        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });

        confirmDialogObject = confirmBuilder.create();





    }

}
