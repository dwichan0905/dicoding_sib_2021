package id.dwichan.githubbook.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.Option
import id.dwichan.githubbook.data.UserItem
import id.dwichan.githubbook.databinding.ActivityDetailBinding
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var isTitleShow = false
    private var scrollRange = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        if (bundle != null) {
            val user = bundle.getParcelable<UserItem>(EXTRA_USER) as UserItem
            setProfileDescription(user)
            if (user.username != null) {
                setToolbarTitle(user.name ?: "", "@${user.username}")
                displayOptions(user)
            } else {
                setToolbarTitle(user.name ?: "")
            }
        } else {
            Toast.makeText(
                this,
                "Failed to get User Data",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) supportFinishAfterTransition()
        return true
    }

    private fun displayOptions(user: UserItem) {
        with(binding) {
            val options: List<Option> = listOf(
                Option(
                    icon = R.drawable.github_mark,
                    title = "Visit ${user.username} on GitHub",
                    showNextIndicator = true,
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("https://github.com/${user.username}")
                        startActivity(intent)
                    }
                ),
                Option(
                    icon = R.drawable.ic_baseline_share_24,
                    title = "Share this user",
                    showNextIndicator = true,
                    onClick = {
                        ShareCompat.IntentBuilder(this@DetailActivity)
                            .setType("text/plain")
                            .setChooserTitle("Share ${user.username} to your friends...")
                            .setText(
                                """
                                ${user.name}
                                (S)he has ${user.repository} repositories.
                                
                                Visit: https://github.com/${user.username}
                            """.trimIndent()
                            )
                            .startChooser()
                    }
                )
            )

            contentDetail.listOptions.layoutManager = LinearLayoutManager(this@DetailActivity)
            contentDetail.listOptions.adapter = OptionsAdapter(options)
        }
    }

    private fun setProfileDescription(item: UserItem) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(resources.getIdentifier(item.avatar, null, packageName))
                .into(imageUserBackgroundDetail)

            Glide.with(this@DetailActivity)
                .load(resources.getIdentifier(item.avatar, null, packageName))
                .error(R.drawable.ic_baseline_person_24)
                .placeholder(R.drawable.ic_baseline_person_24)
                .into(imageUserIcon)

            textUserName.text = item.name
            textUserUsername.text = item.username
            textUserCompany.text = item.company
            textUserLocation.text = item.location
            textRepositories.text = NumberFormat.getInstance().format(item.repository)
            textFollowers.text = NumberFormat.getInstance().format(item.follower)
            textFollowing.text = NumberFormat.getInstance().format(item.following)
        }
    }

    private fun setToolbarTitle(title: String, username: String = "") {
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            Log.d("ToolbarDetail", "scrollRange: $scrollRange | verticalOffset: $verticalOffset")
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange ?: -1
            }
            if (scrollRange + verticalOffset <= 110) {
                binding.toolbarLayout.title = title
                supportActionBar?.title = title
                if (username != "") {
                    supportActionBar?.subtitle = username
                }
                isTitleShow = true
            } else {
                binding.toolbarLayout.title = " "
                supportActionBar?.title = " "
                if (username != "") {
                    supportActionBar?.subtitle = ""
                }
                isTitleShow = false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}