package id.dwichan.moviedicts.ui.main.about

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.moviedicts.core.data.entity.OptionDataEntity
import id.dwichan.moviedicts.databinding.ItemOptionBinding

class OptionsAdapter(private val optionDataEntities: List<OptionDataEntity>) :
    RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder>() {

    inner class OptionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemOptionBinding.bind(itemView)

        fun bind(optionDataEntity: OptionDataEntity) {
            val context = binding.root.context

            Glide.with(context)
                .load(optionDataEntity.icon)
                .into(binding.imageIcon)

            if (optionDataEntity.showNextIndicator) {
                binding.imageNext.visibility = View.VISIBLE
            } else {
                binding.imageNext.visibility = View.GONE
            }

            binding.textTitle.text = optionDataEntity.title
            binding.root.setOnClickListener(optionDataEntity.onClick)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionsViewHolder {
        val view = ItemOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OptionsViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        holder.bind(optionDataEntities[position])
    }

    override fun getItemCount(): Int = optionDataEntities.size
}