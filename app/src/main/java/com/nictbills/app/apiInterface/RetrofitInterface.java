package com.nictbills.app.apiInterface;

import com.nictbills.app.activities.farmequipment.model.AddUserInfoResponse.AddUserInfoResponsemodel;
import com.nictbills.app.activities.farmequipment.model.bookingconfirm.BookingConfirmationResponseModel;
import com.nictbills.app.activities.farmequipment.model.farmlistresponsemodel.FarmEquipmentListResponseModel;
import com.nictbills.app.activities.farmequipment.model.farmlocation.FarmLocationCheckResponse;
import com.nictbills.app.activities.farmequipment.model.ordercreate.OrderCreateResponseModel;
import com.nictbills.app.activities.farmequipment.model.orderhistory.OrderHistoryFarmEquipment;
import com.nictbills.app.activities.health_id_abdm.dto.AddIVRSDto;
import com.nictbills.app.activities.health_id_abdm.dto.AppVersionDTO;
import com.nictbills.app.activities.health_id_abdm.dto.CheckDuplicateBillDto;
import com.nictbills.app.activities.health_id_abdm.dto.GetOrderCreateResDto;
import com.nictbills.app.activities.health_id_abdm.dto.Get_otp_dto;
import com.nictbills.app.activities.health_id_abdm.dto.IVRSListDto;
import com.nictbills.app.activities.health_id_abdm.dto.RazPayElectricityRequest;
import com.nictbills.app.activities.health_id_abdm.dto.UpdateRazPayOrderDto;
import com.nictbills.app.activities.health_id_abdm.dto.banner.Banner;
import com.nictbills.app.activities.health_id_abdm.dto.beneficiary_dto.CovidBeneficiaryListDTO;
import com.nictbills.app.activities.health_id_abdm.dto.cancel_beneficiary.CovidBeneficiaryCancelDTO;
import com.nictbills.app.activities.health_id_abdm.dto.covidPhotoId.IdPhoto;
import com.nictbills.app.activities.health_id_abdm.dto.covid_genders_dto.CovidGendersDetails;
import com.nictbills.app.activities.health_id_abdm.dto.covid_registration.CovidRegistrationRequest;
import com.nictbills.app.activities.health_id_abdm.dto.covid_registration.CovidRegistrationResponse;
import com.nictbills.app.activities.health_id_abdm.dto.covid_slot_availability.CovidTimeSlotDTO;
import com.nictbills.app.activities.health_id_abdm.dto.covid_state_list.CovidDistrictListDTO;
import com.nictbills.app.activities.health_id_abdm.dto.covid_state_list.CovidStatesListDTO;
import com.nictbills.app.activities.health_id_abdm.dto.create_recharge_order_dto.create_mobile_recharge_dto.MobileRechargeCreateOrderResponse;
import com.nictbills.app.activities.health_id_abdm.dto.create_recharge_order_dto.create_mobile_recharge_dto.SendMobileRechargeDto;
import com.nictbills.app.activities.health_id_abdm.dto.create_recharge_order_dto.create_mobile_recharge_dto.create_vpa_order.RechargeVAPOrderRequestDTO;
import com.nictbills.app.activities.health_id_abdm.dto.create_recharge_order_dto.create_mobile_recharge_dto.create_vpa_order.VPAOrderResponse;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.access_token.GetAccessTokenDawai4U;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.access_token.GetAccessTokenDawai4URequest;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.get_shops.GetDawai4UShop;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.medicine_order_history.GetUserMedicineOrderHistory;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.upload_prescription.CreateMedicalOrderDawai4UResponse;
import com.nictbills.app.activities.health_id_abdm.dto.dawai4u.upload_prescription.CreateMedicalOrderRequest;
import com.nictbills.app.activities.health_id_abdm.dto.delete_beneficiary.BeneficiaryDeleteDtoRequest;
import com.nictbills.app.activities.health_id_abdm.dto.getMobileRechargeProvider.GetProvider;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.GenerateHealthIdTokenResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.LoginUsingMobileRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.LoginUsingMobileResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.ResendMobileLoginOtpRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.VerifyMobileOTPRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_mobile_number_login.abha_profile_details.GetABHAUserProfileDetails;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.ABHAProfileResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.abha_invidual_user.GetABHAUserProfileRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.abha_invidual_user.GetUserIndividualProfileDetails;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.check_and_generate_mobile_otp.ABHAMobileNumberVerifyRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.check_and_generate_mobile_otp.ABHAMobileNumberVerifyResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.check_and_generate_mobile_otp.CheckAndGenerateMobileOtpRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.check_and_generate_mobile_otp.CheckAndGenerateMobileResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.create_health_id_pre_verified.CreateHealthIdWithPreVerifiedRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.create_health_id_pre_verified.CreateHealthIdWithPreVerifiedResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.enc_data.EncryptDataRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.enc_data.EncryptDataResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.generateOtp.GenerateOtpRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.generateOtp.GenerateOtpResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.generateOtp.ReGenerateOTP;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.generateOtp.VerifyAadharOTPRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.generateOtp.VerifyAadharOtpResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.health_id_init.HealthIdInitRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.health_id_init.HealthIdInitResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.health_id_init.ResendHealthIdInitRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.phr_linked_abha_address.PHRLinkedABHAAddressRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.phr_linked_abha_address.PHRLinkedABHAAddressResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.update_mobile_number.UpdateMobileNumberRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.update_mobile_number.UpdateMobileNumberResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.verifyABHAAddress.SearchByHealthIdResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.verifyABHAAddress.VerifyABHAAddressRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.verify_abha_aadhaar.ConfirmWithAadhaarRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.verify_abha_aadhaar.ConfirmWithAadhaarResponse;
import com.nictbills.app.activities.health_id_abdm.dto.house_hold.HouseHoldVendorResponse;
import com.nictbills.app.activities.health_id_abdm.dto.lic.FetchLicBillRequest;
import com.nictbills.app.activities.health_id_abdm.dto.lic.bill_fetch_response.LicBillsFetchResponse;
import com.nictbills.app.activities.health_id_abdm.dto.lic.create_lic_order.CreateLICRequest;
import com.nictbills.app.activities.health_id_abdm.dto.lic.create_lic_order.CreateQrRequest;
import com.nictbills.app.activities.health_id_abdm.dto.lic.create_lic_order.LICOrderResponse;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.GetOperatorPaySprint;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.create_upi_order_paysprint.CreatePSMR_QROrderRequest;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.create_upi_order_paysprint.CreatePSMR_UPIOrderRequest;
import com.nictbills.app.activities.health_id_abdm.dto.mobile_recharge_paysprint.getRechargePlan.RechargePlanRequest;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.PaniwalaConfirmOrderRequest;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.PaniwalaCreateOrderRequest;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.PaniwalaCreateOrderResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.paniwala_razorpay.confirm_order.PaniwalaConfirmOrderResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.sub_service_category.GetSubCategoryResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.vendor_profile.PaniwalaVendorProfileListResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.vendor_profile.PaniwalaVendorProfileRequest;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_category.GetWaterCategoryResponse;
import com.nictbills.app.activities.health_id_abdm.dto.paniwala.water_type.GetWaterTypeResponse;
import com.nictbills.app.activities.health_id_abdm.dto.referral_code.GetReferralCodeResponse;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.GetRewardPointsResponse;
import com.nictbills.app.activities.health_id_abdm.dto.reward_points.reward_ledger.RewardPointsLedger;
import com.nictbills.app.activities.health_id_abdm.dto.schedule_dto.ResheduleRequest;
import com.nictbills.app.activities.health_id_abdm.dto.schedule_dto.ScheduleRequest;
import com.nictbills.app.activities.health_id_abdm.dto.schedule_dto.ScheduleResponse;
import com.nictbills.app.activities.health_id_abdm.dto.transaction_history.TxnDetails;
import com.nictbills.app.activities.health_id_abdm.dto.update_order.UpdateOrder;
import com.nictbills.app.activities.health_id_abdm.dto.upiVPADTO.GetQRCodeResponseDto;
import com.nictbills.app.activities.health_id_abdm.dto.upiVPADTO.UPIVpaDataSend;
import com.nictbills.app.activities.health_id_abdm.dto.upiVPADTO.UpiVpaDTO;
import com.nictbills.app.activities.health_id_abdm.dto.user_profile.SaveProfileRequest;
import com.nictbills.app.activities.health_id_abdm.dto.user_profile.SaveProfileResponse;
import com.nictbills.app.activities.health_id_abdm.dto.user_profile.get_user_profile.GetUserProfile;
import com.nictbills.app.activities.health_id_abdm.dto.vaccineDTO.ResponseVerifyOTP;
import com.nictbills.app.activities.health_id_abdm.dto.vaccineDTO.VaccineLoginRequestDto;
import com.nictbills.app.activities.health_id_abdm.dto.vaccineDTO.VaccineLoginResponseDto;
import com.nictbills.app.activities.health_id_abdm.dto.vaccineDTO.VaccineOTPRequestDto;
import com.nictbills.app.activities.health_id_abdm.dto.view_plans_dto.ViewPlansDto;
import com.nictbills.app.activities.tbo.bus.model.agencyresmodel.BusAgencyResponseModel;
import com.nictbills.app.activities.tbo.bus.model.authresmodel.BusAuthResponseModel;
import com.nictbills.app.activities.tbo.bus.model.busblockreqmodel.BusBlockRequestModel;
import com.nictbills.app.activities.tbo.bus.model.busblockresponse.BusBlockResponseModel;
import com.nictbills.app.activities.tbo.bus.model.busbookinghistorymodel.BusBookingHistoryModel;
import com.nictbills.app.activities.tbo.bus.model.busbordingresponsemodel.BusBordingPointResponseModel;
import com.nictbills.app.activities.tbo.bus.model.buscancelticket.BusCancelModelResponse;
import com.nictbills.app.activities.tbo.bus.model.buscitylist.BusCityListResponsemodel;
import com.nictbills.app.activities.tbo.bus.model.busorderresp.BusOrderCreateResponseModelResponse;
import com.nictbills.app.activities.tbo.bus.model.buspaymnetresponse.BusPaymentResponseModel;
import com.nictbills.app.activities.tbo.bus.model.bussearchresmodel.BusSearchResponseModel;
import com.nictbills.app.activities.tbo.bus.model.busseatmodel.BusseatResponsemodel;
import com.nictbills.app.activities.tbo.flight.model.addpasengermodel.AddPassengerResponsemodel;
import com.nictbills.app.activities.tbo.flight.model.air_search.air_search_request.SearchFlightRequest;
import com.nictbills.app.activities.tbo.flight.model.auth_token.AuthTokenTBORequest;
import com.nictbills.app.activities.tbo.flight.model.citylist.FlightCityList;
import com.nictbills.app.activities.tbo.flight.model.farequote.FareQuoteModel;
import com.nictbills.app.activities.tbo.flight.model.farerules.FlightFareRules;
import com.nictbills.app.activities.tbo.flight.model.flightmodelviewhistory.FlightHistoryModelResponse;
import com.nictbills.app.activities.tbo.flight.model.flightpaymentstatus.FlightPaymentStatusResponse;
import com.nictbills.app.activities.tbo.flight.model.flightsearch.FlightSearchModel;
import com.nictbills.app.activities.tbo.flight.model.getpassenger.GetPassengerResponsemodel;
import com.nictbills.app.activities.tbo.flight.model.seatlayoutmodel.SeatLayoutModelResponse;
import com.nictbills.app.activities.tbo.hotel.model.addpassenger.HotelAddPassengerResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.authresponse.HotelAouthModelResponse;
import com.nictbills.app.activities.tbo.hotel.model.getpasengerlistres.HotelGetPassengerListResmodel;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockrep.HotelBlockResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.hotelblockroomreq.HotelBlockRoomRequestmodel;
import com.nictbills.app.activities.tbo.hotel.model.hotelcancel.HotelCancelChangeResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.hotelgetroom.HotelGeRoomResponse;
import com.nictbills.app.activities.tbo.hotel.model.hotelhistoryresmodel.HotelHistoryModelResponse;
import com.nictbills.app.activities.tbo.hotel.model.hotelinfomodel.HotelGetInfoResponse;
import com.nictbills.app.activities.tbo.hotel.model.hotelpaymentres.HotelPaymentResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.hotelsearchrequest.HotelSearchRequest;
import com.nictbills.app.activities.tbo.hotel.model.hotelsearchresponse.HotelSearchResponseModel;
import com.nictbills.app.activities.tbo.hotel.model.orderresmodel.HotelOrderResponseModel;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface RetrofitInterface {
    /*@Headers({
            "x-rapidapi-host: joke3.p.rapidapi.com",
            "x-rapidapi-key: a9b7a3956dmshdb977da34e81562p1467bdjsnad5e55eea725"})*/
    @POST("getOtp")
    Call<Get_otp_dto> getCustomerOtp(@Body Get_otp_dto mobile);

    @POST("verifyOtp")
    Call<Get_otp_dto> verifyOtp(@Body Get_otp_dto otp);

    @GET("verifyApiKey")
    Call<Get_otp_dto> verifyApiKey(@Header("X-API-Key") String AuthKey);

    @POST("addIvrsNo")
    Call<AddIVRSDto> addIVRS(@Header("X-API-Key") String AuthKey, @Body AddIVRSDto keyWord);

    @GET("getIvrsNoList")
    Call<IVRSListDto> getIVRSNumber(@Header("X-API-Key") String AuthKey);

    @POST("deleteIvrsNo")
    Call<AddIVRSDto> deleteIVRS(@Header("X-API-Key") String AuthKey, @Body AddIVRSDto keyWord);

    @POST("createRzpOrderId")
    Call<GetOrderCreateResDto> createOrder(@Header("X-API-Key") String AuthKey, @Body RazPayElectricityRequest ivrsNoList);

    @POST("updateRzpOrder")
    Call<UpdateOrder> confirmOrder(@Header("X-API-Key") String AuthKey, @Body UpdateRazPayOrderDto updateRazPayOrderDto);

    @POST("checkDuplicateBill")
    Call<CheckDuplicateBillDto> checkDuplicateBill(@Header("X-API-Key") String AuthKey, @Body CheckDuplicateBillDto duplicateBillDto);

    @GET("userTxnList")
    Call<TxnDetails> getTransactionDetails(@Header("X-API-Key") String AuthKey);

    @GET("versionInfo")
    Call<AppVersionDTO> getAppVersion();

    @POST("verifyUPITxn")
    Call<UpiVpaDTO> VPARequest(@Header("X-API-Key") String AuthKey, @Body UpiVpaDTO vpaDTO);

    @POST("createUPIOrder")
    Call<UPIVpaDataSend> createUPIVPAOrder(@Header("X-API-Key") String AuthKey, @Body UPIVpaDataSend vpaDTO);

    @POST("createQROrder")
    Call<GetQRCodeResponseDto> createQROrder(@Header("X-API-Key") String AuthKey, @Body UPIVpaDataSend vpaDTO);

    @POST("verifyQRTxn")
    Call<UpiVpaDTO> verifyQRTxn(@Header("X-API-Key") String AuthKey, @Body UpiVpaDTO vpaDTO);

    @GET("apisearch")
    Call<ResponseBody> getAllCourse(@Query("publisher") String publisherId, @Query(value = "q", encoded = true) String course, @Query("co") String country, @Query("cs") int CourseSubject, @Query("cat") String course_category, @Query("start") int start_page, @Query("limit") int limit_page);

    @POST("generateOTP")
    Call<VaccineLoginResponseDto> getVaccineOTP(@Header("X-API-Key") String AuthKey, @Body VaccineLoginRequestDto mobile);

    @POST("confirmOTP")
    Call<ResponseVerifyOTP> verifyVaccineOTP(@Header("X-API-Key") String AuthKey, @Body VaccineOTPRequestDto otpRequestDto);

    @GET("v2/registration/beneficiary/idTypes")
    Call<IdPhoto> getCovidIdDetails(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent);

    @GET("v2/registration/beneficiary/genders")
    Call<CovidGendersDetails> getCovidGendersDetails(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent);

    @POST("v2/registration/beneficiary/new")
    Call<CovidRegistrationResponse> newCovidRegistration(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Body CovidRegistrationRequest registrationRequest);

    @GET("v2/appointment/beneficiaries")
    Call<CovidBeneficiaryListDTO> getBeneficiaryListDetails(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent);

    //v2/appointment/sessions/public/calendarByPin   public
    // v2/appointment/sessions/calendarByPin
    @GET("v2/appointment/sessions/calendarByPin")
    Call<CovidTimeSlotDTO> getAppointmentListByPincode(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Query("pincode") String pincode, @Query("date") String date);

    //v2/appointment/sessions/calendarByDistrict
    // v2/appointment/sessions/public/calendarByDistrict

    @GET("v2/appointment/sessions/calendarByDistrict")
    Call<CovidTimeSlotDTO> getAppointmentListByDistricts(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Query("district_id") String districtsID, @Query("date") String date);


    @GET("v2/admin/location/states")
    Call<CovidStatesListDTO> getAppointmentListByState(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent);

    @GET("v2/admin/location/districts/{state_id}")
    Call<CovidDistrictListDTO> getAppointmentListByDistricts(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Path("state_id") String stateId);

    @POST("v2/appointment/schedule")
    Call<ScheduleResponse> scheduleAppointment(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Body ScheduleRequest scheduleRequest);

    @POST("v2/appointment/reschedule")
    Call<ScheduleResponse> scheduleAppointment(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Body ResheduleRequest scheduleRequest);

    @GET("v2/appointment/appointmentslip/download")
    @Streaming
    Call<ResponseBody> getAppointmentSlipDownload(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Query("appointment_id") String appoinment_id);

    @GET("v2/registration/certificate/download")
    @Streaming
    Call<ResponseBody> getVaccinationCertificateDownload(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Query("beneficiary_reference_id") String beneficiary_reference_id);

    @POST("v2/registration/beneficiary/delete")
    Call<ResponseBody> deleteBeneficiary(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Body BeneficiaryDeleteDtoRequest beneficiaryDeleteDtoRequest);

    @POST("v2/appointment/cancel")
    Call<ResponseBody> cancelBeneficiary(@Header("Authorization") String authHeader, @Header("User-Agent") String user_agent, @Body CovidBeneficiaryCancelDTO covidBeneficiaryCancelDTO);

    @GET("getProviders")
    Call<GetProvider> getMobileOperatorCircle(@Header("X-API-Key") String AuthKey);

    @GET("getPlans/{operator_id}")
    Call<List<ViewPlansDto>> getMobilePlans(@Header("X-API-Key") String AuthKey, @Path("operator_id") int operatorId);

    @POST("createRechQROrder")
    Call<MobileRechargeCreateOrderResponse> createQROrderMobileRecharge(@Header("X-API-Key") String AuthKey, @Body SendMobileRechargeDto sendMobileRechargeDto);

    @POST("verifyPSMR_UPITxn")
    Call<UpiVpaDTO> verifyRechargeQRTxn(@Header("X-API-Key") String AuthKey, @Body UpiVpaDTO vpaDTO);

    @POST("createRechUPIOrder")
    Call<VPAOrderResponse> createVPAOrderRecharge(@Header("X-API-Key") String AuthKey, @Body RechargeVAPOrderRequestDTO vapOrderRequestDTO);

    @POST("verifyPSMR_QRTxn")
    Call<UpiVpaDTO> VPARequestRechargeStatus(@Header("X-API-Key") String AuthKey, @Body UpiVpaDTO vpaDTO);

    @GET("bypass/get_nict_apps_banners")
    Call<Banner> getBanners(@Query("app_name") String app_name);

    @POST("updateToken")
    Call<Get_otp_dto> fbToken(@Header("X-API-Key") String AuthKey, @Body Get_otp_dto token);

    @GET("getRewardBalance")
    Call<GetRewardPointsResponse> getRewardPoints(@Header("X-API-Key") String AuthKey);

    @GET("getRewardLedger")
    Call<RewardPointsLedger> getRewardPointsDetails(@Header("X-API-Key") String AuthKey);

    @POST("saveUserProfile")
    Call<SaveProfileResponse> saveUserProfile(@Header("X-API-Key") String AuthKey, @Body SaveProfileRequest saveProfile);

    @GET("getUserProfile")
    Call<GetUserProfile> getUserProfile(@Header("X-API-Key") String AuthKey);

    @POST("getPSLICBill")
    Call<LicBillsFetchResponse> getLicDetails(@Header("X-API-Key") String AuthKey, @Body FetchLicBillRequest fetchLicBillRequest);

    @POST("createPSLIC_UPIOrder")
    Call<LICOrderResponse> createLicOrder(@Header("X-API-Key") String AuthKey, @Body CreateLICRequest fetchLicBillRequest);

    @POST("verifyPSLIC_UPITxn")
    Call<UpiVpaDTO> vpaVerify_txn(@Header("X-API-Key") String AuthKey, @Body UpiVpaDTO vpaDTO);

    @POST("createPSLIC_QROrder")
    Call<MobileRechargeCreateOrderResponse> createQROrderLIC(@Header("X-API-Key") String AuthKey, @Body CreateQrRequest qrRequest);

    @POST("verifyPSLIC_QRTxn")
    Call<UpiVpaDTO> verifyLicQRTxn(@Header("X-API-Key") String AuthKey, @Body UpiVpaDTO vpaDTO);

    @GET("generateToken")
    Call<GenerateHealthIdTokenResponse> getAccessToken(@Header("X-API-Key") String AuthKey);

    @POST("v2/registration/aadhaar/generateOtp")
    Call<GenerateOtpResponse> generateOTPHI(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body GenerateOtpRequest generateOtpRequest);

    @POST("v1/registration/aadhaar/resendAadhaarOtp")
    Call<GenerateOtpResponse> reGenerateAadhaarOTP(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body ReGenerateOTP generateOtpRequest);

    @POST("v1/auth/init")
    Call<HealthIdInitResponse> healthIdInit(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body HealthIdInitRequest idInitRequest);

    @POST("v1/auth/resendAuthOTP")
    Call<ResponseBody> healthIdInitResendOTP(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body ResendHealthIdInitRequest resendHealthIdInitRequest);

    @POST("v1/auth/confirmWithAadhaarOtp")
    Call<ConfirmWithAadhaarResponse> confirmWithAadhaarOTP(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body ConfirmWithAadhaarRequest confirmWithAadhaarRequest);

    @POST("encryptData")
    Call<EncryptDataResponse> encData(@Header("X-API-Key") String AuthKey, @Body EncryptDataRequest encryptDataRequest);

    @POST("v2/registration/aadhaar/verifyOTP")
    Call<VerifyAadharOtpResponse> verifyAadharOtp(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body VerifyAadharOTPRequest encryptDataRequest);

    @POST("v2/registration/aadhaar/checkAndGenerateMobileOTP")
    Call<CheckAndGenerateMobileResponse> getABHAMobileNumberOtp(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body CheckAndGenerateMobileOtpRequest checkAndGenerateMobileOtpRequest);

    @POST("v2/registration/aadhaar/verifyMobileOTP")
    Call<ABHAMobileNumberVerifyResponse> verifyABHAMobileNumberOtp(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body ABHAMobileNumberVerifyRequest verifyRequest);

    @POST("v1/health/facility/createHealthIdWithPreVerified")
    Call<CreateHealthIdWithPreVerifiedResponse> createHealthWithPreVerified(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body CreateHealthIdWithPreVerifiedRequest verifyRequest);

    @POST("v2/account/phr-linked")
    Call<PHRLinkedABHAAddressResponse> phrLinked(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Header("X-Token") String x_token, @Body PHRLinkedABHAAddressRequest phrLinkedABHAAddressRequest);

    @GET("v1/account/profile")
    Call<ABHAProfileResponse> getABHAUsersProfile(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Header("X-Token") String x_token);

    @GET("v1/account/qrCode")
    Call<ResponseBody> getABHAProfileQR(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Header("X-Token") String x_token);

    @GET("v1/account/getCard")
    Call<ResponseBody> downloadABHACard(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Header("X-Token") String x_token);

    @POST("v2/account/change/mobile/new/generateOTP")
    Call<UpdateMobileNumberResponse> updateABHAMobileNumber(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Header("X-Token") String x_token, @Body UpdateMobileNumberRequest updateMobileNumberRequest);

    // PaySprint
    @GET("getOperators")
    Call<GetOperatorPaySprint> getMobileOperatorCirclePaySprint(@Header("X-API-Key") String AuthKey);

    @POST("getPSPlans")
    Call<ResponseBody> getMobilePlansPaySprint(@Header("X-API-Key") String AuthKey, @Body RechargePlanRequest rechargePlanRequest);

    @POST("createPSMR_UPIOrder")
    Call<VPAOrderResponse> createPaySprintVPAOrderRecharge(@Header("X-API-Key") String AuthKey, @Body CreatePSMR_UPIOrderRequest vapOrderRequestDTO);

    @POST("createPSMR_QROrder")
    Call<MobileRechargeCreateOrderResponse> createPaySprintQROrderMobileRecharge(@Header("X-API-Key") String AuthKey, @Body CreatePSMR_QROrderRequest sendMobileRechargeDto);

    @POST("v2/registration/mobile/login/generateOtp")
    Call<LoginUsingMobileResponse> loginUsingMobileNumber(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body LoginUsingMobileRequest usingMobileRequest);

    @POST("v2/registration/mobile/login/resendOtp")
    Call<ResponseBody> resendMobileOtp(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body ResendMobileLoginOtpRequest resendMobileLoginOtpRequest);

    @POST("v1/search/searchByHealthId")
    Call<SearchByHealthIdResponse> verifyAbhaAddress(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body VerifyABHAAddressRequest verifyABHAAddressRequest);

    @POST("v2/registration/mobile/login/verifyOtp")
    Call<GetABHAUserProfileDetails> otpMobileVerify(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Body VerifyMobileOTPRequest verifyMobileOTPRequest);

    @POST("v2/registration/mobile/login/userAuthorizedToken")
    Call<GetUserIndividualProfileDetails> getIndividualProfile(@Header("Authorization") String AuthKey, @Header("X-HIP-ID") String hip_id, @Header("T-Token") String x_token, @Body GetABHAUserProfileRequest userProfileRequest);

    @POST("v1/auth/get_access_token")
    Call<GetAccessTokenDawai4U> getAccessTokenDawai4U(@Body GetAccessTokenDawai4URequest accessTokenDawai4URequest);

    @GET("api/v1/services/dawai4u/get_all_medical_store?records=ALL")
    Call<GetDawai4UShop> getDawai4UShops(@Header("Authorization") String AuthKey, @Query("pincode") String pincode);

    @POST("api/v1/services/dawai4u/create_medicine_order")
    Call<CreateMedicalOrderDawai4UResponse> createOrderDawai4U(@Header("Authorization") String AuthKey, @Body CreateMedicalOrderRequest createMedicalOrderRequest);

    @GET("api/v1/services/dawai4u/get_user_medicine_request")
    Call<GetUserMedicineOrderHistory> getOrderHistoryDawai4U(@Header("Authorization") String AuthKey, @Query("mobile") String mobileNumber);

    @GET("getReferralCode")
    Call<GetReferralCodeResponse> getReferralCode(@Header("X-API-Key") String AuthKey);

    @GET("allservices")
    Call<GetWaterTypeResponse> getWaterTypePaniwala();

    @GET("subserv")
    Call<GetWaterCategoryResponse> getWaterCategoryPaniwala(@Query("main_serv_code") String main_serve_code);

    @GET("subserviceslist")
    Call<GetSubCategoryResponse> getSubWaterCategoryPaniwala(@Query("main_serv_code") String main_serve_code, @Query("sub_serv_code") String sub_code);

    @POST("vendorlist")
    Call<PaniwalaVendorProfileListResponse> getVendorProfileList(@Body PaniwalaVendorProfileRequest paniwalaVendorProfileRequest);

    @POST("vendorlist")
    Call<HouseHoldVendorResponse> getHouseHoldVendorProfileList(@Body PaniwalaVendorProfileRequest paniwalaVendorProfileRequest);

    @POST("create_order")
    Call<PaniwalaCreateOrderResponse> createRazorPayOrderPaniwala(@Body PaniwalaCreateOrderRequest createOrderRequest);

    @POST("paymentVerified")
    Call<PaniwalaConfirmOrderResponse> confirmRazorPayOrderPaniwala(@Body PaniwalaConfirmOrderRequest confirmOrderRequest);

    @POST("get_auth_token")
    Call<AuthTokenTBORequest> getFlightToken(@Body HashMap<String, String> param);
    // Call<AuthTokenTBORequest> getFlightToken(@Body AuthRequest authRequest);

//    @Headers("content-type: application/json")
//    @POST("air_search")
//    Call<ResponseBody> airSearch(@Body SearchFlightRequest searchFlightRequest);

    @POST("get_air_fare_quote")
    Call<FareQuoteModel> getFareQuote(@Body HashMap<String, String> param);

    @POST("get_air_fare_rule")
    Call<FlightFareRules> getFarerules(@Body HashMap<String, String> param);

    //    @POST("air_ticket_book")
//    Call<FlightFareRules> airticket(@Body HashMap<String, String> param);
    @POST("add_passenger")
    Call<AddPassengerResponsemodel> addpassenger(@Body HashMap<String, String> param);


    @POST("get_passenger")
    Call<GetPassengerResponsemodel> getpassenger(@Body HashMap<String, String> param);


    @POST("get_booking")
    Call<FlightHistoryModelResponse> flighthistory(@Body HashMap<String, String> param);



    @Headers("content-type: application/json")
    @POST("air_search")
    Call<FlightSearchModel> airSearch(@Body SearchFlightRequest searchFlightRequest);


    @POST("get_air_ssr")
    Call<SeatLayoutModelResponse> get_air_ssr(@Body HashMap<String, String> param);

//    @Headers("content-type: application/json")
//    @POST("air_ticket_book")
//    Call<FlightBookWithNonLccResponse> airticket(@Body FlightBookWithNonLccRequest flightBookWithNonLccRequest);


//    @Headers("content-type: application/json")
//    @POST("get_ticket")
//    Call<TicketLccWithoutSeatResponse> getticketwithoutseat(@Body TicketRequestWithLccWithoutSeat ticketRequestWithLccWithoutSeat);

//    @Headers("content-type: application/json")
//    @POST("get_ticket")
//    Call<TicketLccWithSeatResponse> getticketwithseat(@Body TicketLccWithSeatRequest ticketLccWithSeatRequest);


//    @Headers("content-type: application/json")
//    @POST("get_ticket1")
//    Call<TicketNonLccResponse> getticketnonlcc(@Body HashMap<String, String> param);

    //    @Headers("content-type: application/json")
//    @GET("get_cities")
//    Call<CitiesListResponse> citiList();
//    @Headers("content-type: application/json")
    @GET("get_cities")
    Call<FlightCityList> citiList();

    //hotel api

    @POST("get_auth_token")
    Call<HotelAouthModelResponse> gethotelauth_token(@Body HashMap<String, String> param);

    @POST("hotel_info")
    Call<HotelGetInfoResponse> gethotelinfo(@Body HashMap<String, String> param);

    @POST("get_hotel_room")
    Call<HotelGeRoomResponse> getroom(@Body HashMap<String, String> param);

    @POST("get_hotel_booking_details")
    Call<HotelHistoryModelResponse> hotelhistory(@Body HashMap<String, String> param);

    @Headers("content-type: application/json")
    @POST("hotel_block_room")
    Call<HotelBlockResponseModel> hotel_blockroom(@Body HotelBlockRoomRequestmodel hotelBlockRoomRequestmodel);

    @POST("add_passenger")
    Call<HotelAddPassengerResponseModel> addpassenger_hotel(@Body HashMap<String, String> param);

    @POST("get_passenger")
    Call<HotelGetPassengerListResmodel>gethotelpassenger(@Body HashMap<String, String> param);

    @POST("getpayment")
    Call<FlightPaymentStatusResponse> flightgetpayment(@Body HashMap<String, String> PaymentStatusparam);

    @POST("create")
    Call<HotelOrderResponseModel> flightorder(@Body HashMap<String, String> param);


    //hotel
//    @POST("get_auth_token")
//    Call<HotelBookingResponseModel> hotelbookingdetails(@Body HashMap<String, String> param);

    @Headers("content-type: application/json")
    @POST("hotel_search")
    Call<HotelSearchResponseModel> hotelsearch(@Body HotelSearchRequest hotelSearchRequest);

    @POST("createHotelOrder")
    Call<HotelOrderResponseModel> orderhotel(@Body HashMap<String, String> param);

    @POST("getHotelPayment")
    Call<HotelPaymentResponseModel> gethotelpayment(@Body HashMap<String, String> PaymentStatusparam);

    @POST("hotel_cancel_send_change_request")
    Call<HotelCancelChangeResponseModel> hotelticketcancel(@Body HashMap<String, String> paramhotelcasncel);

    //BUS

    @GET("get_cities")
    Call<FlightCityList> buscity();

    @Headers("content-type: application/json")
    @POST("GetBusCityList")
    Call<BusCityListResponsemodel> getbuscity(@Body HashMap<String, String> param);


    @POST("get_auth_token")
    Call<BusAuthResponseModel> getBus_AuthToken(@Body HashMap<String, String> param);

    @POST("get_agency_balance")
    Call<BusAgencyResponseModel> getBus_AgencyBalance(@Body HashMap<String, String> param);

    @Headers("content-type: application/json")
    @POST("bus_search")
    Call<BusSearchResponseModel> bussearch(@Body HashMap<String, String> param);

    @POST("bus_seat_layout")
    Call<BusseatResponsemodel> getBusSeat(@Body HashMap<String, String> param);

    @POST("boarding_point")
    Call<BusBordingPointResponseModel> getBusBording(@Body HashMap<String, String> param);


    @Headers("content-type: application/json")
    @POST("bus_block")
    Call<BusBlockResponseModel> busblock(@Body BusBlockRequestModel busBlockRequestModel);

    @POST("create")
    Call<BusOrderCreateResponseModelResponse> busordercreation(@Body HashMap<String, String> param);

    @POST("getBusPayment")
    Call<BusPaymentResponseModel> getbuspayment(@Body HashMap<String, String> PaymentStatusparam);

    @POST("get_bus_booking_details")
    Call<BusBookingHistoryModel> bushistory(@Body HashMap<String, String> param);

    @POST("send_change_request")
    Call<BusCancelModelResponse> busticketcancel(@Body HashMap<String, String> param);



    //farm

    @GET("equips")
//    Call<FarmEquipmentListResponseModel> getalleqip();
    Call<List<FarmEquipmentListResponseModel>> getalleqip();

    @Headers("Accept: application/json")
    @POST("clients/client")
    Call<AddUserInfoResponsemodel> adduserinfo(@Body HashMap<String, String> param);

    @GET("equips/city/{id}/{location}")
    Call<FarmLocationCheckResponse> checklocation(@Path("id") String id ,@Path("location") String name );

    @Headers("Accept: application/json")
    @POST("orders/order")
    Call<OrderCreateResponseModel> getorder(@Body HashMap<String, String> param);

    @Headers("Accept: application/json")
    @POST("orders/booking")
    Call<BookingConfirmationResponseModel> bookingconfm(@Body HashMap<String, String> param);


    @GET("orders/{mobile}")
    Call<List<OrderHistoryFarmEquipment>> orderhsitory(@Path("mobile") String mobile);


}
