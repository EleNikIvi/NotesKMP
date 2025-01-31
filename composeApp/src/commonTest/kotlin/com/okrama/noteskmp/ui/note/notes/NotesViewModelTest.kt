package com.okrama.noteskmp.ui.note.notes

import androidx.lifecycle.SavedStateHandle
import com.okrama.noteskmp.MainDispatcherTest
import com.okrama.noteskmp.categories
import com.okrama.noteskmp.categoryWithNotes
import com.okrama.noteskmp.domain.category.CategoryInteractor
import com.okrama.noteskmp.domain.note.NoteInteractor
import com.okrama.noteskmp.note1
import com.okrama.noteskmp.note2
import com.okrama.noteskmp.note3
import com.okrama.noteskmp.notes
import com.okrama.noteskmp.runViewModelTest
import com.okrama.noteskmp.ui.core.components.filterrail.model.FILTER_ALL
import com.okrama.noteskmp.ui.core.components.filterrail.model.FilterRailItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.flowOf
import kotlin.test.Test
import kotlin.test.assertEquals

private const val SEARCH = "1"
private const val SEARCH_KEY = "notes-view-model-search-key"
private val FILTER_RAIL_ALL = FILTER_ALL
private const val SELECTED_CATEGORY_KEY = "notes-view-model-selected-category-key"
private val filterRailItem1 = FILTER_RAIL_ALL
private val filterRailItem2 = FilterRailItem(id = 1, value = "Category 1")
private val filterRailItem3 = FilterRailItem(id = 2, value = "Category 2")
private val filterRailList = persistentListOf(filterRailItem1, filterRailItem2, filterRailItem3)

private val initialScreenState = NotesScreenState(
    notes = persistentListOf(
        note1, note2, note3
    ),
    categories = filterRailList,
)
val stateForSearch = NotesScreenState(
    notes = persistentListOf(note1),
    search = SEARCH,
    categories = filterRailList,
    selectedCategory = FILTER_RAIL_ALL
)

class NotesViewModelTest : MainDispatcherTest() {

    private val noteInteractorMock = mockk<NoteInteractor>(relaxed = true)
    {
        every {
            getAllNotesFlow()
        } returns flowOf(notes)

        coEvery {
            getNotesBy(any())
        } returns categoryWithNotes
    }
    private val categoryInteractorMock = mockk<CategoryInteractor>(relaxed = true)
    {
        every {
            getCategories()
        } returns flowOf(categories)
    }

    @Test
    fun `WHEN search changes THEN initial state is updated`() {
        val underTestVm = createViewModelInstance()
        runViewModelTest(underTestVm.screenState, underTestVm.sideEffect) { state, effect ->
            effect.expectNoEvents()
            assertEquals(initialScreenState, state.awaitItem())
            underTestVm.onSearchTermChange(SEARCH)

            assertEquals(stateForSearch, state.expectMostRecentItem())
        }
    }

    @Test
    fun `WHEN search is empty THEN state is updated to all notes`() {
        val underTestVm = createViewModelInstance()
        runViewModelTest(underTestVm.screenState, underTestVm.sideEffect) { state, effect ->
            effect.expectNoEvents()
            assertEquals(initialScreenState, state.awaitItem())
            underTestVm.onSearchTermChange(SEARCH)

            assertEquals(stateForSearch, state.awaitItem())

            underTestVm.onSearchFieldClear()

            assertEquals(initialScreenState, state.awaitItem())
        }
    }

    @Test
    fun `WHEN category changes THEN state is updated for this category`() {
        val expectedState = NotesScreenState(
            notes = persistentListOf(note1, note3),
            categories = filterRailList,
            selectedCategory = filterRailItem3,
        )

        val underTestVm = createViewModelInstance()
        runViewModelTest(underTestVm.screenState, underTestVm.sideEffect) { state, effect ->
            effect.expectNoEvents()
            assertEquals(initialScreenState, state.awaitItem())
            underTestVm.onCategoryChange(filterRailItem3.id)

            assertEquals(expectedState, state.expectMostRecentItem())
        }
    }

    @Test
    fun `WHEN onAddNoteSelected is called THEN sideEffect is emitted with NavigateToAddNoteScreen`() {

        val underTestVm = createViewModelInstance()
        runViewModelTest(underTestVm.screenState, underTestVm.sideEffect) { state, effect ->
            assertEquals(initialScreenState, state.expectMostRecentItem())
            underTestVm.onAddNoteSelected()

            assertEquals(NotesSideEffect.NavigateToAddNoteScreen, effect.awaitItem())
        }
    }

    @Test
    fun `WHEN onEditNoteSelected is called THEN sideEffect is emitted with NavigateToEditNoteScreen with noteId`() {
        val underTestVm = createViewModelInstance()

        runViewModelTest(underTestVm.screenState, underTestVm.sideEffect) { state, effect ->
            assertEquals(initialScreenState, state.expectMostRecentItem())
            val expectedNoteId = 1L
            underTestVm.onEditNoteSelected(expectedNoteId)

            assertEquals(
                NotesSideEffect.NavigateToEditNoteScreen(expectedNoteId),
                effect.expectMostRecentItem()
            )
        }
    }

    @Test
    fun `WHEN onNoteSelected is called THEN sideEffect is emitted with NavigateToNoteDetailsScreen with noteId`() {
        val underTestVm = createViewModelInstance()

        runViewModelTest(underTestVm.screenState, underTestVm.sideEffect) { state, effect ->
            assertEquals(initialScreenState, state.expectMostRecentItem())
            val expectedNoteId = 1L
            underTestVm.onNoteSelected(expectedNoteId)

            assertEquals(
                NotesSideEffect.NavigateToNoteDetailsScreen(expectedNoteId),
                effect.awaitItem()
            )
        }
    }

    @Test
    fun `WHEN onAddCategorySelected is called THEN sideEffect is emitted with NavigateToAddCategoryScreen`() {
        val underTestVm = createViewModelInstance()

        runViewModelTest(underTestVm.screenState, underTestVm.sideEffect) { state, effect ->
            assertEquals(initialScreenState, state.expectMostRecentItem())
            underTestVm.onAddCategorySelected()

            assertEquals(NotesSideEffect.NavigateToAddCategoryScreen, effect.awaitItem())
        }
    }

    @Test
    fun `WHEN ViewModel created with SavedStateHandle for for search THEN state should be restored`() {
        val savedStateHandle = SavedStateHandle(
            mapOf(SEARCH_KEY to SEARCH)
        )
        val underTestVm = createViewModelInstance(
            savedStateHandle
        )

        runViewModelTest(underTestVm.screenState, underTestVm.sideEffect) { state, effect ->
            effect.expectNoEvents()
            assertEquals(SEARCH, savedStateHandle.get<String>(SEARCH_KEY))

            testScheduler.advanceUntilIdle()

            val expectedState = NotesScreenState(
                notes = persistentListOf(note1),
                search = SEARCH,
                categories = filterRailList,
            )

            assertEquals(expectedState, state.awaitItem())
        }
    }

    @Test
    fun `WHEN ViewModel created with SavedStateHandle for for category THEN state should be restored`() {
        val savedStateHandle = SavedStateHandle(
            mapOf(SELECTED_CATEGORY_KEY to filterRailItem3)
        )
        val underTestVm = createViewModelInstance(
            savedStateHandle
        )

        runViewModelTest(underTestVm.screenState, underTestVm.sideEffect) { state, effect ->
            effect.expectNoEvents()
            assertEquals(
                filterRailItem3,
                savedStateHandle.get<FilterRailItem>(SELECTED_CATEGORY_KEY)
            )
            assertEquals(null, savedStateHandle.get<String>(SEARCH_KEY))

            testScheduler.advanceUntilIdle()

            val expectedState = NotesScreenState(
                notes = persistentListOf(note1, note3),
                categories = filterRailList,
                selectedCategory = filterRailItem3,
            )

            assertEquals(expectedState, state.awaitItem())
        }
    }


    private fun createViewModelInstance(savedStateHandle: SavedStateHandle = SavedStateHandle()): NotesViewModel {
        return NotesViewModel(
            noteInteractor = noteInteractorMock,
            categoryInteractor = categoryInteractorMock,
            savedStateHandle = savedStateHandle
        )
    }
}