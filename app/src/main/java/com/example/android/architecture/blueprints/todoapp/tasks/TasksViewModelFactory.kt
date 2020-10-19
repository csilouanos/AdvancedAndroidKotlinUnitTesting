package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

/**
 * Created by SNicolaou on 19-10-2020
 */
class TasksViewModelFactory(private val tasksRepository: TasksRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = (TasksViewModel(tasksRepository) as T)

}