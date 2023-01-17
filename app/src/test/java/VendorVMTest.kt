import com.youarelaunched.challenge.data.repository.VendorsRepositoryImpl
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.view.VendorsVM
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock

class VendorVMTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `getVendors() was loaded successfully and the list contains at least one item`() = runTest {
        val mockedVendor = Vendor(
            id = 1,
            companyName = "company",
            coverPhoto = "",
            area = "area",
            favorite = false,
            categories = null,
            tags = null
        )
        val repositoryMock = mock<VendorsRepositoryImpl> {
            onBlocking { getVendors() }.doReturn(
                listOf(mockedVendor)
            )
        }
        val viewModel = VendorsVM(repositoryMock)
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect()
        }

        viewModel.getVendors()
        delay(500)
        assertEquals(viewModel.uiState.value.vendors, listOf(mockedVendor))

        collectJob.cancel()
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `getVendors() was loaded with error`() = runTest {
        val repositoryMock = mock<VendorsRepositoryImpl> {
            onBlocking { getVendors() }.doThrow(IllegalStateException())
        }
        val viewModel = VendorsVM(repositoryMock)
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect()
        }

        viewModel.getVendors()
        delay(500)
        assertEquals(viewModel.uiState.value.vendors, null)

        collectJob.cancel()
    }
}