package co.fusionx.spotify.webapi.component;

import java.util.Collection;

import co.fusionx.spotify.model.Artist;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SearchResult;
import co.fusionx.spotify.model.SimpleAlbum;
import co.fusionx.spotify.model.SimpleTrack;
import co.fusionx.spotify.webapi.optional.search.OptionalSearch;

public interface SearchComponent {

    public PagingObject<? extends Artist> searchArtist(String artist);

    public PagingObject<? extends SimpleAlbum> searchAlbum(String album);

    public PagingObject<? extends SimpleTrack> searchTrack(String track);

    public SearchResult search(String query,
            Collection<OptionalSearch.SearchType> types);

    public SearchResult search(String query,
            Collection<OptionalSearch.SearchType> types, OptionalSearch search);
}
