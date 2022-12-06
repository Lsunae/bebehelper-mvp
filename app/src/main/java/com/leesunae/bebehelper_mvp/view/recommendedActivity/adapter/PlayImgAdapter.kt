package com.leesunae.bebehelper_mvp.view.recommendedActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.data.model.PlayItem
import com.leesunae.bebehelper_mvp.databinding.ItemPlayBinding
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.recommendedActivity.RecommendedActivityFragment
import com.leesunae.bebehelper_mvp.view.recommendedActivity.presenter.adapter.PlayImgAdapterContract
import java.lang.ref.WeakReference

class PlayImgAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), PlayImgAdapterContract.View,
    PlayImgAdapterContract.Model {
    private lateinit var context: Context
    private var items = mutableListOf<PlayItem>()
    lateinit var recommendedActivityFragment: WeakReference<RecommendedActivityFragment>
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: PlayItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        return ImageHolder(
            ItemPlayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        settingPosition(holder, position)
    }

    private fun settingPosition(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageHolder -> {
                holder.bind(items[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addData(addDataList: MutableList<PlayItem>) {
        this.items.clear()
        items.addAll(addDataList)
        println("addDataList_images_ $addDataList")
    }

    inner class ImageHolder(private val binding: ItemPlayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlayItem, position: Int) {

            println("play_item_ $item")
            binding.apply {
                ivPlay.apply {
                    setImageDrawable(item.image)
                    setOnClickListener { onItemClickListener?.onItemClick(position, item) }

                    val width = (Utils.getDeviceWidth() / 15) * 8
                    println("width__ $width ")
                    val params = ConstraintLayout.LayoutParams(
                        width,
                        width
                    ).apply {
                        topToTop = ConstraintSet.PARENT_ID
                        startToStart = ConstraintSet.PARENT_ID
                        endToEnd = ConstraintSet.PARENT_ID

                    }
                    layoutParams = params
                }

                tvPlay.apply {
                    val nameParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        topToBottom = R.id.iv_play
                        startToStart = ConstraintSet.PARENT_ID
                        endToEnd = ConstraintSet.PARENT_ID
                    }
                    layoutParams = nameParams
                    text = item.name
                }
            }
        }
    }
}