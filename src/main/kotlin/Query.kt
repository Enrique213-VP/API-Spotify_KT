class Query {


    fun searchAgainQuery(): Boolean {
        println()
        print("Would you like to make another search? (y / n ) -> ")
        return when (readLine()) {
            "y" -> true
            "Y" -> true
            else -> false
        }
    }


    /*Asks the user for the type of query and returns them in a string that
      can be read by the API call*/
    fun findQuery(displayAPI: DisplayControl): String {
        while (true) {
            displayAPI.displaySearch()
            print("Enter the number that wish search: ")
            val searchOption = readLine()!!
            println()

            //Search for request of user
            when (searchOption) {
                "1" -> {
                    print("Enter the name of the artist: ")
                    val userSearch = readLine()!!
                    return "artist:$userSearch"
                }
                "2" -> {
                    print("Enter the name of the track: ")
                    val userSearch = readLine()!!
                    return "track:$userSearch"
                }
                "3" -> {
                    print("Enter the name of the user: ")
                    val userSearch = readLine()!!
                    return "user:$userSearch"
                }
                else -> continue
            }
        }
    }
}