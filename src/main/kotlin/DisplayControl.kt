import com.adamratzman.spotify.models.SpotifyPublicUser
import com.adamratzman.spotify.models.SpotifySearchResult

//Interface to tell user what searches they can do.
class DisplayControl {


    //Options table
    fun displaySearch() {
        println(
            """
            "=================="
            "(1) Artist Name"
            "(2) Track Name"
            "(3) User Profile"
            "=================="
        """.trimIndent()
        )
    }

    //Display search based on user results
    fun displayUserSearchResults(searchResults: SpotifyPublicUser) {
        val parsedResults = parseUserSearchResults(searchResults)

        println(parsedResults)
    }

    // Displays the track based search results
    fun displayTrackSearchResults(searchResults: SpotifySearchResult) {
        val parsedResults = parseTrackSearchResults(searchResults)

        println()
        for (printedList in parsedResults) {
            // the first list is empty every time, for this reason so this just skips it.
            if (printedList.isNullOrEmpty()) {
                continue
            }
            println(printedList)
        }
    }

    // Parses through all the results of a track search and presents them in a way
    // that is digestible by a person.
    private fun parseTrackSearchResults(searchResults: SpotifySearchResult): List<List<String>> {
        var resultSet: MutableList<List<String>> = mutableListOf(listOf())

        for (s in searchResults.tracks!!.items) {
            var singleResultSet = mutableListOf<String>()
            singleResultSet.add(s.artists[0].name)
            singleResultSet.add(s.name)

            // API returns track playback time in milliseconds so this
            // converts it to the traditional m:ss display style.
            val timeInSeconds = (s.length/1000).toInt()
            val minutes = (timeInSeconds/60).toInt()
            val remainderSeconds = (timeInSeconds%60).toInt()
            val timeString = "$minutes:$remainderSeconds"
            singleResultSet.add(timeString)

            singleResultSet.add(s.externalUrls.spotify.toString())

            resultSet.add(singleResultSet)
        }
        return resultSet
    }

    // Parses through all the returned values from the API call into user
    // relevant information
    private fun parseUserSearchResults(searchResults: SpotifyPublicUser): List<String> {
        var userProfileInfo = mutableListOf<String>()


        userProfileInfo.add(searchResults.id)
        userProfileInfo.add(searchResults.followers.total.toString())
        userProfileInfo.add(searchResults.externalUrls.spotify.toString())

        return userProfileInfo
    }
}