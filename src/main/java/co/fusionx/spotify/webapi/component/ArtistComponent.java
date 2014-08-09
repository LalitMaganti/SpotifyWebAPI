package co.fusionx.spotify.webapi.component;

import java.util.Collection;
import java.util.List;

import co.fusionx.spotify.model.Artist;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SimpleAlbum;
import co.fusionx.spotify.model.Track;
import co.fusionx.spotify.webapi.optional.artist.OptionalArtistAlbums;

public interface ArtistComponent {

    public Artist getArtist(final String artistId);

    public List<? extends Artist> getArtists(final Collection<String> artistIds);

    public PagingObject<? extends SimpleAlbum> getArtistAlbums(final String artistId);

    public PagingObject<? extends SimpleAlbum> getArtistAlbums(final String artistId,
            final OptionalArtistAlbums artistAlbums);

    public List<? extends Track> getArtistTopTracks(final String artistId, final String country);

    public List<? extends Artist> getRelatedArtists(final String artistId);
}