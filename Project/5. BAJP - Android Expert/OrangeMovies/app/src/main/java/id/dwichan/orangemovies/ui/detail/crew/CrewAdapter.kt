package id.dwichan.orangemovies.ui.detail.crew

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.dwichan.orangemovies.R
import id.dwichan.orangemovies.data.Crew
import kotlinx.android.synthetic.main.item_crews.view.*

class CrewAdapter: RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    private var crews = ArrayList<Crew>()

    class CrewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(crew: Crew) {
            with(itemView) {
                tv_crew_name.text = crew.name
                tv_crew_jobs.text = crew.job
            }
        }
    }

    fun setCrews(crews: ArrayList<Crew>) {
        this.crews.clear()
        this.crews.addAll(crews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_crews, parent, false)
        return CrewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        holder.bind(crews[position])
    }

    override fun getItemCount(): Int = crews.size
}