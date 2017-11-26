package com.example.skadush.mvp_todo_app;

import com.example.skadush.mvp_todo_app.Model.Task;
import com.example.skadush.mvp_todo_app.Presenter.IFragmentPresenter;
import com.example.skadush.mvp_todo_app.Presenter.IPresenter;
import com.example.skadush.mvp_todo_app.Presenter.TaskFragmentPresenter;
import com.example.skadush.mvp_todo_app.Presenter.TaskPresenter;
import com.example.skadush.mvp_todo_app.Repository.ITaskInteractor;
import com.example.skadush.mvp_todo_app.View.ITaskAcitivtyView;
import com.example.skadush.mvp_todo_app.View.ITaskFragmentView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Mock
    ITaskAcitivtyView taskActivityView;


    @Mock
    ITaskFragmentView taskFragmentView;

    @Mock
    ITaskInteractor taskRepository;


    @Mock
    IPresenter taskPresenter;

    @Mock
    IFragmentPresenter fragmentPresenter;

    @Before
    public void setUp()throws  Exception{
        taskPresenter =  spy(new TaskPresenter(taskActivityView,taskRepository));
        fragmentPresenter  = spy(new TaskFragmentPresenter(taskFragmentView,taskRepository));

    }

    @Test
    public void shouldSave(){

        Task task = new Task();
        when(taskRepository.SaveTask(task)).thenReturn(true);
        taskPresenter.CreateTask(task);
        verify(taskActivityView).refreshList();

    }

    @Test
    public void getAllTask(){
        List<Task> TASKS = Arrays.asList(new Task(),new Task(), new Task());
        when(taskRepository.getAllTaskFromRealm()).thenReturn(TASKS);
        fragmentPresenter.loadTasks();
        verify(taskFragmentView).displayAllTask(TASKS);

    }
}