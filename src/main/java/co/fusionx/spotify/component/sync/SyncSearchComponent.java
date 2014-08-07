package co.fusionx.spotify.component.sync;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import co.fusionx.spotify.component.SearchComponent;
import co.fusionx.spotify.model.Artist;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SearchResult;
import co.fusionx.spotify.model.SimpleAlbum;
import co.fusionx.spotify.model.SimpleTrack;
import co.fusionx.spotify.model.jackson.JacksonSearchResult;
import co.fusionx.spotify.optional.search.OptionalSearch;
import co.fusionx.spotify.util.Util;
import retrofit.http.GET;
import retrofit.http.Query;

import static java.util.Collections.singletonList;

public class SyncSearchComponent extends SyncBaseComponent implements SearchComponent {

    private final SyncSearchAPI mSyncSearchAPI;

    public SyncSearchComponent() {
        mSyncSearchAPI = mRestAdapter.create(SyncSearchAPI.class);
    }

    @Override
    public PagingObject<? extends Artist> searchArtist(final String artist) {
        return search(artist, singletonList(OptionalSearch.SearchType.ARTIST)).getArtists();
    }

    @Override
    public PagingObject<? extends SimpleAlbum> searchAlbum(final String album) {
        return search(album, singletonList(OptionalSearch.SearchType.ALBUM)).getAlbums();
    }

    @Override
    public PagingObject<? extends SimpleTrack> searchTrack(final String track) {
        return search(track, singletonList(OptionalSearch.SearchType.TRACK)).getTracks();
    }

    @Override
    public SearchResult search(final String query,
            final Collection<OptionalSearch.SearchType> types) {
        return search(query, types, OptionalSearch.getDefault());
    }

    @Override
    public SearchResult search(final String query,
            final Collection<OptionalSearch.SearchType> types, final OptionalSearch search) {
        final Set<String> strings = new HashSet<>();
        for (final OptionalSearch.SearchType type : types) {
            strings.add(type.getSearchType());
        }
        return mSyncSearchAPI.search(query, Util.join(strings, ","), search.getLimit(),
                search.getOffset());
    }

    private static interface SyncSearchAPI {

        @GET("/search")
        public JacksonSearchResult search(@Query("q") final String query,
                @Query("type") final String type, @Query("limit") final int limit,
                @Query("offset") final int offset);
    }
}