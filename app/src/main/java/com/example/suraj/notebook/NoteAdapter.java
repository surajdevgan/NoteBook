package com.example.suraj.notebook;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by suraj on 04/04/2017.
 */

public class NoteAdapter  extends ArrayAdapter<NoteBean>
 {
     public static class ViewHolder {   // inner class  //  this is for optimization purpose  (this inner class is optional))
         // by this we don't need to create references again and again so its save memory
         TextView title;
         TextView note;
         ImageView noteicon;
     }

     Context context;
     int resource;
     ArrayList<NoteBean> noteslist;


     public NoteAdapter(Context context, int resource, ArrayList<NoteBean> noteslist) {
         super(context, resource,noteslist);
         this.context = context;
         this.resource = resource;
         this.noteslist = noteslist;
     }



     @Override
     public View getView(int position,  View view,  ViewGroup parent) {
         NoteBean noteBean = getItem(position);
         ViewHolder viewHolder;
         if( view==null) {

             viewHolder = new ViewHolder();  // there only by 1 reference we can manage three views reference , 2 textviews and 1 image view


             view = LayoutInflater.from(context).inflate(resource, parent, false);

             viewHolder.title = (TextView) view.findViewById(R.id.notetitle);
             viewHolder.note = (TextView) view.findViewById(R.id.notebody);
             viewHolder.noteicon = (ImageView) view.findViewById(R.id.noteicon);

             view.setTag(viewHolder);  // use setTag to remember our ViewHolder which is holding our references to our widgets

             // and down there we don't need to do findViewById when we are setting the text  and image

         }
         else {
             // this part is just to retrieve our old stored views in the list
             // and down the setmethods is used to set the textviews and image view of old stored data as well as new data the we are entering in if part


             viewHolder = (ViewHolder) view.getTag();


         }




        viewHolder.title.setText(noteBean.getNoteTitle());
         viewHolder.note.setText(noteBean.getNoteBody());
         viewHolder.noteicon.setImageResource(noteBean.getAssociatedDrawable());

         return view;


     }
 }
