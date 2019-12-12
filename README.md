# Simple RecyclerView Adapter [![](https://jitpack.io/v/cregus/simple-recyclerview-adapter.svg)](https://jitpack.io/#cregus/simple-recyclerview-adapter)
Android library written in Kotlin to simplify creating adapters for RecyclerView.

## Setup
### Add the JitPack repository to your build file
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### Add library dependency to your project build file
```
dependencies {
  implementation 'com.github.cregus:simple-recyclerview-adapter:1.0'
}
```

## Sample usage
### Define a class (or classes) for your list items.
```kotlin
data class Header(val label: String) : SimpleAdapterItem<String> {
    override val id: String = label
    override val layoutResId: Int = R.layout.item_header
}

data class User(override val id: Int, val name: String) : SimpleAdapterItem<Int> {
    override val layoutResId: Int = R.layout.item_user
}
```

### Create adapter.
```kotlin
val adapter = adapter { layoutResId, view ->
    when (layoutResId) {
        R.layout.item_header -> viewHolder<Header>(view) { header ->
            label.text = header.label
        }
        R.layout.item_user -> viewHolder<User>(view) { user ->
            name.text = user.name
        }
    }
}
```

### Fill it with items.
```kotlin
adapter.submitList(items)
```