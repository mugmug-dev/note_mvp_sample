package com.example.note_mvp_sample.presentation.presenter

import com.example.note_mvp_sample.R
import com.example.note_mvp_sample.app.App
import com.example.note_mvp_sample.data.entity.NoteContentEntity
import com.example.note_mvp_sample.domain.service.NoteService
import com.example.note_mvp_sample.extension.log
import com.example.note_mvp_sample.presentation.contract.NoteListContract
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NoteListPresenter(override val view: NoteListContract.View?) : NoteListContract.Presenter {
    override lateinit var job: Job
    private val noteService = NoteService()
    private var notes: List<NoteContentEntity>? = null
    private var searchingQuery: String? = null

    /**----------------------
     * View Events
    ----------------------**/

    override fun onViewCreated() {
        view?.showLoading()
        launch {
            notes = noteService.getNotesAsync().await()
            view?.closeLoading()
            notes?.let { view?.showNotes(it) } ?: view?.showAlert(App.instance.getString(R.string.note_get_error_message))
        }
    }

    override fun onRefreshNote() {
        launch {
            notes = noteService.getNotesAsync().await()
            notes?.let {
                searchingQuery?.let { query ->
                    view?.refreshNotes(it.filter { it.name.contains(query, ignoreCase = true) })
                } ?: view?.refreshNotes(it)
            } ?: view?.showAlert(App.instance.getString(R.string.note_get_error_message))
        }
    }

    override fun onClickNote(note: NoteContentEntity) {
        log { "$note" }
    }

    override fun onSubmitSearch(query: String?) {
        searchingQuery = query
        notes?.let {
            query?.let { query ->
                view?.refreshNotes(it.filter { it.name.contains(query, ignoreCase = true) })
            }
        }
    }

    override fun onCloseSearch() {
        searchingQuery = null
        notes?.let {
            view?.refreshNotes(it)
        }
    }
}