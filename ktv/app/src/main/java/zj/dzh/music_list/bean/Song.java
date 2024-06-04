package zj.dzh.music_list.bean;

public class Song {
    private String songId;
    private String songName;
    private String songType;
    private String singer_Id;
    private String imgId;


    public void setSongId(String songId) {
        this.songId = songId;
    }


    public String getSongId() {
        return this.songId;
    }


    public void setSongName(String songName) {
        this.songName = songName;
    }


    public String getSongName() {
        return this.songName;
    }


    public void setSongType(String songType) {
        this.songType = songType;
    }


    public String getSongType() {
        return this.songType;
    }

    public void setSinger_Id(String singer_Id) {
        this.singer_Id = singer_Id;
    }

    public String getSinger_Id() {
        return this.singer_Id;
    }


    public void setImgId(String imgId) {
        this.imgId = imgId;
    }


    public String getImgId() {
        return this.imgId;
    }

}
