package com.dieam.reactnativepushnotification.modules;

import android.content.Context;

import android.graphics.Bitmap;

import android.net.Uri;

import android.text.TextUtils;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;

import com.facebook.imagepipeline.image.CloseableImage; 

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoHelper {

    public static Uri getImageUri(String url){
        if (TextUtils.isEmpty(url)){
            return null;
        }
        return Uri.parse(url);
    }

    public static void loadBitmap(String url, BaseBitmapDataSubscriber baseBitmapDataSubscriber, int width, int height, Context context) {
        Uri uri = getImageUri(url);
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).setResizeOptions(new ResizeOptions(width, height)).build();
        loadBitmap(imageRequest, baseBitmapDataSubscriber, context);
    }

    public static void loadBitmap(ImageRequest imageRequest, BaseBitmapDataSubscriber baseBitmapDataSubscriber, Context context) {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
        dataSource.subscribe(baseBitmapDataSubscriber, CallerThreadExecutor.getInstance());
    }

}
