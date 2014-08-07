package co.fusionx.spotify.component.sync;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

import co.fusionx.spotify.component.AlbumComponent;
import co.fusionx.spotify.model.Album;
import co.fusionx.spotify.model.jackson.JacksonAlbum;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SimpleTrack;
import co.fusionx.spotify.model.jackson.JacksonPagingObject;
import co.fusionx.spotify.model.jackson.JacksonSimpleTrack;
import co.fusionx.spotify.optional.artist.OptionalAlbumTracks;
import co.fusionx.spotify.util.Util;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class SyncAlbumComponent extends SyncBaseComponent implements AlbumComponent {

    private final SyncAlbumAPI mSyncAlbumAPI;

    public SyncAlbumComponent() {
        mSyncAlbumAPI = mRestAdapter.create(SyncAlbumAPI.class);
    }

    @Override
    public Album getAlbum(final String albumId) {
        return mSyncAlbumAPI.getAlbum(albumId);
    }

    @Override
    public List<? extends Album> getAlbums(final Collection<String> albumIds) {
        final String ids = Util.join(albumIds, ",");
        return mSyncAlbumAPI.getAlbums(ids).getAlbums();
    }

    @Override
    public PagingObject<? extends SimpleTrack> getAlbumTracks(final String albumId) {
        return getAlbumTracks(albumId, OptionalAlbumTracks.getDefault());
    }

    @Override
    public PagingObject<? extends SimpleTrack> getAlbumTracks(final String albumId,
            final OptionalAlbumTracks optional) {
        return mSyncAlbumAPI.getTracks(albumId, optional.getLimit(), optional.getOffset());
    }

    public static class Albums {

        @JsonProperty(value = "albums")
        public List<JacksonAlbum> mAlbums;

        public List<JacksonAlbum> getAlbums() {
            return mAlbums;
        }
    }

    private static interface SyncAlbumAPI {

        @GET("/albums/{id}")
        public JacksonAlbum getAlbum(@Path("id") final String albumId);

        @GET("/albums")
        public Albums getAlbums(@Query("ids") final String albumId);

        @GET("/albums/{id}/tracks")
        public JacksonPagingObject<JacksonSimpleTrack> getTracks(@Path("id") final String albumId,
                @Query("limit") final int limit, @Query("offset") final int offset);
    }
}