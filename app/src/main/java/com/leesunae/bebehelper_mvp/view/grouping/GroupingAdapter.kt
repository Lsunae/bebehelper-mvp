package com.leesunae.bebehelper_mvp.view.grouping

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leesunae.bebehelper_mvp.R
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping
import com.leesunae.bebehelper_mvp.databinding.ItemGroupingBinding
import com.leesunae.bebehelper_mvp.util.Utils
import com.leesunae.bebehelper_mvp.view.grouping.presenter.adapter.GroupingAdapterContract
import java.lang.ref.WeakReference

class GroupingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    GroupingAdapterContract.View,
    GroupingAdapterContract.Model {
    private lateinit var context: Context
    private var items = mutableListOf<Grouping>()
    lateinit var groupingFragment: WeakReference<GroupingFragment>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        return GroupingHolder(
            ItemGroupingBinding.inflate(
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
            is GroupingHolder -> {
                holder.bind(items[position], position)

                // 더보기
                if (items.size - 10 == position && groupingFragment.get() != null) {
                    groupingFragment.get()!!.apiListMore()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addData(addDataList: MutableList<Grouping>) {
        this.items.clear()
        items.addAll(addDataList)
    }

    inner class GroupingHolder(private val binding: ItemGroupingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: Grouping

        fun bind(item: Grouping, position: Int) {
            this.item = item

            binding.apply {
                title = item.title
                content = item.content
                area = item.area
                childLimitAge =
                    String.format(Utils.string(context, R.string.age_count), item.ageLimit)
                childLimitCount =
                    String.format(Utils.string(context, R.string.user_count), item.childCount)
            }
        }
    }
}