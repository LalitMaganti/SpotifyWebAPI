package co.fusionx.spotify.webapi.optional.artist;

import java.util.HashSet;
import java.util.Set;

import co.fusionx.spotify.webapi.util.Util;

public class OptionalArtistAlbums {

    private static OptionalArtistAlbums sDefault;

    private final Set<AlbumType> mAlbumTypes;

    private final String mCountry;

    private final int mLimit;

    private final int mOffset;

    private OptionalArtistAlbums(final Set<AlbumType> albumTypes, final String country,
            final int limit, final int offset) {
        mAlbumTypes = albumTypes == null ? new HashSet<AlbumType>() : new HashSet<>(albumTypes);
        mCountry = country;
        mLimit = limit;
        mOffset = offset;
    }

    public static OptionalArtistAlbums getDefault() {
        if (sDefault == null) {
            sDefault = new Builder().build();
        }
        return sDefault;
    }

    public String getAlbumTypesString() {
        final Set<String> strings = new HashSet<>();
        for (final AlbumType type : mAlbumTypes) {
            strings.add(type.getStringRepresentation());
        }
        return Util.join(strings, ",");
    }

    public static enum AlbumType {
        ALBUM("album"),
        SINGLE("single"),
        APPEARS_ON("single"),
        COMPILATION("compilation");

        private final String mStringRepresentation;

        private AlbumType(final String stringRepresentation) {
            mStringRepresentation = stringRepresentation;
        }

        public String getStringRepresentation() {
            return mStringRepresentation;
        }
    }

    public Set<AlbumType> getAlbumTypes() {
        return mAlbumTypes;
    }

    public String getCountry() {
        return mCountry;
    }

    public int getLimit() {
        return mLimit;
    }

    public int getOffset() {
        return mOffset;
    }

    public static class Builder {

        private Set<AlbumType> mAlbumTypes;

        private String mCountry;

        private int mLimit = 20;

        private int mOffset = 0;

        public Set<AlbumType> getAlbumTypes() {
            return mAlbumTypes;
        }

        public String getCountry() {
            return mCountry;
        }

        public int getLimit() {
            return mLimit;
        }

        public int getOffset() {
            return mOffset;
        }

        public Builder setAlbumTypes(Set<AlbumType> albumTypes) {
            mAlbumTypes = albumTypes;
            return this;
        }

        public Builder setCountries(String country) {
            mCountry = country;
            return this;
        }

        public Builder setLimit(int limit) {
            mLimit = limit;
            return this;
        }

        public Builder setOffset(int offset) {
            mOffset = offset;
            return this;
        }

        public OptionalArtistAlbums build() {
            return new OptionalArtistAlbums(mAlbumTypes, mCountry, mLimit, mOffset);
        }
    }
}