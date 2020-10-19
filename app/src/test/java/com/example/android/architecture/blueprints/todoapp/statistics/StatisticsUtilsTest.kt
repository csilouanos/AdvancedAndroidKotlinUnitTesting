package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Test
import java.util.regex.Matcher
import com.google.common.truth.Truth;
// for assertions on Java 8 types (Streams and java.util.Optional)
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage

// for assertions on Java 8 types (Streams and java.util.Optional)
//import com.google.common.truth.Truth8.assertThat


class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        //Given
        //Create an active task
        val tasks = listOf(
                Task("title", "desc", false)
        )

        //When
        //Call your function
        val result = getActiveAndCompletedStats(tasks)

        //Then
        //Check the result
        assertThat(result.completedTasksPercent).isEqualTo(0f)
        assertThat(result.activeTasksPercent).isEqualTo(100f)
    }

    @Test
    fun getActiveAndCompletedStats_TwoSixtyTasks_returnsFortyFifty() {
        //Given

        val tasks = listOf(
                Task("Completed 1", "very completed", true),
                Task("Completed 2", "very completed 2", true),
                Task("Incomplete 1", "very incompleted", false),
                Task("Incomplete 2", "very incompleted 2", false),
                Task("Incomplete 3", "very incompleted 3", false)
        )

        //When
        val result = getActiveAndCompletedStats(tasks)

        //Then
        assertThat(result.completedTasksPercent).isEqualTo(40f)
        assertThat(result.activeTasksPercent).isEqualTo(60f)
    }
    @Test
    fun getActiveAndCompletedStats_ListEmptyOrNull_returnsZero() {
        //Given
        val tasks = emptyList<Task>()

        //When
        val result = getActiveAndCompletedStats(tasks)

        //Then
        assertThat(result.activeTasksPercent).isEqualTo(0f)
        assertThat(result.completedTasksPercent).isEqualTo(0f)
    }
}