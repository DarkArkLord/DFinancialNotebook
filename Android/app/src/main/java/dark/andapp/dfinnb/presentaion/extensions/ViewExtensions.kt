package dark.andapp.dfinnb.presentaion.extensions

import android.app.AlertDialog
import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import dark.andapp.dfinnb.R
import java.math.RoundingMode
import java.text.DecimalFormat

fun TextView.setColoredNumberRG(value: Number) {
    DecimalFormat("#.###").apply {
        roundingMode = RoundingMode.DOWN
    }.format(value).toDouble()
        .also {
            val color = if (it == 0.0)
                R.color.yellow
            else if (it < 0)
                R.color.red
            else
                R.color.green

            this.setTextColor(
                ContextCompat.getColor(
                    this.context,
                    color
                )
            )

            this.text = if (it <= 0)
                it.toString()
            else
                "+${value}"
        }
}

fun Context.createInfoDialog(text: String) {
    AlertDialog.Builder(this)
        .setNeutralButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
        .setMessage(text)
        .show()
}