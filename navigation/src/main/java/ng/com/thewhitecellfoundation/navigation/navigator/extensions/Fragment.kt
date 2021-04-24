package ng.com.thewhitecellfoundation.navigation.navigator.extensions

import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.navigation.navigator.Navigator

val Fragment.navigator: Navigator get() = requireActivity().navigator
