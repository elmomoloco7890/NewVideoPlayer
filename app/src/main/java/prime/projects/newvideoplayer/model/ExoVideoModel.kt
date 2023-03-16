package prime.projects.newvideoplayer.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel

class ExoVideoModel: ViewModel() {

    fun makingToasts(context: Context, message: String, toastType: Int){
        Toast.makeText(context, message, toastType).show()
    }

}