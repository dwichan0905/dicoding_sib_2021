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
import id.dwichan.githubbook.data.entity.User
import id.dwichan.githubbook.databinding.BottomSheetOptionsBinding

class OptionsBottomSheet : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "OptionsBottomFragment"
        const val EXTRA_USER = "extra_user"
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
            val user = requireArguments().getParcelable<User>(EXTRA_USER) as User

            binding.listOptions.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = OptionsAdapter(getListOptions(user))
            }
        }
    }

    private fun getListOptions(user: User): List<Option> {
        return listOf(
            Option(
                icon = R.drawable.github_mark,
                title = getString(R.string.visit_link, user.username),
                showNextIndicator = true,
                onClick = {
                    visitLink(user.username)
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

    private fun visitLink(username: String? = "") {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://github.com/$username")
        startActivity(intent)
    }

    private fun shareUser(user: User) {
        ShareCompat.IntentBuilder(context as Context)
            .setType("text/plain")
            .setChooserTitle(getString(R.string.share_text, user.username))
            .setText(
                """
                    ${user.name}
                    (S)he has ${user.repository} repositories.
                    
                    Visit: https://github.com/${user.username}
                """.trimIndent()
            )
            .startChooser()
    }

}