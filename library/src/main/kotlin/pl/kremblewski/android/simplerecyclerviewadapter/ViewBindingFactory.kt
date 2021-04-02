package pl.kremblewski.android.simplerecyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.viewbinding.ViewBinding

class ViewBindingFactory<VB : ViewBinding>(private val viewBindingClass: Class<VB>) {
    fun inflate(layoutInflater: LayoutInflater, parent: ViewParent?): VB {
        return viewBindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).invoke(null, layoutInflater, parent, false) as VB
    }
}

inline fun <reified VB : ViewBinding> viewBindingFactory(): ViewBindingFactory<VB> {
    return ViewBindingFactory(VB::class.java)
}