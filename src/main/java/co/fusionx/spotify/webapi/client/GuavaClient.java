package co.fusionx.spotify.webapi.client;

import java.util.Collection;
import java.util.List;

import co.fusionx.spotify.model.Album;
import co.fusionx.spotify.model.Artist;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SearchResult;
import co.fusionx.spotify.model.SimpleAlbum;
import co.fusionx.spotify.model.SimpleTrack;
import co.fusionx.spotify.model.Track;
import co.fusionx.spotify.webapi.optional.artist.OptionalAlbumTracks;
import co.fusionx.spotify.webapi.optional.artist.OptionalArtistAlbums;
import co.fusionx.spotify.webapi.optional.search.OptionalSearch;
import co.fusionx.spotify.webapi.request.GuavaRequest;

public interface GuavaClient {

    // Base component
    public <T, U> GuavaRequest<U> previousPage(PagingObject<T> pagingObject, Class<U> result);

    public <T, U> GuavaRequest<U> nextPage(PagingObject<T> pagingObject, Class<U> result);

    // Album component
    public GuavaRequest<Album> getAlbum(String albumId);

    public GuavaRequest<List<? extends Album>> getAlbums(Collection<String> albumIds);

    public GuavaRequest<PagingObject<? extends SimpleTrack>> getAlbumTracks(String albumId);

    public GuavaRequest<PagingObject<? extends SimpleTrack>> getAlbumTracks(String albumId,
            OptionalAlbumTracks tracks);

    // Artist component
    public GuavaRequest<Artist> getArtist(String artistId);

    public GuavaRequest<List<? extends Artist>> getArtists(Collection<String> artistIds);

    public GuavaRequest<PagingObject<? extends SimpleAlbum>> getArtistAlbums(String artistId);

    public GuavaRequest<PagingObject<? extends SimpleAlbum>> getArtistAlbums(String artistId,
            OptionalArtistAlbums artistAlbums);

    public GuavaRequest<List<? extends Track>> getArtistTopTracks(String artistId, String country);

    public GuavaRequest<List<? extends Artist>> getRelatedArtists(String artistId);

    // Track component
    public GuavaRequest<Track> getTrack(String id);

    public GuavaRequest<List<? extends Track>> getTracks(Collection<String> ids);

    // Search component
    public GuavaRequest<PagingObject<? extends Artist>> searchArtist(String artist);

    public GuavaRequest<PagingObject<? extends SimpleAlbum>> searchAlbum(String album);

    public GuavaRequest<PagingObject<? extends SimpleTrack>> searchTrack(String track);

    public GuavaRequest<SearchResult> search(String query, Collection<OptionalSearch.SearchType>
            types);

    public GuavaRequest<SearchResult> search(String query, Collection<OptionalSearch.SearchType>
            types, OptionalSearch optionalSearch);
}