package ru.netology.nmedia

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object IntArg: ReadWriteProperty<Bundle, Int> {
    override fun getValue(thisRef: Bundle, property: KProperty<*>): Int {
        return thisRef.getInt(property.name)
    }

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Int) {
        thisRef.putInt(property.name, value)
    }
}