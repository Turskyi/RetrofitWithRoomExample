package ua.turskyi.retrofitwithroomexample.domain

import ua.turskyi.retrofitwithroomexample.util.smartTruncate

/**
 * Domain objects are plain Kotlin data classes that represent the things in our app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 */

/**
 * Videos represent a devbyte that can be played.
 */
data class Video(val title: String,
                 val description: String,
                 val url: String,
                 val updated: String,
                 val thumbnail: String) {

    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String
        get() = description.smartTruncate(200)
}
