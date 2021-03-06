package com.example.note_mvp_sample.presentation.contract

import androidx.fragment.app.Fragment

interface MainContract {
    interface View : Contract.View<Presenter> {
        /** Define all operations **/
        fun startFragment(fragment: Fragment)
    }

    interface Presenter : Contract.Presenter<View> {
        /** Define all events **/
    }
}