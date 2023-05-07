package dark.andapp.dfinnb.presentaion.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dark.andapp.dfinnb.databinding.FragmentCreateTransactionBinding
import dark.andapp.dfinnb.domain.entity.NullableTransactionEntity
import dark.andapp.dfinnb.presentaion.extensions.launchWhenStarted
import dark.andapp.dfinnb.presentaion.viewmodels.BankAccountViewModel
import dark.andapp.dfinnb.presentaion.viewmodels.CategoryViewModel
import dark.andapp.dfinnb.presentaion.viewmodels.TransactionsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CreateTransactionsFragment : Fragment(), CoroutineScope by MainScope() {
    private var _binding: FragmentCreateTransactionBinding? = null
    private val binding get() = _binding!!

    private val transactionsViewModel: TransactionsViewModel by viewModels()
    private val bankAccountViewModel: BankAccountViewModel by viewModels()
    private val categoryViewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = this.requireContext()

        val newTransaction = NullableTransactionEntity()

        bankAccountViewModel.getAll().onEach {
            binding.sBank.adapter = ArrayAdapter(
                context,
                support_simple_spinner_dropdown_item,
                it.map { it.name }
            )

            binding.sBank.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    newTransaction.bank = it[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    newTransaction.bank = null
                }
            }
        }.launchWhenStarted(lifecycleScope)

//        viewModel.getAll().onEach {
//            binding.recyclerViewTransactions.adapter = TransactionAdapter(
//                transactions = it
//            )
//            binding.tvCurrentBalance.text = it.sumOf { it.amount }.toString()
//        }.launchWhenStarted(lifecycleScope)
//
//        binding.cvBank.setOnClickListener {
//            val fragment = BankAccountFragment()
//            parentFragmentManager
//                .beginTransaction()
//                .add(R.id.fragment_container_view, fragment)
//                .commit()
//        }
//
//        binding.cvCategory.setOnClickListener {
//            val fragment = CategoryFragment()
//            parentFragmentManager
//                .beginTransaction()
//                .add(R.id.fragment_container_view, fragment)
//                .commit()
//        }
//
//        binding.cvAdd.setOnClickListener {
//            val fragment = AddTransaction()
//            parentFragmentManager
//                .beginTransaction()
//                .add(R.id.fragment_container_view, fragment)
//                .commit()
//        }

        binding.ivArrowBack.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .remove(this)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}