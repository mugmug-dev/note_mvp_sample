package com.example.note_mvp_sample.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.note_mvp_sample.R
import com.example.note_mvp_sample.presentation.contract.MainContract
import com.example.note_mvp_sample.presentation.presenter.MainPresenter
import com.example.note_mvp_sample.presentation.view.fragment.BaseFragment

class MainActivity : AppCompatActivity(), BaseFragment.FragmentListener, MainContract.View {
    override val presenter = MainPresenter(this)

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
}