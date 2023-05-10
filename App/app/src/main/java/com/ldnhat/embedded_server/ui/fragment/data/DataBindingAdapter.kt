package com.ldnhat.embedded_server.ui.fragment.data

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ldnhat.embedded_server.R
import com.ldnhat.embedded_server.common.model.Data
import com.ldnhat.embedded_server.enums.ApiStatus

@BindingAdapter("listData")
fun bindDeviceRecyclerView(recyclerView: RecyclerView, data: List<Data>?) {
    val adapter = recyclerView.adapter as DataAdapter
    adapter.submitList(data)
}

@BindingAdapter("dataApiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus.State?) {
    when (status) {
        ApiStatus.State.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.State.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> statusImageView.visibility = View.GONE
    }
}