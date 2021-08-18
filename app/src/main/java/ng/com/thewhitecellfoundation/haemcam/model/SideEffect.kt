package ng.com.thewhitecellfoundation.haemcam.model

import android.os.Parcelable
import com.github.mikephil.charting.data.Entry
import kotlinx.parcelize.Parcelize

@Parcelize
data class SideEffect(var sideEffect: String, var occurence: Int) : Parcelable, Entry()
