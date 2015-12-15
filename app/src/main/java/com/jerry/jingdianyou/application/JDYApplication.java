package com.jerry.jingdianyou.application;

import android.app.Application;
import android.os.Environment;

import com.jerry.jingdianyou.R;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;

/**
 * Created by Jerry.Zou
 */
public class JDYApplication extends Application
{
  private static JDYApplication app;
  private ImageLoader mImageLoader;
  private HttpUtils mHttpUtils;
  private DisplayImageOptions mOptions;

  private static final int POOL_SIZE = 5;
  private static final int RETRY_COUNT = 3;
  private static final String CHARSET = "utf-8";
  private static final int TIMEOUT = 30 * 1000;
  private static final int CACHE_SIZE = 200 * 200 * 1024;
  private static final int CACHE_FILE_COUNt = 300;

  public static JDYApplication getApp()
  {
    return app;
  }

  @Override
  public void onCreate()
  {
    super.onCreate();
    app = this;
    initHttpUtils();
    initImageLoader();
  }

  /**
   * 初始化 HttpUtils
   */
  private void initHttpUtils()
  {
    mHttpUtils = new HttpUtils();
    mHttpUtils.configRequestThreadPoolSize(POOL_SIZE);
    mHttpUtils.configRequestRetryCount(RETRY_COUNT);
    mHttpUtils.configResponseTextCharset(CHARSET);
    mHttpUtils.configSoTimeout(TIMEOUT);
  }

  /**
   * 初始化 ImageLoader
   */
  private void initImageLoader()
  {
    mImageLoader = ImageLoader.getInstance();

    // 用于缓存的内存大小
    int cacheSize = (int) Runtime.getRuntime().maxMemory() / 8;

    // 缓存路径
    String cachePath = null;

    // 判断存储卡的状态
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
    {
      cachePath = getApplicationContext().getExternalCacheDir().getPath();
    }
    else
    {
      cachePath = getApplicationContext().getCacheDir().getPath();
    }

    // 用存储缓存图片的文件夹
    File cacheFileDir = new File(cachePath, "/jingdianyou/images");

    cacheFileDir.mkdirs();

    // 配置UniversalImageLoader
    ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
        .Builder(getApplicationContext())
        .memoryCacheSize(cacheSize)
        .diskCache(new UnlimitedDiskCache(cacheFileDir))
        .diskCacheSize(CACHE_SIZE)
        .diskCacheFileCount(CACHE_FILE_COUNt)
        .build();

    // 初始化 配置 ImageLoader
    ImageLoader.getInstance().init(configuration);

    mOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
        .showImageOnLoading(R.mipmap.no_picture)
        .showImageOnFail(R.mipmap.no_picture)
        .showImageForEmptyUri(R.mipmap.head)
        .build();
  }

  public HttpUtils getmHttpUtils()
  {
    return this.mHttpUtils;
  }

  public ImageLoader getmImageLoader()
  {
    return this.mImageLoader;
  }

  public DisplayImageOptions getmOptions()
  {
    return this.mOptions;
  }
}
