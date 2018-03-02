package com.example.wr.crawler.data.remote;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.wr.crawler.App;
import com.example.wr.crawler.data.remote.dto.ImageDTO;
import com.example.wr.crawler.data.remote.service.BaseUrl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by WR on 2017-11-29.
 */

public class RemoteRepository {

    @Inject
    RemoteRepository() {
    }

    public Single<ArrayList<ImageDTO>> getImageList() {
        Single<ArrayList<ImageDTO>> getImageListSingle = Single.create(emitter -> {
            try {
                Document doc = Jsoup.connect(BaseUrl.BASE_URL).get();
                Elements items = doc.select(".gallery-item-group");
                ArrayList<ImageDTO> itemList = new ArrayList<>();
                for (int i = 0 ; i < items.size() ; i ++) {
                    Element item = items.get(i);
                    String imgSrc = item.select("img").first().absUrl("src");
                    String caption = item.select("p").text();
                    ImageDTO dto = new ImageDTO(imgSrc, caption);
                    itemList.add(dto);
                }
                emitter.onSuccess(itemList);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
        return getImageListSingle;
    }

    public File downloadImageFromURL(String src, String localPath) throws IOException {
        try {
            URL url = new URL(src);
            URLConnection connection = url.openConnection();
            connection.connect();

            long totalFileLength = connection.getContentLength();

            InputStream input = new BufferedInputStream(url.openStream());

            OutputStream output = new FileOutputStream(localPath);

            byte data[] = new byte[1024];

            long currentTotal = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                currentTotal += count;
                output.write(data, 0, count);
            }

            output.flush();

            // closing streams
            output.close();
            input.close();

            File file = new File(localPath);
            if (currentTotal != totalFileLength) {
                Log.d("RemoteRepository", "Download not complete. Remove Image FIle");
                file.delete();
                return null;
            }
            return file;

        } catch (IOException e) {
            return null;
        }
    }

}
