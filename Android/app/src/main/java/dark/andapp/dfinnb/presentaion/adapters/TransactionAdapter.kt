package dark.andapp.dfinnb.presentaion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dark.andapp.dfinnb.databinding.RecyclerViewTransactionListItemBinding
import dark.andapp.dfinnb.domain.entity.TransactionEntity
import dark.andapp.dfinnb.presentaion.extensions.dateToString
import dark.andapp.dfinnb.presentaion.extensions.setColoredNumberRG
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
            tvCategory.text = transaction.category.name

            tvComment.text = transaction.comment

            tvAmount.setColoredNumberRG(transaction.amount)
            tvDate.text = Date(transaction.createdAt).dateToString("dd MMMM yyyy")
        }
    }

    override fun getItemCount(): Int {
        return transactions.count()
    }
}