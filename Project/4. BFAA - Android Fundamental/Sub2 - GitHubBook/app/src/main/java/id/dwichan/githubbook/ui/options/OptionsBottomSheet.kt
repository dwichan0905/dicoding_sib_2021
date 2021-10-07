package id.dwichan.githubbook.ui.options

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.entity.Option
import id.dwichan.githubbook.data.network.response.UserDetailResponse
import id.dwichan.githubbook.databinding.BottomSheetOptionsBinding

class OptionsBottomSheet : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "OptionsBottomFragment"
        const val EXTRA_USER_DETAIL = "extra_user_detail"
    }

    private var _binding: BottomSheetOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val user = requireArguments()
                .getParcelable<UserDetailResponse>(EXTRA_USER_DETAIL) as UserDetailResponse

            binding.listOptions.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = OptionsAdapter(getListOptions(user))
            }
        }
    }

    private fun getListOptions(user: UserDetailResponse): List<Option> {
        return listOf(
            Option(
                icon = R.drawable.github_mark,
                title = getString(R.string.visit_link, user.login),
                showNextIndicator = true,
                onClick = {
                    visitLink(user.htmlUrl)
                    dismiss()
                }
            ),
            Option(
                icon = R.drawable.ic_baseline_share_24,
                title = getString(R.string.share_title),
                showNextIndicator = true,
                onClick = {
                    shareUser(user)
                    dismiss()
                }
            )
        )
    }

    private fun visitLink(url: String? = "") {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun shareUser(user: UserDetailResponse) {
        ShareCompat.IntentBuilder(context as Context)
            .setType("text/plain")
            .setChooserTitle(getString(R.string.share_text, user.login))
            .setText(
                """
                    ${user.name}
                    (S)he has ${user.publicRepos} repositories.
                    
                    Visit: ${user.htmlUrl}
                """.trimIndent()
            )
            .startChooser()
    }

}