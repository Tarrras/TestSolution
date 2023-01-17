import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.youarelaunched.challenge.MainActivity
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.VendorsScreen
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VendorsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun emptyDataMessageVisibleTest() {
        composeTestRule.activity.setContent {
            VendorAppTheme {
                VendorsScreen(
                    uiState = VendorsScreenUiState(vendors = emptyList(), query = "12"),
                    onQueryChanged = {}
                )
            }
        }
        val emptyDataTitle = composeTestRule.activity.getString(R.string.msg_empty_data_title)
        val emptyDataSubtitle = composeTestRule.activity.getString(R.string.msg_empty_data_subtitle)
        composeTestRule.onNodeWithText(emptyDataTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(emptyDataSubtitle).assertIsDisplayed()
    }

    @Test
    fun oneItemVisibleTest() {
        val mockedVendor = Vendor(
            id = 1,
            companyName = "company",
            coverPhoto = "",
            area = "area",
            favorite = false,
            categories = null,
            tags = null
        )
        composeTestRule.activity.setContent {
            VendorAppTheme {
                VendorsScreen(
                    uiState = VendorsScreenUiState(
                        vendors = listOf(mockedVendor),
                        query = null
                    ), onQueryChanged = {}
                )
            }
        }
        composeTestRule.onNodeWithText(mockedVendor.companyName).assertIsDisplayed()
    }
}