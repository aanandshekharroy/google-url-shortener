package com.example.theseus.urlshortener.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.theseus.urlshortener.R
import com.example.theseus.urlshortener.data.db.ShortUrl
import org.greenrobot.eventbus.EventBus
import kotlin.properties.Delegates

class ShortUrlAdapter : RecyclerView.Adapter<ShortUrlAdapter.ShortUrlViewHolder>(), AutoUpdatableAdapter {
    var mShortUrls: List<ShortUrl> by Delegates.observable(emptyList()) {
        property, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id == n.id }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortUrlViewHolder {
        return ShortUrlViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.url_item, null, false))
    }

    override fun getItemCount() = mShortUrls ?.size ?: 0

    override fun onBindViewHolder(holder: ShortUrlViewHolder, position: Int) {
        mShortUrls?.let {
            holder.bind(it.get(position))
        }
    }

    inner class ShortUrlViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        var shortUrl: TextView = v.findViewById(R.id.shortUrl)
        var longUrl: TextView = v.findViewById(R.id.longUrl)
        fun bind(url: ShortUrl) {
            shortUrl.text = url.shortUrl
            longUrl.text = url.longUrl
            v.setOnClickListener {
                EventBus.getDefault().post(url)
            }
        }
    }
}