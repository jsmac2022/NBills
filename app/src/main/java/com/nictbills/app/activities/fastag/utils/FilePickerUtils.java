package com.nictbills.app.activities.fastag.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilePickerUtils {

    private static final String TAG = "FilePickerUtils";

    @SuppressLint("Range")
    public static String getFileName(Context context, Uri uri) {
       // String pathName = getPath(context, uri);
       // Log.e(TAG, "getFileName: pathName : "+pathName );
        String uriString = uri.toString();
        String selectedFileName = null;

      //  String[] projection = { MediaStore.Files.FileColumns.DATA};

       // Log.e(TAG, "getFileName: projection" +projection );

        if (uriString.startsWith("content://")) {
            Cursor myCursor = null;
            try {
                myCursor = context.getContentResolver().query(uri, null, null, null, null);
                if (myCursor != null && myCursor.moveToFirst()) {
                    Log.e(TAG, "getFileName: index "+myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME) );

                   // String columnIndex = myCursor.getColumnIndex(projection[0]);
                   // String picturePath = myCursor.getString(columnIndex);

//                    byte[] fileByte = myCursor.getBlob(myCursor.getColumnIndex(OpenableColumns.SIZE));
//                    Log.e(TAG, "getFileName: fileByte "+fileByte );
                    selectedFileName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    String selectedFileSize = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.SIZE));
                    Log.e(TAG, "getFileName: selectedFileName : "+selectedFileName );
                    Log.e(TAG, "getFileName: selectedFileSize : "+selectedFileSize );
                   // fileName.setText(selectedFileName);
                }
            } finally {
                myCursor.close();
            }
        }
        return selectedFileName;
    }

    private static String getPath(Context context, Uri uri) {
        String path = null;
        String[] projection = { MediaStore.Files.FileColumns.DATA, MediaStore.Files.FileColumns.RELATIVE_PATH };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if(cursor == null){
            path = uri.getPath();
        }
        else{
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            Log.e(TAG, "getPath: "+path );
            cursor.close();
        }

        return ((path == null || path.isEmpty()) ? (uri.getPath()) : path);
    }
    public static File getFileFromContentUri(Context context, Uri contentUri, String selectedFileName) throws IOException {
        // Preparing Temp file name
        String fileExtension = getFileExtension(context, contentUri);
        //Log.e(TAG, "fileExtension: "+fileExtension );
        // Creating Temp file
        File tempFile = new File(context.getCacheDir(), "temp_"+selectedFileName);
        //Log.e("vinod", "fileFromContentUri: tempFile" + tempFile);
        boolean isCreated = tempFile.createNewFile();
        //Log.e(TAG, "getFileFromContentUri: isCreated "+isCreated );
       try {
            FileOutputStream oStream = new FileOutputStream(tempFile);
            //Log.e(TAG, "getFileFromContentUri: contentUri :"+contentUri.getPath() );
            InputStream inputStream = context.getContentResolver().openInputStream(contentUri);
            //write file here
            copy(inputStream,oStream);
            oStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    private static String getFileExtension(Context context, Uri uri) {
        String fileType = context.getContentResolver().getType(uri);
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(fileType);
    }

    private static void copy(InputStream source, FileOutputStream target) throws IOException {
        byte[] buf = new byte[8192];
        int length;
        while ((length = source.read(buf)) > 0) {
            target.write(buf, 0, length);
        }
        source.close();
        target.close();
    }
}
