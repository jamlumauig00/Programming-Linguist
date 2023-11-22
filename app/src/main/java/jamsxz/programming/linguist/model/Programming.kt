package jamsxz.programming.linguist.model

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import android.util.Log
import jamsxz.programming.linguist.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Programming(
    val id: Int,
    val banner: Int,
    val language: String,
    val features: String,
    val description: String,
    val pros: String,
    val cons: String
) : Parcelable {

    companion object {
        @SuppressLint("DiscouragedApi")
        fun programmingList(context: Context): List<Programming> {
            val programmingArray = context.resources.getStringArray(R.array.programming_languages)

            return programmingArray.mapIndexedNotNull { index, languageData ->
                val dataParts = languageData.split("|")

                if (dataParts.size == 6) {
                    val drawableId = context.resources.getIdentifier(dataParts[0], "drawable", context.packageName)
                    Log.d("ProgrammingList", "Drawable ID: $drawableId")

                    Programming(
                        index,
                        drawableId,
                        dataParts[1],  // Language
                        dataParts[2],  // Description
                        dataParts[3],  // Features
                        dataParts[4],  // Pros
                        dataParts[5]   // Cons
                    )
                } else {
                    // Handle the case where data is incomplete
                    null
                }
            }
        }
    }
}