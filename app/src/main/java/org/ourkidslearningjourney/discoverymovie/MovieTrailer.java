package org.ourkidslearningjourney.discoverymovie;

public class MovieTrailer {
    private String trailerName;
    private String trailerSite;
    private String trailerType;
    private String trailerKey;
    private int trailerSize;

    public MovieTrailer() {

    }

    public MovieTrailer(
            String trailername,
            String trailersite,
            String trailertype,
            String trailerkey,
            int trailersize) {
        this.trailerName = trailername;
        this.trailerSite = trailersite;
        this.trailerType = trailertype;
        this.trailerKey = trailerkey;
        this.trailerSize = trailersize;
    }

    public String getTrailerName() {
        return trailerName;
    }

    public void setTrailerName(String trailerName) {
        this.trailerName = trailerName;
    }

    public String getTrailerSite() {
        return trailerSite;
    }

    public void setTrailerSite(String trailerSite) {
        this.trailerSite = trailerSite;
    }

    public String getTrailerType() {
        return trailerType;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType = trailerType;
    }

    public String getTrailerKey() {
        return trailerKey;
    }

    public void setTrailerKey(String trailerKey) {
        this.trailerKey = trailerKey;
    }

    public int getTrailerSize() {
        return trailerSize;
    }

    public void setTrailerSize(int trailerSize) {
        this.trailerSize = trailerSize;
    }
}
