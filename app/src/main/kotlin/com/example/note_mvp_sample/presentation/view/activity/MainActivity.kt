package com.example.note_mvp_sample.presentation.view.activity

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.note_mvp_sample.R
import com.example.note_mvp_sample.presentation.contract.MainContract
import com.example.note_mvp_sample.presentation.presenter.MainPresenter
import com.example.note_mvp_sample.presentation.view.component.ProgressDialog
import com.example.note_mvp_sample.presentation.view.fragment.BaseFragment

class MainActivity : AppCompatActivity(), BaseFragment.FragmentListener, MainContract.View {
    override val presenter = MainPresenter(this)

    /** Views **/
    private val loadingDialog = ProgressDialog()

    /**----------------------
     * Lifecycle Methods
    ----------------------**/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenter)
        setContentView(R.layout.activity_main)
        presenter.onViewCreated()
    }

    /**----------------------
     * UI Methods
    ----------------------**/

    override fun startFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }

    /**----------------------
     * Fragment Listener
    ----------------------**/

    override fun startFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.main_fragment_container, fragment).apply {
            if (isAddToBackStack) addToBackStack(fragment.javaClass.simpleName)
        }.commit()
    }

    override fun showLoading() {
        if (supportFragmentManager.findFragmentByTag(ProgressDialog::class.java.simpleName) == null) {
            loadingDialog.show(supportFragmentManager, ProgressDialog::class.java.simpleName)
        }
    }

    override fun closeLoading() {
        if (loadingDialog.isResumed) loadingDialog.dismiss()
    }

    override fun showAlert(title: String?, message: String?, positiveButtonLabel: String?, negativeButtonLabel: String?, positiveListener: DialogInterface.OnClickListener?, negativeListener: DialogInterface.OnClickListener?) {
        AlertDialog.Builder(this).apply {
            title?.let { setTitle(it) }
            message?.let { setMessage(it) }
            positiveButtonLabel?.let { setPositiveButton(it, positiveListener) }
            negativeButtonLabel?.let { setNegativeButton(it, negativeListener) }
        }.show()
    }
}