suspend fun main(args: Array<String>) {


    val constructApi = SpotifyApiRequest()
    constructApi.buildSearchAPI()

    val displayControl = DisplayControl()
    val query = Query()
    var searchAgain = true


    while (searchAgain) {
        val searchQuery = query.findQuery(displayControl)

        // Determines whether the query is a user query or track query
        if (searchQuery.contains("user")) {
            val userQuery = searchQuery.substringAfter(":")
            val searchResults = constructApi.userSearch(userQuery)

            // Error handling in case the user does not exist.
            val bool = if (searchResults == null) {
                println()
                println("That name was not found.")
                continue
            } else {
                displayControl.displayUserSearchResults(searchResults)
            }

        } else {
            val searchResults = constructApi.trackSearch(searchQuery)
            displayControl.displayTrackSearchResults(searchResults)
        }

        searchAgain = query.searchAgainQuery()
    }
}