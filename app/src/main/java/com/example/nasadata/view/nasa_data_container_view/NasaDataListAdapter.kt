package com.example.nasadata.view.nasa_data_container_view

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasadata.databinding.ViewNasaDataItemBinding
import com.example.nasadata.model.db.NasaDataEntity
import com.example.nasadata.utils.showImage

class NasaDataListAdapter(
    val onItemClicked: (NasaDataEntity) -> Unit,
    val doSaveImageAction: ((Bitmap, String) -> Unit)? = null,
): ListAdapter<NasaDataEntity, NasaDataListAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var context: Context

    private val glideRequestBuilder by lazy {
        Glide.with(context).asDrawable().sizeMultiplier(0.01f)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(ViewNasaDataItemBinding.inflate(
            LayoutInflater.from(context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.itemNasaDataLayoutImage.showImage(item, glideRequestBuilder,
            isThumbnail = true, doSaveImageAction)
//        holder.binding.itemNasaDataLayoutImage.setImageResource(android.R.color.black)
//        Glide.with(context).load(item.url).thumbnail(glideRequestBuilder).into(holder.binding.itemNasaDataLayoutImage)

        holder.binding.itemNasaDataLayoutTitle.text = item.title

        holder.binding.itemNasaDataLayout.setOnClickListener {
            onItemClicked.invoke(getItem(holder.absoluteAdapterPosition))
        }
    }

//    private fun showImage(imageView: AppCompatImageView, item: NasaDataEntity) {
//        imageView.setImageResource(android.R.color.transparent)
//        imageView.setImageBitmap(null)
//        imageView.setImageURI(null)
//        val isSaved = item.title.isSavedInStorage(context, THUMBNAIL_PATH)
//        val imgPath = if(isSaved) {
//            item.title.getImageFile(context, THUMBNAIL_PATH).absolutePath
//        } else {
//            item.url
//        }
//
//        Glide.with(context).load(imgPath).thumbnail(glideRequestBuilder).listener(
//            object: RequestListener<Drawable> {
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    resource?.toBitmap() ?.let {
//                        imageView.setImageBitmap(it)
//
//                        if(!isSaved) {
//                            doSaveImageAction?.invoke(it, item.title)
//                        }
//                    }
//                    return true
//                }
//
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    TODO("Not yet implemented")
//                }
//            }
//        ).into(imageView)
//    }

    inner class ViewHolder(val binding: ViewNasaDataItemBinding): RecyclerView.ViewHolder(binding.root)

    class DiffCallback: DiffUtil.ItemCallback<NasaDataEntity>() {
        override fun areItemsTheSame(oldItem: NasaDataEntity, newItem: NasaDataEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NasaDataEntity, newItem: NasaDataEntity): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.description == newItem.description
                    && oldItem.url == newItem.url
        }
    }
}