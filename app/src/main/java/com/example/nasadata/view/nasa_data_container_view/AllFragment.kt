package com.example.nasadata.view.nasa_data_container_view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nasadata.databinding.FragmentAllBinding
import com.example.nasadata.view.base.BaseFragment
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [AllFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllFragment : BaseFragment<BaseContainerViewModel, FragmentAllBinding>() {

    private val listAdapter by lazy {
        NasaDataListAdapter(
            onItemClicked = { entity ->
                (parentFragment as BaseContainerFragment).go2DetailPage(entity)
            },
            doSaveImageAction = { bitmap, title ->
                viewModel.saveImage(bitmap, title)
            },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("JLin", "viewModel in $TAG: $viewModel")

        setView()
        getAllNasaData()
        setObserver()
    }

    private fun setView() {
        binding.allRecyclerview.adapter = listAdapter
        binding.allRecyclerview.layoutManager = GridLayoutManager(activity, 3)
        binding.allRecyclerview.addItemDecoration(NasaDataItemDecoration(activity))
    }

    private fun getAllNasaData() {
        lifecycleScope.launch {
            activity.showProgressBar(true)
            viewModel.queryAllNasaData()
        }
    }

    private fun setObserver() {
        viewModel.queryAllNasaDataResult.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                listAdapter.submitList(it)
                activity.dismissProgressBar()
            } else {
                viewModel.reachRemoteNasaData()
            }
        }
    }

    override val viewModel by viewModels<BaseContainerViewModel>(
        ownerProducer = { requireParentFragment() }
    )
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllBinding = FragmentAllBinding.inflate(inflater, container, false)
}