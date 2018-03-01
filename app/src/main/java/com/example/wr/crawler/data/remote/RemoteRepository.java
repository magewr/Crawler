package com.example.wr.crawler.data.remote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.wr.crawler.data.remote.dto.ImageDTO;
import com.example.wr.crawler.data.remote.service.BaseUrl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public Bitmap downloadImageFromURL(String src) throws IOException {
        Bitmap bitmap = null;
        InputStream iStream = null;
        URL url = new URL(src);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000 /* milliseconds */);
        conn.setConnectTimeout(7000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        iStream = conn.getInputStream();
        bitmap = BitmapFactory.decodeStream(iStream);
        iStream.close();
        return bitmap;
    }

}
