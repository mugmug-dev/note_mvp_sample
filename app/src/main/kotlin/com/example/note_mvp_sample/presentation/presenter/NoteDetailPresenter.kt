package com.example.note_mvp_sample.presentation.presenter

import com.example.note_mvp_sample.data.entity.NoteDetailContentEntity
import com.example.note_mvp_sample.presentation.contract.NoteDetailContract
import kotlinx.coroutines.Job

class NoteDetailPresenter(override val view: NoteDetailContract.View?) : NoteDetailContract.Presenter {
    override var job: Job = Job()
    private var noteDetail: NoteDetailContentEntity? = null

    /**----------------------
     * View Events
    ----------------------**/

    override fun onViewCreated(detail: NoteDetailContentEntity?) {
        super.onViewCreated(detail)
        noteDetail = detail
        noteDetail?.let { view?.showDetail(it) }
    }

}