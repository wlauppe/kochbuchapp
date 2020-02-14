package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

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
    Gramm("g") {
        override fun getText(): String {
            return "g"
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
    },

    KeineEinheit(""){
        override fun getText(): String{
            return "";
        }
    };

    abstract fun getText():String
}