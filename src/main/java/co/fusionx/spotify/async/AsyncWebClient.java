package co.fusionx.spotify.async;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import co.fusionx.spotify.model.Album;
import co.fusionx.spotify.model.Artist;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SearchResult;
import co.fusionx.spotify.model.SimpleAlbum;
import co.fusionx.spotify.model.SimpleTrack;
import co.fusionx.spotify.model.Track;
import co.fusionx.spotify.optional.artist.OptionalAlbumTracks;
import co.fusionx.spotify.optional.artist.OptionalArtistAlbums;
import co.fusionx.spotify.optional.search.OptionalSearch;
import co.fusionx.spotify.sync.SyncClient;
import co.fusionx.spotify.sync.SyncWebClient;

public class AsyncWebClient implements AsyncClient {

    private final SyncClient mSyncClient;

    private final ListeningExecutorService mExecutorService;

    public AsyncWebClient() {
        mSyncClient = new SyncWebClient();
        mExecutorService = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    }

    @Override
    public ListenableFuture<Album> getAlbum(final String albumId) {
        return mExecutorService.submit(() -> mSyncClient.getAlbum(albumId));
    }

    @Override
    public ListenableFuture<List<? extends Album>> getAlbums(final Collection<String> albumIds) {
        final Callable<List<? extends Album>> callable = () -> mSyncClient.getAlbums(albumIds);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleTrack>> getAlbumTracks(
            final String albumId) {
        final Callable<PagingObject<? extends SimpleTrack>> callable =
                () -> mSyncClient.getAlbumTracks(albumId);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleTrack>> getAlbumTracks(
            final String albumId, final OptionalAlbumTracks tracks) {
        final Callable<PagingObject<? extends SimpleTrack>> callable =
                () -> mSyncClient.getAlbumTracks(albumId, tracks);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<Artist> getArtist(final String artistId) {
        return mExecutorService.submit(() -> mSyncClient.getArtist(artistId));
    }

    @Override
    public ListenableFuture<List<? extends Artist>> getArtists(final Collection<String> artistIds) {
        final Callable<List<? extends Artist>> callable = () -> mSyncClient.getArtists(artistIds);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleAlbum>> getArtistAlbums(
            final String artistId) {
        final Callable<PagingObject<? extends SimpleAlbum>> callable =
                () -> mSyncClient.getArtistAlbums(artistId);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleAlbum>> getArtistAlbums(
            final String artistId,
            final OptionalArtistAlbums artistAlbums) {
        final Callable<PagingObject<? extends SimpleAlbum>> callable =
                () -> mSyncClient.getArtistAlbums(artistId, artistAlbums);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<List<? extends Track>> getArtistTopTracks(final String artistId,
            final String country) {
        final Callable<List<? extends Track>> callable =
                () -> mSyncClient.getArtistTopTracks(artistId, country);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<List<? extends Artist>> getRelatedArtists(final String artistId) {
        final Callable<List<? extends Artist>> callable =
                () -> mSyncClient.getRelatedArtists(artistId);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<Track> getTrack(final String id) {
        return mExecutorService.submit(() -> mSyncClient.getTrack(id));
    }

    @Override
    public ListenableFuture<List<? extends Track>> getTracks(final Collection<String> ids) {
        final Callable<List<? extends Track>> callable =
                () -> mSyncClient.getTracks(ids);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<PagingObject<? extends Artist>> searchArtist(final String artist) {
        final Callable<PagingObject<? extends Artist>> callable =
                () -> mSyncClient.searchArtist(artist);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleAlbum>> searchAlbum(final String album) {
        final Callable<PagingObject<? extends SimpleAlbum>> callable =
                () -> mSyncClient.searchAlbum(album);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleTrack>> searchTrack(final String track) {
        final Callable<PagingObject<? extends SimpleTrack>> callable =
                () -> mSyncClient.searchTrack(track);
        return mExecutorService.submit(callable);
    }

    @Override
    public ListenableFuture<SearchResult> search(final String query,
            final Collection<OptionalSearch.SearchType> types) {
        return mExecutorService.submit(() -> mSyncClient.search(query, types));
    }

    @Override
    public ListenableFuture<SearchResult> search(final String query,
            final Collection<OptionalSearch.SearchType> types, final OptionalSearch search) {
        return mExecutorService.submit(() -> mSyncClient.search(query, types, search));
    }
}