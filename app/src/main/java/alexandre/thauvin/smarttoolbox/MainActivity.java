package alexandre.thauvin.smarttoolbox;



import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, ListFragment.OnListFragmentInteractionListener {
    private List<String> tmp;
    private List<Task>   tasks;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.baseline_home_black_18dp, R.color.black);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.list, R.drawable.baseline_assignment_black_18dp, R.color.black);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        bottomNavigation.setForceTint(true);
        sharedPreferences = getApplicationContext().getSharedPreferences("tmp", MODE_PRIVATE);
        if (sharedPreferences.getStringSet("tmp", null) != null) {
            tmp = new ArrayList<>(sharedPreferences.getStringSet("tmp", null));
            parseTasks(tmp);
        }
        else {
            tmp = new ArrayList<>();
        }
        showHomeFragment();

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 1) {
                    showListFragment();
                }
                else {
                    showHomeFragment();
                }
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void parseTasks(List<String> list) {

        for (String s: list){
            Task task = new Task();
            String[] array = s.split("/");
            task.setAction(array[0]);
            task.setService(array[1]);
            task.setService(array[2]);
            tasks.add(task);
        }
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

    private void showHomeFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_layout, new HomeFragment()).commit();


    }

    private void showListFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_layout, new ListFragment()).commit();

    }

    public void onFragmentInteraction(){

    }

    public void onListFragmentInteraction(String item){

    }

}
