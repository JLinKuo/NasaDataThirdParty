package com.example.nasadata.view.inspection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.nasadata.R
import com.example.nasadata.databinding.FragmentInspectionBinding
import com.example.nasadata.utils.showImage
import com.example.nasadata.view.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [InspectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InspectionFragment : BaseFragment<InspectionViewModel, FragmentInspectionBinding>() {

    private val args: InspectionFragmentArgs by navArgs()

    private val glideRequestBuilder by lazy {
        Glide.with(activity).asDrawable().sizeMultiplier(0.01f)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.entity = args.entity

        setView()
    }

    private fun setView() {
        activity.showProgressBar(true)
        binding.inspectionImage.showImage(viewModel.entity, glideRequestBuilder,
                isThumbnail = false) { bitmap, title ->
            activity.dismissProgressBar()
            viewModel.saveImage(bitmap, title)
        }
//        Glide.with(activity).load(viewModel.entity.hdurl).into(binding.inspectionImage)
    }

    override val viewModel: InspectionViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInspectionBinding = FragmentInspectionBinding.inflate(inflater, container, false)
}