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
import dark.andapp.dfinnb.domain.entity.NamedEntity
import dark.andapp.dfinnb.domain.entity.TransactionEntity
import dark.andapp.dfinnb.presentaion.adapters.TransactionAdapter
import dark.andapp.dfinnb.presentaion.extensions.launchWhenStarted
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
            binding.tvCurrentBalance.text = it.sumOf { it.amount }.toString()
        }.launchWhenStarted(lifecycleScope)

        binding.ivProfileAvatar.setOnClickListener {
            viewModel.createTransaction(
                TransactionEntity(
                    id = 0,
                    bank = NamedEntity(0, "TestBA"),
                    category = NamedEntity(0, "TestC"),
                    amount = (0..100).random().toDouble() - 50,
                    createdAt = System.currentTimeMillis(),
                    comment = "MyComment"
                )
            )
        }

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