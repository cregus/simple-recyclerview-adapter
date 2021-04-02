package pl.kremblewski.android.simplerecyclerviewadapter

import androidx.recyclerview.widget.DiffUtil

internal class DiffCallback<T : AdapterItem<*>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        if (oldItem.javaClass != newItem.javaClass) {
            return false
        }

        if (oldItem.id.javaClass != newItem.id.javaClass) {
            return false
        }

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}