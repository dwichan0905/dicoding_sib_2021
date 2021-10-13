package id.dwichan.githubbook.ui.main.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.dwichan.githubbook.data.repository.GitHubBookRepository
import id.dwichan.githubbook.data.repository.local.entity.FavoriteUser

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GitHubBookRepository(application)

    fun getUsers(query: String = ""): LiveData<List<FavoriteUser>> {
        return if (query.isEmpty()) {
            repository.getAllUsers()
        } else {
            repository.searchUsers(query)
        }
    }
}