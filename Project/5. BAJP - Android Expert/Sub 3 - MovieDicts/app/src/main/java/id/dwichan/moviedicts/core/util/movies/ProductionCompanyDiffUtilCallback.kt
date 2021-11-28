package id.dwichan.moviedicts.core.util.movies

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.moviedicts.core.data.entity.ProductionCompaniesDataEntity

class ProductionCompanyDiffUtilCallback(
    private val oldList: List<ProductionCompaniesDataEntity>,
    private val newList: List<ProductionCompaniesDataEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition
}