package me.nabeeeeel.bot.extensions

fun String.upperWithSpace() : String {
    var newString = ""
    forEach {
        newString += it.toUpperCase() + " "
    }
    return newString
}