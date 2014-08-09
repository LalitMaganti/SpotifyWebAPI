package co.fusionx.spotify.async;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.Collection;
import java.util.List;

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

public interface AsyncClient {

    public ListenableFuture<? extends Album> getAlbum(final String albumId);

    public ListenableFuture<List<? extends Album>> getAlbums(final Collection<String> albumIds);

    public ListenableFuture<PagingObject<? extends SimpleTrack>> getAlbumTracks(
            final String albumId);

    public ListenableFuture<PagingObject<? extends SimpleTrack>> getAlbumTracks(String albumId,
            OptionalAlbumTracks tracks);

    public ListenableFuture<? extends Artist> getArtist(final String artistId);

    public ListenableFuture<List<? extends Artist>> getArtists(final Collection<String> artistIds);

    public ListenableFuture<PagingObject<? extends SimpleAlbum>> getArtistAlbums(
            final String artistId);

    public ListenableFuture<PagingObject<? extends SimpleAlbum>> getArtistAlbums(
            final String artistId, final OptionalArtistAlbums artistAlbums);

    public ListenableFuture<List<? extends Track>> getArtistTopTracks(final String artistId,
            final String country);

    public ListenableFuture<List<? extends Artist>> getRelatedArtists(final String artistId);

    public ListenableFuture<Track> getTrack(final String id);

    public ListenableFuture<List<? extends Track>> getTracks(final Collection<String> ids);

    public ListenableFuture<PagingObject<? extends Artist>> searchArtist(String artist);

    public ListenableFuture<PagingObject<? extends SimpleAlbum>> searchAlbum(String album);

    public ListenableFuture<PagingObject<? extends SimpleTrack>> searchTrack(String track);

    public ListenableFuture<SearchResult> search(String query,
            Collection<OptionalSearch.SearchType> types);

    public ListenableFuture<SearchResult> search(String query,
            Collection<OptionalSearch.SearchType> types, OptionalSearch search);
}