package dark.andapp.dfinnb.presentaion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dark.andapp.dfinnb.R
import dark.andapp.dfinnb.databinding.FragmentTransactionsBinding
import dark.andapp.dfinnb.domain.entity.TransactionEntity
import dark.andapp.dfinnb.presentaion.adapters.TransactionAdapter

class TransactionsFragment : Fragment() {

    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transactions = mutableListOf<TransactionEntity>()
        for (i in 0 until 15) {
            transactions.add(
                TransactionEntity(
                    id = i,
                    name = "Amazon",
                    amount = i.toDouble()
                )
            )
        }

        binding.recyclerViewTransactions.adapter = TransactionAdapter(transactions)

        binding.ivArrowBack.setOnClickListener {
            val fragment = WelcomeFragment()
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}