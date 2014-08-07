package co.fusionx.spotify.sync;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import co.fusionx.spotify.component.sync.SyncArtistComponent;
import co.fusionx.spotify.model.Artist;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SimpleAlbum;
import co.fusionx.spotify.model.Track;

import static org.assertj.core.api.Assertions.assertThat;

public class SyncArtistComponentTest {

    private final SyncArtistComponent mClient;

    public SyncArtistComponentTest() {
        mClient = new SyncArtistComponent();
    }

    @Test
    public void getArtistTest() {
        final Artist artist = mClient.getArtist("4gzpq5DPGxSnKTe4SA8HAU");
        assertThat(artist.getName()).isEqualTo("Coldplay");
    }

    @Test
    public void getArtistsTest() {
        final String[] strings = {"4gzpq5DPGxSnKTe4SA8HAU", "66CXWjxzNUsdJxJ2JdwvnR",
                "6eUKZXaKkcviH0Ku9w2n3V"};
        final List<? extends Artist> artists = mClient.getArtists(Arrays.asList(strings));
        assertThat(artists).extractingResultOf("getName", String.class).containsExactly("Coldplay",
                "Ariana Grande", "Ed Sheeran");
    }

    @Test
    public void getAlbumsTest() {
        final PagingObject<? extends SimpleAlbum> albums = mClient
                .getArtistAlbums("66CXWjxzNUsdJxJ2JdwvnR");
        assertThat(albums.getItems()).extractingResultOf("getName",
                String.class).containsSequence("Problem");
    }

    @Test
    public void getTopTracksTest() {
        final List<? extends Track> tracks = mClient
                .getArtistTopTracks("6eUKZXaKkcviH0Ku9w2n3V", "GB");
        assertThat(tracks).extractingResultOf("getName", String.class).contains("I See Fire");
    }

    @Test
    public void getRelatedArtistsTest() {
        final List<? extends Artist> artists = mClient.getRelatedArtists("6eUKZXaKkcviH0Ku9w2n3V");
        assertThat(artists).extractingResultOf("getName", String.class).contains("Coldplay");
    }
}