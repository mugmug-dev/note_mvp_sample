package com.example.note_mvp_sample.presentation.view.component

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.note_mvp_sample.R

class ProgressDialog : DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            val dialog = Dialog(it)
            dialog.setContentView(R.layout.fragment_progress_dialog)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            return dialog
        } ?: return super.onCreateDialog(savedInstanceState)
    }
}