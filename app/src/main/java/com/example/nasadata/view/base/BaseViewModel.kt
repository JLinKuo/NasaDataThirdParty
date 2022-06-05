package com.example.nasadata.view.base

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasadata.MyApplication
import com.example.nasadata.utils.THUMBNAIL_PATH
import com.example.nasadata.utils.getImageFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileOutputStream
import java.io.OutputStream

abstract class BaseViewModel: ViewModel() {
    protected var TAG = javaClass.simpleName

    fun saveImage(bitmap: Bitmap, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            doSaveImageAction(bitmap, title)
        }
    }

    private fun doSaveImageAction(bitmap: Bitmap, title: String) {
        val imageFile = title.getImageFile(MyApplication.instance, THUMBNAIL_PATH)
        val fOut = FileOutputStream(imageFile) as OutputStream
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
        } catch (ex: Exception) {
            ex.stackTrace
        } finally {
            fOut.close()
        }
    }
}