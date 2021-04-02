package pl.kremblewski.android.simplerecyclerviewadapter

import android.app.Activity
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

class Adapter(
    private val layoutInflater: LayoutInflater
) : ListAdapter<AdapterItem<*>, ViewHolder>(DiffCallback()) {
    private val viewBindings = SparseArray<ViewBindingFactory<*>>()
    private val onBinds = SparseArray<(ViewBinding, AdapterItem<*>, Int) -> Unit>()

    fun <T : AdapterItem<*>, VB : ViewBinding> register(
        itemClass: Class<T>,
        viewBindingFactory: ViewBindingFactory<VB>,
        onBind: Adapter.(binding: VB, item: T, position: Int) -> Unit
    ) {
        val viewType = itemClass.hashCode()
        viewBindings.append(viewType, viewBindingFactory)
        onBinds.append(viewType) { viewBinding, adapterItem, position ->
            onBind(viewBinding as VB, adapterItem as T, position)
        }
    }

    inline fun <reified T : AdapterItem<*>, reified VB : ViewBinding> register(
        noinline onBind: Adapter.(binding: VB, item: T, position: Int) -> Unit
    ) {
        register(T::class.java, viewBindingFactory(), onBind)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).javaClass.hashCode()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = viewBindings[viewType].inflate(layoutInflater, parent)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBinds[getItemViewType(position)].invoke(holder.viewBinding, getItem(position), position)
    }
}

inline fun adapter(layoutInflater: LayoutInflater, block: Adapter.() -> Unit): Adapter {
    return Adapter(layoutInflater).apply(block)
}

inline fun Activity.adapter(block: Adapter.() -> Unit): Adapter {
    return adapter(layoutInflater, block)
}

inline fun Fragment.adapter(block: Adapter.() -> Unit): Adapter {
    return adapter(layoutInflater, block)
}