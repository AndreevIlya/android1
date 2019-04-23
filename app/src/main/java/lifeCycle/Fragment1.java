package lifeCycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment1 extends Fragment {
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i("FRAGMENT INFO","onCreate().");
        Toast.makeText(getActivity(), "Fragment onCreate()", Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        Log.i("FRAGMENT INFO","onCreateView().");
        Toast.makeText(getActivity(), "Fragment onCreateView()", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.i("FRAGMENT INFO","onActivityCreated().");
        Toast.makeText(getActivity(), "Fragment onActivityCreated()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("FRAGMENT INFO","onStart().");
        Toast.makeText(getActivity(), "Fragment onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("FRAGMENT INFO","onResume().");
        Toast.makeText(getActivity(), "Fragment onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("FRAGMENT INFO","onPause().");
        Toast.makeText(getActivity(), "Fragment onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("FRAGMENT INFO","onStopp().");
        Toast.makeText(getActivity(), "Fragment onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.i("FRAGMENT INFO","onDestroyView().");
        Toast.makeText(getActivity(), "Fragment onDestroyView()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Log.i("FRAGMENT INFO","onDetach().");
        Toast.makeText(getActivity(), "Fragment onDetach()", Toast.LENGTH_SHORT).show();
    }
}

