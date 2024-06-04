package zj.dzh.music_list.Page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import java.util.List;

import zj.dzh.music_list.Adapter.SongAdapter;
import zj.dzh.music_list.R;
import zj.dzh.music_list.bean.Song;

public class SearchPage extends Fragment {
    private View view;
    private List<Song> songList;
    private ListView searchView;
    private SongAdapter songAdapter;

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.search_music,null);
        searchView = view.findViewById(R.id.search_view);
        songAdapter = new SongAdapter(getContext(),songList);
        searchView.setAdapter(songAdapter);
        return view;
    }

}
