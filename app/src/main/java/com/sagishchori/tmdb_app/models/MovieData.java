package com.sagishchori.tmdb_app.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.sagishchori.tmdb_app.database.Converters;

import java.util.List;

@Entity (tableName = "favorite_movies")
public class MovieData {

	@PrimaryKey (autoGenerate = true)
	public int database_id;

	@ColumnInfo
	private String overview;

	@ColumnInfo
	private String original_language;

	@ColumnInfo
	private String original_title;

	@ColumnInfo
	private boolean video;

	@ColumnInfo
	private String title;

	@TypeConverters({Converters.class})
	@ColumnInfo
	private List<Integer> genre_ids;

	@ColumnInfo
	private String poster_path;

	@ColumnInfo
	private String backdrop_path;

	@ColumnInfo
	private String release_date;

	@ColumnInfo
	private double vote_average;

	@ColumnInfo
	private double popularity;

	@ColumnInfo
	private int id;

	@ColumnInfo
	private boolean adult;

	@ColumnInfo
	private int vote_count;

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginal_language(String originalLanguage){
		this.original_language = originalLanguage;
	}

	public String getOriginal_language(){
		return original_language;
	}

	public void setOriginal_title(String originalTitle){
		this.original_title = originalTitle;
	}

	public String getOriginal_title(){
		return original_title;
	}

	public void setVideo(boolean video){
		this.video = video;
	}

	public boolean isVideo(){
		return video;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setGenre_ids(List<Integer> genreIds){
		this.genre_ids = genreIds;
	}

	public List<Integer> getGenre_ids(){
		return genre_ids;
	}

	public void setPoster_path(String posterPath){
		this.poster_path = posterPath;
	}

	public String getPoster_path(){
		return poster_path;
	}

	public void setBackdrop_path(String backdropPath){
		this.backdrop_path = backdropPath;
	}

	public String getBackdrop_path(){
		return backdrop_path;
	}

	public void setRelease_date(String releaseDate){
		this.release_date = releaseDate;
	}

	public String getRelease_date(){
		return release_date;
	}

	public void setVote_average(double voteAverage){
		this.vote_average = voteAverage;
	}

	public double getVote_average(){
		return vote_average;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setVote_count(int voteCount){
		this.vote_count = voteCount;
	}

	public int getVote_count(){
		return vote_count;
	}

	@Override
 	public String toString(){
		return 
			"MovieData{" + 
			"overview = '" + overview + '\'' + 
			",original_language = '" + original_language + '\'' +
			",original_title = '" + original_title + '\'' +
			",video = '" + video + '\'' + 
			",title = '" + title + '\'' + 
			",genre_ids = '" + genre_ids + '\'' +
			",poster_path = '" + poster_path + '\'' +
			",backdrop_path = '" + backdrop_path + '\'' +
			",release_date = '" + release_date + '\'' +
			",vote_average = '" + vote_average + '\'' +
			",popularity = '" + popularity + '\'' + 
			",id = '" + id + '\'' + 
			",adult = '" + adult + '\'' + 
			",vote_count = '" + vote_count + '\'' +
			"}";
		}
}