package id.dwichan.moviedicts.ui.detail.television

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.repository.remote.response.television.ProductionCompaniesItem
import id.dwichan.moviedicts.core.util.television.ProductionCompanyDiffUtilCallback
import id.dwichan.moviedicts.databinding.ItemProductionCompanyBinding

class ProductionCompanyAdapter :
    RecyclerView.Adapter<ProductionCompanyAdapter.ProductionCompanyViewHolder>() {

    private val companiesItem: ArrayList<ProductionCompaniesItem> = ArrayList()

    class ProductionCompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemProductionCompanyBinding.bind(itemView)

        fun bind(company: ProductionCompaniesItem) {
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/original${company.logoPath}")
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_error_image)
                .into(binding.imageView)

            binding.tvCompanyName.text = company.name ?: "Unknown Company"
        }
    }

    fun setCompanies(companies: List<ProductionCompaniesItem>) {
        val callback = ProductionCompanyDiffUtilCallback(this.companiesItem, companies)
        val diffUtil = DiffUtil.calculateDiff(callback)
        this.companiesItem.clear()
        this.companiesItem.addAll(companies)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionCompanyViewHolder {
        val binding = ItemProductionCompanyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductionCompanyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductionCompanyViewHolder, position: Int) {
        holder.bind(companiesItem[position])
    }

    override fun getItemCount(): Int = companiesItem.size
}