package com.example.wr.crawler.data.local;

/**
 * Created by loadm on 2018-03-04.
 */

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by loadm on 2018-03-03.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageCacheTest {

    private ImageCache imageCache;

    @Before
    public void setUp() {
        imageCache = new ImageCache();
        try {
            while (!imageCache.isDiskCacheReady())
                Thread.sleep(100);
        }catch (Exception e){}
    }

    @Test
    public void a_cacheDirectoryTest() {
        File cacheDir = imageCache.getDiskCacheDir();
        assertTrue(cacheDir.exists());
    }

    @Test
    public void b_cacheHitTestBeforeAdd() {
        String key = "test";
        assertFalse(imageCache.hasCache(key));
    }

    @Test
    public void c_addCacheTest() {
        String key = "test";
        assertNull(imageCache.getBitmapFileFromDiskCache(key));
        try {
            File testFile = new File(imageCache.getDiskCacheFileName(key));
            assertTrue(testFile.exists());
            imageCache.addBitmapToCache(key, testFile);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void d_cacheHitTest() {
        String key = "test";
        assertNotNull(imageCache.hasCache(key));
    }

    @Test
    public void e_cleanUp() {
        String key = "test";
        try {
            File testFile = new File(imageCache.getDiskCacheFileName(key));
            assertTrue(testFile.exists());
            assertTrue(testFile.delete());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
