package com.example.android.architecture.blueprints.todoapp.data.source

import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultTasksRepositoryTest {

    private val task1 = Task("Title1", "Description1")
    private val task2 = Task("Title2", "Description2")
    private val task3 = Task("Title3", "Description3")
    private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }

    private lateinit var tasksRemoteDataSource: FakeDataSource
    private lateinit var tasksLocalDataSource: FakeDataSource

    // Class under test
    private lateinit var tasksRepository: DefaultTasksRepository

    @Before
    fun createRepository() {
        tasksRemoteDataSource = FakeDataSource(remoteTasks.toMutableList())
        tasksLocalDataSource = FakeDataSource(localTasks.toMutableList())
        // TODO Dispatchers.Unconfined should be replaced with Dispatchers.Main
        //  this requires understanding more about coroutines + testing
        //  so we will keep this as Unconfined for now.
        tasksRepository = DefaultTasksRepository(tasksRemoteDataSource, tasksLocalDataSource, Dispatchers.Unconfined)
    }

    /**
     * Checks if getTasks returns all the remote tasks.
     */
    @Test
    fun getTasks_requestsAllTasksFromRemoteDataSource() {
        runBlockingTest {
            val tasks = tasksRepository.getTasks(true) as Result.Success
            Truth.assertThat(tasks.data).isEqualTo(remoteTasks)
        }
    }
}