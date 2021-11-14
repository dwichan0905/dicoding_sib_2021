package id.dwichan.moviedicts.data.repository

interface TelevisionShowDataSource {

    fun getTrendingTelevisionShowToday()

    fun getTrendingTelevisionShowWeekly()

    fun getTelevisionShowDetails(id: Int)
}