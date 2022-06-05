package com.example.nasadata.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.nasadata.R
import com.example.nasadata.databinding.FragmentDetailBinding
import com.example.nasadata.model.db.NasaDataEntity
import com.example.nasadata.utils.showImage
import com.example.nasadata.view.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    private val args : DetailFragmentArgs by navArgs()

    private val glideRequestBuilder by lazy {
        Glide.with(activity).asDrawable().sizeMultiplier(1f)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.entity = args.entity

        setView()
        setListener()
        setObserver()
    }

    private fun setView() {
        showImageAction()
        binding.copyright.text = "copyright\n${viewModel.entity.copyright}"
        binding.title.text = viewModel.entity.title
        binding.subtitle.text = "${viewModel.entity.mediaType}, ${viewModel.entity.date}"
        binding.description.text = viewModel.entity.description
        updateFavorIcon(viewModel.entity)
    }

    private fun showImageAction() {
        binding.image.showImage(viewModel.entity, glideRequestBuilder,
                isThumbnail = true) { bitmap, title ->
            viewModel.saveImage(bitmap, title)
        }
    }

    private fun setListener() {
        binding.favor.setOnClickListener {
            viewModel.modifyFavorState(viewModel.entity)
        }
        binding.image.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToInspectionFragment(viewModel.entity)
            )
        }
    }

    private fun setObserver() {
        viewModel.updateFavorResult.observe(viewLifecycleOwner) {
            if(it > 0) {
                updateFavorIcon(viewModel.entity)
            } else {
                Toast.makeText(activity, "變更最愛狀況有誤", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateFavorIcon(entity: NasaDataEntity) {
        val favorRes = if(entity.isFavorite) {
            R.drawable.ic_baseline_favorite_red
        } else {
            R.drawable.ic_baseline_favorite_border_white
        }
        Glide.with(activity).load(favorRes).into(binding.favor)
    }

    override val viewModel: DetailViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)
}