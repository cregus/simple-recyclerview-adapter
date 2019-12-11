package pl.kremblewski.android.simplerecyclerviewadapter

import androidx.recyclerview.widget.DiffUtil

internal class ItemCallback : DiffUtil.ItemCallback<SimpleAdapterItem<*>>() {
    override fun areItemsTheSame(
        oldItem: SimpleAdapterItem<*>,
        newItem: SimpleAdapterItem<*>
    ): Boolean {
        if (oldItem::class != newItem::class || oldItem.id::class != newItem.id::class) {
            return false
        }

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SimpleAdapterItem<*>,
        newItem: SimpleAdapterItem<*>
    ): Boolean {
        return oldItem == newItem
    }
}