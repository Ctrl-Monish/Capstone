package com.projects.capstonear

import android.content.Context
import android.location.Location

fun interface PlaceListener {
    companion object {
        private val placeListeners = mutableListOf<PlaceListener>()

        fun register(placeListener: PlaceListener) {
            if (placeListeners.all { it != placeListener }) {
                placeListeners.add(placeListener)
            }
        }

        fun unregister(placeListener: PlaceListener) {
            placeListeners.remove(placeListener)
        }

        fun trigger(places: List<Place>?) {
            placeListeners.forEach {
                it.onPlacesChanged(places)
            }
        }
    }

    fun onPlacesChanged(places: List<Place>?)
}

class Place private constructor(
    val id: String,
    val name: String,
    val type: Type,
    val description: String,
    //val timestamp: LocalDateTime?,
    val location: Location){

    enum class Type {
        Hostel,
        Garden,
        Academics,
        Restaurant,
    }

    companion object {
        fun fetchPlaces(context: Context, tagId: String, silent: Boolean = false) {
            val places = mutableListOf<Place>(
                Place("Jaipur",
                    "Rangoli Garden",
                    Type.Restaurant,
                    "Rangoli Gardens in Jaipur is a society covered " +
                            "on 20 acres of land with club house and swimming pools \n" +
                            "Where one of our beloved team member Chaitanya Kapoor lives ",
                    Location("Place").apply {
                        longitude = 30.3525163
                        latitude = 76.37091895
                        altitude = 0.0
                    }
                ),
            )
            PlaceListener.trigger(places);
        }
    }
}