package co.fusionx.spotify.component.sync;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

import co.fusionx.spotify.component.TrackComponent;
import co.fusionx.spotify.model.Track;
import co.fusionx.spotify.model.jackson.JacksonTrack;
import co.fusionx.spotify.util.Util;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class SyncTrackComponent extends SyncBaseComponent implements TrackComponent {

    private final SyncTrackAPI mSyncTrackAPI;

    public SyncTrackComponent() {
        mSyncTrackAPI = mRestAdapter.create(SyncTrackAPI.class);
    }

    @Override
    public Track getTrack(String id) {
        return mSyncTrackAPI.getTrack(id);
    }

    @Override
    public List<? extends Track> getTracks(final Collection<String> tracksIds) {
        final String ids = Util.join(tracksIds, ",");
        return mSyncTrackAPI.getTracks(ids).getTracks();
    }

    public static class Tracks {

        @JsonProperty(value = "tracks")
        public List<JacksonTrack> mTracks;

        public List<JacksonTrack> getTracks() {
            return mTracks;
        }
    }

    private static interface SyncTrackAPI {

        @GET("/tracks/{id}")
        public JacksonTrack getTrack(@Path("id") final String id);

        @GET("/tracks")
        public Tracks getTracks(@Query("ids") final String id);
    }
}