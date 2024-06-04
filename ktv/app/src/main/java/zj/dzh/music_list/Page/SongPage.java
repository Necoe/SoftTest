package zj.dzh.music_list.Page;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import zj.dzh.music_list.Music.MusicActivity;
import zj.dzh.music_list.R;
import zj.dzh.music_list.bean.MusicFile;
import zj.dzh.music_list.constant.MusicList;
import zj.dzh.music_list.constant.TAG;

public class SongPage extends Fragment {
    private List<MusicFile> musics;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //声明视图变量view
        View view = inflater.inflate(R.layout.music_list, null);
        //1、创建并绑定列表
        ListView listView = view.findViewById(R.id.lv);
        //2、创建适配器对象
        MyBaseAdapter adapter = new MyBaseAdapter();
        //3、给列表设置适配器
        listView.setAdapter(adapter);
        //设置列表条目监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG.id, "onItemClick: " + position + " " + id);
                //创建Intent对象，启动音乐播放界面
                Intent intent = new Intent(SongPage.this.getContext(), MusicActivity.class);
                intent.putExtra("id", String.valueOf(position));
                //进行跳转
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        musics = MusicList.musicFiles;
    }

    class MyBaseAdapter extends BaseAdapter {
        private final List<MusicFile> musicFiles = MusicList.musicFiles;
        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(SongPage.this.getContext()).inflate(R.layout.item_music, parent, false);
            }
            TextView songName = convertView.findViewById(R.id.song_name);
            ImageView songPic = convertView.findViewById(R.id.song_pic);

            MusicFile musicFile = musicFiles.get(i);
            // 设置文字和图片
            songName.setText(musicFile.getTitle() + " - " + musicFile.getArtist());
            songPic.setImageResource(MusicList.musicPic.get(i%MusicList.musicPic.size()));
            return convertView;
        }


        @Override
        public int getCount() {
            return musicFiles.size();
        }

        @Override
        public Object getItem(int position) {
            return musicFiles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return musicFiles.get(position).getId();
        }
    }


}
