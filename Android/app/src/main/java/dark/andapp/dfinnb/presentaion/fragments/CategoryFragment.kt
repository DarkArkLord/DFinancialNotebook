package dark.andapp.dfinnb.presentaion.fragments

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dark.andapp.dfinnb.R
import dark.andapp.dfinnb.presentaion.viewmodels.BaseNamedViewModel
import dark.andapp.dfinnb.presentaion.viewmodels.CategoryViewModel

typealias DataC = dark.andapp.dfinnb.data.local.entity.CategoryEntity

@AndroidEntryPoint
class CategoryFragment : BaseNamedFragment<DataC>() {
    private val viewModel: CategoryViewModel by viewModels()

    override fun getViewModel(): BaseNamedViewModel<DataC> {
        return viewModel
    }

    override fun getHeader(): String {
        return requireContext().getString(R.string.categories);
    }
}