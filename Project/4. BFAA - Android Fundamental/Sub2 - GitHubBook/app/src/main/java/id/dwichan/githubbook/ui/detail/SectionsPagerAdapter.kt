package id.dwichan.githubbook.ui.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.dwichan.githubbook.ui.detail.content.follower.FollowerFragment
import id.dwichan.githubbook.ui.detail.content.following.FollowingFragment
import id.dwichan.githubbook.ui.detail.content.repository.RepositoryFragment

class SectionsPagerAdapter(activity: AppCompatActivity, username: String):
    FragmentStateAdapter(activity) {

    private val fragments: List<Fragment> = listOf(
        RepositoryFragment.newInstance(username),
        FollowerFragment.newInstance(username),
        FollowingFragment.newInstance(username)
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}