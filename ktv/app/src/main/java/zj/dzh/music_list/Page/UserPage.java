package zj.dzh.music_list.Page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import zj.dzh.music_list.R;

public class UserPage extends Fragment {


    View Userview;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        Userview=inflater.inflate(R.layout.activity_user, null);

        return Userview;

    }


}
