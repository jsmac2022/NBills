package com.nictbills.app.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static SharedPreferences sharedPreferences;

    public static boolean isValidMobileNumber(String s) {

        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }


    public static String getUserFormatDate(String date) throws ParseException {
        if(date==null || date.equals("")) {
            return "0000-00-00";
        }

        DateFormat inputDate = new SimpleDateFormat("yyyy-mm-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy");
        return outputFormat.format(inputDate.parse(date));
    }



    public static String getMimeType(Context context, Uri uri) {
        String extension;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());
        }
        return extension;
    }


    public static String getMasterKey() throws GeneralSecurityException, IOException {
        return MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
    }


    public static SharedPreferences getSharedPreferenceInstance(Activity activity, String sharedPreferencesFileName, String masterKeyAlias) throws GeneralSecurityException, IOException {
        sharedPreferences = EncryptedSharedPreferences.create(
                sharedPreferencesFileName,
                masterKeyAlias,
                activity,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
        return sharedPreferences;
    }

}
