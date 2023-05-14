package dark.andapp.dfinnb.presentaion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dark.andapp.dfinnb.R
import dark.andapp.dfinnb.data.local.entity.INamedEntity
import dark.andapp.dfinnb.databinding.FragmentNamedBinding
import dark.andapp.dfinnb.domain.entity.NamedEntity
import dark.andapp.dfinnb.presentaion.adapters.NamedAdapter
import dark.andapp.dfinnb.presentaion.extensions.createInfoDialog
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
    protected abstract fun getHeader(): String

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

        binding.tvHeaderText.text = getHeader()

        getViewModel().getAll().onEach {
            binding.recyclerViewTransactions.adapter = NamedAdapter(
                entities = it,
                viewModel = getViewModel()
            )
        }.launchWhenStarted(lifecycleScope)

        binding.cvAdd.setOnClickListener {
            binding.etEntityNameView.error = null
            val name = binding.etEntityName.text.toString()
            if (name.isNotEmpty()) {
                getViewModel().create(
                    NamedEntity(
                        id = 0,
                        name = name,
                    )
                )
            } else {
                binding.etEntityNameView.error = getString(R.string.error_name)
            }
        }

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