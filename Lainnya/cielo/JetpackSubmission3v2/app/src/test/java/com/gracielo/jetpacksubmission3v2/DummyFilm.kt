package com.gracielo.jetpacksubmission3v2

import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.FilmEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.MovieResponse
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.TVResponse
import java.util.*

object DummyFilm {

    fun generateMovies(): ArrayList<MovieEntity> {
        val MoviesList = ArrayList<MovieEntity>()
        MoviesList.add(
            MovieEntity(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                7.9f,
                "Fantasy, Action, Adventure, Science Fiction, Thriller"
            )
        )
        MoviesList.add(
            MovieEntity(
                399566,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                8.2f,
                "Science Fiction, Action, Drama"
            )
        )
        MoviesList.add(
            MovieEntity(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                FilmEntity.TYPE_MOVIE,
                "2020",
                8.3f,
                "Animation, Action, Adventure, Fantasy, Drama"
            )
        )
        MoviesList.add(
            MovieEntity(
                804435,
                "Vanquish",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                6.2f,
                "Action, Crime , Thriller"
            )
        )
        return MoviesList
    }

    fun generateRemoteMovies(): ArrayList<MovieResponse> {
        val MoviesList = ArrayList<MovieResponse>()
        MoviesList.add(
            MovieResponse(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                7.9f,
                "Fantasy, Action, Adventure, Science Fiction, Thriller"
            )
        )
        MoviesList.add(
            MovieResponse(
                399566,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                8.2f,
                "Science Fiction, Action, Drama"
            )
        )
        MoviesList.add(
            MovieResponse(
                635302,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                FilmEntity.TYPE_MOVIE,
                "2020",
                8.3f,
                "Animation, Action, Adventure, Fantasy, Drama"
            )
        )
        MoviesList.add(
            MovieResponse(
                804435,
                "Vanquish",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                6.2f,
                "Action, Crime , Thriller"
            )
        )
        return MoviesList
    }

    fun generateTV(): ArrayList<TVEntity> {
        val TVList = ArrayList<TVEntity>()
        TVList.add(
            TVEntity(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of \"Avengers: Endgame\", the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                FilmEntity.TYPE_TV_SHOW,
                "2021",
                7.9f,
                "Sci-Fi & Fantasy, Action & Adventure, Drama, War & Politics"
            )
        )
        TVList.add(
            TVEntity(
                71712,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                FilmEntity.TYPE_TV_SHOW,
                "2017",
                8.6f,
                "Drama"
            )
        )
        return TVList
    }

    fun generateRemoteTV(): ArrayList<TVResponse> {
        val TVList = ArrayList<TVResponse>()
        TVList.add(
            TVResponse(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of \"Avengers: Endgame\", the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                FilmEntity.TYPE_TV_SHOW,
                "2021",
                7.9f,
                "Sci-Fi & Fantasy, Action & Adventure, Drama, War & Politics"
            )
        )
        TVList.add(
            TVResponse(
                71712,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                FilmEntity.TYPE_TV_SHOW,
                "2017",
                8.6f,
                "Drama"
            )
        )
        return TVList
    }
}