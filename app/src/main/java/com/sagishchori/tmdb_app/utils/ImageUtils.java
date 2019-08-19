package com.sagishchori.tmdb_app.utils;

public class ImageUtils {

    public enum LogoImageSize {

        LogoImageSize1("w45"),
        LogoImageSize2("w92"),
        LogoImageSize3("w154"),
        LogoImageSize4("w185"),
        LogoImageSize5("w300"),
        LogoImageSize6("w500"),
        LogoImageSize7("original");

        String imageSizeString;

        LogoImageSize(String imageSizeString) {
            this.imageSizeString = imageSizeString;
        }

        public String getValue() {
            return imageSizeString;
        }
    }

    public enum PosterImageSize {

        PosterImageSize1("w92"),
        PosterImageSize2("w154"),
        PosterImageSize3("w185"),
        PosterImageSize4("w342"),
        PosterImageSize5("w500"),
        PosterImageSize6("w780"),
        PosterImageSize7("original");

        String imageSizeString;

        PosterImageSize(String imageSizeString) {
            this.imageSizeString = imageSizeString;
        }

        public String getValue() {
            return imageSizeString;
        }
    }
}
