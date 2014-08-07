package co.fusionx.spotify.sync;

import java.util.Collection;
import java.util.List;

import co.fusionx.spotify.component.AlbumComponent;
import co.fusionx.spotify.component.ArtistComponent;
import co.fusionx.spotify.component.sync.SyncAlbumComponent;
import co.fusionx.spotify.component.sync.SyncArtistComponent;
import co.fusionx.spotify.component.sync.SyncBaseComponent;
import co.fusionx.spotify.component.sync.SyncSearchComponent;
import co.fusionx.spotify.component.sync.SyncTrackComponent;
import co.fusionx.spotify.component.TrackComponent;
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

public class SyncWebClient implements SyncClient {

    private final SyncBaseComponent mBaseComponent;

    private final AlbumComponent mAlbumComponent;

    private final ArtistComponent mArtistComponent;

    private final TrackComponent mTrackComponent;

    private final SyncSearchComponent mSearchComponent;

    public SyncWebClient() {
        mBaseComponent = new SyncBaseComponent();
        mAlbumComponent = new SyncAlbumComponent();
        mArtistComponent = new SyncArtistComponent();
        mTrackComponent = new SyncTrackComponent();
        mSearchComponent = new SyncSearchComponent();
    }

    @Override
    public <T, U> U previousPage(PagingObject<T> pagingObject, Class<U> result) {
        return mBaseComponent.previousPage(pagingObject, result);
    }

    @Override
    public <T, U> U nextPage(PagingObject<T> pagingObject, Class<U> result) {
        return mBaseComponent.nextPage(pagingObject, result);
    }

    @Override
    public Album getAlbum(String albumId) {
        return mAlbumComponent.getAlbum(albumId);
    }

    @Override
    public List<? extends Album> getAlbums(Collection<String> albumIds) {
        return mAlbumComponent.getAlbums(albumIds);
    }

    @Override
    public PagingObject<? extends SimpleTrack> getAlbumTracks(String albumId) {
        return mAlbumComponent.getAlbumTracks(albumId);
    }

    @Override
    public PagingObject<? extends SimpleTrack> getAlbumTracks(String albumId,
            OptionalAlbumTracks tracks) {
        return mAlbumComponent.getAlbumTracks(albumId, tracks);
    }

    @Override
    public Artist getArtist(String artistId) {
        return mArtistComponent.getArtist(artistId);
    }

    @Override
    public List<? extends Artist> getArtists(Collection<String> artistIds) {
        return mArtistComponent.getArtists(artistIds);
    }

    @Override
    public PagingObject<? extends SimpleAlbum> getArtistAlbums(String artistId) {
        return mArtistComponent.getArtistAlbums(artistId);
    }

    @Override
    public PagingObject<? extends SimpleAlbum> getArtistAlbums(String artistId,
            OptionalArtistAlbums artistAlbums) {
        return mArtistComponent.getArtistAlbums(artistId, artistAlbums);
    }

    @Override
    public List<? extends Track> getArtistTopTracks(String artistId, String country) {
        return mArtistComponent.getArtistTopTracks(artistId, country);
    }

    @Override
    public List<? extends Artist> getRelatedArtists(String artistId) {
        return mArtistComponent.getRelatedArtists(artistId);
    }

    @Override
    public Track getTrack(String id) {
        return mTrackComponent.getTrack(id);
    }

    @Override
    public List<? extends Track> getTracks(Collection<String> ids) {
        return mTrackComponent.getTracks(ids);
    }

    @Override
    public PagingObject<? extends Artist> searchArtist(String artist) {
        return mSearchComponent.searchArtist(artist);
    }

    @Override
    public PagingObject<? extends SimpleAlbum> searchAlbum(String album) {
        return mSearchComponent.searchAlbum(album);
    }

    @Override
    public PagingObject<? extends SimpleTrack> searchTrack(String track) {
        return mSearchComponent.searchTrack(track);
    }

    @Override
    public SearchResult search(String query, Collection<OptionalSearch.SearchType> types) {
        return mSearchComponent.search(query, types);
    }

    @Override
    public SearchResult search(String query, Collection<OptionalSearch.SearchType> types,
            OptionalSearch optionalSearch) {
        return mSearchComponent.search(query, types, optionalSearch);
    }
}