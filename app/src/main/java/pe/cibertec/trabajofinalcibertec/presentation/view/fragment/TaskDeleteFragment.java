package pe.cibertec.trabajofinalcibertec.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.cibertec.trabajofinalcibertec.R;
import pe.cibertec.trabajofinalcibertec.domain.model.Tasks;
import pe.cibertec.trabajofinalcibertec.presentation.TasksView;
import pe.cibertec.trabajofinalcibertec.presentation.model.TaskModel;
import pe.cibertec.trabajofinalcibertec.presentation.presenter.TasksPresenter;
import pe.cibertec.trabajofinalcibertec.presentation.presenter.UsersPresenter;

/**
 * Created by USUARIO on 10/06/2017.
 */

public class TaskDeleteFragment extends Fragment
        implements View.OnClickListener,TasksView {

    private TaskModel taskModel;
   static Context context;
    String user_token;
    String ARG_TASK= "tasksModel" ;

    @BindView(R.id.edtId)
    EditText edtId;

    @BindView(R.id.edtTitle)
    EditText edtTitle;

    @BindView(R.id.swRemember)
    Switch swRemember;

    @BindView(R.id.edtDate)
    EditText edtDate;

    @BindView(R.id.btnDelete)
    Button btnDelete;

    @BindView(R.id.btnCancel)
    Button btnCancel;

    private Unbinder unbinder;

    private TasksPresenter taskPresenter;


    public TaskDeleteFragment newInstance(String user_token, TaskModel tasksModel, Context context){
       this.context = context;
        this.user_token = user_token;
        TaskDeleteFragment tf = new TaskDeleteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TASK,tasksModel);
        tf.setArguments(args);

       return tf;
   }

    public TaskDeleteFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            taskModel = getArguments().getParcelable(ARG_TASK);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmenet_task_delete,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        if (savedInstanceState == null){
            taskPresenter = new TasksPresenter(this);
        }
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDelete :
                TaskModel taskModel= new TaskModel();
                taskModel.setId(Long.parseLong(edtId.getText().toString()));
               taskPresenter.eliminar(user_token,taskModel);

                break;

            case R.id.btnCancel :
                FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
                fragmentManager.replace(android.R.id.content, TaskListFragment.newInstance(null));
                fragmentManager.commit();
            default :
                break;
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public Context context() {

        return getContext();
    }

    @Override
    public void getTasks() {

    }

    @Override
    public void renderTasks(List<TaskModel> taskModelList) {

    }
}
