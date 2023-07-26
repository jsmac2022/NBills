package com.nictbills.app.activities.health_id_abdm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nictbills.app.Constant;
import com.nictbills.app.R;
import com.nictbills.app.activities.BaseActivity;
import com.nictbills.app.activities.DashboardActivity;
import com.nictbills.app.activities.LoginActivity;
import com.nictbills.app.apiInterface.RetrofitInterface;
import com.nictbills.app.apiclient.RetrofitClient;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.GenerateHealthIdTokenResponse;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.abha_user_profile.ABHAProfileResponse;
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
import com.nictbills.app.activities.health_id_abdm.dto.health_id.phr_linked_abha_address.PHRLinkedABHAAddressRequest;
import com.nictbills.app.activities.health_id_abdm.dto.health_id.phr_linked_abha_address.PHRLinkedABHAAddressResponse;
import com.nictbills.app.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthIdDashboardActivity extends BaseActivity {

    private EditText aadhar_number_EditText,aadhar_otp_EditText,abha_mobile_otp_EditText,abha_mobile_verification_EditText,abha_address_EditText;
    private Button get_aadhar_OTP_Button,verify_aadhar_OTP_Button,get_abha_mobile_otp_BTN,abha_mobile_verify_BTN,submit_abha_address_Button;
    private RetrofitInterface retroFitInterface;
    private LinearLayout aadhar_number_area_LL,terms_and_conditions_LL,otp_arear_LL,create_abha_with_aadhar_LL,mobile_number_verify_LL,abha_mobile_otp_LL,setABHA_Address_LL;
    private SharedPreferences sharedPreferences,sharedHI;
    private TextView abdh_participating_TV,mobile_number_linked_TV,resent_OTP_TV,counttime_TV,terms_and_conditions_TV,prefilled_mobile_number_TV,user_name_TV,gender_TV,dob_TV,user_address_TV,abha_id_TV;
    private String credential,aadharCardNumber,accessToken,txnIdVerifyAadharOTP,mobileNumber,txnIdGenerateMobileOtp,txnIdVerifyMobileOTP,txnIdCreateHealthIdPreVerified
                    ,profilePhoto,firstName,lastName,txnId,x_token,xToken;
    private boolean is_loginHI;
    private CircleImageView userProfile_Image;
    private SharedPreferences.Editor editor;
    private ImageView backArrow_IMG;
    private CheckBox disclamer_CB;
    private boolean checkBoxStatus;
    private int counter;

    //String profile ="x��X�\u0383J�} u0016��u0017�dr2��9�tO?|�j4�Ŭ�u0012���n�q�t�tu000Bbfc�]�:u0015�2u0015��z�u0019u001EDN�g�Jb�Lb�Jf�L��J�Y����ó�+��/;S)�P�.�Tu000Eu001Bu0015Nٔ��4>�?<su0019�r�w�[^u[u001C�u0018��\u07FD����|��fީn�9k�U����u0001O�q8�Wu0018��u0019Wa�Z`�K`A�f��>�el��b��#p�i�o�ngO�����ޮu0004�9u0015�9}�}�!Ҍ����u000E�(��u000B�u0000�5{���kl�b�u0002i��u0004.T2���wUu�Xg��L��d��N��E��w�r9�����^ u0006au0000�q8u0018]u001C�c\"�K�:p>�X�]�Zt�u001Ak�fּ^r�u001Eo�a��Yu000B��h�e՞�u0013\";������W��V���u0007u001B�u0010��u00189�u0006u0003�u001E?ʉ(\u058B�Yu0010]�(`�Ŀ�����\u007F��o��'>�u001F>ܻ�g��_|lu0015�g�%b�+l����י�7�$(��9S*���?��t���pu�u0019��S�?u0018r/b�����T��u0019��ǲ+��u000F;A��Qu0018�m,u0005C��s2u001AP���B�j2�:J�Fu0019�n6�>N��:$�bvs���6,�jB{���g ��u0012�,�o}Ԡ���<�\"Ƙ`���E:u0012��/|��m(��r�y�c��_u001Cu0012����0z�p�4{�?u0016u0015cj�\u05CCz�-�e*��^M�Ʊ=u0019q�/z��c��'Vu0004�����L��:��YP$k���GrS�Nfr���4pp�GJu0004vOu0005�Rj�Tu0006�p��lu0015c}������^ӿN��b���<�}Ŷ(u0001��u001B����\u007F�T���.u001B��Zu0018Z�~u0017,{u0004<;�����u0003z��k��u001Eu001E^gRÅf!�3�~\"u0007u0016��ΆU�g�m6u0004u001E�H�V���Uxu0007Y$�CZ���u00182_a�̕a����^E� D/OkHttp: x�b��τ��:�,1��Wu0011h'u0011��hY��. ��u4�Ώu0004��?�X�n���A��E�L�u0018��u0013j��>���p��N�u0019�yY,\u007Ff�� n�]�Т~�R��Z�Z�⣴�ͨ{���u0006s�u0006���� �u0007��u000B�P��a��G�u001C�4֬�[�N6T8u001Do8u0012x��O�*xP�~v��W�X��)u0016o�:�Xԋ��u001C���u0015?�����I]'WTr���0w��u001Au0014V���n���E3u001A��t�U�B�ښ�u0006���az�u0015���� u001Dt���';�O�tPu001Boxu0014�ub����9��b��kf���s����߇1Po�r%I?dxY~`u001F���*u0011$u0007�C���Qp)����d�Ph�[}�S���u00153p������%��u0002�u000F�u0005���S�u0018�u0003�u000F�RsKq�K���u0000�u0016籆��Lh�cu0017u0010}<j�Oϵʘ���0Ru001C��+�.�u0015u001A=��q��X�~�f����˜�8t��.aSq�a؏�u0006�����u000E[C���u0012��Eb�4���Yu0011-߰��u0016u000E�'6�~tj5�u0007r��6C����u001Bu0012��?�}5N~,bu0004fu0010�}��R��ٕ�w��y�u0012��Z��ruu0011�'u000B���u000B��\u0380u000F��\\ˑߩ�l��Q7����Q�f�u000E�Z�S*֗�b�;�D�^X o���z�u0013ʥ|��ו�mI%T1�u001D)CB?ٍ\u0588}9��%�g��u001Bņ�u0013f�AJlu0000$0r��Y�n� �3�u001C~u0011ؔ�Մb��W�|�im������;^ؽ�:O��7h�毨f1�u0014�u001E��͝˫DOPb�?�x���d)�mu001EjY@�u0003�Z�z�GQ���e�>]�Fu0010F@�>v[��5Y�Ag���vy�b�u��!u0016e3u0000������u001By�=�L\"�����.[��.��Aͫ)3&u0019_�nu0011��28]҆�*yh���l��nu�0�I�R���b�u0018<�<u001ED���VPu0015��u0018u0000��YB�#�Yj�@��6�,��2��X4u0003�ǵe�P狟W�`8[�A�nN�e�\"!~�j���3��u00008˕�L���t��Ht��^E�#�hu00022,�K�]%��u001BpBz���u0004n?i.7&���u0018�إO�n�u0018u0019��W�u001E��<��k�u000E�@u001F �|oFu0019u0012u000Evt@�6u0010[9ey�>u0018��g��)��Љ`b_���j�1u0018u0010Aȓ{��V�Za�ԍ��V��y~b2N3 ��u0019�k�ڟG2$�>6��M�3ԡJEٌiaKԴ���Lau0017u0014��q�Wu0007��;u001Cu001D�{@u0018Gn��'(��{b{�;Du001CW�u�Tu0017�u0017�[!p��d[e��C\u007F��a�:I�sKIT�?m>u001Cu0016*hN���u0007}�u0002u0019#ScLu000F�}Fy�u0012�n�ku0010���g�8u0001�u0011pG��U��/�m�Gr|^��¸�t�ۆu001C�Ns�Jpg9�u001Au001F}�u0015��]u000E5u001C��\u0A4F�(��uu0018�j��u0019�k�lBu0002}9B�.eQ�u0010鮓�$u0016ʜ�u001A��PY�u000E�Y0r�$�+���pl�跊Ů���W,[�v��\u007F!T�&2u000F��$u00004�C�~�͢u0018�u00145��'�tuRu0012?���e��u001Aju0018uʏu001CZ��u000E�u001E]�qk�sb�o�gou0012b�1����OfHj˷u0019Wѝ6�u0004���_u0019��Uu0006��/�r`5\u007F~�Ru000Eu0011u000E�_6���.P �����%�:Fu0017��Ŧ����������u0003�l�VO�X{�����U*Z�u0006{Y�u0011u0010u000F���u0011��5o�,^��3Ru001Bu001E71�T����q�;u000E��˗<�c����Au001963��w���U�v?u0015��u0010Q�RS�n��d9u0007��6���q����|�7��$��BN��E�QE�u001Af'K�_a��tu0011U�$�ru001D�KtqP���a�'u001D�K�sr��t%����jd�u001F&��.D�u0015����l�i˲�j{�>I��Lu001Ctu0016��u0001�u001D-r��u001Fku0019ﱂ��u��gA��و/˷낑u001F��)KϘ��t)�h��ϭ\"���ӷ�Q�A��I�� �B���u0016u0004��,,u0006�I�i���V�s�����uU�}�g�s��\"�r��u001D��l&\u007F�u0016 �u0011Z�*�t#E@?Qu��a%�U��Ԯ�ƈ�]=u0001�(��u0005��ڥ�����x�;�t�9u0019u0017�O�mu000B�;l�r�-�]�Ϻ�/Y=}�.vY,:?̽�j�)u0004�肋�u0019Ju001Fc2�u0019-HJN�˚��<k^u0002V-��ZAu0014�\u05FC��P#�u001Bn�u0016�}���,+`gQ\u0602*�%��u00035xI�Hl�B�1{};Hu001C��u001D-����@A$u001Cu0017����u0012z�8u0015Ԯ�;f�>ng�nQ��֦��n��;Mۻ��J��'�V�u0011��K4�Ϸo��}�)u0010�նtu001DO�gt��u0015�8=�u�\u007F4�m�voi�!�/��G�*��_��F?P.�U������,{ЪQu000EZv�W�Eu0006u0011D���`u001Dl�iN�/��C�u001Fw���u001D�3m��aru0011�z�0*~$t��y�ֽ9ڧ�h�Ȣ7|n �|�^�v����u0000H���x9�I�������-�C�-(m��1����~��ioBӥj�����D�D?>�qPb!��?;C�2�u0010�L������Mu0017��u0018u0006��r���Ο�!K�[r���u000Eu000F.A�9`�N,�^�u0007u001BfNu000EbV�u0011r�\"�-�>eU&W�;u0007��F<4�p�b�hb�k��orb�u0011�O#�V���5+�C-������u00140=�`�u0011_Bu0012u001AWu000EfsU�u001B5D+��Q��Vv�>�ƋW�@RMu0011H�u)��$u0002U�2��,u001C��1��{|5��Ҫ���{u000Be.��'Gyf-��6Ŭr����u0014J�hu0007u000E�hU��ve��7�n��2u0001�N�qn��M���8�l�DOpď�=��{���E�͈,{渊\u007F�qu001D�@�u001A�u0013��i�̙�Tٙ�﨑���̌�u0011ҔwNs��ݾ��FL\u0382u001F@�y��,9�u000F{���,*�EU�n�l�u0010nu001Au000E�{����qu0002](�ߧUdf��u001A����q�u0017u0005u0014��o�VЭ����c�u001F=)3����u0012%�\u070E�u0016Q��mDu0000�4��g(��|u0016gV��Md;`�u001D�]E�-�mu001D�^y��u0011*���i6�4��V�_��t1������u0017+8F�ޛz2*u0012-2�E3�|y�\"�u000F���}Č�B�?���!C��p/I�i!�u0019[x�R���Ja�@o\u007F��Q���s�u00192S���u000B�*aP�wS�u0015�㿷t��nu00145u001F���XEu0001�9u0006Nu001F�Q^u001Fu0016$�d�8�ǎ �����\u007Fu0001���3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_id_dashboard);

        try {
            sharedPreferences = Util.getSharedPreferenceInstance(this,"BillsDATA",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        credential = sharedPreferences.getString("cred_1", "");
        mobileNumber = sharedPreferences.getString("cred_2", "");

        try {
            sharedHI = Util.getSharedPreferenceInstance(this,"HI",Util.getMasterKey());
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

       // sharedHI = getSharedPreferences("HI", Context.MODE_PRIVATE);
        accessToken = sharedHI.getString("accessToken", "");
        xToken = sharedHI.getString("x_token", "");
        is_loginHI = sharedHI.getBoolean("is_loginHI", false);

        if (is_loginHI){

            Intent intent = new Intent(HealthIdDashboardActivity.this,ABHAUserProfileActivity.class);
            startActivity(intent);
            finish();

        }

        aadhar_number_EditText = findViewById(R.id.aadhar_number_EditText);
        aadhar_otp_EditText = findViewById(R.id.aadhar_otp_EditText);
        verify_aadhar_OTP_Button = findViewById(R.id.verify_aadhar_OTP_Button);
        get_aadhar_OTP_Button = findViewById(R.id.get_aadhar_OTP_Button);
        otp_arear_LL = findViewById(R.id.otp_arear_LL);
        create_abha_with_aadhar_LL = findViewById(R.id.create_abha_with_aadhar_LL);
        mobile_number_verify_LL = findViewById(R.id.mobile_number_verify_LL);
        get_abha_mobile_otp_BTN = findViewById(R.id.get_abha_mobile_otp_BTN);
        abha_mobile_otp_LL = findViewById(R.id.abha_mobile_otp_LL);
        prefilled_mobile_number_TV = findViewById(R.id.prefilled_mobile_number_TV);
        abha_mobile_otp_EditText = findViewById(R.id.abha_mobile_otp_EditText);
        abha_mobile_verification_EditText = findViewById(R.id.abha_mobile_verification_EditText);
        abha_mobile_verify_BTN = findViewById(R.id.abha_mobile_verify_BTN);
        submit_abha_address_Button = findViewById(R.id.submit_abha_address_Button);
        userProfile_Image = findViewById(R.id.userProfile_Image);
        setABHA_Address_LL = findViewById(R.id.setABHA_Address_LL);
        abha_address_EditText = findViewById(R.id.abha_address_EditText);
        backArrow_IMG = findViewById(R.id.backArrow_IMG);
        disclamer_CB = findViewById(R.id.disclamer_CB);
        terms_and_conditions_TV = findViewById(R.id.terms_and_conditions_TV);
        terms_and_conditions_LL = findViewById(R.id.terms_and_conditions_LL);
        aadhar_number_area_LL = findViewById(R.id.aadhar_number_area_LL);
        mobile_number_linked_TV = findViewById(R.id.mobile_number_linked_TV);
        abdh_participating_TV = findViewById(R.id.abdh_participating_TV);

        counttime_TV = findViewById(R.id.counttime_TV);
        resent_OTP_TV = findViewById(R.id.resent_OTP_TV);

        user_name_TV = findViewById(R.id.user_name_TV);
        gender_TV = findViewById(R.id.gender_TV);
        dob_TV = findViewById(R.id.dob_TV);
        user_address_TV = findViewById(R.id.user_address_TV);
        abha_id_TV = findViewById(R.id.abha_id_TV);

       // userProfile_Image


        abha_mobile_verification_EditText.setText(mobileNumber);

        abdh_participating_TV.setMovementMethod(LinkMovementMethod.getInstance());

        disclamer_CB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                // Check which checkbox was clicked
                if (checked){

                    checkBoxStatus=true;
                }
                else{
                    // Do your coding
                    checkBoxStatus=false;

                }
            }
        });

        resent_OTP_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                ReGenerateOTP generateOtpRequest = new ReGenerateOTP();
                generateOtpRequest.setTxnId(txnIdVerifyAadharOTP);
                reGenerateAadharOtp(generateOtpRequest);

            }
        });


        terms_and_conditions_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                terms_and_conditions_LL.setVisibility(View.VISIBLE);
                create_abha_with_aadhar_LL.setVisibility(View.GONE);

            }
        });


        backArrow_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (terms_and_conditions_LL.getVisibility() == View.VISIBLE) {
                    // Its visible
                    terms_and_conditions_LL.setVisibility(View.GONE);
                    create_abha_with_aadhar_LL.setVisibility(View.VISIBLE);

                } else {
                    Intent intent = new Intent(HealthIdDashboardActivity.this, ABHALandingPageActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        get_aadhar_OTP_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (isEmpty(aadhar_number_EditText)){
                    Snackbar.make(view, getString(R.string.enter_aadhar_number), Snackbar.LENGTH_SHORT).show();
                } else if (aadhar_number_EditText.getText().length()<12){
                    Snackbar.make(view, getString(R.string.please_enter_a_correct_aadhaar_number), Snackbar.LENGTH_SHORT).show();
                } else if (!checkBoxStatus){
                    Snackbar.make(view, getString(R.string.please_accept_terms_and_conditions), Snackbar.LENGTH_SHORT).show();
                }else{
                    getAccessTokenHI();
                    aadharCardNumber=aadhar_number_EditText.getText().toString();
                }
            }
        });




        verify_aadhar_OTP_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


                if (isEmpty(aadhar_otp_EditText)){
                    Snackbar.make(view, getString(R.string.enter_otp), Snackbar.LENGTH_SHORT).show();
                } else {
                    EncryptDataRequest encryptDataRequest = new EncryptDataRequest();
                    encryptDataRequest.setData(aadhar_otp_EditText.getText().toString());
                    encData(encryptDataRequest,"VERIFY_AADHAR_OTP");
                }

            }
        });



        get_abha_mobile_otp_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (isEmpty(abha_mobile_verification_EditText)){

                    Snackbar.make(view, getString(R.string.mobile_number_is_required), Snackbar.LENGTH_SHORT).show();

                } else if (!isValidMobileNumber(abha_mobile_verification_EditText.getText().toString())){

                    Snackbar.make(view, getString(R.string.invalid_mobile_number), Snackbar.LENGTH_SHORT).show();

                }else {

                   // String myString = abha_mobile_verification_EditText.getText().toString();
                   // int mobileInt = Integer.parseInt(myString);

                    CheckAndGenerateMobileOtpRequest checkAndGenerateMobileOtpRequest = new CheckAndGenerateMobileOtpRequest();
                    checkAndGenerateMobileOtpRequest.setMobile(abha_mobile_verification_EditText.getText().toString());
                    checkAndGenerateMobileOtpRequest.setTxnId(txnIdGenerateMobileOtp);
                    getABHAMobileNUmberOtp(checkAndGenerateMobileOtpRequest);


                }

            }
        });


        abha_mobile_verify_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (isEmpty(abha_mobile_otp_EditText)){

                    Snackbar.make(view, getString(R.string.kindly_mentioned_correct_OTP), Snackbar.LENGTH_SHORT).show();

                } else {


                  EncryptDataRequest encryptDataRequest = new EncryptDataRequest();
                  encryptDataRequest.setData(abha_mobile_otp_EditText.getText().toString());
                  encData(encryptDataRequest,"VERIFY_MOBILE_OTP");


                }
            }
        });


        submit_abha_address_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (isEmpty(abha_address_EditText)){

                    Snackbar.make(view, getString(R.string.kindly_mentioned_abha_address), Snackbar.LENGTH_SHORT).show();

                } else {

                    PHRLinkedABHAAddressRequest phrLinkedABHAAddressRequest = new PHRLinkedABHAAddressRequest();
                    phrLinkedABHAAddressRequest.setPhrAddress(abha_address_EditText.getText().toString());
                    phrLinkedABHAAddressRequest.setPreferred(true);

                    createAbhaAddress(phrLinkedABHAAddressRequest);

                }
            }
        });


    }

    private void noCount(){

        new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime_TV.setText(String.valueOf(counter));
                counttime_TV.setText( getString(R.string.resend_OTP_in)+" " + millisUntilFinished / 1000 + " " + "sec");

            }
            @Override
            public void onFinish() {
                counttime_TV.setVisibility(View.GONE);
                resent_OTP_TV.setVisibility(View.VISIBLE);
            }
        }.start();


    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    public static boolean isValidMobileNumber(String s) {
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }


    private void getAccessTokenHI() {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_LIC).create(RetrofitInterface.class);

        Call<GenerateHealthIdTokenResponse> call = retroFitInterface.getAccessToken(credential);

        call.enqueue(new Callback<GenerateHealthIdTokenResponse>() {
            @Override
            public void onResponse(Call<GenerateHealthIdTokenResponse> call, Response<GenerateHealthIdTokenResponse> response) {
               // progressDialogDismiss();

                GenerateHealthIdTokenResponse body = response.body();

                if (response.code() == 200) {

                    accessToken=body.getAccessToken();

                   // editor = getSharedPreferences("HI", MODE_PRIVATE).edit();

                    SharedPreferences.Editor editor = sharedHI.edit();
                    editor.putString("accessToken", body.getAccessToken());
                    editor.apply();

                    verify_aadhar_OTP_Button.setVisibility(View.GONE);
                    EncryptDataRequest encryptDataRequest = new EncryptDataRequest();
                    encryptDataRequest.setData(aadharCardNumber);
                    encData(encryptDataRequest,"AADHAR_OTP");


                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GenerateHealthIdTokenResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void generateAadharOtp(GenerateOtpRequest generateOtpRequest) {

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<GenerateOtpResponse> call = retroFitInterface.generateOTPHI("Bearer "+accessToken,Constant.HIP_ID,generateOtpRequest);

        call.enqueue(new Callback<GenerateOtpResponse>() {
            @Override
            public void onResponse(Call<GenerateOtpResponse> call, Response<GenerateOtpResponse> response) {
                progressDialogDismiss();

                GenerateOtpResponse body = response.body();

                Log.e("hheeeee",response.code()+"");

                if (response.code() == 200) {
                    aadhar_number_area_LL.setVisibility(View.GONE);
                    otp_arear_LL.setVisibility(View.VISIBLE);
                    get_aadhar_OTP_Button.setVisibility(View.GONE);
                    verify_aadhar_OTP_Button.setVisibility(View.VISIBLE);
                    txnIdVerifyAadharOTP = body.getTxnId();
                    mobile_number_linked_TV.setText(getString(R.string.we_just_sent_an_OTP_on_the_mobile_number)+" "+body.getMobileNumber()+" "+getString(R.string.linked_with_your_aadhaar));
                    noCount();
                    //Toast.makeText(HealthIdDashboardActivity.this, "OTP Send", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    otp_arear_LL.setVisibility(View.GONE);
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    otp_arear_LL.setVisibility(View.GONE);
                    progressDialogDismiss();
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.invalid_aadhaar_number_entered), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 422) {

                    otp_arear_LL.setVisibility(View.GONE);
                    progressDialogDismiss();
                    progressDialogDismiss();

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        JSONArray jarray= jsonObject.getJSONArray("details");
                        JSONObject deatilsJsonOb = jarray.getJSONObject(0);
                        deatilsJsonOb.getString("message");
                        Log.e("error",deatilsJsonOb.getString("message"));
                        Toast.makeText(HealthIdDashboardActivity.this, deatilsJsonOb.getString("message"), Toast.LENGTH_LONG).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }                }

            }

            @Override
            public void onFailure(Call<GenerateOtpResponse> call, Throwable t) {
                progressDialogDismiss();
                otp_arear_LL.setVisibility(View.GONE);
                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void reGenerateAadharOtp(ReGenerateOTP generateOtpRequest) {
        progressDialogShow();
        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<GenerateOtpResponse> call = retroFitInterface.reGenerateAadhaarOTP("Bearer "+accessToken,Constant.HIP_ID,generateOtpRequest);

        call.enqueue(new Callback<GenerateOtpResponse>() {
            @Override
            public void onResponse(Call<GenerateOtpResponse> call, Response<GenerateOtpResponse> response) {
                progressDialogDismiss();

                GenerateOtpResponse body = response.body();

                Log.e("hheeeee",response.code()+"");

                if (response.code() == 200) {

                   /* otp_arear_LL.setVisibility(View.VISIBLE);
                    get_aadhar_OTP_Button.setVisibility(View.GONE);
                    verify_aadhar_OTP_Button.setVisibility(View.VISIBLE);
                    txnIdVerifyAadharOTP = body.getTxnId();*/
                    txnIdVerifyAadharOTP=body.getTxnId();

                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.otp_resend_successfully), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.invalid_aadhaar_number_entered), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 422) {

                    progressDialogDismiss();
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.you_have_requested_multiple_in_this_transaction), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<GenerateOtpResponse> call, Throwable t) {
                progressDialogDismiss();
                otp_arear_LL.setVisibility(View.GONE);
                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void encData(EncryptDataRequest encryptDataRequest,String encDataFor) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_LIC).create(RetrofitInterface.class);

        Call<EncryptDataResponse> call = retroFitInterface.encData(credential,encryptDataRequest);

        call.enqueue(new Callback<EncryptDataResponse>() {
            @Override
            public void onResponse(Call<EncryptDataResponse> call, Response<EncryptDataResponse> response) {
              //  progressDialogDismiss();

                EncryptDataResponse body = response.body();

                if (response.code() == 200) {

                    if (encDataFor.equalsIgnoreCase("AADHAR_OTP")){
                        GenerateOtpRequest generateOtpRequest = new GenerateOtpRequest();
                        generateOtpRequest.setAadhaar(body.getEncData());
                        generateAadharOtp(generateOtpRequest);

                    } else if (encDataFor.equalsIgnoreCase("VERIFY_AADHAR_OTP")){

                        VerifyAadharOTPRequest verifyAadharOTPRequest = new VerifyAadharOTPRequest();
                        verifyAadharOTPRequest.setOtp(body.getEncData());
                        verifyAadharOTPRequest.setTxnId(txnIdVerifyAadharOTP);
                        verifyAadharOtp(verifyAadharOTPRequest);

                    } else if(encDataFor.equalsIgnoreCase("VERIFY_MOBILE_OTP")){

                        ABHAMobileNumberVerifyRequest verifyRequest = new ABHAMobileNumberVerifyRequest();
                        verifyRequest.setTxnId(txnIdVerifyMobileOTP);
                        verifyRequest.setOtp(body.getEncData());
                        aBHAMobileNUmberOtpVerify(verifyRequest);

                    }


                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EncryptDataResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void verifyAadharOtp(VerifyAadharOTPRequest verifyAadharOTPRequest) {

         progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<VerifyAadharOtpResponse> call = retroFitInterface.verifyAadharOtp("Bearer "+accessToken,Constant.HIP_ID,verifyAadharOTPRequest);

        call.enqueue(new Callback<VerifyAadharOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyAadharOtpResponse> call, Response<VerifyAadharOtpResponse> response) {
                  progressDialogDismiss();

                  Log.e("resssss",response.code()+"");

                VerifyAadharOtpResponse body = response.body();

                if (response.code() == 200) {

                 //   Toast.makeText(HealthIdDashboardActivity.this, "Verify", Toast.LENGTH_SHORT).show();

                    txnIdGenerateMobileOtp = body.getTxnId();

                    profilePhoto = body.getPhoto();
                    firstName = body.getName();


                    if (body.getHealthIdNumber()==null){

                        // abha_id_TV.setText(body.getHealthIdNumber());
                        get_abha_mobile_otp_BTN.setVisibility(View.VISIBLE);
                        create_abha_with_aadhar_LL.setVisibility(View.GONE);
                        mobile_number_verify_LL.setVisibility(View.VISIBLE);

                    }else {

                      /*  byte[] bytes = new byte[0];
                        bytes = body.getPhoto().getBytes();

                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        userProfile_Image.setImageBitmap(bitmap);*/

                        byte[] decodedString = Base64.decode(body.getPhoto(), Base64.DEFAULT);

                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        userProfile_Image.setImageBitmap(decodedByte);

                        user_name_TV.setText(body.getName());
                        gender_TV.setText(body.getGender()+" "+"| DOB ");
                        dob_TV.setText(body.getBirthdate());
                        user_address_TV.setText(body.getDistrict()+", "+body.getState());

                        abha_id_TV.setText(getString(R.string.abha_number)+" "+body.getHealthIdNumber());

                        if (body.getJwtResponse()==null){

                            SharedPreferences.Editor editor = sharedHI.edit();
                            editor.putString("x_token",body.getJwtResponse().getToken());
                            editor.putString("x_refresh_token",body.getJwtResponse().getRefreshToken());

                            editor.apply();

                            x_token= body.getJwtResponse().getToken();

                            create_abha_with_aadhar_LL.setVisibility(View.GONE);
                            mobile_number_verify_LL.setVisibility(View.GONE);
                            setABHA_Address_LL.setVisibility(View.VISIBLE);

                        } else {

                          //  editor = getSharedPreferences("HI", MODE_PRIVATE).edit();
                            SharedPreferences.Editor editor = sharedHI.edit();
                            editor.putString("x_token",body.getJwtResponse().getToken());
                            editor.putString("x_refresh_token",body.getJwtResponse().getRefreshToken());
                            editor.apply();

                            x_token= body.getJwtResponse().getToken();

                            getABHAProfile(body.getJwtResponse().getToken());


                        }

                    }

                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("BillsDATA", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();

                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.invalid_OTP), Toast.LENGTH_SHORT).show();

                } else if (response.code() == 422) {
                    progressDialogDismiss();

                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.invalid_OTP), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<VerifyAadharOtpResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getABHAMobileNUmberOtp(CheckAndGenerateMobileOtpRequest checkAndGenerateMobileOtpRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<CheckAndGenerateMobileResponse> call = retroFitInterface.getABHAMobileNumberOtp("Bearer "+accessToken,Constant.HIP_ID,checkAndGenerateMobileOtpRequest);

        call.enqueue(new Callback<CheckAndGenerateMobileResponse>() {
            @Override
            public void onResponse(Call<CheckAndGenerateMobileResponse> call, Response<CheckAndGenerateMobileResponse> response) {
                progressDialogDismiss();

                CheckAndGenerateMobileResponse body = response.body();

                if (response.code() == 200) {

                 //   Toast.makeText(HealthIdDashboardActivity.this, "Mobile send", Toast.LENGTH_SHORT).show();

                    txnIdVerifyMobileOTP= body.getTxnId();


                    if (body.isMobileLinked()){

                        CreateHealthIdWithPreVerifiedRequest checkAndGenerateMobileOtpRequest = new CreateHealthIdWithPreVerifiedRequest();
                        checkAndGenerateMobileOtpRequest.setFirstName(firstName);
                        checkAndGenerateMobileOtpRequest.setProfilePhoto(profilePhoto);
                        checkAndGenerateMobileOtpRequest.setTxnId(txnIdVerifyMobileOTP);
                        createHealthIdPreVerified(checkAndGenerateMobileOtpRequest);


                    } else {

                        abha_mobile_otp_LL.setVisibility(View.VISIBLE);
                        setABHA_Address_LL.setVisibility(View.GONE);
                        create_abha_with_aadhar_LL.setVisibility(View.GONE);
                        prefilled_mobile_number_TV.setVisibility(View.VISIBLE);
                        mobile_number_verify_LL.setVisibility(View.VISIBLE);
                        get_abha_mobile_otp_BTN.setVisibility(View.GONE);
                        abha_mobile_verify_BTN.setVisibility(View.VISIBLE);
                    }



                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CheckAndGenerateMobileResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void aBHAMobileNUmberOtpVerify(ABHAMobileNumberVerifyRequest checkAndGenerateMobileOtpRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<ABHAMobileNumberVerifyResponse> call = retroFitInterface.verifyABHAMobileNumberOtp("Bearer "+accessToken,Constant.HIP_ID,checkAndGenerateMobileOtpRequest);

        call.enqueue(new Callback<ABHAMobileNumberVerifyResponse>() {
            @Override
            public void onResponse(Call<ABHAMobileNumberVerifyResponse> call, Response<ABHAMobileNumberVerifyResponse> response) {
                progressDialogDismiss();

                ABHAMobileNumberVerifyResponse body = response.body();

                if (response.code() == 200) {


                    txnIdCreateHealthIdPreVerified= body.getTxnId();


                    CreateHealthIdWithPreVerifiedRequest checkAndGenerateMobileOtpRequest = new CreateHealthIdWithPreVerifiedRequest();
                    checkAndGenerateMobileOtpRequest.setFirstName(firstName);
                    checkAndGenerateMobileOtpRequest.setProfilePhoto(profilePhoto);
                    checkAndGenerateMobileOtpRequest.setTxnId(txnIdCreateHealthIdPreVerified);
                    createHealthIdPreVerified(checkAndGenerateMobileOtpRequest);



                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ABHAMobileNumberVerifyResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void createHealthIdPreVerified(CreateHealthIdWithPreVerifiedRequest checkAndGenerateMobileOtpRequest) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<CreateHealthIdWithPreVerifiedResponse> call = retroFitInterface.createHealthWithPreVerified("Bearer "+accessToken,Constant.HIP_ID,checkAndGenerateMobileOtpRequest);

        call.enqueue(new Callback<CreateHealthIdWithPreVerifiedResponse>() {
            @Override
            public void onResponse(Call<CreateHealthIdWithPreVerifiedResponse> call, Response<CreateHealthIdWithPreVerifiedResponse> response) {
                progressDialogDismiss();

                CreateHealthIdWithPreVerifiedResponse body = response.body();

                if (response.code() == 200) {

                        setABHA_Address_LL.setVisibility(View.VISIBLE);
                        mobile_number_verify_LL.setVisibility(View.GONE);
                        abha_mobile_otp_LL.setVisibility(View.GONE);
                        create_abha_with_aadhar_LL.setVisibility(View.GONE);

                       // Log.e("Imageeeee",body.getProfilePhoto());


                   /* byte[] bytes = new byte[0];

                        bytes = body.getProfilePhoto().getBytes();

                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    userProfile_Image.setImageBitmap(bitmap);*/

                    byte[] decodedString = Base64.decode(profilePhoto, Base64.DEFAULT);
                  //  byte[] decodedString = java.util.Base64.getDecoder().decode(body.getProfilePhoto().getBytes(), Base64.DEFAULT);

                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    userProfile_Image.setImageBitmap(decodedByte);

                    user_name_TV.setText(body.getName());
                    gender_TV.setText(body.getGender()+" "+"| DOB ");
                    dob_TV.setText(body.getDayOfBirth()+"-"+body.getMonthOfBirth()+"-"+body.getYearOfBirth());
                    user_address_TV.setText(body.getDistrictName()+", "+body.getStateName());

                    abha_id_TV.setText(getString(R.string.abha_number)+" "+body.getHealthIdNumber());

                    SharedPreferences.Editor editor = sharedHI.edit();
                    editor.putString("x_token", body.getToken());
                    editor.putString("x_refresh_token",body.getRefreshToken());
                    editor.apply();

                    x_token= body.getToken();

                } else if (response.code() == 401) {
                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateHealthIdWithPreVerifiedResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void createAbhaAddress(PHRLinkedABHAAddressRequest phrLinkedABHAAddressRequest) {

         progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<PHRLinkedABHAAddressResponse> call = retroFitInterface.phrLinked("Bearer "+accessToken,Constant.HIP_ID,"Bearer "+x_token,phrLinkedABHAAddressRequest);

        call.enqueue(new Callback<PHRLinkedABHAAddressResponse>() {
            @Override
            public void onResponse(Call<PHRLinkedABHAAddressResponse> call, Response<PHRLinkedABHAAddressResponse> response) {
                progressDialogDismiss();

                PHRLinkedABHAAddressResponse body = response.body();

                if (response.code() == 200) {

                    SharedPreferences.Editor editor = sharedHI.edit();
                    editor.putBoolean("is_loginHI",true);
                    editor.apply();

                    Intent intent = new Intent(HealthIdDashboardActivity.this,ABHAUserProfileActivity.class);
                    startActivity(intent);
                    finish();


                } else if (response.code() == 401) {

                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {
                    progressDialogDismiss();
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else if (response.code()== 422){
                    progressDialogDismiss();
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.phr_address_already_exists_try_another), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<PHRLinkedABHAAddressResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getABHAProfile(String xTok) {

        progressDialogShow();

        retroFitInterface = RetrofitClient.getClient(Constant.BASE_URL_HEALTH_ID).create(RetrofitInterface.class);

        Call<ABHAProfileResponse> call = retroFitInterface.getABHAUsersProfile("Bearer "+accessToken,Constant.HIP_ID,"Bearer "+xTok);

        call.enqueue(new Callback<ABHAProfileResponse>() {
            @Override
            public void onResponse(Call<ABHAProfileResponse> call, Response<ABHAProfileResponse> response) {

                progressDialogDismiss();

                ABHAProfileResponse body = response.body();

                if (response.code() == 200) {


                    if (body.getHealthId()==null){

                        create_abha_with_aadhar_LL.setVisibility(View.GONE);
                        mobile_number_verify_LL.setVisibility(View.GONE);
                        setABHA_Address_LL.setVisibility(View.VISIBLE);

                    }else {

                        SharedPreferences.Editor editor = sharedHI.edit();
                        editor.putBoolean("is_loginHI",true);
                        editor.apply();

                        Intent intent = new Intent(HealthIdDashboardActivity.this,ABHAUserProfileActivity.class);
                        startActivity(intent);
                        finish();


                    }



                } else if (response.code() == 401) {

                    progressDialogDismiss();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HealthIdDashboardActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setContentText(getString(R.string.unable_to_connect_to_server));
                    sweetAlertDialog.setConfirmText(getString(R.string.login_again));
                    sweetAlertDialog.setCustomImage(R.drawable.nict_bills_services_logo);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            SharedPreferences preferences = getSharedPreferences("HI", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(HealthIdDashboardActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                            sDialog.dismissWithAnimation();

                        }
                    })
                            .show();


                } else if (response.code() == 500) {
                    progressDialogDismiss();
                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());
                        jsonObject.getString("error");
                        Log.e("error",jsonObject.getString("error"));
                        Toast.makeText(HealthIdDashboardActivity.this, jsonObject.getString("error"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HealthIdDashboardActivity.this,getString(R.string.something_went_wrong) , Toast.LENGTH_SHORT).show();
                    }

                } else if (response.code() == 400) {

                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ABHAProfileResponse> call, Throwable t) {
                progressDialogDismiss();

                Toast.makeText(HealthIdDashboardActivity.this, getString(R.string.something_went_wrong_please_try_again_later), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {

        if (terms_and_conditions_LL.getVisibility() == View.VISIBLE) {
            // Its visible
            terms_and_conditions_LL.setVisibility(View.GONE);
            create_abha_with_aadhar_LL.setVisibility(View.VISIBLE);

        }else {
            Intent intent = new Intent(HealthIdDashboardActivity.this, ABHALandingPageActivity.class);
            startActivity(intent);
            finish();
        }


    }
}