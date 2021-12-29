package com.dicoding.habitapp.ui.random

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.habitapp.R
import com.dicoding.habitapp.data.Habit
import com.google.android.material.button.MaterialButton

class RandomHabitAdapter(
    private val onClick: (Habit) -> Unit
) : RecyclerView.Adapter<RandomHabitAdapter.PagerViewHolder>() {

    private val habitMap = LinkedHashMap<PageType, Habit>()

    fun submitData(key: PageType, habit: Habit) {
        habitMap[key] = habit
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PagerViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.pager_item, parent, false))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val key = getIndexKey(position) ?: return
        val pageData = habitMap[key] ?: return
        holder.bind(key, pageData)
    }

    override fun getItemCount() = habitMap.size

    private fun getIndexKey(position: Int) = habitMap.keys.toTypedArray().getOrNull(position)

    enum class PageType {
        HIGH, MEDIUM, LOW
    }

    inner class PagerViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        //TODO 14 : Create view and bind data to item view

        fun bind(pageType: PageType, pageData: Habit) {
            with (itemView) {
                val tvTitle = findViewById<TextView>(R.id.pager_tv_title)
                val tvStartTime = findViewById<TextView>(R.id.pager_tv_start_time)
                val tvMinutes = findViewById<TextView>(R.id.pager_tv_minutes)
                val imgPriorityLevel = findViewById<ImageView>(R.id.item_priority_level)
                val btnOpenCountDown = findViewById<MaterialButton>(R.id.pager_button_open_count_down)

                tvTitle.text = pageData.title
                tvStartTime.text = pageData.startTime
                tvMinutes.text = pageData.minutesFocus.toString()

                when(pageType) {
                    PageType.LOW -> imgPriorityLevel.setImageDrawable(
                        ContextCompat.getDrawable(itemView.context, R.drawable.ic_priority_low)
                    )
                    PageType.MEDIUM -> imgPriorityLevel.setImageDrawable(
                        ContextCompat.getDrawable(itemView.context, R.drawable.ic_priority_medium)
                    )
                    PageType.HIGH -> imgPriorityLevel.setImageDrawable(
                        ContextCompat.getDrawable(itemView.context, R.drawable.ic_priority_high)
                    )
                }

                btnOpenCountDown.setOnClickListener {
                    onClick(pageData)
                }
            }
        }
    }
}
