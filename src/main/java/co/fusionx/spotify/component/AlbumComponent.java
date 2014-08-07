package co.fusionx.spotify.component;

import java.util.Collection;
import java.util.List;

import co.fusionx.spotify.model.Album;
import co.fusionx.spotify.model.PagingObject;
import co.fusionx.spotify.model.SimpleTrack;
import co.fusionx.spotify.optional.artist.OptionalAlbumTracks;

public interface AlbumComponent {

    public Album getAlbum(final String albumId);

    public List<? extends Album> getAlbums(final Collection<String> albumIds);

    public PagingObject<? extends SimpleTrack> getAlbumTracks(final String albumId);

    public PagingObject<? extends SimpleTrack> getAlbumTracks(String albumId,
            OptionalAlbumTracks tracks);
}