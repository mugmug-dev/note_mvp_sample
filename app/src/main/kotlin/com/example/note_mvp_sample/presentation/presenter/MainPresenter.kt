package com.example.note_mvp_sample.presentation.presenter

import com.example.note_mvp_sample.presentation.contract.MainContract
import com.example.note_mvp_sample.presentation.view.fragment.NoteListFragment
import kotlinx.coroutines.Job

class MainPresenter(override val view: MainContract.View?) : MainContract.Presenter {
    override var job: Job = Job()

    /**----------------------
     * View Events
    ----------------------**/

    override fun onViewCreated() {
        super.onViewCreated()
        view?.startFragment(NoteListFragment())
    }
}