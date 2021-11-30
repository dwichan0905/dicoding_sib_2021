package id.dwichan.moviedicts.core.util

import id.dwichan.moviedicts.core.data.repository.local.entity.BookmarkEntity
import id.dwichan.moviedicts.vo.Type

object DataDummy {
    fun generateDummyBookmarkMovies(): List<BookmarkEntity> {
        val items = ArrayList<BookmarkEntity>()
        items.add(
            BookmarkEntity(
                id = 1,
                title = "Lorem Ipsum",
                backdropPath = "/ADdaDAdADadADa.svg",
                posterPath = "/kAOKakOAKowojA.svg",
                mediaType = Type.MEDIA_TYPE_MOVIES
            )
        )
        items.add(
            BookmarkEntity(
                id = 2,
                title = "Dolor Sit",
                backdropPath = "/ADdaDAdADadADa.svg",
                posterPath = "/kAOKakOAKowojA.svg",
                mediaType = Type.MEDIA_TYPE_MOVIES
            )
        )
        items.add(
            BookmarkEntity(
                id = 1,
                title = "Amet: Expecteteur Adispicing Elit",
                backdropPath = "/ADdaDAdADadADa.svg",
                posterPath = "/kAOKakOAKowojA.svg",
                mediaType = Type.MEDIA_TYPE_MOVIES
            )
        )
        return items
    }

    fun generateDummyBookmarkTvShow(): List<BookmarkEntity> {
        val items = ArrayList<BookmarkEntity>()
        items.add(
            BookmarkEntity(
                id = 1,
                title = "Lorem Ipsum",
                backdropPath = "/ADdaDAdADadADa.svg",
                posterPath = "/kAOKakOAKowojA.svg",
                mediaType = Type.MEDIA_TYPE_TELEVISION
            )
        )
        items.add(
            BookmarkEntity(
                id = 2,
                title = "Dolor Sit",
                backdropPath = "/ADdaDAdADadADa.svg",
                posterPath = "/kAOKakOAKowojA.svg",
                mediaType = Type.MEDIA_TYPE_TELEVISION
            )
        )
        items.add(
            BookmarkEntity(
                id = 1,
                title = "Amet: Expecteteur Adispicing Elit",
                backdropPath = "/ADdaDAdADadADa.svg",
                posterPath = "/kAOKakOAKowojA.svg",
                mediaType = Type.MEDIA_TYPE_TELEVISION
            )
        )
        return items
    }
}