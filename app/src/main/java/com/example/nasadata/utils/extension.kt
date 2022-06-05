package com.example.nasadata.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.nasadata.model.db.NasaDataEntity
import java.io.File

const val THUMBNAIL_PATH = "thumbnail"
const val HD_PATH = "hd"
private const val IMAGE_TYPE = ".jpg"

fun String.isSavedInStorage(context: Context, directoryName: String): Boolean {
    return this.getImageFile(context, directoryName).exists()
}

fun String.getDirectoryPath(context: Context): File {
    val strPath = "${context.filesDir.absolutePath}${File.separator}$this"
    val file = File(strPath)
    if(!file.exists()) {
        file.mkdirs()
    }
    return file
}

fun String.getImageFile(context: Context, directoryName: String): File {
    val filename = this.replace("\\s".toRegex(), "")
    val filePath = "${directoryName.getDirectoryPath(context)}${File.separator}"
    return File(filePath, "$filename$IMAGE_TYPE")
}

fun ImageView.showImage(
    item: NasaDataEntity,
    glideReqBuilder: RequestBuilder<Drawable>,
    isThumbnail: Boolean,
    doSaveImageAction: ((Bitmap, String) -> Unit)?
) {
    this.setImageResource(android.R.color.transparent)
    val imgDirPath = if(isThumbnail) THUMBNAIL_PATH else HD_PATH
    val isSaved = item.title.isSavedInStorage(context, imgDirPath)
    val imgPath = if(isSaved) {
        item.title.getImageFile(context, THUMBNAIL_PATH).absolutePath
    } else {
        item.url
    }

    Glide.with(context).load(imgPath).thumbnail(glideReqBuilder).listener(
        object: RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                resource?.toBitmap() ?.let {
                    this@showImage.setImageBitmap(it)

                    if(!isSaved) {
                        doSaveImageAction?.invoke(it, item.title)
                    }
                }
                return true
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.d("JLin", "ex: ${e?.stackTrace}")
                return true
            }
        }
    ).into(this)
}

