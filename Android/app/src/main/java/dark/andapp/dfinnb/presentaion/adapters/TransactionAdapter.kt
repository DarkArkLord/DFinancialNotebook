package dark.andapp.dfinnb.presentaion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dark.andapp.dfinnb.databinding.RecyclerViewTransactionListItemBinding
import dark.andapp.dfinnb.domain.entity.TransactionEntity

class TransactionAdapter(
    private val transactions: MutableList<TransactionEntity>
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecyclerViewTransactionListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewTransactionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val transaction = transactions[position]
            tvTitle.text = transaction.name
            tvAmount.text = transaction.amount.toString()
        }
    }

    override fun getItemCount(): Int {
        return transactions.count()
    }
}