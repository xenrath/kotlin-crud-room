package com.xenrath.crudroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xenrath.crudroom.room.Note
import kotlinx.android.synthetic.main.adapter_note.view.*

class NoteAdapter (private val notes: ArrayList<Note>, private val listener: onAdapterListener)
    : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.adapter_note, parent, false)
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.view.text_title.text = note.title
        holder.view.text_title.setOnClickListener{
            listener.onClick(note)
        }
        holder.view.icon_edit.setOnClickListener{
            listener.onUpdate(note)
        }
        holder.view.icon_delete.setOnClickListener{
            listener.onDelete(note)
        }
    }

    class NoteViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun setData(list: List<Note>){
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }

    interface onAdapterListener {
        fun onClick(note: Note)
        fun onUpdate(note: Note)
        fun onDelete(note: Note)
    }
}