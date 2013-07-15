package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static com.squareup.picasso.BitmapHunter.forRequest;
import static com.squareup.picasso.TestUtils.FILE_1_URL;
import static com.squareup.picasso.TestUtils.FILE_KEY_1;
import static com.squareup.picasso.TestUtils.RESOURCE_ID_1;
import static com.squareup.picasso.TestUtils.RESOURCE_ID_KEY_1;
import static com.squareup.picasso.TestUtils.URI_1;
import static com.squareup.picasso.TestUtils.mockRequest;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class BitmapHunterTest {

  @Mock Context context;
  @Mock Dispatcher dispatcher;
  @Mock Downloader downloader;

  @Test public void forNetworkRequest() {
    Request request = mockRequest(TestUtils.URI_KEY_1, URI_1);
    BitmapHunter hunter = forRequest(context, dispatcher, request, downloader);
    assertThat(hunter).isInstanceOf(NetworkBitmapHunter.class);
  }

  @Test public void forFileWithAuthorityRequest() {
    Request request = mockRequest(FILE_KEY_1, FILE_1_URL);
    BitmapHunter hunter = forRequest(context, dispatcher, request, downloader);
    assertThat(hunter).isInstanceOf(FileBitmapHunter.class);
  }

  @Test public void forAndroidResourceRequest() {
    Request request = mockRequest(RESOURCE_ID_KEY_1, null, null, RESOURCE_ID_1);
    BitmapHunter hunter = forRequest(context, dispatcher, request, downloader);
    assertThat(hunter).isInstanceOf(ResourceBitmapHunter.class);
  }

  private static class TestableBitmapHunter extends BitmapHunter {
    TestableBitmapHunter(Dispatcher dispatcher, Request request) {
      super(dispatcher, request);
    }

    @Override Bitmap load(Uri uri, PicassoBitmapOptions options) throws IOException {
      return null;
    }
  }
}
