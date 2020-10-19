package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule

//Not need to use RunWith because we are not using any AndroidX test such as getApplicationContext.
@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    private lateinit var viewModel: TasksViewModel
    private lateinit var tasksRepository: FakeTestRepository

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)
        viewModel = TasksViewModel(tasksRepository)
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {
        //Given

        // When adding a new task
        viewModel.addNewTask()

        //When
        val value = viewModel.newTaskEvent.getOrAwaitValue()

        //Then
        assertThat(value.getContentIfNotHandled()).isNotNull()
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {
        // Given a fresh ViewModel

        // When the filter type is ALL_TASKS
        viewModel.setFiltering(TasksFilterType.ALL_TASKS)
        val value = viewModel.tasksAddViewVisible.getOrAwaitValue()

        // Then the "Add task" action is visible
        assertThat(value).isTrue()
    }
}