package pl.kremblewski.android.simplerecyclerviewadapter

interface SimpleAdapterItem<ID : Any> {
    val id: ID
    val layoutResId: Int

    override fun equals(other: Any?): Boolean
}