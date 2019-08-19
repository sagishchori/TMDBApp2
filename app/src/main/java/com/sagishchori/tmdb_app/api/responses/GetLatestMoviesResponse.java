package com.sagishchori.tmdb_app.api.responses;

import com.sagishchori.tmdb_app.api.responses.Dates;
import com.sagishchori.tmdb_app.models.MovieData;

import java.util.ArrayList;

public class GetLatestMoviesResponse{
	private Dates dates;
	private int page;
	private int total_pages;
	private ArrayList<MovieData> results;
	private int total_results;
	private MovieData selectedMovieData;

	public void setDates(Dates dates){
		this.dates = dates;
	}

	public Dates getDates(){
		return dates;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.total_pages = totalPages;
	}

	public int getTotalPages(){
		return total_pages;
	}

	public void setResults(ArrayList<MovieData> results){
		this.results = results;
	}

	public ArrayList<MovieData> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.total_results = totalResults;
	}

	public int getTotalResults(){
		return total_results;
	}

	@Override
 	public String toString(){
		return 
			"GetLatestMoviesResponse{" + 
			"dates = '" + dates + '\'' + 
			",page = '" + page + '\'' + 
			",total_pages = '" + total_pages + '\'' +
			",results = '" + results + '\'' + 
			",total_results = '" + total_results + '\'' +
			"}";
		}

	public void setSelectedMovie(MovieData data) {
		this.selectedMovieData = data;
	}
}