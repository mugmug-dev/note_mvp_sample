package com.example.note_mvp_sample.presentation.view.fragment

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

/**
 * NOTE:
 * このクラスはActivityとFragmentのインタラクションのためのみに使用する
 */

open class BaseFragment : Fragment(){
    interface FragmentListener {
        /**
         * NOTE:
         * FragmentからActivityをコールするメソッドを定義
         */
        fun startFragment(fragment: Fragment, isAddToBackStack: Boolean = true)
    }

    interface ActivityListener {
        /**
         * NOTE:
         * ActivityからFragmentにコールバックするメソッドを定義
         */
    }

    protected var parent: FragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            parent = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        parent = null
    }

    fun showAlert(title: String?, message: String?, positiveButtonLabel: String?, negativeButtonLabel: String?, positiveListener: DialogInterface.OnClickListener?, negativeListener: DialogInterface.OnClickListener?) {
        context?.let {
            AlertDialog.Builder(it).apply {
                title?.let { setTitle(it) }
                message?.let { setMessage(it) }
                positiveButtonLabel?.let { setPositiveButton(it, positiveListener) }
                negativeButtonLabel?.let { setNegativeButton(it, negativeListener) }
            }.show()
        }
    }
}