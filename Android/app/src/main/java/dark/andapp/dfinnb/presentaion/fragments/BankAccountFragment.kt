package dark.andapp.dfinnb.presentaion.fragments

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dark.andapp.dfinnb.R
import dark.andapp.dfinnb.presentaion.viewmodels.BankAccountViewModel
import dark.andapp.dfinnb.presentaion.viewmodels.BaseNamedViewModel

typealias DataBA = dark.andapp.dfinnb.data.local.entity.BankAccountEntity

@AndroidEntryPoint
class BankAccountFragment : BaseNamedFragment<DataBA>() {
    private val viewModel: BankAccountViewModel by viewModels()

    override fun getViewModel(): BaseNamedViewModel<DataBA> {
        return viewModel
    }

    override fun getHeader(): String {
        return requireContext().getString(R.string.banks);
    }
}