package com.example.note_mvp_sample.presentation.contract

import com.example.note_mvp_sample.data.entity.NoteDetailContentEntity

interface NoteDetailContract {
    interface View : Contract.View<Presenter> {
        /** Define all operations **/
        fun showDetail(detail: NoteDetailContentEntity)
    }

    interface Presenter : Contract.Presenter<View> {
        /** Define all events **/
        fun onViewCreated(detail: NoteDetailContentEntity?)
    }
}