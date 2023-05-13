package dark.andapp.dfinnb.presentaion.extensions

import android.app.AlertDialog
import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import dark.andapp.dfinnb.R

fun TextView.setColoredNumberRG(value: Number) {
    this.setTextColor(
        ContextCompat.getColor(
            this.context,
            value.toDouble().let {
                if (it == 0.0)
                    R.color.yellow
                else if (it < 0)
                    R.color.red
                else
                    R.color.green
            }
        )
    )

    this.text = if (value.toDouble() <= 0)
        value.toString()
    else
        "+${value}"
}

fun Context.createInfoDialog(text: String) {
    AlertDialog.Builder(this)
        .setNeutralButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
        .setMessage(text)
        .show()
}