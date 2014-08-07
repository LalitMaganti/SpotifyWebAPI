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
        return mExecutorService.submit(new Callable<Album>() {
            @Override
            public Album call() throws Exception {
                return mSyncClient.getAlbum(albumId);
            }
        });
    }

    @Override
    public ListenableFuture<List<? extends Album>> getAlbums(final Collection<String> albumIds) {
        return mExecutorService.submit(new Callable<List<? extends Album>>() {
            @Override
            public List<? extends Album> call() throws Exception {
                return mSyncClient.getAlbums(albumIds);
            }
        });
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleTrack>> getAlbumTracks(
            final String albumId) {
        return mExecutorService.submit(new Callable<PagingObject<? extends SimpleTrack>>() {
            @Override
            public PagingObject<? extends SimpleTrack> call() throws Exception {
                return mSyncClient.getAlbumTracks(albumId);
            }
        });
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleTrack>> getAlbumTracks(
            final String albumId,
            final OptionalAlbumTracks tracks) {
        return mExecutorService.submit(new Callable<PagingObject<? extends SimpleTrack>>() {
            @Override
            public PagingObject<? extends SimpleTrack> call() throws Exception {
                return mSyncClient.getAlbumTracks(albumId, tracks);
            }
        });
    }

    @Override
    public ListenableFuture<Artist> getArtist(final String artistId) {
        return mExecutorService.submit(new Callable<Artist>() {
            @Override
            public Artist call() throws Exception {
                return mSyncClient.getArtist(artistId);
            }
        });
    }

    @Override
    public ListenableFuture<List<? extends Artist>> getArtists(final Collection<String> artistIds) {
        return mExecutorService.submit(new Callable<List<? extends Artist>>() {
            @Override
            public List<? extends Artist> call() throws Exception {
                return mSyncClient.getArtists(artistIds);
            }
        });
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleAlbum>> getArtistAlbums(
            final String artistId) {
        return mExecutorService.submit(new Callable<PagingObject<? extends SimpleAlbum>>() {
            @Override
            public PagingObject<? extends SimpleAlbum> call() throws Exception {
                return mSyncClient.getArtistAlbums(artistId);
            }
        });
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleAlbum>> getArtistAlbums(
            final String artistId,
            final OptionalArtistAlbums artistAlbums) {
        return mExecutorService.submit(new Callable<PagingObject<? extends SimpleAlbum>>() {
            @Override
            public PagingObject<? extends SimpleAlbum> call() throws Exception {
                return mSyncClient.getArtistAlbums(artistId, artistAlbums);
            }
        });
    }

    @Override
    public ListenableFuture<List<? extends Track>> getArtistTopTracks(final String artistId,
            final String country) {
        return mExecutorService.submit(new Callable<List<? extends Track>>() {
            @Override
            public List<? extends Track> call() throws Exception {
                return mSyncClient.getArtistTopTracks(artistId, country);
            }
        });
    }

    @Override
    public ListenableFuture<List<? extends Artist>> getRelatedArtists(final String artistId) {
        return mExecutorService.submit(new Callable<List<? extends Artist>>() {
            @Override
            public List<? extends Artist> call() throws Exception {
                return mSyncClient.getRelatedArtists(artistId);
            }
        });
    }

    @Override
    public ListenableFuture<Track> getTrack(final String id) {
        return mExecutorService.submit(new Callable<Track>() {
            @Override
            public Track call() throws Exception {
                return mSyncClient.getTrack(id);
            }
        });
    }

    @Override
    public ListenableFuture<List<? extends Track>> getTracks(final Collection<String> ids) {
        return mExecutorService.submit(new Callable<List<? extends Track>>() {
            @Override
            public List<? extends Track> call() throws Exception {
                return mSyncClient.getTracks(ids);
            }
        });
    }

    @Override
    public ListenableFuture<PagingObject<? extends Artist>> searchArtist(final String artist) {
        return mExecutorService.submit(new Callable<PagingObject<? extends Artist>>() {
            @Override
            public PagingObject<? extends Artist> call() throws Exception {
                return mSyncClient.searchArtist(artist);
            }
        });
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleAlbum>> searchAlbum(final String album) {
        return mExecutorService.submit(new Callable<PagingObject<? extends SimpleAlbum>>() {
            @Override
            public PagingObject<? extends SimpleAlbum> call() throws Exception {
                return mSyncClient.searchAlbum(album);
            }
        });
    }

    @Override
    public ListenableFuture<PagingObject<? extends SimpleTrack>> searchTrack(final String track) {
        return mExecutorService.submit(new Callable<PagingObject<? extends SimpleTrack>>() {
            @Override
            public PagingObject<? extends SimpleTrack> call() throws Exception {
                return mSyncClient.searchTrack(track);
            }
        });
    }

    @Override
    public ListenableFuture<SearchResult> search(final String query,
            final Collection<OptionalSearch.SearchType> types) {
        return mExecutorService.submit(new Callable<SearchResult>() {
            @Override
            public SearchResult call() throws Exception {
                return mSyncClient.search(query, types);
            }
        });
    }

    @Override
    public ListenableFuture<SearchResult> search(final String query,
            final Collection<OptionalSearch.SearchType> types, final OptionalSearch search) {
        return mExecutorService.submit(new Callable<SearchResult>() {
            @Override
            public SearchResult call() throws Exception {
                return mSyncClient.search(query, types, search);
            }
        });
    }
}