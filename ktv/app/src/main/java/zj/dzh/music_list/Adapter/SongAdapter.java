package zj.dzh.music_list.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zj.dzh.music_list.R;
import zj.dzh.music_list.bean.Song;

public class SongAdapter extends BaseAdapter {
    private List<Song> songList;
    private Context context;

    public SongAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public Object getItem(int i) {
        return songList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_search, null);
            viewHodler = new ViewHodler(view);
            viewHodler.songname = view.findViewById(R.id.songname);
            viewHodler.singer = view.findViewById(R.id.singer);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        viewHodler.songname.setText(songList.get(i).getSongName());
        viewHodler.singer.setText(songList.get(i).getSinger_Id());
        return view;
    }

    class ViewHodler {
        TextView songname, singer;
        public ViewHodler(View view) {
            songname = view.findViewById(R.id.songname);
            singer = view.findViewById(R.id.singer);
        }
    }
}
