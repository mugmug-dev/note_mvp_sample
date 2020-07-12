package com.example.note_mvp_sample.extension

import android.util.Log
import com.example.note_mvp_sample.BuildConfig

/**
 * NOTE:
 * Utility系の関数はこのファイルに定義する.
 * 必要に応じてStringExtensionsなど、拡張するクラスに応じてファイルを分ける場合もある.
 * Utility系の関数は他のプロジェクトでも再利用することが多いため、できる限りドキュメントを書く.
 */


/**
 * Send a log message when build type is DEBUG.
 *
 * @param[level] log level.
 * @param[tag] Used to identify the source of a log message. Default is the simple name of the class that called this function.
 * @param[message] The message you would like logged.
 */
inline fun <reified T> T.log(level: Int = Log.DEBUG, tag: String = T::class.java.simpleName, message: () -> String?) {
    if (BuildConfig.DEBUG) Log.println(level, tag, "[${Thread.currentThread().id}][${Thread.currentThread().name}] ${message() ?: "message is nothing"}")
}
