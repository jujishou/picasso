package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.IOException;

import static com.squareup.picasso.Utils.calculateInSampleSize;

class ResourceBitmapHunter extends BitmapHunter {

  private final int resourceId;
  private final Context context;

  ResourceBitmapHunter(Context context, Dispatcher dispatcher, Request request) {
    super(dispatcher, request);
    this.context = context;
    this.resourceId = request.resourceId;
  }

  @Override Bitmap load(Uri uri, PicassoBitmapOptions options) throws IOException {
    return decodeResource(context.getResources(), resourceId, options);
  }

  Bitmap decodeResource(Resources resources, int resourceId, PicassoBitmapOptions bitmapOptions) {
    if (bitmapOptions != null && bitmapOptions.inJustDecodeBounds) {
      BitmapFactory.decodeResource(resources, resourceId, bitmapOptions);
      calculateInSampleSize(bitmapOptions);
    }
    return BitmapFactory.decodeResource(resources, resourceId, bitmapOptions);
  }
}
