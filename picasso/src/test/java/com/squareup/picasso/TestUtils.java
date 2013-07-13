package com.squareup.picasso;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestUtils {

  static final Uri URI_1 = Uri.parse("http://example.com/1.png");
  static final Uri URI_2 = Uri.parse("http://example.com/2.png");
  static final String KEY_1 = URI_1.toString() + "\nempty()";
  static final String KEY_2 = URI_2.toString() + "\nempty()";
  static final Bitmap BITMAP_1 = Bitmap.createBitmap(10, 10, null);

  static Request mockRequest(String key, Uri uri) {
    return mockRequest(key, uri , null);
  }

  static Request mockRequest(String key, Uri uri, Object target) {
    Request request = mock(Request.class);
    when(request.getKey()).thenReturn(key);
    when(request.getUri()).thenReturn(uri);
    when(request.getTarget()).thenReturn(target);
    return request;
  }

  static ImageView mockImageViewTarget() {
    return mock(ImageView.class);
  }

  static Target mockTarget() {
    return mock(Target.class);
  }

  static BitmapHunter mockHunter(String key, Bitmap result, boolean skipCache) {
    BitmapHunter hunter = mock(BitmapHunter.class);
    when(hunter.getKey()).thenReturn(key);
    when(hunter.getResult()).thenReturn(result);
    when(hunter.shouldSkipCache()).thenReturn(skipCache);
    hunter.retryCount = BitmapHunter.DEFAULT_RETRY_COUNT;
    return hunter;
  }

  TestUtils() {
  }
}
