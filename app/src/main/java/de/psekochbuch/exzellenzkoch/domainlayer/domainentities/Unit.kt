package com.example.simplekochbuchapp.domainlayer.domainentities

enum class Unit(unit:String) {
    EssLöffel("EL.") {
        override fun getText(): String {
            return "EssLöffel"
        }
    },
    TeeLöffel("TL.") {
        override fun getText(): String {
            return "TeeLöffel"
        }
    },
    Gramm("Gr.") {
        override fun getText(): String {
            return "Gramm"
        }
    },
    Liter("L.") {
        override fun getText(): String {
            return "Liter"
        }
    },
    Stück("Stk.") {
        override fun getText(): String {
            return "Stück"
        }
    },
    Prise("Prise") {
        override fun getText(): String {
            return "Prise"
        }
    };

    abstract fun getText():String
}