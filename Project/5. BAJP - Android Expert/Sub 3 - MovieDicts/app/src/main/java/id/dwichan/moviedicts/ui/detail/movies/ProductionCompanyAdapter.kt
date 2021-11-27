package id.dwichan.moviedicts.ui.detail.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.entity.ProductionCompaniesDataEntity
import id.dwichan.moviedicts.core.util.movies.ProductionCompanyDiffUtilCallback
import id.dwichan.moviedicts.databinding.ItemProductionCompanyBinding

class ProductionCompanyAdapter :
    RecyclerView.Adapter<ProductionCompanyAdapter.ProductionCompanyViewHolder>() {

    private var companies: ArrayList<ProductionCompaniesDataEntity> = ArrayList()

    class ProductionCompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemProductionCompanyBinding.bind(itemView)

        fun bind(company: ProductionCompaniesDataEntity) {
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/original${company.logoPath}")
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_error_image)
                .into(binding.imageView)

            binding.tvCompanyName.text = company.name ?: "Unknown Company"
        }
    }

    fun setCompanies(companiesItem: List<ProductionCompaniesDataEntity>) {
        val callback = ProductionCompanyDiffUtilCallback(this.companies, companiesItem)
        val diffUtil = DiffUtil.calculateDiff(callback)
        this.companies.clear()
        this.companies.addAll(companiesItem)
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
        holder.bind(companies[position])
    }

    override fun getItemCount(): Int = companies.size
}