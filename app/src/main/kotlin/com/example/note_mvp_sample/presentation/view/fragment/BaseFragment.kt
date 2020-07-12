package com.example.note_mvp_sample.presentation.view.fragment

import android.content.Context
import android.content.DialogInterface
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
        fun showLoading()
        fun closeLoading()
        fun showAlert(title: String? = null, message: String? = null, positiveButtonLabel: String? = "OK", negativeButtonLabel: String? = null, positiveListener: DialogInterface.OnClickListener? = null, negativeListener: DialogInterface.OnClickListener? = null)
    }

    interface ActivityListener {
        /**
         * NOTE:
         * ActivityからFragmentにコールバックするメソッドを定義
         */
    }

    lateinit var parent: FragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            parent = context
        }
    }
}