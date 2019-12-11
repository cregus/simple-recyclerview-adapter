package pl.kremblewski.android.simplerecyclerviewadapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class ViewHolder(
    override val containerView: View,
    val onBind: ViewHolder.(item: SimpleAdapterItem<*>) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    inline val context: Context
        get() = containerView.context
}

inline fun <reified Item : SimpleAdapterItem<*>> viewHolder(
    view: View,
    crossinline onBindBlock: ViewHolder.(item: Item) -> Unit
): ViewHolder {
    return ViewHolder(view) { item ->
        onBindBlock(this, item as? Item ?: throw IllegalStateException())
    }
}