package extras

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KProperty

    operator fun <T> StateFlow<T>.getValue(thisRef: Any?, property: KProperty<*>): T {
        return this.value
    }

    operator fun <T> MutableStateFlow<T>.setValue(thisRef: Any?, property: KProperty<*>, newValue: T) {
        this.value = newValue
    }
