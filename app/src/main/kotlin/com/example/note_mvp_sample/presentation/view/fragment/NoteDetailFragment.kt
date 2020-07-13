package com.example.note_mvp_sample.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.note_mvp_sample.R
import com.example.note_mvp_sample.data.entity.NoteDetailContentEntity
import com.example.note_mvp_sample.extension.log
import com.example.note_mvp_sample.presentation.contract.NoteDetailContract
import com.example.note_mvp_sample.presentation.presenter.NoteDetailPresenter
import kotlinx.serialization.json.Json

class NoteDetailFragment : BaseFragment(), NoteDetailContract.View {
    override val presenter = NoteDetailPresenter(this)

    /**----------------------
     * Lifecycle Callbacks
    ----------------------**/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteDetail = arguments?.let {
            it.getString(NoteDetailContentEntity::class.java.simpleName)?.let {
                Json.parse(NoteDetailContentEntity.serializer(),it)
            }
        }
        presenter.onViewCreated(noteDetail)
    }

    /**----------------------
     * Methods
    ----------------------**/


    /**----------------------
     * UI Methods
    ----------------------**/

    override fun showDetail(detail: NoteDetailContentEntity) {
        log { "$detail" }
    }
}