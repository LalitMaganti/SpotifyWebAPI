package co.fusionx.spotify.optional.artist;

public class OptionalAlbumTracks {

    private final int mLimit;

    private final int mOffset;

    private static OptionalAlbumTracks sDefault;

    private OptionalAlbumTracks(final int limit, final int offset) {
        mLimit = limit > 50 ? 50 : (limit < 0 ? 0 : limit);
        mOffset = offset;
    }

    public static OptionalAlbumTracks getDefault() {
        if (sDefault == null) {
            sDefault = new Builder().build();
        }
        return sDefault;
    }

    public int getLimit() {
        return mLimit;
    }

    public int getOffset() {
        return mOffset;
    }

    public static class Builder {

        // Default specified by Spotify
        private int mLimit = 20;

        // Default specified by Spotify
        private int mOffset = 0;

        public Builder setLimit(int limit) {
            mLimit = limit;
            return this;
        }

        public Builder setOffset(int offset) {
            mOffset = offset;
            return this;
        }

        public OptionalAlbumTracks build() {
            return new OptionalAlbumTracks(mLimit, mOffset);
        }
    }
}