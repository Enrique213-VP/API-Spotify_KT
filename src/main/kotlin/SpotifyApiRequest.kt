import com.adamratzman.spotify.SpotifyAppApi
import com.adamratzman.spotify.models.SpotifyPublicUser
import com.adamratzman.spotify.models.SpotifySearchResult
import com.adamratzman.spotify.spotifyAppApi
import com.adamratzman.spotify.utils.Market

class SpotifyApiRequest {

    private val clientId = "Replace for the identification "
    private val clientSecret = "Replace for the secret client"
    private var api: SpotifyAppApi? = null


    //Pulls the clientId and clientSecret tokens and builds them into an object
    suspend fun buildSearchAPI() {
        api = spotifyAppApi(clientId, clientSecret).build()
    }

    //Performs Spotify database query for queries related to user information.
    suspend fun userSearch(userQuery: String): SpotifyPublicUser? {
        return api!!.users.getProfile(userQuery)
    }

    // Performs Spotify database query for queries related to track information.
    suspend fun trackSearch(searchQuery: String): SpotifySearchResult {
        return api!!.search.searchAllTypes(searchQuery, 50, 1, market = Market.CO)
    }
}