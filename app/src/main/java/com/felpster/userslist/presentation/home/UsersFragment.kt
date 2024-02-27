package com.felpster.userslist.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.felpster.userslist.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment() {
    private val viewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireActivity()).apply {
            setContent {
                AppTheme {
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    UsersScreen(
                        viewState = state,
                        onEvent = viewModel::onEvent,
                    )
                }
            }
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigationEvents.collectLatest { navigationEvent ->
                    when (navigationEvent) {
                        is NavigationEvent.NavigateToUserDetails -> navigateToUserDetails(navigationEvent)
                    }
                }
            }
        }
    }

    private fun navigateToUserDetails(navigationEvent: NavigationEvent.NavigateToUserDetails) {
        Toast.makeText(requireContext(), "Display details for ${navigationEvent.user.name}", Toast.LENGTH_LONG).show()
    }
}
