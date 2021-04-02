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
  implementation 'com.github.cregus:simple-recyclerview-adapter:1.1.0'
}
```

## Sample usage
### Define a class (or classes) for your list items.
```kotlin
data class Header(val label: String) : AdapterItem<String> {
    override val id: String = label
}

data class User(override val id: Int, val name: String) : AdapterItem<Int>
```

### Create adapter using Jetpack View Binding and assign it to your RecyclerView.
```kotlin
val adapter = adapter {
    register<Header, ItemHeaderBinding> { binding, item, position ->
        binding.label.text = item.label
    }
    register<User, ItemUserBinding> { binding, item, position ->
        binding.name.text = item.name
    }
}
recyclerView.adapter = adapter
```

### Fill it with items.
```kotlin
adapter.submitList(items)
```