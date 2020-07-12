package com.example.note_mvp_sample.presentation.contract

import com.example.note_mvp_sample.data.entity.NoteContentEntity

interface NoteListContract {
    interface View : Contract.View<Presenter> {
        /** Define all View operations **/
        fun showAlert(message: String)
        fun showLoading()
        fun closeLoading()
        fun showNotes(notes: List<NoteContentEntity>)
    }

    interface Presenter : Contract.Presenter<View> {
        /** Define all View events **/
        fun onClickNote(note: NoteContentEntity)
        fun onSubmitSearch(query: String?)
        fun onCloseSearch()
    }
}