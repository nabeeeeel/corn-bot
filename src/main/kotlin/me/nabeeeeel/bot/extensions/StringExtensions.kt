package me.nabeeeeel.bot.extensions


/**
 * Capitalizes and adds a space to each character in a string: test -> T E S T
 */
fun String.upperWithSpace() : String {
    var newString = ""
    forEach {
        newString += it.toUpperCase() + " "
    }
    return newString
}