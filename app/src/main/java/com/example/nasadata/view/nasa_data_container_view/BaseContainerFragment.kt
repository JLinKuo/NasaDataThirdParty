package com.example.nasadata.view.nasa_data_container_view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nasadata.R
import com.example.nasadata.databinding.FragmentBaseContainerBinding
import com.example.nasadata.model.db.NasaDataEntity
import com.example.nasadata.view.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator


/**
 * A simple [Fragment] subclass.
 * Use the [BaseContainerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BaseContainerFragment : BaseFragment<BaseContainerViewModel, FragmentBaseContainerBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("JLin", "viewModel in $TAG: $viewModel")

        setView()
        setObserver()
    }

    private fun setView() {
        binding.viewPager2.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when(position % 2) {
                0 -> tab.text = getTabString(R.string.all_image, viewModel.cntAllData)
                1 -> tab.text = getTabString(R.string.favor_image, viewModel.cntFavor)
            }
        }.also { it.attach() }
    }

    fun go2DetailPage(entity: NasaDataEntity) {
        findNavController().navigate(
            BaseContainerFragmentDirections.actionBaseContainerFragmentToDetailFragment(entity))
    }

    private fun setObserver() {
        viewModel.queryAllNasaDataResult.observe(viewLifecycleOwner) {
            binding.tabLayout.getTabAt(0)?.text =
                getTabString(R.string.all_image, it.size)
        }

        viewModel.queryFavorDataResult.observe(viewLifecycleOwner) {
            binding.tabLayout.getTabAt(1)?.text =
                getTabString(R.string.favor_image, it.size)
        }
    }

    private fun getTabString(res: Int, cnt: Int) = getString(res, cnt.toString())

    override val viewModel: BaseContainerViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBaseContainerBinding =
        FragmentBaseContainerBinding.inflate(inflater, container, false)
}