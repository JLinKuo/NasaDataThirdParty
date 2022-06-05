package com.example.nasadata.view.nasa_data_container_view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nasadata.databinding.FragmentFavorBinding
import com.example.nasadata.view.base.BaseFragment
import kotlinx.coroutines.internal.artificialFrame


/**
 * A simple [Fragment] subclass.
 * Use the [FavorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavorFragment : BaseFragment<BaseContainerViewModel, FragmentFavorBinding>() {
    private val listAdapter by lazy {
        NasaDataListAdapter(
            onItemClicked = { entity ->
                (parentFragment as BaseContainerFragment).go2DetailPage(entity)
            },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        getFavorData()
        setObserver()
    }

    private fun setView() {
        binding.favorRecyclerview.layoutManager = GridLayoutManager(activity, 3)
        binding.favorRecyclerview.adapter = listAdapter
        binding.favorRecyclerview.addItemDecoration(NasaDataItemDecoration(activity))
    }

    private fun getFavorData() {
        viewModel.queryFavorData()
    }

    private fun setObserver() {
        viewModel.queryFavorDataResult.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                for (element in it) {
                    Log.d("JLin", "title in $TAG: ${element.title}")
                }
            }

            listAdapter.submitList(it)
        }
    }

    override val viewModel by viewModels<BaseContainerViewModel>(
        ownerProducer = { requireParentFragment() }
    )

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavorBinding = FragmentFavorBinding.inflate(inflater, container, false)
}