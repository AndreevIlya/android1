package lifeCycle;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity {

    private boolean fragmentOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
        }else
            instanceState = "Повторный запуск!";

        Log.i("INFO ACTIVITY",instanceState + "onCreate().");
        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();

        Fragment fragment1 = new Fragment1();

        Button buttonOn = findViewById(R.id.buttonOn);
        buttonOn.setOnClickListener(new ListenerOnAdd(fragment1));
        Button buttonOff = findViewById(R.id.buttonOff);
        buttonOff.setOnClickListener(new ListenerOnRemove(fragment1));

    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i("INFO ACTIVITY","onSaveInstanceState().");
        Toast.makeText(getApplicationContext(), "Activity  onSaveInstanceState()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Log.i("INFO ACTIVITY","onSaveInstanceState().");
        Toast.makeText(getApplicationContext(), "Activity  Повторный запуск!! - onRestoreInstanceState()",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("INFO ACTIVITY","onStart().");
        Toast.makeText(getApplicationContext(), "Activity onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("INFO ACTIVITY","onResume().");
        Toast.makeText(getApplicationContext(), "Activity onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("INFO ACTIVITY","onPause().");
        Toast.makeText(getApplicationContext(), "Activity onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("INFO ACTIVITY","onStop().");
        Toast.makeText(getApplicationContext(), "Activity onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("INFO ACTIVITY","onRestart().");
        Toast.makeText(getApplicationContext(), "Activity onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("INFO ACTIVITY","onDestroy().");
        Toast.makeText(getApplicationContext(), "Activity onDestroy()", Toast.LENGTH_SHORT).show();
    }

    //Inner classes from methodics to manage fragments. Dunno if they all are needed.
    class ListenerOnAdd implements View.OnClickListener{

        Fragment fragment;

        ListenerOnAdd(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            addFragment();
        }
        // Добавить фрагмент
        private void addFragment(){
            if(!fragmentOn) {
                // Открыть транзакцию
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                // Добавить фрагмент
                fragmentTransaction.add(R.id.fragment_container, fragment);
                // Закрыть транзакцию
                fragmentTransaction.commit();
                fragmentOn = true;
            }
        }
    }

    class ListenerOnRemove implements View.OnClickListener{

        Fragment fragment;

        ListenerOnRemove(Fragment fragment){
            this.fragment = fragment;
        }
        @Override
        public void onClick(View v) {
            removeFragment();
        }
        // Удалить фрагмент
        private void removeFragment(){
            if(fragmentOn) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.remove(fragment);
                fragmentTransaction.commit();
                fragmentOn = false;
            }
        }
    }

    class ListenerOnReplace implements View.OnClickListener{

        Fragment fragment;

        ListenerOnReplace(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            replaceFragment();
        }
        // Заменить фрагмент
        private void replaceFragment(){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }
}

