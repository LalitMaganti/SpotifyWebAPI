package co.fusionx.spotify.webapi.sync;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import co.fusionx.spotify.webapi.component.retrofit.RetrofitTrackComponent;
import co.fusionx.spotify.model.Track;

import static org.assertj.core.api.Assertions.assertThat;

public class SyncTrackComponentTest {

    private final RetrofitTrackComponent mClient;

    public SyncTrackComponentTest() {
        mClient = new RetrofitTrackComponent();
    }

    @Test
    public void getTrackTest() {
        final Track track = mClient.getTrack("27jdUE1EYDSXZqhjuNxLem");
        assertThat(track.getName()).isEqualTo("Magic");
    }

    @Test
    public void getTracksTest() {
        final String[] strings = {"27jdUE1EYDSXZqhjuNxLem", "3vCzHYgSjMuGjFMfJSCx4c",
                "6nek1Nin9q48AVZcWs9e9D"};
        final List<? extends Track> trackList = mClient.getTracks(Arrays.asList(strings));
        assertThat(trackList)
                .extractingResultOf("getName", String.class)
                .containsExactly("Magic", "Mylo Xyloto", "Paradise");
        assertThat(trackList)
                .extractingResultOf("getId", String.class)
                .containsExactly(strings);
    }
}