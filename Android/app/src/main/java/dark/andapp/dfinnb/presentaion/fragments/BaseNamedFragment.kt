package dark.andapp.dfinnb.presentaion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dark.andapp.dfinnb.data.local.entity.INamedEntity
import dark.andapp.dfinnb.databinding.FragmentNamedBinding
import dark.andapp.dfinnb.presentaion.adapters.NamedAdapter
import dark.andapp.dfinnb.presentaion.extensions.launchWhenStarted
import dark.andapp.dfinnb.presentaion.viewmodels.BaseNamedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.onEach

abstract class BaseNamedFragment<TData : INamedEntity> : Fragment(),
    CoroutineScope by MainScope() {
    private var _binding: FragmentNamedBinding? = null
    private val binding get() = _binding!!

    protected abstract fun getViewModel(): BaseNamedViewModel<TData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNamedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel().getAll().onEach {
            binding.recyclerViewTransactions.adapter = NamedAdapter(
                entities = it
            )
            binding.tvCurrentBalance.text = it.sumOf { it.amount }.toString()
        }.launchWhenStarted(lifecycleScope)

//        binding.ivProfileAvatar.setOnClickListener {
//            getViewModel().createTransaction(
//                TransactionEntity(
//                    id = 0,
//                    bank = NamedEntity(0, "TestBA"),
//                    category = NamedEntity(0, "TestC"),
//                    amount = (0..100).random().toDouble() - 50,
//                    createdAt = System.currentTimeMillis(),
//                    comment = "MyComment"
//                )
//            )
//        }

        binding.ivArrowBack.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .remove(this)
//                .replace(R.id.fragment_container_view, fragment)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}