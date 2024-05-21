package com.nbc.video.presenters.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.nbc.video.databinding.ItemSpinnerBinding

class SpinnerAdapter(
    context: Context,
    @LayoutRes private val resId: Int,
    private val list: List<String>
) : ArrayAdapter<String>(context, resId, list) {

    //스피너에서
    //드롭다운 하지 않은 뷰
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemSpinnerBinding =
            ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemSpinnerBinding.spinnerItem.text = list[position]
        return itemSpinnerBinding.root
    }

    //드롭 다운된 리스트 뷰
    @SuppressLint("ViewHolder")
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemSpinnerBinding =
            ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        itemSpinnerBinding.spinnerItem.text = list[position]
        return itemSpinnerBinding.root
    }

    override fun getCount(): Int = list.size

}
