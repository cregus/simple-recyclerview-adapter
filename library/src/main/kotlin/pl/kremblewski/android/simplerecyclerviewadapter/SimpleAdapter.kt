package pl.kremblewski.android.simplerecyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class SimpleAdapter(
    private val onCreateViewHolder: SimpleAdapter.(layoutResId: Int, view: View) -> ViewHolder
) : ListAdapter<SimpleAdapterItem<*>, ViewHolder>(ItemCallback()) {
    val items: List<SimpleAdapterItem<*>>
        get() = (0 until itemCount).map(::getItem)

    override fun getItemViewType(position: Int): Int {
        return getItem(position).layoutResId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)
        return onCreateViewHolder(viewType, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(holder, getItem(position))
    }
}

fun adapter(onCreateViewHolder: SimpleAdapter.(layoutResId: Int, view: View) -> ViewHolder): SimpleAdapter {
    return SimpleAdapter(onCreateViewHolder)
}