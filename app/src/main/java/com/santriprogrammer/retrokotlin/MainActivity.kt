package com.santriprogrammer.retrokotlin

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.santriprogrammer.retrokotlin.R.layout.activity_main
import com.santriprogrammer.retrokotlin.adapter.MainAdapter
import com.santriprogrammer.retrokotlin.model.News
import com.santriprogrammer.retrokotlin.popup.showEnableLiteModePopup
import com.santriprogrammer.retrokotlin.retrofit.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var enableLitePopup: AlertDialog? = null
    private var enableLiteMode: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        val apiService = ApiService.create()

        apiService.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Log.i("", "subscribed")
                    shimmerLayout.startShimmerAnimation()
                }
                .doOnComplete {
                    Log.i("", "complete")
                    shimmerLayout.stopShimmerAnimation()
                    shimmerLayout.visibility = View.GONE
                }
                .doOnError { Log.e("e", "error") }
                .map { it.articles }
                .subscribe(
                        {
                            displayList(it)
                            showPopup(it)
                        },
                        { displayError(it) }
                )

    }

    private fun showPopup(it: List<News.Item>) {
        if (enableLitePopup == null) {
            enableLitePopup = showEnableLiteModePopup {
                okIconClickListener {
                    enableLiteMode = true
                    displayList(it)
                }
                noIconClickListener {
                    displayList(it)
                }
            }
            enableLitePopup?.show()
        }
    }

    private fun displayList(list: List<News.Item>?) {
        val adapter = MainAdapter()
        if (enableLiteMode != false) {
            adapter.addData(list, 0)
        } else {
            adapter.addData(list, 1)
        }
        recyclerview.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
        adapter.notifyDataSetChanged()
    }

    private fun displayError(throwable: Throwable?) {
        Log.i("", "Failed to show list")
    }
}
