package com.example.note_mvp_sample.presentation.view.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.note_mvp_sample.R
import com.example.note_mvp_sample.data.entity.NoteContentEntity
import com.example.note_mvp_sample.presentation.contract.NoteListContract
import kotlinx.android.synthetic.main.component_note_card.view.*

class NoteAdapter(var notes: List<NoteContentEntity>, private val presenter: NoteListContract.Presenter): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val card: ConstraintLayout = view.note_card_container
        val image: ImageView = view.note_image_view
        val title: TextView = view.note_title_view
        val like: TextView = view.note_like_view
        val body: TextView = view.note_body_view
        val date: TextView = view.note_date_view
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.component_note_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.image.context).load(notes[position].eyecatch).into(holder.image)
        holder.title.text = notes[position].name
        holder.like.text = "スキ ${notes[position].likeCount}"
        holder.body.text = notes[position].body
        holder.date.text = notes[position].publishAt
        holder.card.setOnClickListener { presenter.onClickNote(notes[position]) }
    }
}