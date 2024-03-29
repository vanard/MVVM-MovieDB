package com.vanard.ovotask.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.vanard.ovotask.R
import com.vanard.ovotask.utils.extension.getParentActivity

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: AppCompatTextView,  text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableTextDouble")
fun setMutableTextDouble(view: AppCompatTextView,  text: MutableLiveData<Double>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value.toString()})
    }
}

@BindingAdapter("mutableTextInt")
fun setMutableTextInt(view: AppCompatTextView,  text: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value.toString()})
    }
}

@BindingAdapter("genre")
fun setGenre(view: AppCompatTextView,  text: MutableLiveData<List<Int>>) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        val genreIds = parentActivity.resources.getStringArray(R.array.genre_ids)
        val genre = parentActivity.resources.getStringArray(R.array.genre_names)
        var genreItem = ""
        text.value?.forEachIndexed { index, i ->
            for (number in genreIds){
                if (i.toString() == number) genreItem += "${genre[index]}, "
            }
        }

        text.observe(parentActivity, Observer { value -> view.text = genreItem.substring(0, genreItem.length-2)})
    }
}

@BindingAdapter("toolbarTitle")
fun setCollapseToolbarTitle(view: CollapsingToolbarLayout, text: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.title = value?:""})
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && url != null) {
        Glide.with(parentActivity)
            .load(url)
            .into(view)
    }
}

@BindingAdapter("favoriteState")
fun favoriteState(view: AppCompatImageView, state: MutableLiveData<Boolean>) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null) {
        state.observe(parentActivity, Observer {
            if (it) view.setImageResource(R.drawable.ic_favorite_heart)
            else view.setImageResource(R.drawable.ic_favorite_heart_button)
        })
    }
}