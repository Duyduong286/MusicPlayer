package com.example.musicplayer.Service;



import com.example.musicplayer.databaseAPI.Album;
import com.example.musicplayer.databaseAPI.Baihat;
import com.example.musicplayer.databaseAPI.Playlist;
import com.example.musicplayer.databaseAPI.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songBanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistDay();

    @GET("album.php")
    Call<List<Album>> GetAlbum();

    @GET("baihatduocthich.php")
    Call<List<Baihat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoQuangCao(@Field("id_ads") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoPlaylist(@Field("id_playlist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhsachPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("likesSong") String likesSong, @Field("idSong") String idSong);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> SearchBaiHat(@Field("tukhoa") String tukhoa);

/*


    @GET("chudevatheloai.php")
    Call<ChuDeTheLoai> GetChuDeDay();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoAlbum(@Field("idalbum") String idalbum);



    @GET("tatcachude.php")
    Call<List<ChuDe>> GetDanhsachChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAlbum();

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> SearchBaiHat(@Field("tukhoa") String tukhoa);

 */


}
