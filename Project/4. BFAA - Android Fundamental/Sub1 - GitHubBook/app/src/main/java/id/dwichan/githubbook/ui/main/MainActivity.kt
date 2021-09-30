package id.dwichan.githubbook.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.UserItem
import id.dwichan.githubbook.databinding.ActivityMainBinding
import id.dwichan.githubbook.databinding.ItemUsersBinding
import id.dwichan.githubbook.ui.detail.DetailActivity
import id.dwichan.githubbook.util.FileManagement
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)

        showUsersList()
    }

    private fun showUsersList() {
        with(binding) {
            val listItem = parseRawJSONToObjects(FileManagement.readJSONFile(this@MainActivity))
            val adapter = UsersAdapter(listItem)

            listUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            listUsers.adapter = adapter

            adapter.onItemAction = object : UsersAdapter.OnItemActionListener {
                override fun onClick(item: UserItem, itemBinding: ItemUsersBinding) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_USER, item)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this@MainActivity, itemBinding.imageUser, "UserIcon"
                    )
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }

    private fun parseRawJSONToObjects(rawJsonString: String): List<UserItem> {
        val userItems = ArrayList<UserItem>()
        val jsonObject = JSONObject(rawJsonString)
        val usersJsonList = jsonObject.getJSONArray("users")

        for (position in 0 until usersJsonList.length()) {
            val user = usersJsonList.getJSONObject(position)
            val userItem = UserItem()

            userItem.username = user.getString("username")
            userItem.name = user.getString("name")
            userItem.avatar = user.getString("avatar")
            userItem.company = user.getString("company")
            userItem.location = user.getString("location")
            userItem.repository = user.getInt("repository")
            userItem.follower = user.getInt("follower")
            userItem.following = user.getInt("following")

            userItems.add(userItem)
        }

        return userItems
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}