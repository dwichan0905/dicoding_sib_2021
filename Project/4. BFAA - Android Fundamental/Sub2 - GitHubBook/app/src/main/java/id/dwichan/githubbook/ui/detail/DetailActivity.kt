package id.dwichan.githubbook.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.UserItem
import id.dwichan.githubbook.databinding.ActivityDetailBinding
import id.dwichan.githubbook.ui.detail.options.OptionsBottomSheet
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MIN_WIDTH_TO_COLLAPSE = 110
        const val EXTRA_USER = "extra_user"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var isTitleShow = false
    private var scrollRange = -1

    private lateinit var user: UserItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        if (bundle != null) {
            user = bundle.getParcelable<UserItem>(EXTRA_USER) as UserItem
            setProfileDescription(user)
            if (user.username != null) {
                setToolbarTitle(user.name ?: "", "@${user.username}")
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> supportFinishAfterTransition()
            R.id.menu_more -> {
                val bundle = Bundle().apply {
                    putParcelable(OptionsBottomSheet.EXTRA_USER, user)
                }
                OptionsBottomSheet().apply {
                    arguments = bundle
                    show(supportFragmentManager, OptionsBottomSheet.TAG)
                }
            }
        }
        return true
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
        binding.appBar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = barLayout?.totalScrollRange ?: -1
                }
                if (scrollRange + verticalOffset <= MIN_WIDTH_TO_COLLAPSE) {
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
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}