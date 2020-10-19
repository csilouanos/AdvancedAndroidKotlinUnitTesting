package com.example.android.architecture.blueprints.todoapp.taskdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.tasks.TasksViewModel

/**
 * Created by SNicolaou on 19-10-2020
 */
class TaskDetailViewModelFactory(private val tasksRepository: TasksRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = (TaskDetailViewModel(tasksRepository) as T)
}