package id.dwichan.moviedicts.core.util

import id.dwichan.moviedicts.core.data.entity.GenresDataEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConverterTest {

    private val dummyMovieGenres = ArrayList<GenresDataEntity>()
    private val dummyTvShowGenres = ArrayList<GenresDataEntity>()

    private val expectedMovieGenres = "Action, Adventure"
    private val expectedTvShowGenres = "Family, Action"

    private val dummyDuration = 163
    private val expectedDurationOutput = "2h 43m"

    @Before
    fun setup() {
        dummyMovieGenres.add(
            GenresDataEntity(
                id = 1,
                name = "Action"
            )
        )
        dummyMovieGenres.add(
            GenresDataEntity(
                id = 2,
                name = "Adventure"
            )
        )
        dummyTvShowGenres.add(
            GenresDataEntity(
                id = 1,
                name = "Family"
            )
        )
        dummyTvShowGenres.add(
            GenresDataEntity(
                id = 2,
                name = "Action"
            )
        )
    }

    @Test
    fun `Movie Genres should be returned as expected`() {
        val actual = Converter.Movies.listGenresToStringList(dummyMovieGenres)
        assertEquals(expectedMovieGenres, actual)
    }

    @Test
    fun `Television Show Genres should be returned as expected`() {
        val actual = Converter.TelevisionShow.listGenresToStringList(dummyTvShowGenres)
        assertEquals(expectedTvShowGenres, actual)
    }

    @Test
    fun `If Movie Genre is Empty, should be return an empty string`() {
        val expected = ""
        val actual = Converter.Movies.listGenresToStringList(ArrayList())
        assertEquals(expected, actual)
    }

    @Test
    fun `If Television Show Genre is Empty, should be return an empty string`() {
        val expected = ""
        val actual = Converter.TelevisionShow.listGenresToStringList(ArrayList())
        assertEquals(expected, actual)
    }

    @Test
    fun `163 minutes should be '2h 43m'`() {
        val actual = Converter.Duration.minutesToString(dummyDuration.toLong())
        assertEquals(expectedDurationOutput, actual)
    }
}