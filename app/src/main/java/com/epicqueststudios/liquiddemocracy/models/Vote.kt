package com.epicqueststudios.liquiddemocracy.models

import java.util.*

class Vote (val name:String, val action: ACTION, val voted:String) {
    enum class ACTION {
        DELEGATE , PICK;

        companion object {
            fun parse(text: String) = when (text.toUpperCase(Locale.ROOT)) {
                DELEGATE.name -> DELEGATE
                PICK.name -> PICK
                else -> throw NotImplementedError()
            }
        }
    }

}
