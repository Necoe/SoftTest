package zj.dzh.music_list.Music;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import zj.dzh.music_list.Activity.MainActivity;
import zj.dzh.music_list.R;
import zj.dzh.music_list.bean.MusicFile;
import zj.dzh.music_list.constant.MusicList;
import zj.dzh.music_list.constant.TAG;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    private ObjectAnimator animator;//旋转动画
    private TextView song_name;//歌曲名控件
    private Button pause;       //暂停按钮
    private Button con;         //播放按钮
    private Button pre;         //上一首按钮
    private Button next;        //下一首按钮
    private ImageView exit;     //返回图片
    private ImageView iv_music; //歌手图片框

    // 当前音乐的id
    private int currentMusicId = 0;
    // 音乐总数
    private final int music_num = MusicList.musicFiles.size();
    // 播放器
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //从上一个页面获得id
        Intent intent = getIntent();
        String str = intent.getStringExtra("id");
        Log.d(TAG.id, "获取id: " + str);
        if (str != null) {
            currentMusicId = Integer.parseInt(str);
        }

        //初始化
        initView();
        playMusic(currentMusicId);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        song_name = findViewById(R.id.song_name);
        iv_music = findViewById(R.id.iv_music);
        pause = findViewById(R.id.btn_pause);
        con = findViewById(R.id.btn_continue_play);
        pre = findViewById(R.id.btn_pre);
        next = findViewById(R.id.btn_next);
        exit = findViewById(R.id.btn_exit);
        //设置单击监听器
        pause.setOnClickListener(this);
        con.setOnClickListener(this);
        pre.setOnClickListener(this);
        next.setOnClickListener(this);
        exit.setOnClickListener(this);
        iv_music.setImageResource(MusicList.musicPic.get(currentMusicId % MusicList.musicPic.size()));
        // 设置歌手图片框旋转动画
        animator = ObjectAnimator.ofFloat(iv_music, "rotation", 0f, 360.0f);
        animator.setDuration(10000);//动画旋转一周的时间为10秒
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setRepeatCount(-1);//-1表示设置动画无限循环
    }

    @Override
    public void onClick(View v) {
        // 根据按钮id执行相应操作
        if (v.getId() == R.id.btn_pre) {
            Log.d(TAG.tag, "onClick: 上一首");
            playOrPause(true);
            currentMusicId = (currentMusicId - 1 + music_num) % music_num;
            playMusic(currentMusicId);
        } else if (v.getId() == R.id.btn_next) {
            Log.d(TAG.tag, "onClick: 下一首");
            playOrPause(true);
            playMusic((++currentMusicId) % music_num);
        } else if (v.getId() == R.id.btn_pause) {
            playOrPause(true);
            // 暂停音乐
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        } else if (v.getId() == R.id.btn_continue_play) {
            playOrPause(false);
            // 继续播放音乐
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        } else if (v.getId() == R.id.btn_exit) {
            mediaPlayer.stop();
            mediaPlayer.release();
            startActivity(new Intent(MusicActivity.this, MainActivity.class));
        }

    }

    /**
     * 播放音乐
     *
     * @param i 音乐id
     */
    public void playMusic(int i) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        // 设置旋转图片
        int size = MusicList.musicPic.size();
        iv_music.setImageResource(MusicList.musicPic.get((currentMusicId + size) % size));

        // 创建一个新的MediaPlayer对象
        mediaPlayer = new MediaPlayer();
        try {
            // 从本地音乐文件路径加载音乐
            MusicFile music = MusicList.musicFiles.get(i);
            String musicFilePath = music.getData();
            mediaPlayer.setDataSource(musicFilePath);
            song_name.setText(String.format("%s - %s", music.getTitle(), music.getArtist()));
            // 准备并开始播放音乐
            Log.d("musicinfo", "开始播放第" + i + "首音乐: " + music);
            playOrPause(false);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playMusic(++currentMusicId % music_num);
                }
            });
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 界面组件开始或暂停
     *
     * @param isPlay 是否播放
     */
    public void playOrPause(boolean isPlay) {
        if (isPlay) {
            pause.setVisibility(View.INVISIBLE);
            con.setVisibility(View.VISIBLE);
            animator.pause();
        } else {
            pause.setVisibility(View.VISIBLE);
            con.setVisibility(View.INVISIBLE);
            animator.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}


