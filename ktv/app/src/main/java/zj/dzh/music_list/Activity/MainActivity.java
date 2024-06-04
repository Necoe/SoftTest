package zj.dzh.music_list.Activity;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import zj.dzh.music_list.Page.SingerPage;
import zj.dzh.music_list.Page.SongPage;
import zj.dzh.music_list.R;
import zj.dzh.music_list.bean.MusicFile;
import zj.dzh.music_list.constant.MusicList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView menu1, menu2;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ActivityResultLauncher<String> requestPermissionLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去除标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //绑定控件
        menu1 = findViewById(R.id.menu1);
        menu2 = findViewById(R.id.menu2);
        //设置监听器
        menu1.setOnClickListener(this);
        menu2.setOnClickListener(this);
        //获得布局管理器
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        //默认情况下显示frag1（音乐列表界面）
        ft.replace(R.id.content, new SongPage());
        ft.commit();


        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                accessMusicFiles();
            } else {
                Toast.makeText(this, "权限被拒绝！", Toast.LENGTH_SHORT).show();
            }
        });

        // 请求存储权限
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_MEDIA_AUDIO") != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch("android.permission.READ_MEDIA_AUDIO");
        } else {
            MusicList.musicFiles = accessMusicFiles();
        }

        // 初始化图片信息
        initPic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onClick(menu1);
    }

    @Override
    //点击切换歌曲和歌手页面
    public void onClick(View v) {
        ft = fm.beginTransaction();
        // 绑定事件
        if (v.getId() == R.id.menu1) {
            //如果是menu1，则音乐列表界面frag1替换content
            ft.replace(R.id.content, new SongPage());
            MusicList.musicFiles = accessMusicFiles();
        } else if (v.getId() == R.id.menu2) {
            //如果是menu2，则歌手界面frag2替换content
            ft.replace(R.id.content, new SingerPage());
        }
        ft.commit();
    }


    // 初始化图片列表
    public void initPic() {
        List<Integer> picList = new ArrayList<>();

        Collections.addAll(picList,
                R.drawable.music0,
                R.drawable.music1,
                R.drawable.music2,
                R.drawable.music3,
                R.drawable.music4,
                R.drawable.music5,
                R.drawable.music6);
        MusicList.musicPic = picList;
    }

    private List<MusicFile> accessMusicFiles() {
        List<MusicFile> musicFiles = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA
        };

        // 查询媒体库音乐文件
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                MusicFile musicFile = new MusicFile(id, title, artist, album, duration, path);
                musicFiles.add(musicFile);
            }
            cursor.close();
        }

        Toast.makeText(this, "共扫描到" + musicFiles.size() + "首音乐", Toast.LENGTH_SHORT).show();
        musicFiles.forEach(System.out::println);
        return musicFiles;
    }
}
