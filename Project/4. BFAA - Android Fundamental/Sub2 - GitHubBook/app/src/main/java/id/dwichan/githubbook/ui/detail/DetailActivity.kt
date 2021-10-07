package id.dwichan.githubbook.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.entity.User
import id.dwichan.githubbook.databinding.ActivityDetailBinding
import id.dwichan.githubbook.ui.animationdialog.AnimationDialogActivity
import id.dwichan.githubbook.ui.detail.content.SectionsPagerAdapter
import id.dwichan.githubbook.ui.options.OptionsBottomSheet
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MIN_WIDTH_TO_COLLAPSE = 110
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var isTitleShow = false
    private var scrollRange = -1
    private var lastOffset = -1
    private var isFavorite = false

    private lateinit var user: User
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        if (bundle != null) {
            user = bundle.getParcelable<User>(EXTRA_USER) as User
            setProfileDescription(user)
            setToolbarTitle(user.name, "@${user.username}")
            initSectionPager(user.username)
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
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> supportFinishAfterTransition()
            R.id.menu_favorite -> setFavorite()
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

    private fun setFavorite() {
        swapFavoriteIcon()
        isFavorite = !isFavorite

        val intent = Intent(this, AnimationDialogActivity::class.java)
        if (isFavorite) {
            intent.apply {
                putExtra(AnimationDialogActivity.EXTRA_TYPE, AnimationDialogActivity.TYPE_FAVORITE_ADD)
                putExtra(AnimationDialogActivity.EXTRA_MESSAGE, "Added to Favorite!")
            }
        } else {
            intent.apply {
                putExtra(AnimationDialogActivity.EXTRA_TYPE, AnimationDialogActivity.TYPE_FAVORITE_REMOVE)
                putExtra(AnimationDialogActivity.EXTRA_MESSAGE, "Removed from Favorite!")
            }
        }
        startActivity(intent)
    }

    private fun swapFavoriteIcon() {
        if (isFavorite) {
            menu?.getItem(0)?.icon =
                AppCompatResources.getDrawable(this, R.drawable.ic_baseline_favorite_off_24)
        } else {
            menu?.getItem(0)?.icon =
                AppCompatResources.getDrawable(this, R.drawable.ic_baseline_favorite_on_24)
        }
    }

    private fun initSectionPager(username: String) {
        binding.contentDetail.apply {
            val sectionsPagerAdapter = SectionsPagerAdapter(this@DetailActivity, username)
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    private fun setProfileDescription(item: User) {
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

            isFavorite = item.isFavorite
            swapFavoriteIcon()
        }
    }

    private fun setToolbarTitle(title: String, username: String = "") {
        binding.appBar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
                if (scrollRange == -1) {
                    scrollRange = barLayout?.totalScrollRange ?: -1
                }
                if (lastOffset == verticalOffset) return@OnOffsetChangedListener
                lastOffset = verticalOffset
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