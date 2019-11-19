package cloud.liso;


class AppTest
{

//    @Test
//    void getMovie() throws InterruptedException {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Movie.class, new MovieDeserializer());
//
//        HttpClient.create()
//                .get()
//                .uri("https://yts.lt/api/v2/movie_details.json?movie_id=14109")
//                .responseContent()
//                .aggregate()
//                .asString()
//                .map(content -> gsonBuilder.create().fromJson(content, Movie.class))
//                .subscribe(movie -> LoggerFactory.getLogger(this.getClass().getName()).info(movie.toString()));
//
//        Thread.sleep(4000);
//    }
//
//    @Test
//    void getListOfMovies() throws InterruptedException {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Response.class, new ResponseDeserializer());
//
//        HttpClient.create()
//                .get()
//                .uri("https://yts.lt/api/v2/list_movies.json?query_term=die%20hard")
//                .responseContent()
//                .aggregate()
//                .asString()
//                .map(content -> gsonBuilder.create().fromJson(content, Response.class))
//                .subscribe(movie -> LoggerFactory.getLogger(this.getClass().getName()).info(movie.toString()));
//        Thread.sleep(4000);
//    }
}
