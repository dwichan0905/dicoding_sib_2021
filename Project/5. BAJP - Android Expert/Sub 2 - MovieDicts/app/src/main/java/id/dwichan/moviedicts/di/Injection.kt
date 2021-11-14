package id.dwichan.moviedicts.di

import id.dwichan.moviedicts.data.repository.MoviesRepository
import id.dwichan.moviedicts.data.repository.TelevisionShowRepository
import id.dwichan.moviedicts.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.data.repository.remote.api.ApiService

object Injection {

    fun provideMoviesRepository(): MoviesRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiService.getApiService())
        return MoviesRepository.getInstance(remoteDataSource)
    }

    fun provideTelevosionShowRepository(): TelevisionShowRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiService.getApiService())
        return TelevisionShowRepository.getInstance(remoteDataSource)
    }

}