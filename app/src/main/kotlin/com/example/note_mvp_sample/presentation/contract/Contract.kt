package com.example.note_mvp_sample.presentation.contract

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

interface Contract {
    interface View<T>: LifecycleOwner {
        /** required properties **/
        val presenter: T?
    }

    interface Presenter<T>: LifecycleObserver, CoroutineScope {
        /** required properties **/
        val view : T?

        /** coroutine job **/
        var job: Job
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main + job

        /** required view events **/
        fun onViewCreated() {}

        /** required lifecycle events **/
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() { job = Job() }
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() { if (job.isCancelled) job = Job() }
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {}
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {}
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop() { job.cancel() }
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {}
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onAny() {}
    }
}