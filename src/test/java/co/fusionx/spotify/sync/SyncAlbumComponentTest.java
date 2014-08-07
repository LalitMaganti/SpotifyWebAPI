package co.fusionx.spotify.sync;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import co.fusionx.spotify.component.sync.SyncAlbumComponent;
import co.fusionx.spotify.model.Album;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SimpleTrack;

import static org.assertj.core.api.Assertions.assertThat;

public class SyncAlbumComponentTest {

    private final SyncAlbumComponent mClient;

    public SyncAlbumComponentTest() {
        mClient = new SyncAlbumComponent();
    }

    @Test
    public void getAlbumTest() {
        final Album album = mClient.getAlbum("1xn54DMo2qIqBuMqHtUsFd");
        assertThat(album.getName()).isEqualTo("x (Deluxe Edition)");
    }

    @Test
    public void getAlbumsTest() {
        final String[] strings = {"1xn54DMo2qIqBuMqHtUsFd", "4ImqOjB9lCi86ZhshUbNUm",
                "2R7iJz5uaHjLEVnMkloO18"};
        final List<? extends Album> artists = mClient.getAlbums(Arrays.asList(strings));
        assertThat(artists).extractingResultOf("getName", String.class).containsExactly("x "
                + "(Deluxe Edition)", "Ghost Stories", "Mylo Xyloto");
    }

    @Test
    public void getAlbumTracksTest() {
        final PagingObject<? extends SimpleTrack> tracks = mClient
                .getAlbumTracks("2R7iJz5uaHjLEVnMkloO18");
        assertThat(tracks.getItems()).extractingResultOf("getName",
                String.class).containsSequence("Hurts Like Heaven", "Paradise", "Charlie Brown");
    }
}