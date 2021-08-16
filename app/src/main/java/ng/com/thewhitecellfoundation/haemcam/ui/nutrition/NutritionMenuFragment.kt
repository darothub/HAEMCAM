package ng.com.thewhitecellfoundation.haemcam.ui.nutrition

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentNutritionMenuBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.nutritionMenuView
import ng.com.thewhitecellfoundation.haemcam.ui.home.HomeBaseFragment
import ng.com.thewhitecellfoundation.navigation.navigator.extensions.navigator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NutritionMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NutritionMenuFragment : HomeBaseFragment(R.layout.fragment_nutrition_menu) {
    private val binding by viewBinding(FragmentNutritionMenuBinding::bind)
    override val toolLeftImageDrawable: Int?
        get() = R.drawable.ic_baseline_keyboard_backspace_24

    override val toolBarTitle: String?
        get() = getString(R.string.nutrition)
    override val leftAction: () -> Unit
        get() = {
            navigator.navController.popBackStack()
        }
    override val toolRightImageDrawable: Int
        get() = R.drawable.ic_settings_icon
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfNutritionMenu = arrayListOf(
            StringItemData("Recipes"),
            StringItemData("Meal planner"),
            StringItemData("Hydration status"),
            StringItemData("Grocery list")
        )

        binding.ercv.withModels {
            listOfNutritionMenu.forEach { sid ->
                nutritionMenuView {
                    id(sid.id)
                    data(sid)
                    onClick { _, _, _, _ ->
                        navigator.goto(R.id.recipesFragment)
                    }
                }
            }
        }
    }
}
