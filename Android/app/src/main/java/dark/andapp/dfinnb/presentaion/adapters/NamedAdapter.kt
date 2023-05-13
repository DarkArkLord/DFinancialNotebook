package dark.andapp.dfinnb.presentaion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dark.andapp.dfinnb.databinding.RecyclerViewNamedListItemBinding
import dark.andapp.dfinnb.domain.entity.NamedEntity
import dark.andapp.dfinnb.presentaion.extensions.setColoredNumberRG

class NamedAdapter(
    private val entities: List<NamedEntity>
) : RecyclerView.Adapter<NamedAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecyclerViewNamedListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewNamedListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val entity = entities[position]

            tvId.text = entity.id.toString()
            tvName.text = entity.name
            tvCount.text = entity.count.toString()
            tvAmount.setColoredNumberRG(entity.amount)
        }
    }

    override fun getItemCount(): Int {
        return entities.count()
    }
}