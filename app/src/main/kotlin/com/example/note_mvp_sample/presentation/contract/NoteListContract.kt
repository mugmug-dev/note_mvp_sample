package com.example.note_mvp_sample.presentation.contract

import com.example.note_mvp_sample.data.entity.NoteContentEntity
import com.example.note_mvp_sample.data.entity.NoteDetailContentEntity

interface NoteListContract {
    interface View : Contract.View<Presenter> {
        /** Define all operations **/
        fun showAlert(message: String)
        fun showLoading()
        fun closeLoading()
        fun refreshNotes(notes: List<NoteContentEntity>)
        fun startNoteDetailFragment(detail: NoteDetailContentEntity)
    }

    interface Presenter : Contract.Presenter<View> {
        /** Define all events **/
        fun onRefreshNote()
        fun onClickNote(note: NoteContentEntity)
        fun onSubmitSearch(query: String?)
        fun onCloseSearch()
    }
}