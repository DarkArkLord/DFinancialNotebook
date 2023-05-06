package dark.andapp.dfinnb.presentaion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dark.andapp.dfinnb.R
import dark.andapp.dfinnb.databinding.RecyclerViewTransactionListItemBinding
import dark.andapp.dfinnb.domain.entity.TransactionEntity
import dark.andapp.dfinnb.presentaion.extensions.dateToString
import java.util.*

class TransactionAdapter(
    private val transactions: List<TransactionEntity>
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecyclerViewTransactionListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewTransactionListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val transaction = transactions[position]

            tvId.text = transaction.id.toString()
            tvBank.text = transaction.bank.name

            if (transaction.amount < 0) {
                tvAmount.setTextColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.red)
                )
            } else {
                tvAmount.setTextColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.green)
                )
            }

            if (transaction.amount <= 0) {
                tvAmount.text = transaction.amount.toString()
            } else {
                tvAmount.text = "+${transaction.amount}"
            }

            tvCategory.text = transaction.category.name
            tvDate.text = Date(transaction.createdAt).dateToString("dd MMMM yyyy")
            tvComment.text = transaction.comment
        }
    }

    override fun getItemCount(): Int {
        return transactions.count()
    }
}