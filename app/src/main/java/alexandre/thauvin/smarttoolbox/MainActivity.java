package alexandre.thauvin.smarttoolbox;



import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    private List<String> tmp;
    private List<Task>   tasks;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getApplicationContext().getSharedPreferences("tmp", MODE_PRIVATE);
        if (sharedPreferences.getStringSet("tmp", null) != null) {
            tmp = new ArrayList<>(sharedPreferences.getStringSet("tmp", null));
            parseTasks(tmp);
        }
        else {
            tmp = new ArrayList<>();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void parseTasks(List<String> list)
    {

    }

    public List<String> getTmp() {
        return tmp;
    }

    public void updateListOfTasks(String s){
        Set<String> set = new HashSet<>(tmp);
        sharedPreferences.edit().putStringSet("tmp", set).apply();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
