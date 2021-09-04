package id.dwichan.chromebookpedia.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import id.dwichan.chromebookpedia.R
import id.dwichan.chromebookpedia.data.entity.Chromebook
import id.dwichan.chromebookpedia.databinding.ItemChromebookBinding

class ChromebookAdapter: Adapter<ChromebookAdapter.ChromebookViewHolder>() {

    private val items = ArrayList<Chromebook>()

    @SuppressLint("NotifyDataSetChanged")
    fun setChromebooks(chromebooks: ArrayList<Chromebook>) {
        this.items.clear()
        this.items.addAll(chromebooks)
        notifyDataSetChanged()
    }

    inner class ChromebookViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        private val binding = ItemChromebookBinding.bind(itemView)

        fun bind(chromebook: Chromebook) {
            with (binding) {
                Glide.with(itemView.context)
                    .load(chromebook.image)
                    .into(imageLaptop)

                textLaptopName.text = chromebook.name
                textStorage.text = chromebook.storage
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChromebookViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chromebook, parent, false)

        return ChromebookViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ChromebookViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}