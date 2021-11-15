package id.dwichan.moviedicts.ui.detail.television

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.repository.remote.response.television.CreatedByItem
import id.dwichan.moviedicts.core.util.television.CreatorItemDiffUtilCallback
import id.dwichan.moviedicts.databinding.ItemCreatorBinding

class CreatorAdapter : RecyclerView.Adapter<CreatorAdapter.CreatorViewHolder>() {

    private val creators: ArrayList<CreatedByItem> = ArrayList()

    inner class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCreatorBinding.bind(itemView)

        fun bind(creator: CreatedByItem) {
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/original${creator.profilePath}")
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_error_image)
                .into(binding.imageCreator)

            binding.textCreatorName.text = creator.name ?: "Anonymous Creator"
        }
    }

    fun setCreators(creators: List<CreatedByItem>) {
        val callback = CreatorItemDiffUtilCallback(this.creators, creators)
        val diffUtil = DiffUtil.calculateDiff(callback)
        this.creators.clear()
        this.creators.addAll(creators)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreatorAdapter.CreatorViewHolder {
        val binding = ItemCreatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CreatorViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CreatorAdapter.CreatorViewHolder, position: Int) {
        holder.bind(creators[position])
    }

    override fun getItemCount(): Int = creators.size
}