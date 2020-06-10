package com.example.athome.retrofit;


import com.example.athome.admin.AdminResult;
import com.example.athome.admin.AllUserResult;
import com.example.athome.admin_enroll.AdminEnrollResult;
import com.example.athome.notice.ItemNoticeResult;

import java.util.Date;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    //사용자 회원가입
    @FormUrlEncoded
    @POST("user/join")
    Call<RegisterResult> signUp(@Field("userId") String userId,
                                @Field("userPassword") String userPassword,
                                @Field("userName") String userName,
                                @Field("userEmail") String userEmail,
                                @Field("userPhone") String userPhone);
    //사용자 로그인
    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResult> login(@Field("userId") String userId,
                            @Field("userPassword") String userPassword);
    //토큰 인증
    @FormUrlEncoded
    @POST("api/auth")
    Call<AuthResult> authenticate(@Header("x-access-token") String token
            , @Field("authKey") String key);

    //사용자 비밀번호 수정
    @FormUrlEncoded
    @POST("user/editPassword")
    Call<EditResult> editPassword(@Header("x-access-token") String token,
                                  @Field("userPassword") String userPassword,
                                  @Field("newUserPassword") String newUserPassword);
    //사용자 예약 등록
    @FormUrlEncoded
    @POST("api/reservation/enroll")
    Call<ResponseBody> sendReserve(@Header("x-access-token") String token,
                                   @Field("_id") String locationId,
                                   @Field("carNumber") String carNum,
                                   @Field("startTime") Date startTime,
                                   @Field("endTime") Date endTime);
    //사용자 배정자 등록 신청
    @Multipart
    @POST("api/sharedLocation/enroll")
    Call<EnrollResult> postRegister(@Header("x-access-token") String token,
                                    @Part MultipartBody.Part image,
                                    @Part("userBirth") RequestBody userBirth,
                                    @Part("userCarNumber")  RequestBody userCarNumber,
                                    @Part("location")  RequestBody location,
                                    @Part("latitude")  RequestBody latitude,
                                    @Part("longitude")  RequestBody longitude,
                                    @Part("parkingInfo")  RequestBody parkingInfo);
    //마커 정보 요청
    @FormUrlEncoded
    @POST("api/allSharedLocation")
    Call<MarkerResult> getMarkerData(@Header("x-access-token") String token,
                                     @Field("capstone") String capstone);

    @FormUrlEncoded
    @POST("api/shareInfo")
    Call<ShareInfoResult> getShareData(@Header("x-access-token") String token,
                                    @Field("trash")String trash);

    @FormUrlEncoded
    @POST("api/sendShareInfo")
    Call<ResponseBody> sendShareData(@Header("x-access-token") String token,
                                     @Field("days") String days,
                                     @Field("startTime") String startTime,
                                     @Field("endTime") String endTime);

    @GET("api/locationInfo")
    Call<LocationInfoList> getLocationInfo(@Query("locationId") String locationId);

    @GET("api/reserveList")
    Call<ReserveListResult> getReserveData(@Query("locationId") String locationId);

    @GET("notices")
    Call<ItemNoticeResult> allNotice(@Query("noticeName") String noticeName);

    //예약정보 받아오기
    @FormUrlEncoded
    @POST("user/myReservation")
    Call<ReservationListResult> getReservation(@Header("x-access-token") String token
            , @Field("reserve") String reserve);

    //==========================관리자===========================================================
    @FormUrlEncoded
    @POST("admin/users")
    Call<AllUserResult> getAllUsers(@Header("x-access-token") String token
                                    , @Field("secret") String secret);
    @FormUrlEncoded
    @POST("admin/unCheckedList")
    Call<AdminEnrollResult> getUncheckedSharedLocation(@Header("x-access-token") String token
                                    , @Field("secret") String secret);
    @FormUrlEncoded
    @POST("admin/sharedLocation/enroll")
    Call<AdminResult> registerSharedLocation(@Header("x-access-token") String token
            ,@Field("userId") String userId, @Field("_id") String Id);

    @FormUrlEncoded
    @POST("admin/enroll/notice")
    Call<EnrollResult> enrollNotice(@Header("x-access-token") String token
            , @Field("title") String title,@Field("description") String description);

    //비밀번호 변경
    @FormUrlEncoded
    @POST("admin/editPassword")
    Call<AdminResult> adminEditPassword(@Header("x-access-token") String token,
                                        @Field("editPassword") String editPassword,
                                        @Field("userId") String userId);



    @FormUrlEncoded
    @POST("admin/editPhone")
    Call<AdminResult> adminEditPhone(@Header("x-access-token") String token,
                                     @Field("phone") String Phone,
                                     @Field("userId") String userId);

    @FormUrlEncoded
    @POST("admin/editPoint")
    Call<AdminResult> adminEditPoint(@Header("x-access-token") String token,
                                     @Field("point") int point,
                                     @Field("userId") String userId);

    @FormUrlEncoded
    @POST("admin/editState")
    Call<AdminResult> adminEditState(@Header("x-access-token") String token, @Field("userId") String userId,@Field("editState") Integer editState);


}