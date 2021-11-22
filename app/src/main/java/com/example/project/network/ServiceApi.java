package com.example.project.network;

import com.example.project.data.RegionDetailData;
import com.example.project.data.board.CommentData;
import com.example.project.data.board.PostData;
import com.example.project.data.user.JoinData;
import com.example.project.data.board.PostRegData;
import com.example.project.data.user.UpdateData;
import com.example.project.data.user.UserData;
import com.example.project.response.board.CommentResponse;
import com.example.project.data.user.LoginData;
import com.example.project.response.board.ListupResponse;
import com.example.project.response.user.LoginResponse;
import com.example.project.data.QrData;
import com.example.project.response.board.PostResponse;
import com.example.project.response.QrResponse;
import com.example.project.data.RegionData;
import com.example.project.response.RegionResponse;
import com.example.project.response.SubregionResponse;
import com.example.project.response.user.UserinfoResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {

    //로그인
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    //회원가입
    @POST("/user/join")
    Call<UserinfoResponse> userJoin(@Body JoinData data);

    //업데이트
    @PUT("/user/update")
    Call<UserinfoResponse> userInfoUpdate(@Body UpdateData data);

    //탈퇴
    @DELETE("/user/exit/{email}")
    Call<ResponseBody> userExit(@Path("email") String email);

    //정보 호출
    @GET("/user/info")
    Call<LoginResponse> getCurrentInfo(@Query("email") String email);



    // QR
    @POST("/qr/addpoint")
    Call<QrResponse> qrSuccess(@Body QrData data);


    //지도 지역
    @POST("/map/regions")
    Call<RegionResponse> callRegionList();

    @POST("/map/subRegions")
    Call<SubregionResponse> callSubregionList(@Body RegionData data);



    //게시판
    @GET("/board")
    Call<ListupResponse> ListUP(@Query("type") String type);

    //게시판목록 누른 글 불러오기
    @GET("/board/posts")
    Call<PostResponse> viewPOST(@Query("id") int post_id);

    //게시판 검색
    @GET("/board/search")
    Call<PostResponse> searchPOST(@Query("type") String type, @Query("text") String text);

    //글 댓글 불러오기
    @GET("/board/posts/comment")
    Call<CommentResponse> commentUpdate(@Query("post_id") int post_id);

    //글 작성하기
    @POST("/board/write")
    Call<ResponseBody> writePOST(@Body PostRegData data);

    //댓글 작성하기
    @POST("/board/posts/write")
    Call<ResponseBody> writeComment(@Body CommentData data);
    
    //글 수정
    @PUT("/board/posts/update")
    Call<ResponseBody> updatePost(@Body PostData data);

    //글 삭제
    @DELETE("/board/posts/delete/{post_id}")
    Call<ResponseBody> deletePost(@Path("post_id") int id);
}
