package co.fusionx.spotify.async;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.junit.Test;

import co.fusionx.spotify.model.Album;

import static org.assertj.core.api.Assertions.assertThat;

public class AsyncWebClientTest {

    private final AsyncWebClient mClient;

    public AsyncWebClientTest() {
        mClient = new AsyncWebClient();
    }

    @Test
    public void testGetAlbum() {
        final ListenableFuture<Album> future = mClient.getAlbum("1xn54DMo2qIqBuMqHtUsFd");
        Futures.addCallback(future, new FutureCallback<Album>() {
            @Override
            public void onSuccess(final Album album) {
                assertThat(album.getName()).isEqualTo("x (Deluxe Edition)");
                assertThat(false).isTrue();
            }

            @Override
            public void onFailure(final Throwable t) {
                t.printStackTrace();
            }
        });
    }
}