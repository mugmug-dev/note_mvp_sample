package com.example.note_mvp_sample.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note_mvp_sample.R
import com.example.note_mvp_sample.data.entity.NoteContentEntity
import com.example.note_mvp_sample.data.entity.NoteDetailContentEntity
import com.example.note_mvp_sample.presentation.contract.NoteListContract
import com.example.note_mvp_sample.presentation.presenter.NoteListPresenter
import com.example.note_mvp_sample.presentation.view.component.NoteAdapter
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlinx.serialization.json.Json

class NoteListFragment : BaseFragment(), NoteListContract.View {
    override val presenter = NoteListPresenter(this)

    /**----------------------
     * Lifecycle Callbacks
    ----------------------**/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNotes()
        swipe_container.setOnRefreshListener { presenter.onRefreshNote() }
        setOnSearchListener()
        presenter.onViewCreated()
    }

    /**----------------------
     * Methods
    ----------------------**/

    private fun setNotes() {
        val adapter = NoteAdapter(listOf(), presenter)
        val layoutManager = LinearLayoutManager(context)
        note_list_view.also {
            it.layoutManager = layoutManager
            it.setHasFixedSize(true)
            it.adapter = adapter
        }
    }

    private fun setOnSearchListener() {
        note_search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean { presenter.onSubmitSearch(newText); return false }
            override fun onQueryTextSubmit(query: String?): Boolean { presenter.onSubmitSearch(query); return false }
        })
        note_search_view.setOnCloseListener {
            presenter.onCloseSearch()
            return@setOnCloseListener false
        }
    }

    /**----------------------
     * UI Methods
    ----------------------**/

    override fun showAlert(message: String) {
        parent?.showAlert(message)
    }

    override fun showLoading() {
        parent?.showLoading()
    }

    override fun closeLoading() {
        parent?.closeLoading()
    }

    override fun refreshNotes(notes: List<NoteContentEntity>) {
        (note_list_view.adapter as? NoteAdapter)?.let { adapter ->
            adapter.notes = notes
            adapter.notifyDataSetChanged()
        }
        if (swipe_container.isRefreshing) swipe_container.isRefreshing = false
    }

    override fun startNoteDetailFragment(detail: NoteDetailContentEntity) {
        val fragment = NoteDetailFragment().apply {
            arguments = Bundle().apply {
                putString(NoteDetailContentEntity::class.java.simpleName, Json.stringify(NoteDetailContentEntity.serializer(), detail))
            }
        }
        parent?.startFragment(fragment)
    }
}