package co.fusionx.spotify.component.sync;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

import co.fusionx.spotify.component.ArtistComponent;
import co.fusionx.spotify.model.Artist;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SimpleAlbum;
import co.fusionx.spotify.model.Track;
import co.fusionx.spotify.model.jackson.JacksonArtist;
import co.fusionx.spotify.model.jackson.JacksonPagingObject;
import co.fusionx.spotify.model.jackson.JacksonSimpleAlbum;
import co.fusionx.spotify.optional.artist.OptionalArtistAlbums;
import co.fusionx.spotify.util.Util;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class SyncArtistComponent extends SyncBaseComponent implements ArtistComponent {

    private final SyncArtistAPI mSyncArtistAPI;

    public SyncArtistComponent() {
        mSyncArtistAPI = mRestAdapter.create(SyncArtistAPI.class);
    }

    @Override
    public Artist getArtist(final String artistId) {
        return mSyncArtistAPI.getArtist(artistId);
    }

    @Override
    public List<? extends Artist> getArtists(final Collection<String> artistIds) {
        final String ids = Util.join(artistIds, ",");
        return mSyncArtistAPI.getArtists(ids).getArtists();
    }

    @Override
    public PagingObject<? extends SimpleAlbum> getArtistAlbums(final String artistId) {
        return getArtistAlbums(artistId, OptionalArtistAlbums.getDefault());
    }

    @Override
    public PagingObject<? extends SimpleAlbum> getArtistAlbums(final String artistId,
            final OptionalArtistAlbums optional) {
        return mSyncArtistAPI.getAlbums(artistId, optional.getAlbumTypesString(),
                optional.getCountry(), optional.getLimit(), optional.getOffset());
    }

    @Override
    public List<? extends Track> getArtistTopTracks(final String artistId, final String country) {
        return mSyncArtistAPI.getTopTracks(artistId, country).getTracks();
    }

    @Override
    public List<? extends Artist> getRelatedArtists(final String artistId) {
        return mSyncArtistAPI.getRelatedArtists(artistId).getArtists();
    }

    public static class Artists {

        @JsonProperty(value = "artists")
        public List<JacksonArtist> mArtists;

        public List<JacksonArtist> getArtists() {
            return mArtists;
        }
    }

    private static interface SyncArtistAPI {

        @GET("/artists/{id}")
        public JacksonArtist getArtist(@Path("id") final String artistId);

        @GET("/artists")
        public Artists getArtists(@Query("ids") final String artistIds);

        @GET("/artists/{id}/albums")
        public JacksonPagingObject<JacksonSimpleAlbum> getAlbums(@Path("id") final String artistId,
                @Query("album_type") final String albumType, @Query("country") final String country,
                @Query("limit") final int limit, @Query("offset") final int offset);

        @GET("/artists/{id}/top-tracks")
        public SyncTrackComponent.Tracks getTopTracks(@Path("id") final String artistId,
                @Query("country") final String country);

        @GET("/artists/{id}/related-artists")
        public Artists getRelatedArtists(@Path("id") final String artistId);
    }
}