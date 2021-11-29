package dev.nairnei.objective.model


import com.google.gson.annotations.SerializedName

data class CharactersModel(
    @SerializedName("attributionHTML")
    var attributionHTML: String,
    @SerializedName("attributionText")
    var attributionText: String,
    @SerializedName("code")
    var code: Int,
    @SerializedName("copyright")
    var copyright: String,
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("etag")
    var etag: String,
    @SerializedName("status")
    var status: String
) {
    data class Data(
        @SerializedName("count")
        var count: Int,
        @SerializedName("limit")
        var limit: Int,
        @SerializedName("offset")
        var offset: Int,
        @SerializedName("results")
        var results: List<Result>,
        @SerializedName("total")
        var total: Int
    ) {
        data class Result(
            @SerializedName("comics")
            var comics: Comics,
            @SerializedName("description")
            var description: String,
            @SerializedName("events")
            var events: Events,
            @SerializedName("id")
            var id: Int,
            @SerializedName("modified")
            var modified: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("resourceURI")
            var resourceURI: String,
            @SerializedName("series")
            var series: Series,
            @SerializedName("stories")
            var stories: Stories,
            @SerializedName("thumbnail")
            var thumbnail: Thumbnail,
            @SerializedName("urls")
            var urls: List<Url>
        ) {
            data class Comics(
                @SerializedName("available")
                var available: Int,
                @SerializedName("collectionURI")
                var collectionURI: String,
                @SerializedName("items")
                var items: List<Item>,
                @SerializedName("returned")
                var returned: Int
            ) {
                data class Item(
                    @SerializedName("name")
                    var name: String,
                    @SerializedName("resourceURI")
                    var resourceURI: String
                )
            }

            data class Events(
                @SerializedName("available")
                var available: Int,
                @SerializedName("collectionURI")
                var collectionURI: String,
                @SerializedName("items")
                var items: List<Item>,
                @SerializedName("returned")
                var returned: Int
            ) {
                data class Item(
                    @SerializedName("name")
                    var name: String,
                    @SerializedName("resourceURI")
                    var resourceURI: String
                )
            }

            data class Series(
                @SerializedName("available")
                var available: Int,
                @SerializedName("collectionURI")
                var collectionURI: String,
                @SerializedName("items")
                var items: List<Item>,
                @SerializedName("returned")
                var returned: Int
            ) {
                data class Item(
                    @SerializedName("name")
                    var name: String,
                    @SerializedName("resourceURI")
                    var resourceURI: String
                )
            }

            data class Stories(
                @SerializedName("available")
                var available: Int,
                @SerializedName("collectionURI")
                var collectionURI: String,
                @SerializedName("items")
                var items: List<Item>,
                @SerializedName("returned")
                var returned: Int
            ) {
                data class Item(
                    @SerializedName("name")
                    var name: String,
                    @SerializedName("resourceURI")
                    var resourceURI: String,
                    @SerializedName("type")
                    var type: String
                )
            }

            data class Thumbnail(
                @SerializedName("extension")
                var extension: String,
                @SerializedName("path")
                var path: String
            )

            data class Url(
                @SerializedName("type")
                var type: String,
                @SerializedName("url")
                var url: String
            )
        }
    }
}