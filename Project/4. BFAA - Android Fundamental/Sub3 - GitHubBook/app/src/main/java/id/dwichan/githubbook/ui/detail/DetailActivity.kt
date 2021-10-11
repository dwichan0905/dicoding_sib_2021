package id.dwichan.githubbook.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.network.response.UserDetailResponse
import id.dwichan.githubbook.data.network.response.UserItem
import id.dwichan.githubbook.databinding.ActivityDetailBinding
import id.dwichan.githubbook.ui.animationdialog.AnimationDialogActivity
import id.dwichan.githubbook.ui.options.OptionsBottomSheet
import java.text.NumberFormat

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    private var isTitleShow = false
    private var scrollRange = -1
    private var lastOffset = -1
    private var isFavorite = false

    private lateinit var user: UserItem
    private lateinit var userDetailResponse: UserDetailResponse

    private var isLoading: Boolean = false
    private var menu: Menu? = null

    private val comingSoon: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.isLoading.observe(this) { state ->
            isLoading = state
            setLoading(state)
        }

        viewModel.isFailed.observe(this) { failed ->
            if (failed == true) {
                Toast.makeText(
                    this,
                    "Failed to fetch data!",
                    Toast.LENGTH_SHORT
                ).show()
                supportFinishAfterTransition()
            }

        }

        viewModel.data.observe(this) {
            userDetailResponse = it
            setProfileDescription(it)
        }

        val bundle = intent.extras
        if (bundle != null) {
            user = bundle.getParcelable<UserItem>(EXTRA_USER_ITEM) as UserItem
            viewModel.fetchUserData(user.login)
        } else {
            Toast.makeText(
                this,
                "Failed to get User Data",
                Toast.LENGTH_SHORT
            ).show()
            supportFinishAfterTransition()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        viewModel.isLoading.observe(this) {
            if (!isLoading) {
                menuInflater.inflate(R.menu.menu_detail, menu)
                this.menu = menu
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> supportFinishAfterTransition()
            R.id.menu_favorite -> setFavorite()
            R.id.menu_more -> {
                val bundle = Bundle().apply {
                    putParcelable(OptionsBottomSheet.EXTRA_USER_DETAIL, userDetailResponse)
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
        if (comingSoon) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Coming soon")
                .setMessage("This feature will coming very soon, stay back!")
                .setCancelable(false)
                .setPositiveButton("Okay", null)
                .create()
                .show()
        } else {
            swapFavoriteIcon()
            isFavorite = !isFavorite

            val intent = Intent(this, AnimationDialogActivity::class.java)
            if (isFavorite) {
                intent.apply {
                    putExtra(
                        AnimationDialogActivity.EXTRA_TYPE,
                        AnimationDialogActivity.TYPE_FAVORITE_ADD
                    )
                    putExtra(AnimationDialogActivity.EXTRA_MESSAGE, "Added to Favorite!")
                }
            } else {
                intent.apply {
                    putExtra(
                        AnimationDialogActivity.EXTRA_TYPE,
                        AnimationDialogActivity.TYPE_FAVORITE_REMOVE
                    )
                    putExtra(AnimationDialogActivity.EXTRA_MESSAGE, "Removed from Favorite!")
                }
            }
            startActivity(intent)
        }
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

    private fun setProfileDescription(item: UserDetailResponse) {
        with(binding) {
            imageUserIcon.loadImage(item.avatarUrl ?: R.drawable.ic_baseline_error_24)
            imageUserBackgroundDetail.loadImage(item.avatarUrl ?: "")

            textUserName.text = item.name ?: getString(R.string.no_name)
            textUserUsername.text = item.login ?: getString(R.string.no_username)
            textUserCompany.text = item.company ?: getString(R.string.no_company)
            textUserLocation.text = item.location ?: getString(R.string.no_location)
            textRepositories.text = NumberFormat.getInstance().format(item.publicRepos)
            textFollowers.text = NumberFormat.getInstance().format(item.followers)
            textFollowing.text = NumberFormat.getInstance().format(item.following)

            isFavorite = false // coming soon
            if (!isFavorite) {
                menu?.getItem(0)?.icon =
                    AppCompatResources
                        .getDrawable(this@DetailActivity, R.drawable.ic_baseline_favorite_off_24)
            } else {
                menu?.getItem(0)?.icon =
                    AppCompatResources
                        .getDrawable(this@DetailActivity, R.drawable.ic_baseline_favorite_on_24)
            }

            setToolbarTitle(
                textUserName.text.toString(),
                textUserUsername.text.toString()
            )
            initSectionPager(item.login ?: "")
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

    private fun setLoading(state: Boolean) {
        binding.layoutLoading.root.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.lottieLoading.visibility =
            if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textLoadingMessage.visibility = if (state) View.VISIBLE else View.GONE
        binding.layoutLoading.textLoadingMessage.text = getString(R.string.fetch_user_details, user.login)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val MIN_WIDTH_TO_COLLAPSE = 110
        const val EXTRA_USER_ITEM = "extra_user_item"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }
}

private fun ImageView.loadImage(url: Any) {
    Glide.with(this.context)
        .load(url)
        .into(this)

    Glide.with(this.context)
        .load(url)
        .error(R.drawable.ic_baseline_person_24)
        .placeholder(R.drawable.ic_baseline_person_24)
        .into(this)
}