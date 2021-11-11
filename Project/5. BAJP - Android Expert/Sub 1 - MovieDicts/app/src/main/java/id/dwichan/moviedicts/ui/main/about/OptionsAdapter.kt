package id.dwichan.moviedicts.ui.main.about

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.moviedicts.data.entity.Option
import id.dwichan.moviedicts.databinding.ItemOptionBinding

class OptionsAdapter(private val options: List<Option>) :
    RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder>() {

    inner class OptionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemOptionBinding.bind(itemView)

        fun bind(option: Option) {
            val context = binding.root.context

            Glide.with(context)
                .load(option.icon)
                .into(binding.imageIcon)

            if (option.showNextIndicator) {
                binding.imageNext.visibility = View.VISIBLE
            } else {
                binding.imageNext.visibility = View.GONE
            }

            binding.textTitle.text = option.title
            binding.root.setOnClickListener(option.onClick)
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
        holder.bind(options[position])
    }

    override fun getItemCount(): Int = options.size
}