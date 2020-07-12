package com.example.note_mvp_sample.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note_mvp_sample.R
import com.example.note_mvp_sample.data.entity.NoteContentEntity
import com.example.note_mvp_sample.presentation.contract.NoteListContract
import com.example.note_mvp_sample.presentation.presenter.NoteListPresenter
import com.example.note_mvp_sample.presentation.view.component.NoteAdapter
import kotlinx.android.synthetic.main.fragment_list.*

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
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnSearchListener()
        presenter.onViewCreated()
    }

    /**----------------------
     * Methods
    ----------------------**/

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

    override fun showNotes(notes: List<NoteContentEntity>) {
        val adapter = NoteAdapter(notes, presenter)
        val layoutManager = LinearLayoutManager(context)
        note_list_view.also {
            it.layoutManager = layoutManager
            it.setHasFixedSize(true)
            it.adapter = adapter
        }
    }
}