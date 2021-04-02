package pl.kremblewski.android.simplerecyclerviewadapter

interface AdapterItem<out ID : Any> {
    val id: ID

    override fun equals(other: Any?): Boolean
}