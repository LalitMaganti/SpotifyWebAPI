package co.fusionx.spotify.webapi.client;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;

import co.fusionx.spotify.model.Album;
import co.fusionx.spotify.model.Artist;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SearchResult;
import co.fusionx.spotify.model.SimpleAlbum;
import co.fusionx.spotify.model.SimpleTrack;
import co.fusionx.spotify.model.Track;
import co.fusionx.spotify.webapi.component.AlbumComponent;
import co.fusionx.spotify.webapi.component.ArtistComponent;
import co.fusionx.spotify.webapi.component.BaseComponent;
import co.fusionx.spotify.webapi.component.SearchComponent;
import co.fusionx.spotify.webapi.component.TrackComponent;
import co.fusionx.spotify.webapi.component.retrofit.RetrofitAlbumComponent;
import co.fusionx.spotify.webapi.component.retrofit.RetrofitArtistComponent;
import co.fusionx.spotify.webapi.component.retrofit.RetrofitBaseComponent;
import co.fusionx.spotify.webapi.component.retrofit.RetrofitSearchComponent;
import co.fusionx.spotify.webapi.component.retrofit.RetrofitTrackComponent;
import co.fusionx.spotify.webapi.optional.artist.OptionalAlbumTracks;
import co.fusionx.spotify.webapi.optional.artist.OptionalArtistAlbums;
import co.fusionx.spotify.webapi.optional.search.OptionalSearch;
import co.fusionx.spotify.webapi.request.GuavaRequest;

public class ConfigurableGuavaClient implements GuavaClient {

    private final BaseComponent mBaseComponent;

    private final AlbumComponent mAlbumComponent;

    private final ArtistComponent mArtistComponent;

    private final TrackComponent mTrackComponent;

    private final SearchComponent mSearchComponent;

    private final ListeningExecutorService mService;

    public ConfigurableGuavaClient() {
        mBaseComponent = new RetrofitBaseComponent();
        mAlbumComponent = new RetrofitAlbumComponent();
        mArtistComponent = new RetrofitArtistComponent();
        mTrackComponent = new RetrofitTrackComponent();
        mSearchComponent = new RetrofitSearchComponent();

        mService = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    }

    @Override
    public <T, U> GuavaRequest<U> previousPage(PagingObject<T> pagingObject, Class<U> result) {
        return new GuavaRequest<>(() -> mBaseComponent.previousPage(pagingObject, result),
                mService);
    }

    @Override
    public <T, U> GuavaRequest<U> nextPage(PagingObject<T> pagingObject, Class<U> result) {
        return new GuavaRequest<>(() -> mBaseComponent.nextPage(pagingObject, result), mService);
    }

    @Override
    public GuavaRequest<Album> getAlbum(String albumId) {
        return new GuavaRequest<>(() -> mAlbumComponent.getAlbum(albumId), mService);
    }

    @Override
    public GuavaRequest<List<? extends Album>> getAlbums(Collection<String> albumIds) {
        return new GuavaRequest<>(() -> mAlbumComponent.getAlbums(albumIds), mService);
    }

    @Override
    public GuavaRequest<PagingObject<? extends SimpleTrack>> getAlbumTracks(String albumId) {
        return new GuavaRequest<>(() -> mAlbumComponent.getAlbumTracks(albumId), mService);
    }

    @Override
    public GuavaRequest<PagingObject<? extends SimpleTrack>> getAlbumTracks(String albumId,
            OptionalAlbumTracks tracks) {
        return new GuavaRequest<>(() -> mAlbumComponent.getAlbumTracks(albumId, tracks), mService);
    }

    @Override
    public GuavaRequest<Artist> getArtist(String artistId) {
        return new GuavaRequest<>(() -> mArtistComponent.getArtist(artistId), mService);
    }

    @Override
    public GuavaRequest<List<? extends Artist>> getArtists(Collection<String> artistIds) {
        return new GuavaRequest<>(() -> mArtistComponent.getArtists(artistIds), mService);
    }

    @Override
    public GuavaRequest<PagingObject<? extends SimpleAlbum>> getArtistAlbums(String artistId) {
        return new GuavaRequest<>(() -> mArtistComponent.getArtistAlbums(artistId), mService);
    }

    @Override
    public GuavaRequest<PagingObject<? extends SimpleAlbum>> getArtistAlbums(String artistId,
            OptionalArtistAlbums artistAlbums) {
        return new GuavaRequest<>(() -> mArtistComponent.getArtistAlbums(artistId, artistAlbums),
                mService);
    }

    @Override
    public GuavaRequest<List<? extends Track>> getArtistTopTracks(String artistId, String country) {
        return new GuavaRequest<>(() -> mArtistComponent.getArtistTopTracks(artistId, country),
                mService);
    }

    @Override
    public GuavaRequest<List<? extends Artist>> getRelatedArtists(String artistId) {
        return new GuavaRequest<>(() -> mArtistComponent.getRelatedArtists(artistId), mService);
    }

    @Override
    public GuavaRequest<Track> getTrack(String id) {
        return new GuavaRequest<>(() -> mTrackComponent.getTrack(id), mService);
    }

    @Override
    public GuavaRequest<List<? extends Track>> getTracks(Collection<String> ids) {
        return new GuavaRequest<>(() -> mTrackComponent.getTracks(ids), mService);
    }

    @Override
    public GuavaRequest<PagingObject<? extends Artist>> searchArtist(String artist) {
        return new GuavaRequest<>(() -> mSearchComponent.searchArtist(artist), mService);
    }

    @Override
    public GuavaRequest<PagingObject<? extends SimpleAlbum>> searchAlbum(String album) {
        return new GuavaRequest<>(() -> mSearchComponent.searchAlbum(album), mService);
    }

    @Override
    public GuavaRequest<PagingObject<? extends SimpleTrack>> searchTrack(String track) {
        return new GuavaRequest<>(() -> mSearchComponent.searchTrack(track), mService);
    }

    @Override
    public GuavaRequest<SearchResult> search(String query, Collection<OptionalSearch.SearchType>
            types) {
        return new GuavaRequest<>(() -> mSearchComponent.search(query, types), mService);
    }

    @Override
    public GuavaRequest<SearchResult> search(String query, Collection<OptionalSearch.SearchType>
            types, OptionalSearch optionalSearch) {
        return new GuavaRequest<>(() -> mSearchComponent.search(query, types, optionalSearch),
                mService);
    }
}