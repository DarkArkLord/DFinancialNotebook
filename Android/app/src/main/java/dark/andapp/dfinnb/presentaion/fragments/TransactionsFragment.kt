package dark.andapp.dfinnb.presentaion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dark.andapp.dfinnb.R
import dark.andapp.dfinnb.databinding.FragmentTransactionsBinding
import dark.andapp.dfinnb.presentaion.adapters.TransactionAdapter
import dark.andapp.dfinnb.presentaion.extensions.launchWhenStarted
import dark.andapp.dfinnb.presentaion.extensions.setColoredNumberRG
import dark.andapp.dfinnb.presentaion.viewmodels.TransactionsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TransactionsFragment : Fragment(), CoroutineScope by MainScope() {
    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TransactionsViewModel by viewModels()

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

        viewModel.getAll().onEach {
            binding.recyclerViewTransactions.adapter = TransactionAdapter(
                transactions = it
            )
            val sumOfTransactions = it.sumOf { it.amount }
            binding.tvCurrentBalance.setColoredNumberRG(sumOfTransactions)
        }.launchWhenStarted(lifecycleScope)

        binding.cvBank.setOnClickListener {
            val fragment = BankAccountFragment()
            parentFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view, fragment)
                .commit()
        }

        binding.cvCategory.setOnClickListener {
            val fragment = CategoryFragment()
            parentFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view, fragment)
                .commit()
        }

        binding.cvAdd.setOnClickListener {
            val fragment = CreateTransactionsFragment()
            parentFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view, fragment)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}