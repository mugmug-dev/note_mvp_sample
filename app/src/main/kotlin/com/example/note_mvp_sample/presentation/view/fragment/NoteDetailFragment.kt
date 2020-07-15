package com.example.note_mvp_sample.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.note_mvp_sample.R
import com.example.note_mvp_sample.data.entity.NoteDetailContentEntity
import com.example.note_mvp_sample.presentation.contract.NoteDetailContract
import com.example.note_mvp_sample.presentation.presenter.NoteDetailPresenter
import kotlinx.android.synthetic.main.fragment_note_detail.*
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
        setDetailBodyWebView()
        presenter.onViewCreated(noteDetail)
    }

    /**----------------------
     * Methods
    ----------------------**/

    private fun setDetailBodyWebView() {
        detail_body.clearCache(true)
    }


    /**----------------------
     * UI Methods
    ----------------------**/

    override fun showDetail(detail: NoteDetailContentEntity) {
        val head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>"
        val html = "<html>$head<body>${detail.body}</body></html>"
        detail_body.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
        Glide.with(user_image.context).load(detail.user?.user_profile_image_path).into(user_image)
        user_name.text = detail.user?.urlname
        detail_title.text = detail.name
        detail_date.text = detail.publish_at?.slice(0 until 19)?.replace("T", " ")
        detail_like.text = "スキ ${detail.like_count}"
    }
}