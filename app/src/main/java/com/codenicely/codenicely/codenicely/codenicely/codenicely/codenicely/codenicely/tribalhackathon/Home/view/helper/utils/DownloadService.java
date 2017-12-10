package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.utils;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by aman on 4/7/17.
 */

public class DownloadService extends IntentService {
    public static final int UPDATE_PROGRESS = 8344;
    String fileName;
    File outputFile;
    public DownloadService() {
        super("DownloadService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String urlToDownload = intent.getStringExtra("url");
        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        try {
            URL url = new URL(urlToDownload);
            URLConnection connection = url.openConnection();
            connection.connect();
            // this will be useful so that you can show a typical 0-100% progress bar
            int fileLength = connection.getContentLength();
            fileName= Uri.parse(urlToDownload).getLastPathSegment();
            // download the file
            InputStream input = new BufferedInputStream(connection.getInputStream());
            Log.d("DownloadService",""+getFilesDir());
            OutputStream output;
            try {
                outputFile = new File(Environment.getExternalStorageDirectory()+"/GSTInvoiceMaker/",
                        fileName);
                output = new FileOutputStream(outputFile);
            }catch (FileNotFoundException e){
                e.printStackTrace();
                Log.d("DownloadService","GstInvoicemaker Directory does not exist");
                File GSTInvoiceMaker = new File(Environment.getExternalStorageDirectory()+"/GSTInvoiceMaker/");
                GSTInvoiceMaker.mkdirs();
                outputFile= new File(GSTInvoiceMaker,fileName);
                output = new FileOutputStream(outputFile);

            }

            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                Log.d("While","Count:"+String.valueOf(count));
                Log.d("While","Total:"+String.valueOf(total));



                total += count;
                // publishing the progress....
                Bundle resultData = new Bundle();
                resultData.putInt("progress" ,(int) (total * 100 / fileLength));
                receiver.send(UPDATE_PROGRESS, resultData);
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();

            Bundle resultData = new Bundle();
            resultData.putInt("progress" ,101);
            resultData.putString("filename",fileName);
            resultData.putString("file_path",outputFile.getAbsolutePath());
            receiver.send(UPDATE_PROGRESS, resultData);

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("DownloadServException","Failure");
        }

    }
}
