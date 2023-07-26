package com.nictbills.app.activities.fastag.retrofit;


import com.google.gson.JsonObject;
import com.nictbills.app.activities.fastag.Model.Authentication;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface UserService {

    @GET(APIClient.APPEND_URL + "get_fastag_types")
    Call<JsonObject> getFasTagtypeList(@HeaderMap Map<String,String> headerList);

    @GET
    Call<JsonObject> getFasTagtCertificateList(@Url String url, @HeaderMap Map<String,String> headerList);

    @Multipart
    @POST(APIClient.APPEND_URL+"fastag_request_for_individual")
    Call<JsonObject> addFasTagRequestForIndividuals(@HeaderMap Map<String,String> headerList, @Part("addr_id") RequestBody  addr_id, @Part("applicant_name") RequestBody  applicant_name, @Part("mobile_no") RequestBody  mobile_no, @Part("dob") RequestBody  dob, @Part("sector") RequestBody  sector, @Part("fastag_type") RequestBody  fastag_type, @Part("pancard_no") RequestBody  pancard_no, @Part MultipartBody.Part  pancard_photo, @Part MultipartBody.Part  aadhaar_photo_front, @Part MultipartBody.Part  aadhaar_photo_back, @Part MultipartBody.Part  vehicle_rc_photo, @Part MultipartBody.Part  applicant_photo, @Part("addr_name") RequestBody  addr_name, @Part("addr_email") RequestBody  addr_email, @Part("addr_pincode") RequestBody  addr_pincode, @Part("addr_state") RequestBody  addr_state, @Part("addr_city") RequestBody  addr_city, @Part("address") RequestBody  address, @Part("addr_alt_mobile") RequestBody  addr_alt_mobile, @Part("aadhaar_no") RequestBody  aadhaar_no, @Part("vehicle_rc_no") RequestBody vehicle_rc_no) ;

    @Multipart
    @POST(APIClient.APPEND_URL+"fastag_request_for_proprietorship")
    Call<JsonObject> addFasTagRequestForProprietors(@HeaderMap Map<String,String> headerList, @Part("addr_id") RequestBody  addr_id, @Part("applicant_name") RequestBody  applicant_name, @Part("mobile_no") RequestBody  mobile_no, @Part("dob") RequestBody  dob, @Part("sector") RequestBody  sector, @Part("fastag_type") RequestBody  fastag_type, @Part("pancard_no") RequestBody  pancard_no, @Part MultipartBody.Part  pancard_photo, @Part MultipartBody.Part  aadhaar_photo_front, @Part MultipartBody.Part  aadhaar_photo_back, @Part MultipartBody.Part  vehicle_rc_photo, @Part MultipartBody.Part  applicant_photo, @Part("addr_name") RequestBody  addr_name, @Part("addr_email") RequestBody  addr_email, @Part("addr_pincode") RequestBody  addr_pincode, @Part("addr_state") RequestBody  addr_state, @Part("addr_city") RequestBody  addr_city, @Part("address") RequestBody  address, @Part("addr_alt_mobile") RequestBody  addr_alt_mobile, @Part("aadhaar_no") RequestBody  aadhaar_no, @Part("vehicle_rc_no") RequestBody vehicle_rc_no, @Part MultipartBody.Part  license_of_entity) ;

    @Multipart
    @POST(APIClient.APPEND_URL+"fastag_request_for_corporate")
    Call<JsonObject> addFasTagRequestForCorporate(@HeaderMap Map<String,String> headerList, @Part("addr_id") RequestBody  addr_id, @Part("applicant_name") RequestBody  applicant_name, @Part("mobile_no") RequestBody  mobile_no, @Part("dob") RequestBody  dob, @Part("sector") RequestBody  sector, @Part("fastag_type") RequestBody  fastag_type, @Part("certificate_name") RequestBody  certificate_name, @Part("pancard_no") RequestBody  pancard_no, @Part MultipartBody.Part  pancard_photo, @Part MultipartBody.Part  license_of_entity, @Part("vehicle_rc_no") RequestBody  vehicle_rc_no, @Part MultipartBody.Part  vehicle_rc_photo, @Part MultipartBody.Part  applicant_photo, @Part("addr_name") RequestBody  addr_name, @Part("addr_email") RequestBody  addr_email, @Part("addr_pincode") RequestBody  addr_pincode, @Part("addr_state") RequestBody  addr_state, @Part("addr_city") RequestBody  addr_city, @Part("address") RequestBody  address, @Part("addr_alt_mobile") RequestBody  addr_alt_mobile, @Part MultipartBody.Part  certificate_file, @Part MultipartBody.Part  address_proof, @Part MultipartBody.Part  photo_id_authorized_signatory, @Part MultipartBody.Part  corporate_directors, @Part MultipartBody.Part  moa_aoa) ;

    @POST( "nict-bills-services-api/v1/auth/get_access_token")
    Call<Authentication> getAccessToken(@Body RequestBody requestBody);

}
