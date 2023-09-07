import androidx.compose.runtime.MutableState

fun <T> MutableState<T>.update(callback: (T) -> T ) {
    value = callback(value)
}