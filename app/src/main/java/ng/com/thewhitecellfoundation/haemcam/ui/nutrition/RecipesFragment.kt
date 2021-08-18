package ng.com.thewhitecellfoundation.haemcam.ui.nutrition

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ng.com.thewhitecellfoundation.common.utils.viewBinding
import ng.com.thewhitecellfoundation.haemcam.R
import ng.com.thewhitecellfoundation.haemcam.databinding.FragmentRecipesBinding
import ng.com.thewhitecellfoundation.haemcam.model.StringItemData
import ng.com.thewhitecellfoundation.haemcam.ui.adapter.recipiesListView
import ng.com.thewhitecellfoundation.haemcam.ui.home.HomeBaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [RecipesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipesFragment : HomeBaseFragment(R.layout.fragment_recipes) {
    private val binding by viewBinding(FragmentRecipesBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfNutritionMenu = arrayListOf(
            StringItemData("1 1⁄2 pound beef, sirloin steak"),
            StringItemData("3 tablespoons all-purpose flour"),
            StringItemData("1 1⁄2 teaspoon salt"),
            StringItemData("1⁄4 teaspoon black pepper 1 teaspoon paprika")
        )

        binding.recipeErcv.withModels {
            listOfNutritionMenu.forEach { sid ->
                recipiesListView {
                    id(sid.id)
                    data(sid)
//                    onClick { _, _, _, _ ->
//                        navigator.goto(R.id.recipesFragment)
//                    }
                }
            }
        }
    }
}
