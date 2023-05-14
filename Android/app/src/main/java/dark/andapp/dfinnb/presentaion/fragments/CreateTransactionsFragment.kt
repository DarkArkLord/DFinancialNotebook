package dark.andapp.dfinnb.presentaion.fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dark.andapp.dfinnb.R
import dark.andapp.dfinnb.databinding.FragmentCreateTransactionBinding
import dark.andapp.dfinnb.domain.entity.NullableTransactionEntity
import dark.andapp.dfinnb.presentaion.extensions.dateToString
import dark.andapp.dfinnb.presentaion.extensions.launchWhenStarted
import dark.andapp.dfinnb.presentaion.extensions.toDomain
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

        val context = requireContext()
        val newTransaction = NullableTransactionEntity()

        bankAccountViewModel.getAll().onEach {
            binding.etBank.setAdapter(
                ArrayAdapter(
                    context,
                    support_simple_spinner_dropdown_item,
                    it.map { it.name }
                )
            )

            binding.etBank.setOnItemClickListener { _, _, position, _ ->
                newTransaction.bank = it[position]
            }
        }.launchWhenStarted(lifecycleScope)

        categoryViewModel.getAll().onEach {
            binding.etCategory.setAdapter(
                ArrayAdapter(
                    context,
                    support_simple_spinner_dropdown_item,
                    it.map { it.name }
                )
            )

            binding.etCategory.setOnItemClickListener { _, _, position, _ ->
                newTransaction.category = it[position]
            }
        }.launchWhenStarted(lifecycleScope)

        binding.etCreatingDate.apply {
            isFocusable = false
            isClickable = true
            isFocusableInTouchMode = false
        }.setOnClickListener {
            Calendar.getInstance().let {
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        Calendar.getInstance()
                            .apply {
                                set(Calendar.YEAR, year)
                                set(Calendar.MONTH, month)
                                set(Calendar.DAY_OF_MONTH, day)
                            }
                            .time.also {
                                newTransaction.createdAt = it.time
                            }
                            .dateToString("dd MMMM yyyy")
                            .also {
                                binding.etCreatingDate.setText(it)
                            }
                    },
                    it.get(Calendar.YEAR),
                    it.get(Calendar.MONTH),
                    it.get(Calendar.DAY_OF_MONTH)
                )
            }.show()
        }

        binding.cvAdd.setOnClickListener {
            listOf(
                binding.etBankView,
                binding.etCategoryView,
                binding.etAmountView,
                binding.etCreatingDateView,
            ).forEach {
                it.error = null
            }

            var canCreate = true

            if (newTransaction.bank == null) {
                binding.etBankView.error = context.getString(R.string.error_bank)
                canCreate = false
            }

            if (newTransaction.category == null) {
                binding.etCategoryView.error = context.getString(R.string.error_category)
                canCreate = false
            }

            if (binding.etAmount.text.toString().isEmpty()) {
                binding.etAmountView.error = context.getString(R.string.error_amount)
                canCreate = false
            } else {
                newTransaction.amount = binding.etAmount.text.toString().toDouble()
            }

            if (newTransaction.createdAt == null) {
                binding.etCreatingDateView.error = context.getString(R.string.error_creating_date)
                canCreate = false
            }

            if (canCreate) {
                newTransaction.apply {
                    comment = binding.etComment.text.toString()
                }.toDomain().also {
                    transactionsViewModel.createTransaction(it)
                }

                parentFragmentManager
                    .beginTransaction()
                    .remove(this)
                    .commit()
            }
        }

        binding.ivArrowBack.setOnClickListener {
            val fragment = TransactionsFragment()
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