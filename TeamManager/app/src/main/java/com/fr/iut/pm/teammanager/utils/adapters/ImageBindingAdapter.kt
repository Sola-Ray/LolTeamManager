package com.fr.iut.pm.teammanager.utils.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


object ImageBindingAdapter {

    /**
     * Charge dans l'imageView une image depuis une url
     */
    @BindingAdapter("bind:imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        if (url != "") {
            Picasso.get()
                .load(url)
                .into(imageView)
        }
    }
}