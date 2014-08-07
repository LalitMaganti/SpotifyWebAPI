package co.fusionx.spotify.component;

import java.util.Collection;
import java.util.List;

import co.fusionx.spotify.model.Track;

public interface TrackComponent {

    public Track getTrack(final String id);

    public List<? extends Track> getTracks(final Collection<String> ids);
}